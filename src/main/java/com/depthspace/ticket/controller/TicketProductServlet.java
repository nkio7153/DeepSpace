package com.depthspace.ticket.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import com.depthspace.account.model.account.AccountVO;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.ticket.model.TicketTypesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticket.service.TicketImagesService;
import com.depthspace.ticket.service.TicketService;
import com.depthspace.ticket.service.TicketServiceImpl;
import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;

@WebServlet("/ticketproduct/*")
public class TicketProductServlet extends HttpServlet {

	private TicketService ticketService;
	private TicketImagesService ticketImagesService;

	@Override
	public void init() throws ServletException {
		ticketService = new TicketServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String pathInfo = req.getPathInfo();
		if (pathInfo == null) {
			pathInfo = "";
		}

		switch (pathInfo) {
		case "/": // 票券進入連結
			res.sendRedirect(req.getContextPath() + "/frontend/ticketproduct/info.jsp");
			break;

		case "/list": // 票券列表

			String searchTerm = req.getParameter("searchTerm");
			if (searchTerm == null || searchTerm.trim().isEmpty()) {
				// 如果為空沒有觸發搜尋，則列出全部
				doList(req, res);
				return;
			} else {
				// 否則執行搜尋
				doSearch(req, res);
				return;
			}

		case "/item": // 票券單一頁面
			doProduct(req, res);
			break;

		case "/search": // 票券查找
			doSearch(req, res);
			break;

		default:
			System.out.println("Path not handled: " + pathInfo);
		}
	}

	/************ 左側搜尋欄 **********/
	public void searchList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		List<TicketVO> ticketListAll = ticketService.getAllTickets();
		req.setAttribute("ticketListAll", ticketListAll);

		// 處理票券類型不重複
		Set<TicketTypesVO> uniqueTicketTypes = new HashSet<>();
		for (TicketVO ticket : ticketListAll) {
			uniqueTicketTypes.add(ticket.getTicketType());
		}
		req.setAttribute("uniqueTicketTypes", new ArrayList<>(uniqueTicketTypes));
		// 處理票券區域不重複
		Set<CityVO> uniqueTicketArea = new HashSet<>();
		for (TicketVO ticket : ticketListAll) {
			uniqueTicketArea.add(ticket.getCity());
		}
		req.setAttribute("uniqueTicketArea", new ArrayList<>(uniqueTicketArea));

	}

	/************ 列表 ************/
	private void doList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		// 取得所有票券內容(VO)
		List<TicketVO> ticketList = ticketService.getAllTickets2(currentPage);

		req.setAttribute("resultSet", ticketList); // 票券內容
		req.setAttribute("currentPage", currentPage); // 分頁

		if (req.getSession().getAttribute("ticketPageQty") == null) {
			int ticketPageQty = ticketService.getPageTotal();
			req.getSession().setAttribute("ticketPageQty", ticketPageQty);
		}

		long totalTickets = ticketService.getTotalTickets();
		req.setAttribute("totalTickets", totalTickets); // 總票券數量

		// 存放星星數跟評價數
		Map<Integer, Double> averageStarsMap = new HashMap<>();
		Map<Integer, Integer> totalRatingCountMap = new HashMap<>();

		//計算星星跟評價平均數
		for (TicketVO ticket : ticketList) {
		    Integer ticketId = ticket.getTicketId();
		    Integer totalStars = ticketService.getTotalStars(ticketId);
		    Integer totalRatingCount = ticketService.getTotalRatingCount(ticketId);
		    
		    double averageStars = totalRatingCount > 0 ? (double) totalStars / totalRatingCount : 0;
		    
		    averageStarsMap.put(ticketId, averageStars);
		    totalRatingCountMap.put(ticketId, totalRatingCount);
		}

		req.setAttribute("averageStarsMap", averageStarsMap);
		req.setAttribute("totalRatingCountMap", totalRatingCountMap);

		searchList(req, res);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/ticketproduct/list.jsp");
		dispatcher.forward(req, res);
	}
	
	/************ 搜尋 ************/	
	private void doSearch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");

	    // 創建查詢map
	    Map<String, String[]> parameterMap = req.getParameterMap();
	    // 調用萬用查詢方法
	    List<TicketVO> resultList = ticketService.getTicketsByCompositeQuery(parameterMap);
	    Set<TicketVO> resultSet = new HashSet<>(resultList);
	    
		req.setAttribute("paramValues", parameterMap);
		
	    // 查詢結果的票券數量
	    int searchCount = resultSet.size();
	    req.setAttribute("searchCount", searchCount);

	    // 查詢結果存入
	    req.setAttribute("resultSet", resultSet);
	    
		searchList(req, res);

	    req.getRequestDispatcher("/frontend/ticketproduct/list.jsp").forward(req, res);
	}


	/************ 單一票券頁面 ************/
	private void doProduct(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String ticketIdStr = req.getParameter("ticketId");
		Integer ticketId;
		try {
			ticketId = Integer.valueOf(ticketIdStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}

		TicketVO ticket = ticketService.getTicketById(ticketId);
		if(ticket == null) {
			return;
		}
		
	    // 評價列表
	    List<TicketOrderDetailVO> reviews = ticketService.findTicketOrderDetailsByTicketId(ticketId);

	    // 星星平均數
	    double averageStars = reviews.stream()
                .filter(r -> r.getStars() != null)
                .mapToInt(TicketOrderDetailVO::getStars)
                .average()
                .orElse(0);
	    String formattedAverageStars = String.format("%.1f", averageStars);

	    // 評價單數
	    long totalRatingCount = reviews.stream()
	                                   .filter(r -> r.getStars() != null)
	                                   .count();

	    // 評價內容
	    List<String> reviewContents = reviews.stream()
	                                         .map(TicketOrderDetailVO::getTicketReviews)
	                                         .filter(Objects::nonNull)
	                                         .collect(Collectors.toList());


	    
	    req.setAttribute("ticket", ticket);
	    req.setAttribute("averageStars", averageStars);
	    req.setAttribute("formattedAverageStars", formattedAverageStars);
	    req.setAttribute("totalRatingCount", totalRatingCount);
	    req.setAttribute("reviews", reviews);
	    req.setAttribute("reviewContents", reviewContents);
	    
		req.getRequestDispatcher("/frontend/ticketproduct/item.jsp").forward(req, res);
	}
}
