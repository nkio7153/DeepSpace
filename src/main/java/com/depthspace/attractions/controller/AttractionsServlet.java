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
		Integer areaId;//縣市編號
		String attractionsName = req.getParameter("attractionsName");//使用者搜尋的景點名稱
		
//		System.out.println("areaId=" + areaId + "，attractionsName=" + attractionsName);
	    try {
	    	areaId = Integer.valueOf(req.getParameter("areaId"));
	    } catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		
		
		
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
	
}
