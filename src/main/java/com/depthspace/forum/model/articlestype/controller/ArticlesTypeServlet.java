package com.depthspace.forum.model.articlestype.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson2.JSON;
import com.depthspace.forum.model.articlestype.ArticlesTypeVO;
import com.depthspace.forum.model.articlestype.service.ArticlesTypeService;
import com.depthspace.forum.model.articlestype.service.ArticlesTypeServiceImpl;
@WebServlet("/type/*")
public class ArticlesTypeServlet extends HttpServlet{
	private ArticlesTypeService articlesTypeService;

	@Override
	public void init() throws ServletException {
		articlesTypeService = new ArticlesTypeServiceImpl();
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
			addArticlesType(req, resp);
			break;
		case "del":
			deleteArticlesType(req, resp);
			break;
		case "list":
			listArticlesType(req, resp);
			break;
		}
	}

	private void listArticlesType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<ArticlesTypeVO> atvo = articlesTypeService.getAll();
		JSONArray arr = JSONArray.parseArray(JSON.toJSONString(atvo));
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		System.out.println(arr.toString());
		out.print(arr.toString());
	}

	private void deleteArticlesType(HttpServletRequest req, HttpServletResponse resp) {
		Integer artiTypeId = Integer.parseInt(req.getParameter("artiTypeId"));
		articlesTypeService.delete(artiTypeId);
	}

	private void addArticlesType(HttpServletRequest req, HttpServletResponse resp) {
		String artiTypeText = req.getParameter("artiTypeText");
		ArticlesTypeVO articlesType = new ArticlesTypeVO(null,artiTypeText);
		articlesTypeService.insert(articlesType);
	}
	
}
