package com.depthspace.tour.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.attractions.model.AreaVO;
import com.depthspace.attractions.model.AttractionsVO;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.attractions.service.AreaService;
import com.depthspace.attractions.service.AttractionsService;
import com.depthspace.attractions.service.CityService;
import com.depthspace.tour.model.TourDaysVO;
import com.depthspace.tour.model.TourDetailVO;
import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.tour.model.tour.TourView;
import com.depthspace.tour.model.tourtype.TourTypeVO;
import com.depthspace.tour.service.TourDaysService;
import com.depthspace.tour.service.TourDetailService;
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
	private AttractionsService attrs;
	private TourDaysService tds;
	private TourDetailService tdls;

	public void init() throws ServletException {
		ts = new TourService();
		tts = new TourTypeService();
		cs = new CityService();
		as = new AreaService();
		attrs = new AttractionsService();
		tds = new TourDaysService();
		tdls = new TourDetailService();
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
			//查詢單一行程內容
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
			doGetArea(req, resp);
			break;
		case "/getAttractions":
			doGetAttractions(req, resp);
			break;
		case "/saveSecond":
			doSaveSecond(req, resp);
			break;
		}

	}

private void doSaveSecond(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	//取得天數讓資料庫可以新增
	Integer memId;
	Integer tourdaysId = null;
	Integer tourId;
	Integer allDays;
	String[] allAttr;
	String[] allTime;
//	取的幾天的陣列
	String[] oneDay;
	
	//轉型
	try {
		memId = Integer.valueOf(req.getParameter("memId"));
		allDays = Integer.valueOf(req.getParameter("allDays"));
		tourId = Integer.valueOf(req.getParameter("tourId"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
//	System.out.println("memId=" + memId);
	//用總天數的數量去新增天數
	for(int i=1 ; i <= allDays ; i++) {
		TourDaysVO tourDaysVO = new TourDaysVO(tourdaysId , allDays , tourId);
		tds.insert(tourDaysVO);
		//取得自行輸入的時間及景點
		allTime = req.getParameterValues("attractionTime[" + i + "]");
		allAttr = req.getParameterValues("attractions[" + i + "]");
		oneDay = req.getParameterValues("oneDay[" + i + "]");

		for(String one : oneDay) {
			//one為Id
			System.out.println("天數對應=" + one);
		}
		for(String one : allTime) {
			//one為Id
			System.out.println("時間=" + one);
		}
		for(String one : allAttr) {
			//one為Id			
			System.out.println("第" + i + "景點編號=" + one);
		}
	}
	
	 req.setAttribute("memId", memId);
	 req.getRequestDispatcher("/tr/tourList").forward(req, resp);
}

//	ajax傳遞找尋對應縣市景點選項
	private void doGetAttractions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 依據cityName找尋對應的景點的集合
		String cityName = req.getParameter("cityName");
//		System.out.println("cityName="+cityName);
		List<AttractionsVO> list = attrs.findOtherAttractions(cityName);
//		System.out.println("list="+list);
		//轉型成Josn送回jsp
		setJsonResponse(resp, list);
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
		Integer tourTypeId = null;
		// 行程描述
		String tourDescription = req.getParameter("tourDescription");

		String str1 = req.getParameter("startDate");
		String str2 = req.getParameter("endDate");
		Date startDate = null;
		Date endDate = null;
		Integer allDays;

		try {
			memId = Integer.valueOf(req.getParameter("memId"));
			startDate = java.sql.Date.valueOf(str1);
			endDate = java.sql.Date.valueOf(str2);
			tourTypeId = Integer.valueOf(req.getParameter("tourType"));
			allDays = Integer.valueOf(req.getParameter("tripDuration"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}

		TourVO tourVO = new TourVO(tourId, memId, tourName, tourTypeId, allDays, tourDescription, startDate, endDate);
//		新增一筆行程資料
		TourVO tvo = null;
		tvo = ts.insert(tourVO);
//		System.out.println("新增的那些東西"+ tourVO);
		
		// 額外設定天數顯示
//		System.out.println("總天數=" + tourVO.getAllDays());
//		跑回圈把所有天數放進一個集合裡
		List<Integer> dayList = new ArrayList<>();
		int allday = tourVO.getAllDays();
		for (int i = 1; i <= allday; i++) {
			dayList.add(i);
		}
		// 找尋對應的行程類型
		TourTypeVO ttvo = new TourTypeVO();
		ttvo = tts.findByPrimaryKey(tourTypeId);

		// 尋找所有縣市
		List<CityVO> cityList = cs.getAll();
//		//尋找所有縣市及景點
//		List<AreaVO> data = as.getAllArea(101);

		// 尋找所有的景點
		List<AttractionsVO> attrList = attrs.getAll();
//		變例出來看看
//		for (AttractionsVO attraction : attrList) {
//		    System.out.println(attraction.getAttractionsName());
//		}

		// 尋找台北市對應景點
		List<AttractionsVO> attrvo = attrs.findOneAttractions();
//		for (AttractionsVO attraction : attrvo) {
//			System.out.println(attraction);
//		}

		// 傳送所有縣市
		req.setAttribute("cityList", cityList);
//		//找尋地區 預設為台北市，其他縣市則由ajax去發送請求
//		req.setAttribute("data", data);
		// 傳送上一個頁面新增的物件到下一個頁面顯示
		req.setAttribute("tourVO", tourVO);
		// 傳送天數讓jsp可以顯示天數
		req.setAttribute("dayList", dayList);
		// 傳送所有景點
		req.setAttribute("attrList", attrList);
		// 傳送台北市景點(預設)
		req.setAttribute("attrvo", attrvo);
		// 設定對應的行程類型
		req.setAttribute("ttvo", ttvo);
		//設定最後一筆形成Id
		req.setAttribute("tvo", tvo);
		
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
		
		//找出日期，總天數，敘述
//		List<TourVO> list = ts.getOneByMemId(tourId, memId);
//		System.out.println("list=" +  list);
		
		
		System.out.println("memId=" + memId + "," + "tourId=" + tourId);
		
		//用memId及tourId去找所有行程(ok有找到對應的值)
		//依照行程編號去找行程天數編號(一個行程會有很多個行程天數編號)
//		List<TourDaysVO> tourdaysvo = tds.getOneTour(tourId);
//		System.out.println(tourdaysvo);
//		[TourDaysVO [tourDaysId=1, tourDays=3, tourId=1], 
//		TourDaysVO [tourDaysId=5, tourDays=1, tourId=1], 
//		TourDaysVO [tourDaysId=6, tourDays=2, tourId=1], 
//		TourDaysVO [tourDaysId=7, tourDays=4, tourId=1], 
//		TourDaysVO [tourDaysId=8, tourDays=5, tourId=1]]
//		將tourDaysId變例出來
//		List<Integer> allTourDaysIds = new ArrayList<>();
//		for (TourDaysVO tourDays : tourdaysvo) {
		    // 獲取並添加 tourDaysId 的值到 tourDaysIds 列表中
//			allTourDaysIds.add(tourDays.getTourDaysId());
//		}
		//成功找出所有的id
//		System.out.println("allTourDaysIds = " + allTourDaysIds);
		//用Id的陣列去取行程裡的明細
		//在變例一次裡面的值
//		List<TourDetailVO> result = null;
//		for (Integer tourDaysId : allTourDaysIds) {
		    // 調用 getTourDaysId 方法並處理結果
		    // 這裡假設 getTourDaysId 方法返回 String，你應該根據實際情況修改
//		    result = tdls.getTourDaysId(tourDaysId);
//		    System.out.println("result = " + result);
//		}
		
//		req.setAttribute("tourdaysvo", tourdaysvo);
//		req.setAttribute("result", result);
//		for (int i = 0 ; i < allTourDaysIds.size() ; i ++) {
//			List<TourDetailVO> tourDetail = tdls.getTourDaysId(allTourDaysId[i]);
//		}
//		Integer tourDaysId ;
		//再用找出來的所有行程天數編號去找對應的行程明細
//		List<TourDetailVO> tourDetail = tdls.getTourDaysId(tourDaysId);
		
		//用TourView把所有要放在會員形成列表的資料都拿出來
		List<TourView> list = ts.getOneTourList(tourId, memId);
//		for(TourView a : list){
//			System.out.println("a=" + a);
//			System.out.println("a.getAttractionsName()=" + a.getAttractionsName());
//					}
//		System.out.println(list);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/tour/memTourList.jsp").forward(req, resp);
	}

//	ajax傳遞找尋縣市選項
	private void doGetArea(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 依據cityId找尋對應的areaId及name的集合
		Integer cityId;

		try {
			cityId = Integer.valueOf(req.getParameter("cityId"));
//			System.out.println("cityId="+cityId);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		List<AreaVO> list = as.getAllArea(cityId);
//		System.out.println("list="+list);

		setJsonResponse(resp, list);

	}

	// fetch返回json格式
	private void setJsonResponse(HttpServletResponse resp, Object obj) throws IOException {
		Gson gson = new Gson();
		String jsonData = gson.toJson(obj);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonData);
	}
}
