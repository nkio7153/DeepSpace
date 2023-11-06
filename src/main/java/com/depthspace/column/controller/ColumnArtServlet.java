package com.depthspace.column.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.column.model.ColumnTypesVO;
import com.depthspace.column.model.ColumnImagesVO;
import com.depthspace.admin.model.model.AdminVO;
import com.depthspace.column.service.ColumnArticlesService;
import com.depthspace.column.service.ColumnArticlesServiceImpl;
import com.depthspace.column.service.ColumnImagesService;
import com.depthspace.column.service.ColumnImagesServiceImpl;
import com.depthspace.utils.HibernateUtil;

@WebServlet("/columnarticles/*")
@MultipartConfig
public class ColumnArtServlet extends HttpServlet {

	private ColumnArticlesService columnArticlesService;
	private ColumnImagesService columnImagesService;

	public void init() throws ServletException {
		columnArticlesService = new ColumnArticlesServiceImpl();
		columnImagesService = new ColumnImagesServiceImpl();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String pathInfo = req.getPathInfo();
		if (pathInfo == null) {
			pathInfo = "";
		}

		switch (pathInfo) {
		case "/": // 專欄進入連結
			res.sendRedirect(req.getContextPath() + "/frontend/columnarticles/info.jsp");
			break;
		case "/list": // 專欄總列表
			doList(req, res);
			break;
		case "/item": // 專欄單一頁面
			doItem(req, res);
			break;
//		case "/find": // 專欄查找
//			doSearch(req, res);
//			break;

		default:
			System.out.println("Path not handled: " + pathInfo);
		}

	}

	/************ 專欄列表 ************/
	private void doList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		long total = columnArticlesService.getTotal();
		
		List<ColumnArticlesVO> columnList = columnArticlesService.getAllArti2(currentPage);

		if (req.getSession().getAttribute("PageQty") == null) {
			int pageQty = columnArticlesService.getPageTotal();
			req.getSession().setAttribute("PageQty", pageQty);
		}
		
		req.setAttribute("total", total); //專欄文章總數
		req.setAttribute("currentPage", currentPage); //分頁數
		req.setAttribute("columnList", columnList);  

		RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/columnarticles/list.jsp");
		dispatcher.forward(req, res);
	}

	/************ 單一專欄頁面 ************/	
	private void doItem(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String artiIdStr = req.getParameter("artiId");
		Integer artiId;
		try {
			artiId = Integer.valueOf(artiIdStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}

		ColumnArticlesVO columnArticles = columnArticlesService.getArtiByArtiId(artiId);

		req.setAttribute("columnArticles", columnArticles);
		req.getRequestDispatcher("/frontend/columnarticles/item.jsp").forward(req, res);
	} 
}