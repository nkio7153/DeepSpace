package com.depthspace.keywordqa.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.keywordqa.model.KeywordQaVO;
import com.depthspace.keywordqa.service.KeywordQaService;
import com.depthspace.keywordqa.service.KeywordQaServiceImpl;

@WebServlet("/kw/*")
@MultipartConfig
public class KeywordQaServlet extends HttpServlet {
	private KeywordQaService keywordQaService;

	@Override
	public void init() throws ServletException {
		keywordQaService = new KeywordQaServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		switch (action) {
		case "add":
			addKeywordQa(req, resp);
			break;
		case "update":
			updateKeywordQa(req, resp);
			break;
		case "del":
			deleteKeywordQa(req, resp);
			break;
		// 取得ajax分帳表會員列表
		case "list":
			listKeywordQa(req, resp);
			break;
		}
	}
	
	private void addKeywordQa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    KeywordQaVO keywordQaVO = new KeywordQaVO();
	    keywordQaVO.setKwTypes(req.getParameter("kwTypes"));
	    keywordQaVO.setKwAns(req.getParameter("kwAns"));
	    keywordQaService.addKeywordQa(keywordQaVO);
	    resp.sendRedirect("keywordQaList.jsp"); // 重定向到列表頁面
	}

	private void updateKeywordQa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    Integer serialId = Integer.parseInt(req.getParameter("serialId"));
	    String kwTypes = req.getParameter("kwTypes");
	    String kwAns = req.getParameter("kwAns");
	    
	    KeywordQaVO keywordQaVO = keywordQaService.getKeywordQaiBySerialId(serialId);
	    keywordQaVO.setKwTypes(kwTypes);
	    keywordQaVO.setKwAns(kwAns);
	    keywordQaService.updateKeywordQa(keywordQaVO);
	    resp.sendRedirect("keywordQaList.jsp");
	}

	private void deleteKeywordQa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    Integer serialId = Integer.parseInt(req.getParameter("serialId"));
	    keywordQaService.deleteKeywordQa(serialId);
	    resp.sendRedirect("keywordQaList.jsp");
	}

	private void listKeywordQa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    List<KeywordQaVO> list = keywordQaService.getAllKeywordQa();
	    req.setAttribute("keywordQaList", list); // 此行將數據設置為請求屬性
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/keywordQaList.jsp"); // 確保路徑正確
	    dispatcher.forward(req, resp); // 使用forward方法轉發請求
	}


}
