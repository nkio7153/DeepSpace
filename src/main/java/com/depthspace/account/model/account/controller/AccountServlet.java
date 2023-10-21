package com.depthspace.account.model.account.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
		case "list":
			listAccounts(req, resp);
			break;
		}
	}

	private void listAccounts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<AccountVO> accounts = accountService.getAll();
//	        req.setAttribute("accounts", accounts);
//	        req.getRequestDispatcher("/account/list.jsp").forward(req, resp);
		JSONArray arr = JSONArray.parseArray(JSON.toJSONString(accounts));
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(arr.toString());
	}
//
//	private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getRequestDispatcher("/account/add.jsp").forward(req, resp);
//	}
//
//	private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Integer accountId = Integer.parseInt(req.getParameter("accountId"));
//		AccountVO account = accountService.findByPrimaryKey(accountId);
//		req.setAttribute("account", account);
//		req.getRequestDispatcher("/account/edit.jsp").forward(req, resp);
//	}

	private void addAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String accountName = req.getParameter("accountName");
		Integer memId = Integer.parseInt(req.getParameter("memId"));
		System.out.println(accountName);

		AccountVO account = new AccountVO(null, accountName, memId);
		accountService.insert(account);

	}

	private void updateAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {

//		Integer accountId = Integer.parseInt(req.getParameter("accountId"));
//		String accountName = req.getParameter("accountName");
//		Integer memId = Integer.parseInt(req.getParameter("memId"));
//
//		AccountVO account = new AccountVO(null, accountName, memId);
//		accountService.update(account);
		System.out.println("Update Account");
		 String accountIdStr = req.getParameter("accountId");
		    if (accountIdStr != null && !accountIdStr.isEmpty()) {
		        Integer accountId = Integer.parseInt(accountIdStr);
		        String accountName = req.getParameter("accountName");
		        Integer memId = Integer.parseInt(req.getParameter("memId"));
		        
		        AccountVO account = new AccountVO(accountId, accountName, memId);
		        System.out.println(account.toString());
		        accountService.update(account);
		    } else {
		        // 处理无效的 accountId，可能需要返回错误或进行其他适当的操作
		    }

	}

	private void deleteAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println(req.getParameter("accountId"));
		Integer accountId = Integer.parseInt(req.getParameter("accountId"));
		accountService.delete(accountId);
	}
}
