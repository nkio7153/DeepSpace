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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.depthspace.attractions.model.CityVO;
import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.ticket.model.TicketTypesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticket.service.TicketImagesService;
import com.depthspace.ticket.service.TicketService;
import com.depthspace.ticket.service.TicketServiceImpl;
import com.depthspace.ticketcollection.model.TicketCollectionVO;
import com.depthspace.ticketcollection.service.TicketCollectionService;
import com.depthspace.ticketcollection.service.TicketCollectionServiceImpl;
import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.utils.JedisUtil;

@WebServlet("/ticketproduct/*")
public class TicketProductServlet extends HttpServlet {
	
	private static final int PAGE_MAX_RESULT = 8;
	private TicketService ticketService;
	private TicketImagesService ticketImagesService;
	private TicketCollectionService ticketCollectionService;

	@Override
	public void init() throws ServletException {
		ticketService = new TicketServiceImpl();
		ticketCollectionService = new TicketCollectionServiceImpl();
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
				doList(req, res);
			break;
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
		List<TicketVO> filteredList = ticketListAll.stream()
                .filter(ticketVO -> ticketVO.getStatus() != 0) 
                .collect(Collectors.toList());
		req.setAttribute("ticketListAll", filteredList);

		// 處理票券類型不重複
		Set<TicketTypesVO> uniqueTicketTypes = new HashSet<>();
		for (TicketVO ticket : filteredList) {
			uniqueTicketTypes.add(ticket.getTicketType());
		}
		req.setAttribute("uniqueTicketTypes", new ArrayList<>(uniqueTicketTypes));
		// 處理票券區域不重複
		Set<CityVO> uniqueTicketArea = new HashSet<>();
		for (TicketVO ticket : filteredList) {
			uniqueTicketArea.add(ticket.getCity());
		}
		req.setAttribute("uniqueTicketArea", new ArrayList<>(uniqueTicketArea));

	}
	
	/************ 右側列表評價 ************/
	private void reviewsList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    // 取得所有票券內容(VO)
	    List<TicketVO> ticketList = ticketService.getAllTickets();

	    // 存放星星數跟評價數
	    Map<Integer, Double> averageStarsMap = new HashMap<>();
	    Map<Integer, Integer> totalRatingCountMap = new HashMap<>();

	    // Redis連線
	    JedisPool pool = JedisUtil.getJedisPool();
	    Jedis jedis = null;
	    boolean isRedisEnabled = false;

	    try {
	        jedis = pool.getResource();
	        jedis.select(3);
	        isRedisEnabled = true;
	    } catch (Exception e) {
	        System.out.println("Redis連接失敗：" + e.getMessage());
	    }
	    
