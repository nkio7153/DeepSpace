package com.depthspace.tour.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		case "/tourList":// 用會員編號查出該會員所有行程
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
		case "/edit":
			doEdit(req, resp);
			break;
		case "/update":
			doUpdate(req, resp);
			break;
		}
	}

private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//使用行程編號跟會員編號查出獨一無二的一筆行程(tour)、行程天數(tourdays)、行稱明細(tourdetail)、景點(attractions)的view
	//縣市的下拉式選單可以選擇 縣市(city)
	//縣市跟景點中的區域 區域(area)
	//縣市對應的景點 景點(attractions)
	//update
	//全部刪除、全部重新新增
	
	Integer memId;
	Integer tourId;
	
	try {
		memId = Integer.valueOf(req.getParameter("memId"));
		tourId = Integer.valueOf(req.getParameter("tourId"));
	} catch (Exception e) {
		e.printStackTrace();
		return;
	}
//	System.out.println("memId= " + memId + "," + tourId);
	//用TourView把所有要放在會員形成列表的資料都拿出來
	List<TourView> list = ts.getOneTourList(tourId, memId);
	// 使用Comparator對list物件進行排序，依據天數排序
	Collections.sort(list, Comparator.comparingInt(TourView::getTourDays));
	//找出所有行程資訊
//	Collections.sort(list, Comparator.comparingInt(TourView::getTourDays)
//			.thenComparing(TourView::getStartDate));
	List<CityVO> cityList = cs.getAll();
	List<TourTypeVO> tourTypeVOS = tts.getAll();
	TourVO toVo = ts.getByTourId(tourId);
	Integer tourTypeId = toVo.getTourTypeId();
	TourTypeVO ttpVo = tts.findByPrimaryKey(tourTypeId);
	String tourTypName = ttpVo.getTourTypName();



	List<AttractionsVO> attrvo = attrs.findOneAttractions();
	List<AttractionsVO> attrAll = attrs.getAll();

	List<TourTypeVO> typeList = tts.getAll();

	req.setAttribute("typeList", typeList);

	req.setAttribute("tourTypName",tourTypName);
	req.setAttribute("cityList", cityList);
	req.setAttribute("attrvo", attrvo);
	req.setAttribute("attrAll", attrAll);



//	System.out.println(list);
	req.setAttribute("list", list);
	
//	List<AttractionsVO> attrList = attrs.getAll();
	
	req.getRequestDispatcher("/tour/tourEdit.jsp").forward(req, resp);
	
	}

private void doSaveSecond(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	//取得天數讓資料庫可以新增
//	取得會員
	Integer memId;
//	取得天數
	Integer tourdaysId = null;
//	取得行程編號，從上個頁面新增後有的
	Integer tourId;
//	取得總天數
	Integer allDays;
//	取得會員輸入的時間
	String[] allTime;
//	取得會員輸入景點
	String[] allAttr;
//	取的幾天的陣列
	String[] oneDay;
	//要找對應的時間
	String two;
	//要找對應的景點
	Integer three;
	//找對應景點名稱
	AttractionsVO four;
	
	Time time;
	
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
	//用總天數的數量去新增天數到資料庫
	for(int i=1 ; i <= allDays ; i++) {
		TourDaysVO tourDaysVO = new TourDaysVO(tourdaysId , i , tourId);
		tds.insert(tourDaysVO);
//		回傳剛才新增的天數編號
		int dayId = tourDaysVO.getTourDaysId();
//		System.out.println("新增的天數編號=" + tourDaysVO.getTourDaysId());
		System.out.println("回傳新增的tourdaysId=" + dayId);
		//取得自行輸入的時間及景點
		allTime = req.getParameterValues("attractionTime[" + i + "]");
		allAttr = req.getParameterValues("attractions[" + i + "]");
		oneDay = req.getParameterValues("oneDay[" + i + "]");
		
		for (int y = 0; y < allAttr.length; y++) {
		    two = allTime[y];
//		    System.out.println("第" + (y + 1) + "時間=" + two);
		    try {
		    	//時間轉型，經查Java 8或更新版本中，建議使用java.time.LocalTime
		    	LocalTime localTime = LocalTime.parse(two);
		    	time = Time.valueOf(localTime);
//	            System.out.println("time=" + time);
	            //景點編號轉型
		    	three = Integer.valueOf(allAttr[y]);
		    } catch (NumberFormatException e) {
				e.printStackTrace();
				return;
			}
		    four = attrs.getAttractionsById(three);
//		    System.out.println("第" + (y + 1) + "景點編號=" + three);
//		    System.out.println(dayId + allAttr[y] + time + time + four.getAttractionsName());
//		    Timestamp nullableTimestamp = null;
		    TourDetailVO tdvo = new TourDetailVO(dayId , three , time , null , four.getAttractionsName());
//		insert到detail裡面
		    tdls.insert(tdvo);
		    		
		}
		
	}
	
	 req.setAttribute("memId", memId);
	 req.getRequestDispatcher("/tr/tourList").forward(req, resp);
}

