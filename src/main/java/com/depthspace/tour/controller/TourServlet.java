package com.depthspace.tour.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.attractions.model.AreaVO;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.attractions.service.AreaService;
import com.depthspace.attractions.service.CityService;
import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.tour.model.tour.TourView;
import com.depthspace.tour.model.tourtype.TourTypeVO;
import com.depthspace.tour.service.TourService;
import com.depthspace.tour.service.TourTypeService;
import com.google.gson.Gson;

@WebServlet({ "/tr/*" })
public class TourServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	private TourService ts;
	private TourTypeService tts;
	private CityService cs;
	private AreaService as;

	public void init() throws ServletException {
		ts = new TourService();
		tts = new TourTypeService();
		cs = new CityService();
		as = new AreaService();
//		System.out.println("成功開啟");
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String pathInfo = req.getPathInfo();
		System.out.println("pathInfo=" + pathInfo);
		switch (pathInfo) {
		case "/tourList":
			doTourList(req, resp);
			break;
		case "/memTourList":
			domemTourList(req, resp);
			break;
		case "/showDetail":
			showDetail(req, resp);
			break;
		case "/save":
			doSave(req, resp);
			break;
		case "/addTour":
			doaddTour(req, resp);
			break;
		case "/getArea":
			dogetArea(req, resp);
			break;
		}

	}

	private void doaddTour(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		取出所有行程類型名稱
		List<TourTypeVO> list = tts.getAll();
//		System.out.println("list=" + list);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/tour/newTour.jsp").forward(req, resp);
	}

	// 新增新行程-1
	private void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer tourId = null;
		Integer memId = null;
		String tourName = req.getParameter("tourName");
		Integer tourTypeId= null;
		//行程描述
		String tourDescription = req.getParameter("tourDescription");
		
		String str1 = req.getParameter("startDate");
		String str2 = req.getParameter("endDate");
		Date startDate = null;
		Date endDate = null;
		Integer allDays;
	
		try {
			memId= Integer.valueOf(req.getParameter("memId"));
		    startDate = java.sql.Date.valueOf(str1);
		    endDate = java.sql.Date.valueOf(str2);
		    tourTypeId = Integer.valueOf(req.getParameter("tourType"));
		    allDays = Integer.valueOf( req.getParameter("tripDuration"));
		} catch (NumberFormatException e) {
			 e.printStackTrace();
	            return;
		}
		
		TourVO tourVO = new TourVO(tourId,memId,tourName,tourTypeId,allDays,tourDescription,startDate,endDate);
//		新增一筆行程資料
		TourVO tvo = null;
//		tvo = ts.insert(tourVO);
//		System.out.println("新增的那些東西"+ tourVO);
		
		TourTypeVO ttvo = new TourTypeVO();
		ttvo = tts.findByPrimaryKey(tourTypeId);
//		System.out.println("ttvo= " + ttvo);
		
		//尋找所有縣市
		List<CityVO> cityList = cs.getAll();
//		System.out.println("list=" + list);
		
		//尋找所有縣市及景點
		List<AreaVO> areaList = as.getAll();
		
		
		req.setAttribute("cityList", cityList);
		req.setAttribute("areaList", areaList);
		req.setAttribute("tourVO", tourVO);
		//設定對應的行程類型
		req.setAttribute("ttvo", ttvo);
		req.getRequestDispatcher("/tour/newTour2.jsp").forward(req, resp);

	}

	private void showDetail(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}

	// 用會員編號查出該會員所有行程
	private void doTourList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer memId;
		try {
			memId = Integer.valueOf(req.getParameter("memId"));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		List<TourVO> list = ts.getByMemId(memId);
		req.setAttribute("list", list);
		req.setAttribute("memId", memId);
		req.getRequestDispatcher("/tour/index.jsp").forward(req, resp);
	}

	// 查詢單獨行程
	private void domemTourList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer memId;
		Integer tourId;
		try {
			memId = Integer.valueOf(req.getParameter("memId"));
			tourId = Integer.valueOf(req.getParameter("tourId"));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
//		System.out.println("memId=" + memId + "," + "tourId=" + tourId);
		List<TourView> list = ts.getOneTourList(tourId, memId);
//		System.out.println(list);

		req.setAttribute("list", list);

		req.getRequestDispatcher("/tour/memTourList.jsp").forward(req, resp);
	}
	
	private void dogetArea(HttpServletRequest req, HttpServletResponse resp) {
		//依據cityId找尋對應的areaId及name的集合
		Integer cityId;
		try {
			cityId =  Integer.valueOf(req.getParameter("cityId"));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		List<AreaVO> areaList = as.getAllArea(cityId);
		
	}
	
	
	//fetch返回json格式
    private void setJsonResponse(HttpServletResponse resp, Object obj) throws IOException {
        Gson gson = new Gson();
        String jsonData = gson.toJson(obj);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonData);
    }
}