	    // 計算星星數和評價數
//	    final double UPDATE_PROBABILITY = 0.1; 
	    for (TicketVO ticket : ticketList) {
	        Integer ticketId = ticket.getTicketId();
	        double averageStars;
	        int totalRatingCount;

	        if (isRedisEnabled) {
	            String averageStarsCache = jedis.get("ticket:" + ticketId + ":averageStars");
	            String totalRatingCountCache = jedis.get("ticket:" + ticketId + ":totalRatingCount");

//	            if (Math.random() < UPDATE_PROBABILITY || averageStarsCache != null && totalRatingCountCache != null) {
	            if (averageStarsCache != null && totalRatingCountCache != null) {
	            	averageStars = Double.parseDouble(averageStarsCache);
	                totalRatingCount = Integer.parseInt(totalRatingCountCache);
	            } else {
	                // 從資料庫更新到 Redis
	            	averageStars = calculateAverageStars(ticketId);
	            	totalRatingCount = calculateTotalRatingCount(ticketId);

	            	String formattedAverage = String.format("%.1f", averageStars);
	            	jedis.set("ticket:" + ticketId + ":averageStars", formattedAverage);
	            	jedis.set("ticket:" + ticketId + ":totalRatingCount", String.valueOf(totalRatingCount));

	            }
	        } else {
	            // 無成功連線就直接從資料庫取得
	            averageStars = calculateAverageStars(ticketId);
	            totalRatingCount = calculateTotalRatingCount(ticketId);
	        }
	        averageStarsMap.put(ticketId, averageStars);
	        totalRatingCountMap.put(ticketId, totalRatingCount);
	    }
	    if (jedis != null) {
	        jedis.close();
	    }
	    req.setAttribute("averageStarsMap", averageStarsMap);
	    req.setAttribute("totalRatingCountMap", totalRatingCountMap);
	}

	private double calculateAverageStars(Integer ticketId) {
	    Integer totalStars = ticketService.getTotalStars(ticketId);
	    Integer totalRatingCount = ticketService.getTotalRatingCount(ticketId);
	    if (totalRatingCount > 0) {
	        double average = (double) totalStars / totalRatingCount;
	        return Double.parseDouble(String.format("%.1f", average));
	    } else {
	        return 0.0;
	    }
	}


	private int calculateTotalRatingCount(Integer ticketId) {
	    return ticketService.getTotalRatingCount(ticketId);
	}

	/************ 票券列表 ************/
	private void doList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    String page = req.getParameter("page");
	    String sort = req.getParameter("sort");
	    int currentPage = (page != null) ? Integer.parseInt(page) : 1;
	    long total = ticketService.getStatusTotalTickets();

	    List<TicketVO> ticketList = ticketService.getAllTickets2(currentPage);
	    
	    //排序
	    if ("desc".equalsIgnoreCase(sort)) {
	    	ticketList.sort((a1, a2) -> a2.getTicketId().compareTo(a1.getTicketId()));
	    } else if ("asc".equalsIgnoreCase(sort)) {
	    	ticketList.sort(Comparator.comparing(TicketVO::getTicketId));
	    }
	    
	    if (req.getSession().getAttribute("pageQty") == null) {
	        int pageQty = ticketService.getPageTotal();
	        req.getSession().setAttribute("pageQty", pageQty);
	    }

	    searchList(req, res);
	    reviewsList(req, res);
	    req.setAttribute("total", total);
	    req.setAttribute("currentPage", currentPage);
	    req.setAttribute("resultSet", ticketList); 
	    
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/ticketproduct/list.jsp");
	    dispatcher.forward(req, res);
	}

	/************ 搜尋 ************/	
	private void doSearch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	    Map<String, String[]> parameterMap = req.getParameterMap();
	    List<TicketVO> resultList = ticketService.getTicketsByCompositeQuery(parameterMap);
	    Set<TicketVO> resultSet = new HashSet<>(resultList);
		List<TicketVO> filteredList = resultSet.stream()
                 .filter(ticketVO -> ticketVO.getStatus() != 0) 
                 .collect(Collectors.toList());
		
	    //排序
		String sort = req.getParameter("sort");	
	    if ("desc".equalsIgnoreCase(sort)) {
	    	filteredList.sort((a1, a2) -> a2.getTicketId().compareTo(a1.getTicketId()));
	    } else if ("asc".equalsIgnoreCase(sort)) {
	    	filteredList.sort(Comparator.comparing(TicketVO::getTicketId));
	    }
	    
	    // 分頁處理
	    int filtercurrentPage = Integer.parseInt(req.getParameter("page") != null ? req.getParameter("page") : "1");
	    int recordsPerPage = PAGE_MAX_RESULT; // 定義每頁的最大記錄數
	    int totalRecords = filteredList.size();
	    int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
	    int startIndex = (filtercurrentPage - 1) * recordsPerPage;
	    int endIndex = Math.min(startIndex + recordsPerPage, totalRecords);
	    List<TicketVO> pageData = filteredList.subList(startIndex, endIndex);

	    req.setAttribute("resultSet", pageData); 
	    req.setAttribute("searchCount", totalRecords);
	    req.setAttribute("searchSet", filteredList);
	    req.setAttribute("pageQtyA", totalPages);
	    req.setAttribute("currentPage", filtercurrentPage);
	    
	    System.out.println("QQQQQQQQQsearchCount"+ totalRecords+"totalPages"+ totalPages+"filtercurrentPage"+ filtercurrentPage);
	    
	    reviewsList(req, res);
	    req.getRequestDispatcher("/frontend/ticketproduct/search.jsp").forward(req, res);

	}

	
	
	/************ 單一票券頁面 ************/
	private void doProduct(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
	    Integer memId = (Integer) session.getAttribute("memId");
	    
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


	    //判斷收藏與否
	    TicketCollectionVO ticketCollection = ticketCollectionService.getOne(memId, ticketId);
	    boolean isFavorite = ticketCollection != null;
		
	    req.setAttribute("ticket", ticket);
	    req.setAttribute("averageStars", averageStars);
	    req.setAttribute("formattedAverageStars", formattedAverageStars);
	    req.setAttribute("totalRatingCount", totalRatingCount);
	    req.setAttribute("reviews", reviews);
	    req.setAttribute("reviewContents", reviewContents);
		req.setAttribute("isFavorite", isFavorite);
	    
		req.getRequestDispatcher("/frontend/ticketproduct/item.jsp").forward(req, res);
	}
}
