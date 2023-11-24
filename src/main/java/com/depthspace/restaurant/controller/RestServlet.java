package com.depthspace.restaurant.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.admin.service.HbAdminService;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.attractions.service.AreaService;
import com.depthspace.attractions.service.CityService;
import com.depthspace.restaurant.model.membooking.MemBookingVO;
import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.restaurant.model.restbookingdate.RestBookingDateVO;
import com.depthspace.restaurant.service.MemBookingService;
import com.depthspace.restaurant.service.MemBookingServiceImpl;
import com.depthspace.restaurant.service.RestBookingDateService;
import com.depthspace.restaurant.service.RestBookingDateServiceImpl;
import com.depthspace.restaurant.service.RestService;
import com.depthspace.restaurant.service.RestServiecImpl;

@WebServlet("/backend/Rest.do")
@MultipartConfig()
public class RestServlet extends HttpServlet {
	
	private RestService restService;
	private MemBookingService memBookingService;
	private RestBookingDateService bookingDateService;
	private HbAdminService hbAdminService;

	@Override
	public void init() throws ServletException {
		restService = new RestServiecImpl();
		memBookingService = new MemBookingServiceImpl();
		bookingDateService = new RestBookingDateServiceImpl();
		hbAdminService = new HbAdminService();
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 不緩存設定
		resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		resp.setHeader("Pragma", "no-cache");
		resp.setDateHeader("Expires", 0);

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
			case "getAll":
				forwardPath = getAllRests(req, resp);
				break;
			case "add":
				forwardPath = add(req, resp);
				break;
			case "forAdd":
				forwardPath = forAdd(req, resp);
				break;
			case "delete":
				forwardPath = delete(req, resp);
				break;
			case "getId_for_update":
				forwardPath = forUpdate(req, resp);
				break;
			case "update":
				forwardPath = update(req, resp);
				break;
			case "getMembooking":
				forwardPath = getMembooking(req, resp);
				break;
			case "getBookingDate":
				forwardPath = getBookingDate(req, resp);
				break;
			case "checkBookingUpdate":
				forwardPath = checkBookingUpdate(req, resp);
				break;
			default:
				forwardPath = getAllRests(req, resp);
				break;
				
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, resp);
	}
	
	private String getAllRests(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RestVO> restList = restService.getAllRest();
		req.setAttribute("restList", restList);
		return "/backend/rest/listRest.jsp";
	}
	
	private String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<AdminVO> adminlist = hbAdminService.getAll();
		req.setAttribute("adminlist", adminlist);
		
		CityService cityService = new CityService();
		List<CityVO> citylist = cityService.getAll();
		req.setAttribute("citylist", citylist);
		
		AreaService areaService = new AreaService();
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		String restName = req.getParameter("restName");
		if (restName.isEmpty() || restName == null || restName.length() == 0) {
			errorMsgs.put("restName", "餐廳名稱請勿空白");
		}
		String restTel = req.getParameter("restTel");
		if (restTel.isEmpty() || restTel == null || restTel.trim().length() == 0) {
			errorMsgs.put("restTel", "餐廳電話請勿空白");
		}
		
		String city = cityService.findByPrimaryKey(Integer.parseInt(req.getParameter("city"))).getCityName();
		String area = areaService.findByPrimaryKey(Integer.parseInt(req.getParameter("area"))).getAreaName();
		String restAddress = req.getParameter("restAddress");
		if (restAddress.isEmpty() || restAddress == null || restAddress.trim().length() == 0) {
			errorMsgs.put("restAddress", "餐廳地址請勿空白");
		}
		String restType = req.getParameter("restType");
		if (restType.isEmpty() || restType == null || restType.trim().length() == 0) {
			errorMsgs.put("restType", "餐廳類型請勿空白");
		}
		String restOpen = req.getParameter("restOpen");
		if (restOpen.isEmpty() || restOpen == null || restOpen.trim().length() == 0) {
			errorMsgs.put("restOpen", "營業時間請勿空白");
		} else if (!restOpen.matches("([01]?[0-9]|2[0-3]):[0-5][0-9] - ([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
			errorMsgs.put("restOpen", "營業時間格式錯誤請用24小時制");
		}
		
		
		System.out.println(city + " " + area + " " + restAddress );
		
		if (!errorMsgs.isEmpty()) {
			RestVO restList = new RestVO();
			restList.setRestName(restName);
			restList.setRestTel(restTel);
			restList.setRestAddress(restAddress);
			restList.setRestType(restType);
			restList.setRestOpen(restOpen);
			restList.setRestStatus(Integer.valueOf(req.getParameter("restStatus")));
			restList.setRestText(req.getParameter("restText"));
			req.setAttribute("rest", restList);
			
			return "/backend/rest/addRest.jsp";
		}
		
		RestVO rest = new RestVO();
		AdminVO admin = new AdminVO();
		rest.setRestName(restName);
		rest.setRestTel(restTel);
		rest.setRestAddress(city + area + restAddress);
		rest.setRestType(restType);
		rest.setRestOpen(restOpen);
		rest.setRestStatus(Integer.valueOf(req.getParameter("restStatus")));
		rest.setBookingLimit(0);
		rest.setAmLimit(Integer.parseInt(req.getParameter("amLimit")));
		rest.setNoonLimit(Integer.parseInt(req.getParameter("noonLimit")));
		rest.setPmLimit(Integer.parseInt(req.getParameter("pmLimit")));
		rest.setRestText(req.getParameter("restText"));
		admin = hbAdminService.getOneAdmin(Integer.parseInt(req.getParameter("adminId")));
		rest.setAdminVO(admin);
		String restId = Integer.toString(restService.addRest(rest));
		saveImg(restId, req.getPart("uploadimg"));
		
		return "/backend/Rest.do?action=getAll";
	}
	
	private String delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String restId = req.getParameter("restId");
		restService.deleteRest(Integer.parseInt(restId));
		return "/backend/Rest.do?action=getAll";
	}
	
	private String forUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		AdminVO admin = (AdminVO) session.getAttribute("admin");
		Integer adminId = admin.getAdminId();
		Byte adminFunName = admin.getAdminFuncName();
		if (adminId != null && adminFunName == 1) {
			try {
				RestVO restVO = restService.findByAdmin(adminId);
				Integer restId = restVO.getRestId();
				RestVO restList = restService.getRestByRestId(restId);
				req.setAttribute("rest", restList);
				List<AdminVO> adminlist = hbAdminService.getAll();
				req.setAttribute("adminlist", adminlist);
				return "/backend/rest/editRest.jsp";
			} catch (Exception e) {
				return "/backend/backIndex/index.jsp";
			}
		}
		String restId = req.getParameter("restId");
		RestVO restList = restService.getRestByRestId(Integer.parseInt(restId));
		req.setAttribute("rest", restList);
		List<AdminVO> adminlist = hbAdminService.getAll();
		req.setAttribute("adminlist", adminlist);
		return "/backend/rest/editRest.jsp";
	}
	
	private String update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		String restName = req.getParameter("restName");
		if (restName.isEmpty() || restName == null || restName.length() == 0) {
			errorMsgs.put("restName", "餐廳名稱請勿空白");
		}
		String restTel = req.getParameter("restTel");
		if (restTel.isEmpty() || restTel == null || restTel.trim().length() == 0) {
			errorMsgs.put("restTel", "餐廳電話請勿空白");
		} else if (!restTel.matches("09[0-9]{8}") && !restTel.matches("0[2-9]-[0-9]{7,8}")) {
			errorMsgs.put("restTel", "餐廳電話格式錯誤");
		}
		String restAddress = req.getParameter("restAddress");
		if (restAddress.isEmpty() || restAddress == null || restAddress.trim().length() == 0) {
			errorMsgs.put("restAddress", "餐廳地址請勿空白");
		}
		String restType = req.getParameter("restType");
		if (restType.isEmpty() || restType == null || restType.trim().length() == 0) {
			errorMsgs.put("restType", "餐廳類型請勿空白");
		}
		String restOpen = req.getParameter("restOpen");
		if (restOpen.isEmpty() || restOpen == null || restOpen.trim().length() == 0) {
			errorMsgs.put("restOpen", "營業時間請勿空白");
		} else if (!restOpen.matches("([01]?[0-9]|2[0-3]):[0-5][0-9] - ([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
			errorMsgs.put("restOpen", "營業時間格式錯誤請用24小時制");
		}
		
		if (!errorMsgs.isEmpty()) {
			return "/backend/Rest.do?action=getId_for_update";
		}
		
		RestVO rest = new RestVO();
		AdminVO admin = new AdminVO();
		rest.setRestName(restName);
		rest.setRestTel(restTel);
		rest.setRestAddress(restAddress);
		rest.setRestType(restType);
		rest.setRestOpen(restOpen);
		rest.setRestStatus(Integer.valueOf(req.getParameter("restStatus")));
		rest.setBookingLimit(0);
		rest.setAmLimit(Integer.parseInt(req.getParameter("amLimit")));
		rest.setNoonLimit(Integer.parseInt(req.getParameter("noonLimit")));
		rest.setPmLimit(Integer.parseInt(req.getParameter("pmLimit")));
		rest.setRestText(req.getParameter("restText"));
		admin = hbAdminService.getOneAdmin(Integer.parseInt(req.getParameter("adminId")));
		rest.setAdminVO(admin);
		rest.setRestId(Integer.parseInt(req.getParameter("restId")));
		
		saveImg(req.getParameter("restId"), req.getPart("uploadimg"));
		
		restService.updateRest(rest);
		return "/backend/Rest.do?action=getAll";
	}
	
	private String getMembooking(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		AdminVO admin = (AdminVO) session.getAttribute("admin");
		Integer adminId = admin.getAdminId();
		Byte adminFunName = admin.getAdminFuncName();
		if (adminId != null && adminFunName == 1) {
			try {
				RestVO restVO = restService.findByAdmin(adminId);
				Integer restId = restVO.getRestId();
				List<MemBookingVO> mb = memBookingService.getByRestId(restId);
				req.setAttribute("mbList", mb);
				return "/backend/rest/listMembooking.jsp";
			} catch (Exception e) {
				return "/backend/backIndex/index.jsp";
			}
		}
		List<MemBookingVO> mb = memBookingService.getAllMembooking();
		req.setAttribute("mbList", mb);
		return "/backend/rest/listMembooking.jsp";
	}
	
	private String getBookingDate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		AdminVO admin = (AdminVO) session.getAttribute("admin");
		Integer adminId = admin.getAdminId();
		Byte adminFunName = admin.getAdminFuncName();
		if (adminId != null && adminFunName == 1) {
			try {
				RestVO restVO = restService.findByAdmin(adminId);
				Integer restId = restVO.getRestId();
				List<RestBookingDateVO> bookDate = bookingDateService.getById(Integer.valueOf(restId));
				req.setAttribute("admin", admin);
				req.setAttribute("bookDate", bookDate);
				return "/backend/rest/listBookingDate.jsp";
			} catch (Exception e) {
				return "/backend/backIndex/index.jsp";
			}
		}
		List<RestBookingDateVO> bookDate = bookingDateService.getAll();
		req.setAttribute("bookDate", bookDate);
		return "/backend/rest/listBookingDate.jsp";
	}
	
	
	private String checkBookingUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemBookingVO vo = new MemBookingVO();
		Integer bookingId = Integer.parseInt(req.getParameter("bookingId"));
		vo = memBookingService.getByMembookingId(bookingId);
		vo.setCheckStatus(Integer.parseInt(req.getParameter("checkStatus")));
		memBookingService.update(vo);
		
		return "/backend/Rest.do?action=getMembooking";
	}
	
	private void saveImg(String restId, Part file) {
		// 設定儲存圖片路徑與檔名
        String uploadPath = getServletContext().getRealPath("/static/images/rest");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
        	uploadDir.mkdir();
        String fileName = "r_" + restId + ".jpg";
        String filePath = uploadPath + File.separator + fileName;
        System.out.println(filePath);
        // 處理圖片儲存
        // enctype="multipart/form-data" form上傳 取上傳圖片
        Part filePart = file;
        // 判斷沒上傳圖片則不執行
        if (filePart != null && filePart.getSize() > 0) {
        	try {
        		// 將圖片輸入
        		InputStream input = filePart.getInputStream();
        		// 將圖片輸出到設定的檔案路徑
        		OutputStream output = new FileOutputStream(filePath);
        		byte[] buffer = new byte[1024];
        		int length;
        		// 輸入流中讀取檔案數據並寫入輸出流 讀完返回-1
        		while ((length = input.read(buffer)) != -1) {
        			output.write(buffer, 0, length);
        		}
        		output.close();
            	input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
	}
	
	private String forAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	    List<AdminVO> adminlist = hbAdminService.getAll();
		req.setAttribute("adminlist", adminlist);
		
		CityService cityService = new CityService();
		List<CityVO> citylist = cityService.getAll();
		req.setAttribute("citylist", citylist);
		
		return "/backend/rest/addRest.jsp";
	}
	
	
}
