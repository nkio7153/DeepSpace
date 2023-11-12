package com.depthspace.ticketcollection.cotroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticket.service.TicketService;
import com.depthspace.ticket.service.TicketServiceImpl;
import com.depthspace.ticketcollection.model.TicketCollectionVO;
import com.depthspace.ticketcollection.service.TicketCollectionService;
import com.depthspace.ticketcollection.service.TicketCollectionServiceImpl;
import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/ticketcollection/*")
public class TicketCollectionServlet extends HttpServlet {

	private TicketCollectionService ticketCollectionService;
	private TicketService ticketService;

	public void init() throws ServletException {
		ticketCollectionService = new TicketCollectionServiceImpl();
		ticketService = new TicketServiceImpl();

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
		case "/":
			res.sendRedirect(req.getContextPath() + "/frontend/ticketcollection/info.jsp");
			break;
		case "/login": // 登入
			login(req, res);
			break;
		case "/list": // 列表
			doList(req, res);
			break;
		case "/toggleFavorite": // 切換收藏狀態
			toggleFavorite(req, res);
			break;
		case "/del": // 移除
			doDel(req, res);
			break;
		default:
			System.out.println("Path not handled:" + pathInfo);
		}
	}

	/*************** 會員登入 *****************/
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TicketCollectionVO> list = ticketCollectionService.getAll();
		HashSet<Integer> uniqueMemIds = new HashSet<>();

		for (TicketCollectionVO vo : list) {
			uniqueMemIds.add(vo.getMemId());
		}
		req.setAttribute("uniqueMemIds", uniqueMemIds);
		req.getRequestDispatcher("/frontend/ticketcollection/login.jsp").forward(req, resp);
	}

	/*************** 收藏列表 *****************/
	private void doList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
	    Integer memId = (Integer) session.getAttribute("memId");
	    
//	    if (memId == null) {
//	        res.sendRedirect("login.jsp");  //登入頁面***假資料
//	        return;
//	    }
//	    memId = (Integer)session.getAttribute("memId");
		System.out.println(memId);
		
		
//		Integer memId = 2; // ****測試寫死****
		// 取得會員收藏
		List<TicketCollectionVO> ticketList = ticketCollectionService.getOne(memId);
		req.setAttribute("resultSet", ticketList);
		// 存放於TicketVO
		List<TicketVO> tickets = new ArrayList<>();

		// 存放星星數跟評價數、訂單數
		Map<Integer, Double> averageStarsMap = new HashMap<>();
		Map<Integer, Integer> totalRatingCountMap = new HashMap<>();
		Map<Integer, Integer> ticketOrderCountMap = new HashMap<>();

		// 計算星星跟評價平均數
		for (TicketCollectionVO collection : ticketList) {
			TicketVO ticket = collection.getTicketVO(); // 要先放入ticketVO，下面才能根據此內容執行
			tickets.add(ticket); // 加到列表

			Integer ticketId = ticket.getTicketId();
			Integer totalStars = ticketService.getTotalStars(ticketId);
			Integer totalRatingCount = ticketService.getTotalRatingCount(ticketId);

			double averageStars = totalRatingCount > 0 ? (double) totalStars / totalRatingCount : 0;
			String formattedAverageStars = String.format("%.1f", averageStars);

			averageStarsMap.put(ticketId, Double.parseDouble(formattedAverageStars));
			totalRatingCountMap.put(ticketId, totalRatingCount);

			// 查詢訂單數
			List<TicketOrderDetailVO> ticketOrderDetails = ticketService.findTicketOrderDetailsByTicketId(ticketId);
			int orderCount = ticketOrderDetails.size(); // 訂單數為票券訂單明細列表的大小
			ticketOrderCountMap.put(ticketId, orderCount); // 將票券ID和對應的訂單數存放到map中

		}

		req.setAttribute("ticket", tickets);
		req.setAttribute("averageStarsMap", averageStarsMap);
		req.setAttribute("totalRatingCountMap", totalRatingCountMap);
		req.setAttribute("ticketOrderCountMap", ticketOrderCountMap);

		// 會員收藏票券數
		long totalTickets = ticketCollectionService.getTotalTickets(memId);
		req.setAttribute("totalTickets", totalTickets);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/ticketcollection/list.jsp");
		dispatcher.forward(req, res);
	}

	/*************** 加入 *****************/
	private void toggleFavorite(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
	    Integer memId = (Integer) session.getAttribute("memId");
	    
//	    if (memId == null) {
//	        res.sendRedirect("login.jsp");  //登入頁面***假資料
//	        return;
//	    }
	    memId = (Integer)session.getAttribute("memId");
		System.out.println(memId);
		
		String ticketIdStr = req.getParameter("ticketId");
		Integer ticketId = Integer.parseInt(ticketIdStr);

			TicketCollectionVO ticketCollection = ticketCollectionService.getOne(memId, ticketId);
			boolean isFavorite;

			if (ticketCollection != null) {
				// 存在於收藏中，則移除
				ticketCollectionService.deleteByCom(memId, ticketId);
				isFavorite = false;
			} else {
				// 不在收藏中，則加入
				ticketCollection = new TicketCollectionVO();
				ticketCollection.setTicketId(ticketId);
				ticketCollection.setMemId(memId);

				ticketCollectionService.add(ticketCollection);
				isFavorite = true;
			}

			// 返回更新後的收藏狀態
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.print("{ \"isFavorite\": " + isFavorite + " }");
			out.flush();
//		} else {
//			// memId 未提供，返回錯誤狀態碼(應返回登入頁面)
//			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
//		}
	}

	/*************** 刪除一個 *****************/
	protected void doDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
	    Integer memId = (Integer) session.getAttribute("memId");
	    
	    memId = (Integer)session.getAttribute("memId");
		System.out.println(memId);
		
		System.out.println(memId);
//		Integer memId = 2; // ****測試寫死****

		String ticketIdStr = req.getParameter("ticketId");
		Integer ticketId = Integer.parseInt(ticketIdStr);

		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();

		JSONObject responseJson = new JSONObject();
		try {
			ticketCollectionService.deleteByCom(memId, ticketId);
			responseJson.put("success", true);
			responseJson.put("message", "收藏已成功移除。");
		} catch (Exception e) {
			responseJson.put("success", false);
			responseJson.put("message", "移除收藏錯誤: " + e.getMessage());
		}

		// 發送回前端的 JSON 響應
		out.print(responseJson.toString());
		out.flush();
		out.close();
	}

}