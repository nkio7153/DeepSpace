package com.depthspace.tour.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.tour.model.tour.TourView;
import com.depthspace.tour.service.TourService;

@WebServlet({ "/tr/*" })
public class TourServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	private TourService ts;

	public void init() throws ServletException {
		ts = new TourService();
//		System.out.println("成功開啟");
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String pathInfo = req.getPathInfo();
//		System.out.println("pathInfo=" + pathInfo);
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
		case "/addTour":
			addTour(req, resp);
			break;
		}

	}

	// 新增新行程
	private void addTour(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getParameter("memId");
		String tourName = req.getParameter("tourName");
		String tourDescription = req.getParameter("tourDescription");
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		String tripDuration = req.getParameter("tripDuration");
//		Map<String, String> attractionsMap = new HashMap<>();
		String[] attractionTime = req.getParameterValues("attractionTime");
		String[] attraction = req.getParameterValues("attraction");
		String[] tourDays = req.getParameterValues("days");
//		for (String addTourDays : tourDays) {
//				System.out.println("天數=" + addTourDays);
//			}
////		}
//		用迴圈將所有景點及時間對應
//		Map<String, String> attractionsMap = new HashMap<>();
//		for(int i = 0 ; i < attractionTime.length ; i++) {
//			
//				attractionsMap.put("attractionTime", attractionTime[i]);
//				attractionsMap.put("attraction", attraction[i]);
////				System.out.println("attractionsMap=" + attractionsMap);
//		}
		 String[] container = req.getParameterValues("container");
		 if (container != null) {
	            for (String day : container) {
	                // 根据类名获取每一天的景点
	                String dayNumber = req.getParameter("day-" + day); // 使用 "day-" + day 作为参数名称
	                String[] attractions = req.getParameterValues("attraction-" + day); // 使用 "attraction-" + day 作为参数名称

	                // 处理每一天的景点数据
	                System.out.println("第 " + dayNumber + " 天的景点：");
	                if (attractions != null) {
	                    for (String attraction2 : attractions) {
	                        System.out.println(attraction2);
	                    }
	                }
	            }
	        }
		 
		 
		 //==============================================================
//		List<Map<String, String>> tourDaysList = new ArrayList<>();
//		if (attractionTime != null && attraction != null && tourDays != null) {
//		    for (int i = 0; i < attractionTime.length; i++) {
//		        Map<String, String> dayInfo = new HashMap<>();
//		        dayInfo.put("attractionTime", attractionTime[i]);
//		        dayInfo.put("attraction", attraction[i]);
//		        dayInfo.put("day", tourDays[i]);
//		        tourDaysList.add(dayInfo);
//		        System.out.println(tourDaysList);
//		    }
//		}
		//==============================================================
//		System.out.println("tourDays="+tourDays);
		
//		System.out.println("會員編號=" + memId + ", 行程名稱(使用者自訂)=" + tourName + ", 行程敘述=" + tourDescription + ", 開始日期="
//		+ startDate + ", 結束日期=" + endDate + ", 總天數=" + tripDuration);
		
		
		// 確認長度相同
//		if (attractionTime != null && attraction != null && attractionTime.length == attraction.length) {
//			for (int i = 0; i < attractionTime.length; i++) {
//				Map<String, String> attractionData = new HashMap<>();
//				attractionData.put("attraction", attraction[i]);
//				attractionData.put("attractionTime", attractionTime[i]);
//				attractionsList.add(attractionData);
//			}
//			System.out.println("attractionsList=" + attractionsList);
//		}
		// 用迴圈把所有已輸入的景點取出來
//		for (String addAttraction : attraction) {
//			for (String addAttractionTime : attractionTime) {
//				System.out.println("景點=" + addAttraction);
//				System.out.println("時間=" + addAttractionTime);
//			}
//		}


		req.getRequestDispatcher("/tour/newTour.jsp").forward(req, resp);

	}

	private void showDetail(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}

	// 用會員編號查出該會員所有行程
	private void doTourList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer memId;
		try {
			memId = Integer.valueOf(req.getParameter("memId"));
//          System.out.println(memId);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		List<TourVO> list = ts.getByMemId(memId);
//		System.out.println(list);
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

}
