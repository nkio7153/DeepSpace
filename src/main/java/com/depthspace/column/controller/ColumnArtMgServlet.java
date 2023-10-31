package com.depthspace.column.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.attractions.model.CityVO;
import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.column.service.ColumnArticlesService;
import com.depthspace.column.service.ColumnArticlesServiceImpl;
import com.depthspace.ticket.model.TicketTypesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticket.service.TicketImagesService;
import com.depthspace.ticket.service.TicketImagesServiceImpl;
import com.depthspace.ticket.service.TicketService;
import com.depthspace.ticket.service.TicketServiceImpl;
import com.depthspace.utils.HibernateUtil;

@WebServlet("/columnmg/*")
public class ColumnArtMgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ColumnArticlesService columnArticlesService;
	Session session;

	public void init() throws ServletException {
		columnArticlesService = new ColumnArticlesServiceImpl();

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
//		ticketImagesService = new TicketImagesServiceImpl(session); // 初始化ticketImagesService
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
		case "/": // 專欄管理頁面
			res.sendRedirect(req.getContextPath() + "/backend/column/mg.jsp");
			break;
		case "/list": // 專欄總列表
			doList(req, res);
			break;
//		case "/add": // 專欄新增
//			doAdd(req, res);
//			break;
//		case "/edit": // 專欄修改
//			doEdit(req, res);
//			break;
//		case "/find": // 專欄查找
//			doSearch(req, res);
//			break;
//		case "/del": // 票券刪除
//			doDel(req, res);
//			break;

		default:
			System.out.println("Path not handled: " + pathInfo);
		}

	}

	/************ 專欄列表 ************/
	private void doList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 取得所有票券內容 未分頁取這兩行即可
		List<ColumnArticlesVO> columnListAll = columnArticlesService.getAllArti();
		req.setAttribute("columnListAll", columnListAll);

		// 取得所有票券內容(VO) "分頁"
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<ColumnArticlesVO> columnList = columnArticlesService.getAllArti2(currentPage);

		if (req.getSession().getAttribute("ticketPageQty") == null) {
			int pageQty = columnArticlesService.getPageTotal();
			req.getSession().setAttribute("PageQty", pageQty);
		}
		req.setAttribute("columnList", columnList);
		req.setAttribute("currentPage", currentPage);

//		// 處理票券類型不重複
//		Set<TicketTypesVO> uniqueTicketTypes = new HashSet<>();
//		for (TicketVO ticket : ticketListAll) {
//			uniqueTicketTypes.add(ticket.getTicketType());
//		}
//		req.setAttribute("uniqueTicketTypes", new ArrayList<>(uniqueTicketTypes));

		RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/column/list.jsp");
		dispatcher.forward(req, res);
	}

}
