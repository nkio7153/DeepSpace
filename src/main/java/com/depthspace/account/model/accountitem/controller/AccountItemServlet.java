package com.depthspace.account.model.accountitem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson2.JSON;
import com.depthspace.account.model.accountitem.AccountItemVO;
import com.depthspace.account.model.accountitem.service.AccountItemService;
import com.depthspace.account.model.accountitem.service.AccountItemServiceImpl;

public class AccountItemServlet extends HttpServlet {
	private AccountItemService accountItemService;

	@Override
	public void init() throws ServletException {
		accountItemService = new AccountItemServiceImpl();
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
			addAccountItem(req, resp);
			break;
		case "update":
			updateAccountItem(req, resp);
			break;
		case "del":
			deleteAccountItem(req, resp);
			break;
		case "list":
			listAccountItem(req, resp);
			break;
		case "select":
			selectAccountItem(req, resp);
			break;
		}
	}

	private void selectAccountItem(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}

	private void listAccountItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		List<AccountItemVO> accountitem = accountItemService.getAll();
		JSONArray arr = JSONArray.parseArray(JSON.toJSONString(accountitem));
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(arr.toString());
	}

	private void deleteAccountItem(HttpServletRequest req, HttpServletResponse resp) {
		Integer accountItemId = Integer.parseInt(req.getParameter("accountItemId"));
		accountItemService.delete(accountItemId);
	}

	private void updateAccountItem(HttpServletRequest req, HttpServletResponse resp) {
		String accountItem = req.getParameter("accountItem");
		Date date = Date.valueOf(req.getParameter("date"));
		Integer consumeAccount = Integer.parseInt(req.getParameter("consumeAccount"));
		AccountItemVO accountitem = new AccountItemVO(null, accountItem, date, consumeAccount);
		accountItemService.insert(accountitem);
	}

	private void addAccountItem(HttpServletRequest req, HttpServletResponse resp) {
		String accountItem = req.getParameter("accountItem");
		Date date = Date.valueOf(req.getParameter("date"));
		Integer consumeAccount = Integer.parseInt(req.getParameter("consumeAccount"));
		AccountItemVO accountitem = new AccountItemVO(null, accountItem, date, consumeAccount);
		accountItemService.insert(accountitem);
	}
}
