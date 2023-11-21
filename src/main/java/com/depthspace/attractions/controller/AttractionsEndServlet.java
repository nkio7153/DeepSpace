package com.depthspace.attractions.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.attractions.model.AreaVO;
import com.depthspace.attractions.model.AttractionsTypeVO;
import com.depthspace.attractions.model.AttractionsVO;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.attractions.service.AreaService;
import com.depthspace.attractions.service.AttractionsImageService;
import com.depthspace.attractions.service.AttractionsService;
import com.depthspace.attractions.service.AttractionsTypeService;
import com.depthspace.attractions.service.CityService;

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
		case "/list": // 景點列表
			doList(req, res);
			break;
		case "/search": //搜尋
			doSearch(req, res);
			break;
		case "/add": //新增
			doAdd(req, res);
			break;
		case "/add2": //新增
			doAdd2(req, res);
			break;
		}
	
	}
	private void doAdd(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AttractionsTypeService attrTypeService = new AttractionsTypeService();
		CityService cityService = new CityService();
		AreaService areaService = new AreaService();
		List<AttractionsTypeVO> attractionsTypes = attrTypeService.getAll();
		List<CityVO> city = cityService.getAll();
		List<AreaVO> area = areaService.getAll();
		req.setAttribute("attractionsTypes", attractionsTypes);
		req.setAttribute("city", city);
		req.setAttribute("area", area);
		req.getRequestDispatcher("/backend/attractions/add.jsp").forward(req, res);
		
		
	}
	//新增景點
	private void doAdd2(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		AttractionsService attractionService = new AttractionsService();
		AttractionsImageService attractionsImageService = new AttractionsImageService();
		Integer attractionsTypesId = Integer.valueOf(req.getParameter("attractionsTypesId"));
		Integer city = Integer.valueOf(req.getParameter("city"));
		String attrName = req.getParameter("attractionsName");
		String description = req.getParameter("description");
		System.out.println("description="+description);
		
		
//		if (!req.getMethod().equalsIgnoreCase("POST")) {
//			// 新增頁面中要放入選單項目，要取其值，set屬性到頁面中
//			List<AttractionsVO> attrTypes = attractionService.getAllAttrType();
////			List<AdminVO> admins = attractionService.getAllAdmins();
//			req.setAttribute("attrTypes", attrTypes);
////			req.setAttribute("admins", admins);
//
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/attractions/add.jsp");
//			dispatcher.forward(req, res);
//			return;
//		} else {
//			// 完成表單填寫，按下送出觸發POST，就將下列的資料送出
//			AttractionsVO attrVO = new AttractionsVO();
//			attrVO.setAttractionsName(req.getParameter("attractionsName"));
//			Integer areaId = Integer.valueOf(req.getParameter("areaId"));
//			attrVO.setDescription(req.getParameter("description"));
//			attrVO.setAttractionsStatus(Integer.valueOf(req.getParameter("attractionsStatus")));
//			attrVO.setAddress(req.getParameter("address"));
//
//			Integer attrTypeId = Integer.valueOf(req.getParameter("attrTypeId"));
//			AttractionsTypeVO attr = new AttractionsTypeVO();
//			attr.setAttractionsTypeId(attrTypeId);
//			attrVO.setAttractionsTypeId(attr);
//			attractionService.insert(attrVO);
//			
//			
//			// 存入圖片
//			Part filePart = req.getPart("Img");
//			if (filePart != null && filePart.getSize() > 0) {
//				AttractionsImagesVO attrImg = new AttractionsImagesVO();
//				attrImg.setAttractionsId(attrVO);
//
//				InputStream inputStream = filePart.getInputStream();
//				byte[] imageBytes = readInputStream(inputStream);
//				attrImg.setAttractionsImg(imageBytes);
//				attractionsImageService.save(attrImg);
//			}
//		}
//		int pageQty = attractionService.getPageTotal();
//		req.getSession().setAttribute("pageQty", pageQty);
		
		res.sendRedirect(req.getContextPath() + "/attractionsEnd/list");
		
	}
	/************ 圖片讀入DB ************/
	public byte[] readInputStream(InputStream inputStream) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int bytesRead;

		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		return outputStream.toByteArray();
	}
	private void doSearch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AttractionsService attractionService = new AttractionsService();
		AttractionsTypeService attrTypeService = new AttractionsTypeService();
		Integer attrTypeId = null;
//		String attractionsName = req.getParameter("attractionsName");
    	Map<String, String[]> map =req.getParameterMap();
    	
    	try {
    		attrTypeId = Integer.valueOf(req.getParameter("attrTypeId"));
//    		System.out.println("attrTypeId= " + attrTypeId);
    	} catch (NumberFormatException e) {
    		attrTypeId = null;
    	}
    	
    	List<AttractionsVO> list = new ArrayList<>();
    	
    	if (attrTypeId != null) {
    		List<AttractionsVO> atteList = attractionService.getAllAttrType(attrTypeId);
    		list.addAll(atteList);
		} 
    	
    	String[] attrTitle = map.get("attractionsName");
    	if (attrTitle != null && attrTitle.length > 0 && !attrTitle[0].isEmpty()) {
			List<AttractionsVO> attrName = attractionService.getAttractionsByCompositeQuery(map);
			list.addAll(attrName);
		}
    	
//    	System.out.println(list);
    	req.setAttribute("list", list);
		req.getRequestDispatcher("/backend/attractions/search.jsp").forward(req, res);
	}
	private void doList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AttractionsService attractionService = new AttractionsService();
		List<AttractionsVO> list = attractionService.getAll();
		req.setAttribute("list", list);
//
		List<AttractionsVO> attrList = attractionService.getAll();
		
//		int pageQty = attractionService.getPageTotal();
//		req.getSession().setAttribute("pageQty", pageQty);
//		System.out.println("attrList=" + attrList);
//		System.out.println("currentPage=" + currentPage);
//		
//		req.setAttribute("attrList", attrList);
//		req.setAttribute("currentPage", currentPage);
		
		//處理類型不重複
		Set<AttractionsTypeVO> uniqueTypes = new HashSet<>();
		for (AttractionsVO avo : attrList) {
			uniqueTypes.add(avo.getAttractionsTypeId());
//			System.out.println("123="+avo);
		}
//		System.out.println("456="+new ArrayList<>(uniqueTypes));
		
		req.setAttribute("uniqueTypes", new ArrayList<>(uniqueTypes));
		
		req.getRequestDispatcher("/backend/attractions/list.jsp").forward(req, res);
		
	}
}
