package com.depthspace.restaurant.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.admin.service.HbAdminService;
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
@MultipartConfig
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
			case "checkBooking":
				forwardPath = checkBooking(req, resp);
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
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		String restName = req.getParameter("restName");
		if (restName.isEmpty() || restName == null || restName.trim().length() == 0) {
			errorMsgs.add("餐廳名稱請勿空白");
		}
		String restTel = req.getParameter("restTel");
		if (restTel.isEmpty() || restTel == null || restTel.trim().length() == 0) {
			errorMsgs.add("餐廳電話請勿空白");
		}
		String restAddress = req.getParameter("restAddress");
		if (restAddress.isEmpty() || restAddress == null || restAddress.trim().length() == 0) {
			errorMsgs.add("餐廳電話請勿空白");
		}
		String restType = req.getParameter("restType");
		if (restType.isEmpty() || restType == null || restType.trim().length() == 0) {
			errorMsgs.add("餐廳類型請勿空白");
		}
		String restOpen = req.getParameter("restOpen");
		if (restOpen.isEmpty() || restOpen == null || restOpen.trim().length() == 0) {
			errorMsgs.add("營業時間請勿空白");
		}
		Integer bookingLimit = null;
		try {
			bookingLimit = Integer.valueOf(req.getParameter("bookingLimit").trim());
		} catch (Exception e) {
			errorMsgs.add("可預約組數請勿空白");
			bookingLimit = 0;
		}
		RestVO rest = new RestVO();
		AdminVO admin = new AdminVO();
		rest.setRestName(restName);
		rest.setRestTel(restTel);
		rest.setRestAddress(restAddress);
		rest.setRestType(restType);
		rest.setRestOpen(restOpen);
		rest.setRestStatus(Integer.valueOf(req.getParameter("restStatus")));
		rest.setBookingLimit(bookingLimit);
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
		String restId = req.getParameter("restId");
		RestVO restList = restService.getRestByRestId(Integer.parseInt(restId));
		req.setAttribute("rest", restList);
		return "/backend/rest/editRest.jsp";
	}
	
	private String update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestVO rest = new RestVO();
		AdminVO admin = new AdminVO();
		rest.setRestName(req.getParameter("restName"));
		rest.setRestTel(req.getParameter("restTel"));
		rest.setRestAddress(req.getParameter("restAddress"));
		rest.setRestType(req.getParameter("restType"));
		rest.setRestOpen(req.getParameter("restOpen"));
		rest.setRestStatus(Integer.parseInt(req.getParameter("restStatus")));
		rest.setBookingLimit(Integer.parseInt(req.getParameter("bookingLimit")));
		admin = hbAdminService.getOneAdmin(Integer.parseInt(req.getParameter("adminId")));
		rest.setAdminVO(admin);
		rest.setRestId(Integer.parseInt(req.getParameter("restId")));
		
		saveImg(req.getParameter("restId"), req.getPart("uploadimg"));
		
		restService.updateRest(rest);
		return "/backend/Rest.do?action=getAll";
	}
	
	private String getMembooking(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String restId = req.getParameter("restId");
		List<MemBookingVO> mb = memBookingService.getByRestId(Integer.valueOf(restId));
		req.setAttribute("mbList", mb);
		return "/backend/rest/listMembooking.jsp";
	}
	
	private String getBookingDate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String restId = req.getParameter("restId");
		if (restId != null) {
			List<RestBookingDateVO> bookDate = bookingDateService.getById(Integer.valueOf(restId));
			req.setAttribute("bookDate", bookDate);
			return "/backend/rest/listBookingDate.jsp";
		}
		List<RestBookingDateVO> bookDate = bookingDateService.getAll();
		req.setAttribute("bookDate", bookDate);
		return "/backend/rest/listBookingDate.jsp";
	}
	
	private String checkBooking(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemBookingVO mb = memBookingService.getByMembookingId(Integer.valueOf(req.getParameter("bookingId")));
		req.setAttribute("mb", mb);
		return "/backend/rest/checkBooking.jsp";
	}
	
	private String checkBookingUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemBookingVO vo = new MemBookingVO();
		String restId = req.getParameter("restId");
		vo.setRestId(Integer.parseInt(req.getParameter("restId")));
		vo.setMemId(Integer.parseInt(req.getParameter("memId")));
		vo.setCheckStatus(Integer.parseInt(req.getParameter("checkStatus")));
		vo.setBookingTime(Integer.parseInt(req.getParameter("bookingTime")));
		vo.setBookingNumber(Integer.parseInt(req.getParameter("bookingNumber")));
		vo.setBookingDate(java.sql.Date.valueOf(req.getParameter("bookingDate")));
		vo.setBookingId(Integer.parseInt(req.getParameter("bookingId")));
		memBookingService.update(vo);
		return "/backend/Rest.do?action=getMembooking&restId=" + restId;
	}
	
	private void saveImg(String restId, Part file) {
		// 設定儲存圖片路徑與檔名
        String uploadPath = getServletContext().getRealPath("/static/images/rest");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
        	uploadDir.mkdir();
        String fileName = "r_" + restId + ".jpg";
        String filePath = uploadPath + File.separator + fileName;
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
        		int nRead;
        		// 輸入流中讀取檔案數據並寫入輸出流 讀完返回-1
        		while ((nRead = input.read(buffer)) != -1) {
        			output.write(buffer, 0, nRead);
        		}
        		output.close();
            	input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
	}
	
	
	
}