//	ajax傳遞找尋對應縣市景點選項
	private void doGetAttractions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 依據cityName找尋對應的景點的集合
		String cityName = req.getParameter("cityName");
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

	// 更新行程
	private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//更新表格Tour

		//取得 欲修改的行程物件變數
		Integer tourId = Integer.parseInt(req.getParameter("tourId"));
		String tourName = req.getParameter("tourName");
		String tourDescription = req.getParameter("tourDescription");
		Integer tourTypeId = Integer.parseInt(req.getParameter("tourType"));
		String str1 = req.getParameter("startDate");
		String str2 = req.getParameter("endDate");
		Date startDate=java.sql.Date.valueOf(str1);
		Date endDate=java.sql.Date.valueOf(str2);
		Integer allDays = Integer.parseInt(req.getParameter("allDays"));

		//取得原本的行程物件
		TourVO tourVO = ts.getByTourId(tourId);
		//設定欲修改的變數值
		tourVO.setAllDays(allDays);
		tourVO.setTourName(tourName);
		tourVO.setTourDescription(tourDescription);
		tourVO.setTourTypeId(tourTypeId);
		tourVO.setStartDate(startDate);
		tourVO.setEndDate(endDate);
		//更新行程物件
		ts.update(tourVO);

		//刪除後重新新增多個行程天數TOUR_DAYS
		//		行程天數編號
		//		行程天數
		//		行程編號

		//刪除新增行程明細TOUR_DETAIL
		//		行程天數編號
		//		景點編號
		//		開始時間
		//		結束時間
		//		景點名稱

//	取得天數
		Integer tourdaysId = null;
//	取得會員輸入的時間
		String[] allTime;
//	取得會員輸入景點
		String[] allAttr;
//	取的幾天的陣列
		String[] oneDay;
		//要找對應的時間
		String two;
		//要找對應的景點
		Integer three;
		//找對應景點名稱
		AttractionsVO four;

		Time time;

		//轉型

		Integer memId = Integer.parseInt(req.getParameter("memId"));

		//刪除原本行程編號對應的  多個行程天數物件 及 多個行程明細物件
		tds.deleteByTourId(tourId);

		//用總天數的數量去新增天數到資料庫
		for(int i=1 ; i <= allDays ; i++) {
			TourDaysVO tourDaysVO = new TourDaysVO(tourdaysId , i , tourId);
			tds.insert(tourDaysVO);
//		回傳剛才新增的天數編號
			int dayId = tourDaysVO.getTourDaysId();
//		System.out.println("新增的天數編號=" + tourDaysVO.getTourDaysId());
			System.out.println("回傳新增的tourdaysId=" + dayId);
//			取得自行輸入的時間及景點
			allTime = req.getParameterValues("attractionTime[" + i + "]");
			allAttr = req.getParameterValues("attractions[" + i + "]");
			oneDay = req.getParameterValues("oneDay[" + i + "]");

			for (int y = 0; y < allAttr.length; y++) {
				two = allTime[y];
//		    System.out.println("第" + (y + 1) + "時間=" + two);
				try {
					//時間轉型，經查Java 8或更新版本中，建議使用java.time.LocalTime
					LocalTime localTime = LocalTime.parse(two);
					time = Time.valueOf(localTime);
//	            System.out.println("time=" + time);
					//景點編號轉型
					three = Integer.valueOf(allAttr[y]);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return;
				}
				four = attrs.getAttractionsById(three);
//		    System.out.println("第" + (y + 1) + "景點編號=" + three);
//		    System.out.println(dayId + allAttr[y] + time + time + four.getAttractionsName());
//		    Timestamp nullableTimestamp = null;
				TourDetailVO tdvo = new TourDetailVO(dayId , three , time , null , four.getAttractionsName());
//		insert到detail裡面
				tdls.insert(tdvo);

			}

		}
		resp.sendRedirect(req.getContextPath()+"/tr/tourList");
	}


	private void showDetail(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}

	// 用會員編號查出該會員所有行程
	private void doTourList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer memId = null;
		HttpSession session = req.getSession(false);
		if(session.getAttribute("memId")!=null) {
            memId = (Integer)session.getAttribute("memId");
        }
//		try {
//			memId = Integer.valueOf(session.getParameter("memId"));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return;
//		}
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
		
//		=======================================================================================
		//用TourView把所有要放在會員形成列表的資料都拿出來
		List<TourView> list = ts.getOneTourList(tourId, memId);

		// 使用Comparator對list物件進行排序，依據天數排序
		Collections.sort(list, Comparator.comparingInt(TourView::getTourDays));
		
		System.out.println(list);
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
