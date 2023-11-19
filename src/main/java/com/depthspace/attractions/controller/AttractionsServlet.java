package com.depthspace.attractions.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.attractions.model.AttractionsImagesVO;
import com.depthspace.attractions.model.AttractionsVO;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.attractions.service.AttractionsImageService;
import com.depthspace.attractions.service.AttractionsService;
import com.depthspace.attractions.service.CityService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet({ "/attr/*" })
public class AttractionsServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AttractionsService attrs;
	private CityService cs;
	private AttractionsImageService ais;
	
	public void init() throws ServletException {
		attrs = new AttractionsService();
		cs = new CityService();
		ais = new AttractionsImageService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String pathInfo = req.getPathInfo();
//		System.out.println("pathInfo=" + pathInfo);
		switch (pathInfo) {
//		case "/":
//			doTList(req, resp);
//			break;
		case "/list"://所有景點
			doList(req, resp);
			break;
		case "/oneList"://單一頁面
			dooneList(req, resp);
			break;
		case "/search"://前端送出搜尋請求
			doSearch(req, resp);
			break;
			
		}

	}
	//前端關鍵字及選取框搜尋
	private void doSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	    
		String cityId = req.getParameter("cityId");//縣市編號
		String attractionsName = req.getParameter("attractionsName").trim();//使用者搜尋的景點名稱
		Integer getCityId = null;
		List<AttractionsVO> list = null ;
		
		if(cityId != null) { //如果有選縣市
			try {
		    	getCityId = Integer.valueOf(cityId);
		    	
//			    先將cityId對應的cityName的中文找出來
			    CityVO cityName = cs.findByPrimaryKey(getCityId);
//			    再把對應的城市名前兩個字取出
			    String Cityname2 = cityName.getCityName();
			    String firstCityname = Cityname2.substring(0,2);
//			    System.out.println("firstCityname=" + firstCityname);
//			    再用模糊比對找出對應的景點
			    list = attrs.findOtherAttractions(firstCityname);
		    } catch (NumberFormatException e) {
				e.printStackTrace();	
				return;
			}
		}else if( attractionsName != null){ //如果沒有選縣市
			 list = attrs.getAttractionsName(attractionsName);
			 System.out.println("list="+list);
		}
//	    再把所有縣市找回來
	    List<CityVO> city = cs.getAll();
	    
	    req.setAttribute("list", list);//取得所有符合的景點
		req.setAttribute("city", city);//取得台灣所有縣市
//		帶資料回list
		req.getRequestDispatcher("/attractions/list.jsp").forward(req, resp);
	}

	private void dooneList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("跳轉成功");
		Integer attractionsId;//使用者搜尋的景點名稱
				
	    try {
	    	attractionsId = Integer.valueOf(req.getParameter("attractionsId"));
	    } catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
//		System.out.println("attractionsId=" + attractionsId);
//		id去找景點資訊
		AttractionsVO avo = attrs.getAttractionsById(attractionsId);
//		System.out.println("單一景點： " + avo);
		//找景點圖片
		List<AttractionsImagesVO> imageList = ais.getAttractionsImagesById(attractionsId);
//		System.out.println("imageList=" + imageList);
		
		req.setAttribute("attractions" , avo);
		req.setAttribute("image" , imageList);
		req.getRequestDispatcher("/attractions/item.jsp").forward(req, resp);
	}

	private void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<AttractionsVO> list = attrs.getAll();		
		List<CityVO> city = cs.getAll();

		req.setAttribute("list", list);//取得所有景點
		req.setAttribute("city", city);//取得台灣所有縣市
		
		req.getRequestDispatcher("/attractions/list.jsp").forward(req, resp);
	}
	
	
	// fetch返回json格式
		private void setJsonResponse(HttpServletResponse resp, Object obj) throws IOException {
			Gson gson = new GsonBuilder()
					 .setDateFormat("yyyy-MM-dd") // 設定日期格式
		             .create();
			String jsonData = gson.toJson(obj);
//			System.out.println("jsonData=" + jsonData);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(jsonData);
		}
	
}
