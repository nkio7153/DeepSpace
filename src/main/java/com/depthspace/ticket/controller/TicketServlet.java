package com.depthspace.ticket.controller;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alibaba.fastjson.util.IOUtils;
import com.depthspace.account.model.account.AccountVO;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.column.model.ColumnImagesVO;
import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.ticket.model.TicketTypesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticket.service.TicketService;
import com.depthspace.ticket.service.TicketImagesService;
import com.depthspace.ticket.service.TicketImagesServiceImpl;
import com.depthspace.ticket.service.TicketServiceImpl;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.utils.HibernateUtil;
import com.depthspace.ticket.dao.TicketImagesDAO;
import com.depthspace.ticket.dao.TicketImagesDAOImpl;

@WebServlet("/ticketmg/*")
@MultipartConfig
public class TicketServlet extends HttpServlet {

	private TicketService ticketService;
	private TicketImagesService ticketImagesService;
	Session session;

	@Override
	public void init() throws ServletException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		ticketService = new TicketServiceImpl();
		ticketImagesService = new TicketImagesServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String pathInfo = req.getPathInfo();
		if (pathInfo == null) {
			pathInfo = "";
		}

		switch (pathInfo) {
		case "/": // 票券管理頁面
			res.sendRedirect(req.getContextPath() + "/backend/ticket/mg.jsp");
			break;
		case "/list": // 票券總列表
			doList(req, res);
			break;
		case "/add": // 票券新增
			doAdd(req, res);
			break;
		case "/edit": // 票券修改
			doEdit(req, res);
			break;
		case "/find": // 票券查找
			doSearch(req, res);
			break;
		case "/view":
			view(req, res);
			break;

		default:
			System.out.println("Path not handled: " + pathInfo);
		}
	}

	/************ 票券列表 ************/
	private void doList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 取得所有票券內容 未分頁取這兩行即可(處理篩選故要留)
		List<TicketVO> ticketListAll = ticketService.getAllTickets();
		req.setAttribute("ticketListAll", ticketListAll);

		// 取得所有票券內容(VO) "分頁"
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<TicketVO> ticketList = ticketService.getAllTickets2(currentPage);

		int ticketPageQty = ticketService.getPageTotal();
		req.getSession().setAttribute("ticketPageQty", ticketPageQty);

		req.setAttribute("ticketList", ticketList);
		req.setAttribute("currentPage", currentPage);

		// 處理票券類型不重複
		Set<TicketTypesVO> uniqueTicketTypes = new HashSet<>();
		for (TicketVO ticket : ticketListAll) {
			uniqueTicketTypes.add(ticket.getTicketType());
		}
		req.setAttribute("uniqueTicketTypes", new ArrayList<>(uniqueTicketTypes));
		// 處理票券區域不重複
		Set<CityVO> uniqueTicketArea = new HashSet<>();
		for (TicketVO ticket : ticketListAll) {
			uniqueTicketArea.add(ticket.getCity());
		}
		req.setAttribute("uniqueTicketArea", new ArrayList<>(uniqueTicketArea));

		RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/ticket/list.jsp");
		dispatcher.forward(req, res);
	}

	/************ 票券新增 ************/
	protected void doAdd(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (!req.getMethod().equalsIgnoreCase("POST")) {
			// 判斷如果不是POST，就是到新增頁面

			// 新增頁面中要放入選單項目，要取其值，set屬性到頁面中
			List<TicketTypesVO> ticketTypes = ticketService.getAllTicketTypes();
			List<CityVO> cities = ticketService.getAllCities();
			req.setAttribute("ticketTypes", ticketTypes);
			req.setAttribute("cities", cities);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/ticket/add.jsp");
			dispatcher.forward(req, res);
			return;
		} else {
			// 完成表單填寫，按下送出觸發POST，就將下列的資料送出
			TicketVO ticket = new TicketVO();
			ticket.setTicketName(req.getParameter("ticketName"));
			ticket.setPrice(Integer.valueOf(req.getParameter("price")));
			ticket.setStock(Integer.valueOf(req.getParameter("stock")));
			ticket.setValidDays(Integer.valueOf(req.getParameter("validDays")));
			ticket.setDescription(req.getParameter("description"));
			ticket.setAddress(req.getParameter("address"));
			ticket.setLongitude(Double.valueOf(req.getParameter("longitude")));
			ticket.setLatitude(Double.valueOf(req.getParameter("latitude")));
			ticket.setStatus(Byte.valueOf(req.getParameter("status")));
			// 關聯的 縣市欄位
			Integer cityId = Integer.valueOf(req.getParameter("cityId")); // parameter 對應前端name
			CityVO city = new CityVO();
			city.setCityId(cityId);
			ticket.setCity(city);
			// 關聯的 類型欄位
			Integer ticketTypeId = Integer.valueOf(req.getParameter("ticketTypeId"));
			TicketTypesVO ticketType = new TicketTypesVO();
			ticketType.setTicketTypeId(ticketTypeId);
			ticket.setTicketType(ticketType);

			ticketService.addTicket(ticket); // 必須先將上述資料存入

			// 存入多張圖片
			Collection<Part> fileParts = req.getParts(); // 多份圖
			boolean isFirstImage = true; // 標記是否為第一張圖
			List<TicketImagesVO> ticketImagesList = new ArrayList<>(); // 創建一個列表來收集所有的TicketImagesVO

			for (Part filePart : fileParts) {
				if (filePart.getName().equals("ticketImages[]") && filePart.getSize() > 0) {
					TicketImagesVO ticketImage = new TicketImagesVO();
					ticketImage.setTicket(ticket);

					// 判斷第一張圖則為主圖→byte=1
					if (isFirstImage) {
						ticketImage.setIsMainImage((byte) 1);
						isFirstImage = false;
					} else {
						ticketImage.setIsMainImage((byte) 0);
					}

					// 讀取圖片並存入
					InputStream inputStream = filePart.getInputStream();
					byte[] imageBytes = readInputStream(inputStream);
					ticketImage.setImage(imageBytes);
					ticketImagesList.add(ticketImage);
					// 儲存所有圖片
					ticketImagesService.saveAll(ticketImagesList);
				}
			}
		}
		int ticketPageQty = ticketService.getPageTotal();
		req.getSession().setAttribute("ticketPageQty", ticketPageQty);

		res.sendRedirect(req.getContextPath() + "/ticketmg/list");
	}

	/************ 票券查看 ************/
	protected void view(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer ticketId = Integer.valueOf(req.getParameter("ticketId"));
		TicketVO ticket = ticketService.getTicketById(ticketId);

		JSONObject jsonTicket = new JSONObject();
		if (ticket != null) {
			jsonTicket.put("ticketId", ticket.getTicketId());
			jsonTicket.put("ticketName", ticket.getTicketName());
			jsonTicket.put("price", ticket.getPrice());
			jsonTicket.put("description", ticket.getDescription());
			jsonTicket.put("stock", ticket.getStock());
			jsonTicket.put("address", ticket.getAddress());
			jsonTicket.put("latitude", ticket.getLatitude());
			jsonTicket.put("longitude", ticket.getLongitude());
			jsonTicket.put("validDays", ticket.getValidDays());
			jsonTicket.put("status", ticket.getStatus());

			if (ticket.getTicketType() != null) {
				jsonTicket.put("ticketType", ticket.getTicketType().getTypeName());
			}
			if (ticket.getCity() != null) {
				jsonTicket.put("cityName", ticket.getCity().getCityName());
			}
		}

		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");

		res.getWriter().write(jsonTicket.toString());
	}

	/************ 票券修改 ************/
	protected void doEdit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer ticketId = Integer.valueOf(req.getParameter("ticketId"));
		TicketVO ticket = ticketService.getTicketById(ticketId);

		if (!req.getMethod().equalsIgnoreCase("POST")) {
			// 若不是POST送出請求，就到編輯頁面 (將該id票券的值塞入)
			List<TicketTypesVO> ticketTypes = ticketService.getAllTicketTypes();
			List<CityVO> cities = ticketService.getAllCities();

			req.setAttribute("ticketTypes", ticketTypes);
			req.setAttribute("cities", cities);
			req.setAttribute("ticket", ticket);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/ticket/edit.jsp");
			dispatcher.forward(req, res);

		} else {
			// 送出更新後的資料
			ticket.setTicketName(req.getParameter("ticketName"));
			ticket.setPrice(Integer.valueOf(req.getParameter("price")));
			ticket.setStock(Integer.valueOf(req.getParameter("stock")));
			ticket.setValidDays(Integer.valueOf(req.getParameter("validDays")));
			ticket.setDescription(req.getParameter("description"));
			ticket.setAddress(req.getParameter("address"));
			ticket.setLongitude(Double.valueOf(req.getParameter("longitude")));
			ticket.setLatitude(Double.valueOf(req.getParameter("latitude")));
			ticket.setStatus(Byte.valueOf(req.getParameter("status")));

			// 關聯 區域欄位
			Integer cityId = Integer.valueOf(req.getParameter("cityId"));
			CityVO city = new CityVO();
			city.setCityId(cityId);
			ticket.setCity(city);

			// 關聯 類型欄位
			Integer ticketTypeId = Integer.valueOf(req.getParameter("ticketTypeId"));
			TicketTypesVO ticketType = new TicketTypesVO();
			ticketType.setTicketTypeId(ticketTypeId);
			ticket.setTicketType(ticketType);

			// 圖片
			Collection<Part> imageParts = req.getParts().stream() //取得圖片名稱(既有或新增)存入list
					.filter(part -> part.getName().startsWith("image_") || part.getName().startsWith("ticketImage_new"))
					.collect(Collectors.toList());

			for (Part imagePart : imageParts) {
				String partName = imagePart.getName(); //取得list中圖片名稱
				InputStream inputStream = imagePart.getInputStream(); 
				byte[] imageBytes = readInputStream(inputStream);

				if (partName.startsWith("ticketImage_new")) { //若是新增則存入
					TicketImagesVO newImage = new TicketImagesVO();
					newImage.setTicket(ticket);
					newImage.setIsMainImage((byte) 0);
					newImage.setImage(imageBytes);
					ticketImagesService.save(newImage);
				} else if (partName.startsWith("image_")) { //若是既有則存入既有或更新
					Integer serialId = extractSerialId(partName);
					TicketImagesVO existingImage = ticketImagesService.getImageById(serialId);
					if (existingImage != null && imageBytes.length > 0) {
						existingImage.setImage(imageBytes);
						ticketImagesService.update(existingImage);
					}
				}
			}

			ticketService.updateTicket(ticket);
			res.sendRedirect(req.getContextPath() + "/ticketmg/list");
		}
	}

	protected Integer extractSerialId(String partName) {
		try {
			if (partName != null && partName.startsWith("image_")) {
				return Integer.parseInt(partName.substring("image_".length()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/************ 票券搜尋 ************/
	protected void doSearch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer ticketTypeId = null;
		Integer ticketId = null;
		Integer cityId = null;
		Map<String, String[]> map = req.getParameterMap();

		// 查詢票券類型
		try {
			ticketTypeId = Integer.valueOf(req.getParameter("ticketTypeId"));
		} catch (NumberFormatException e) {
			ticketTypeId = null;
		}
		// 查詢票券名稱
		try {
			ticketId = Integer.valueOf(req.getParameter("ticketId"));
		} catch (NumberFormatException e) {
			ticketId = null;
		}
		// 查詢票券區域
		try {
			cityId = Integer.valueOf(req.getParameter("areaId"));
		} catch (NumberFormatException e) {
			cityId = null;
		}
		// 儲存list
		List<TicketVO> list = new ArrayList<>();

		// 票券類型不為空就加入列表
		if (ticketTypeId != null) {
			List<TicketVO> ticketTypeList = ticketService.getAllTicketTypeIds(ticketTypeId);
			list.addAll(ticketTypeList);
		}
		// 票券ID不為空就加入列表
		if (ticketId != null) {
			List<TicketVO> ticketIdList = ticketService.getTicketById2(ticketId);
			list.addAll(ticketIdList);
		}
		// 票券區域不為空就加入列表
		if (cityId != null) {
			List<TicketVO> areaList = ticketService.getAllTicketAreaId(cityId);
			list.addAll(areaList);
		}
		// 票券名稱的模糊查詢參數
		String[] ticketNameQueries = map.get("ticketName");
		if (ticketNameQueries != null && ticketNameQueries.length > 0 && !ticketNameQueries[0].isEmpty()) {
			List<TicketVO> ticketNameList = ticketService.getTicketsByCompositeQuery(map);
			list.addAll(ticketNameList);
		}
		
		
		System.out.println(list);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/backend/ticket/find.jsp").forward(req, res);
	}


	/************ 圖片讀入DB ************/

	public byte[] readInputStream(InputStream inputStream) throws IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[1024];
		while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}
		buffer.flush();
		return buffer.toByteArray();
	}
}
