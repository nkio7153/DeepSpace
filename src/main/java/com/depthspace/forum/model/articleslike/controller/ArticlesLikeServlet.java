package com.depthspace.forum.model.articleslike.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.depthspace.forum.model.articleslike.ArticlesLikeVO;
import com.depthspace.forum.model.articleslike.service.ArticlesLikeService;
import com.depthspace.forum.model.articleslike.service.ArticlesLikeServiceImpl;
import com.depthspace.forum.model.forumarticles.service.ForumArticlesService;
import com.depthspace.forum.model.forumarticles.service.ForumArticlesServiceImpl;

@WebServlet("/like/*")
public class ArticlesLikeServlet extends HttpServlet {
	private ArticlesLikeService articlesLikeService;
	private ForumArticlesService forumArticlesService;

	@Override
	public void init() throws ServletException {
		articlesLikeService = new ArticlesLikeServiceImpl();
		forumArticlesService = new ForumArticlesServiceImpl(); // 初始化 ForumArticlesService
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
			addArticlesLike(req, resp);
			break;
		}
	}

	private void addArticlesLike(HttpServletRequest req, HttpServletResponse resp) {
		Integer articleId = null;
		try {
			articleId = Integer.valueOf(req.getParameter("articleId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer memId = null;
		HttpSession session = req.getSession(false);
		if (session.getAttribute("memId") != null) {
			memId = (Integer) session.getAttribute("memId");
		}
		System.out.println("memId" + memId);
		System.out.println("articleId" + articleId);
		// 检查是否已按讚
		boolean islike = articlesLikeService.islike(articleId, memId);
		ArticlesLikeVO alvo = new ArticlesLikeVO(articleId, memId);
		if (islike) {
			articlesLikeService.delete(alvo);
			forumArticlesService.unlikeArticle(articleId);
			setJsonResponse(resp, "取消按讚");
		} else {
			articlesLikeService.insert(alvo);
			forumArticlesService.likeArticle(articleId);
			setJsonResponse(resp, "添加按讚");
		}

	}

	private void setJsonResponse(HttpServletResponse resp, String message) {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = resp.getWriter();
			out.print("{\"message\":\"" + message + "\"}");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
