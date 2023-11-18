package com.depthspace.attractions.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.attractions.model.AttractionsTypeVO;
import com.depthspace.attractions.model.AttractionsVO;
import com.depthspace.attractions.service.AttractionsService;
import com.depthspace.column.model.ColumnArticlesVO;

@WebServlet("/attractionsEnd/*")
public class AttractionsEndServlet extends HttpServlet {
	
	public void init() throws ServletException {
		
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String pathInfo = req.getPathInfo();
		switch (pathInfo) {
		case "/list": // 行程列表
			doList(req, res);
			break;
		case "/search": //搜尋
			doSearch(req, res);
			break;
		}
	
	}
	private void doSearch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AttractionsService attractionService = new AttractionsService();
		Integer attrTypeId = null;
    	Map<String, String[]> map =req.getParameterMap();
    	
    	try {
    		attrTypeId = Integer.valueOf(req.getParameter("attrTypeId"));
    	} catch (NumberFormatException e) {
    		attrTypeId = null;
    	}
    	List<AttractionsVO> list = new ArrayList<>();
    	
		if (attrTypeId != null) {
			List<AttractionsVO> colTypeList = attractionService.getAllAttrType(attrTypeId);
			list.addAll(colTypeList);
		}
		
		String[] artiTitleQueries = map.get("artiTitle");
		if (artiTitleQueries != null && artiTitleQueries.length > 0 && !artiTitleQueries[0].isEmpty()) {
			List<AttractionsVO> artiTitleList = attractionService.getAttractionsByCompositeQuery(map);
			list.addAll(artiTitleList);
		}
		System.out.println(list);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/backend/attractions/search.jsp").forward(req, res);
		
	}
	private void doList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AttractionsService attractionService = new AttractionsService();
		List<AttractionsVO> allAttr = attractionService.getAll();
		req.setAttribute("allAttr", allAttr);

		// 取得所有內容(VO) "分頁"
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<AttractionsVO> attrList = attractionService.getAllPage(currentPage);
		
		int pageQty = attractionService.getPageTotal();
		req.getSession().setAttribute("pageQty", pageQty);
		System.out.println("attrList=" + attrList);
		System.out.println("currentPage=" + currentPage);
		
		req.setAttribute("attrList", attrList);
		req.setAttribute("currentPage", currentPage);
		
		//處理類型不重複
		Set<AttractionsTypeVO> uniqueTypes = new HashSet<>();
		for (AttractionsVO avo : attrList) {
			uniqueTypes.add(avo.getAttractionsTypeId());
			System.out.println("123="+avo);
		}
		System.out.println("456="+new ArrayList<>(uniqueTypes));
		
		req.setAttribute("uniqueTypes", new ArrayList<>(uniqueTypes));
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/attractions/list.jsp");
		dispatcher.forward(req, res);
		
	}
}
