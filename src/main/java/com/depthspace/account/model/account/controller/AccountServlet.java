package com.depthspace.account.model.account.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson2.JSON;
import com.depthspace.account.model.account.AccountVO;
import com.depthspace.account.model.account.service.AccountService;
import com.depthspace.account.model.account.service.AccountServiceImpl;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;

public class AccountServlet extends HttpServlet {
	private AccountService accountService;

	@Override
	public void init() throws ServletException {
		accountService = new AccountServiceImpl();
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
			addAccount(req, resp);
			break;
		case "update":
			updateAccount(req, resp);
			break;
		case "del":
			deleteAccount(req, resp);
			break;
		// 取得ajax分帳表會員列表
		case "list":
			listAccounts(req, resp);
			break;
		// 取得分帳表會員列表
		case "getMemList":
			getMemList(req, resp);
			break;
		case "doMemList":
			doMemList(req, resp);
			break;
		case "addMemList":
			addMemList(req, resp);
			break;
		case "updateMemList":
			updateMemList(req, resp);
			break;
		}
	}

	private void updateMemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer accountId = Integer.parseInt(req.getParameter("accountId"));
		String accountName = req.getParameter("accountName");
		Integer memId = Integer.parseInt(req.getParameter("memId"));
		AccountVO account = new AccountVO(accountId, accountName, memId);
		accountService.update(account);
	}

	private void addMemList(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String accountName = req.getParameter("accountName");
		Integer memId = Integer.parseInt(req.getParameter("memId"));
		System.out.println(accountName);
		AccountVO account = new AccountVO(null, accountName, memId);
		accountService.insert(account);
		req.getRequestDispatcher("/account/index.jsp").forward(req,resp);
	}

	private void doMemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=UTF-8");
		Integer memId;
		try {
			memId = Integer.valueOf(req.getParameter("memId"));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		List<AccountVO> list = accountService.getbyMemId(memId);
		req.setAttribute("list", list);
		req.setAttribute("memId", memId);
		System.out.println(list);
		req.getRequestDispatcher("/account/memlist.jsp").forward(req,resp);
	}

	private void getMemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<AccountVO> accounts = accountService.getAll();
		HashSet<Integer> memId = new HashSet<>();
		for (AccountVO vo : accounts) {
			memId.add(vo.getMemId());
		}
		JSONArray arr = JSONArray.parseArray(JSON.toJSONString(memId));
		resp.setContentType("application/json; charset=UTF-8");
		resp.getWriter().write(arr.toString());
	}

	private void listAccounts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<AccountVO> accounts = accountService.getAll();
		JSONArray arr = JSONArray.parseArray(JSON.toJSONString(accounts));
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(arr.toString());
	}

	private void addAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String accountName = req.getParameter("accountName");
		Integer memId = Integer.parseInt(req.getParameter("memId"));
		AccountVO account = new AccountVO(null, accountName, memId);
		accountService.insert(account);
	}

	private void updateAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Integer accountId = Integer.parseInt(req.getParameter("accountId"));
		String accountName = req.getParameter("accountName");
		Integer memId = Integer.parseInt(req.getParameter("memId"));
		AccountVO account = new AccountVO(accountId, accountName, memId);
		accountService.update(account);
	}

	private void deleteAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Integer accountId = Integer.parseInt(req.getParameter("accountId"));
		accountService.delete(accountId);
	}
	
}
