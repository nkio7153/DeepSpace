package com.depthspace.forum.model.forumarticles.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson2.JSON;
import com.depthspace.forum.model.articlestype.ArticlesTypeVO;
import com.depthspace.forum.model.articlestype.service.ArticlesTypeService;
import com.depthspace.forum.model.articlestype.service.ArticlesTypeServiceImpl;
import com.depthspace.forum.model.forumarticles.ForumArticlesVO;
import com.depthspace.forum.model.forumarticles.service.ForumArticlesService;
import com.depthspace.forum.model.forumarticles.service.ForumArticlesServiceImpl;

@MultipartConfig
public class ForumArticlesServlet extends HttpServlet {
	private ForumArticlesService forumArticlesService;
	private ArticlesTypeService articlesTypeService;

	@Override
	public void init() throws ServletException {
		forumArticlesService = new ForumArticlesServiceImpl();
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
			addForumArticles(req, resp);
			break;
		case "update":
			updateForumArticles(req, resp);
			break;
		case "del":
			deleteForumArticles(req, resp);
			break;
		case "list":
			listForumArticles(req, resp);
			break;
		case "getmemlist":
			getMemListForumArticles(req, resp);
			break;
		case "domemlist":
			doMemListForumArticles(req, resp);
			break;
		case "addArticle":
			addArticle(req, resp);
			break;
		case "getArtiTypeList":
			getArtiTypeList(req, resp);
			break;
		case "doArtiTypeList":
			doArtiTypeList(req, resp);
			break;
		}
	}

	private void doArtiTypeList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=UTF-8");
		Integer artiTypeId;
		try {
			artiTypeId = Integer.valueOf(req.getParameter("artiTypeId"));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		List<ForumArticlesVO> list = forumArticlesService.getByArtiTypeId(artiTypeId);
		req.setAttribute("list", list);
		req.setAttribute("artiTypeId", artiTypeId);
		System.out.println(list);
		req.getRequestDispatcher("/forumArticles/articlesTypeList.jsp").forward(req, resp);	
	}

	private void getArtiTypeList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<ForumArticlesVO> forum = forumArticlesService.getAll();
		HashSet<Integer> artiTypeId = new HashSet<>();
		for (ForumArticlesVO vo : forum) {
			artiTypeId.add(vo.getArtiTypeId());
		}
		JSONArray arr = JSONArray.parseArray(JSON.toJSONString(artiTypeId));
		resp.setContentType("application/json; charset=UTF-8");
		resp.getWriter().write(arr.toString());		
	}

	private void addArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ArticlesTypeVO> atvo = articlesTypeService.getAll();
		req.setAttribute("atvo", atvo);
		req.getRequestDispatcher("/forumArticles/add.jsp").forward(req, resp);
	}

	private void doMemListForumArticles(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json; charset=UTF-8");
		Integer memId;
		try {
			memId = Integer.valueOf(req.getParameter("memId"));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		List<ForumArticlesVO> list = forumArticlesService.getByMemId(memId);
		req.setAttribute("list", list);
		req.setAttribute("memId", memId);
		System.out.println(list);
		req.getRequestDispatcher("/forumArticles/memList.jsp").forward(req, resp);
	}

	private void getMemListForumArticles(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<ForumArticlesVO> forum = forumArticlesService.getAll();
		HashSet<Integer> memId = new HashSet<>();
		for (ForumArticlesVO vo : forum) {
			memId.add(vo.getMemId());
		}
		JSONArray arr = JSONArray.parseArray(JSON.toJSONString(memId));
		resp.setContentType("application/json; charset=UTF-8");
		resp.getWriter().write(arr.toString());
	}

	private void listForumArticles(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<ForumArticlesVO> forum = forumArticlesService.getAll();
		JSONArray arr = JSONArray.parseArray(JSON.toJSONString(forum));
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		System.out.println(arr.toString());
		out.print(arr.toString());
	}

	private void deleteForumArticles(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Integer articleId = Integer.parseInt(req.getParameter("articleId"));
		forumArticlesService.delete(articleId);
	}

	private void updateForumArticles(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		byte[] artiImg = null;
		try {
			Integer articleId = parseIntegerParameter(req.getParameter("articleId"));
			Integer memId = parseIntegerParameter(req.getParameter("memId"));
			Integer msgId = parseIntegerParameter(req.getParameter("msgId"));
			Integer artiTypeId = parseIntegerParameter(req.getParameter("artiTypeId"));
			String artiTitle = req.getParameter("artiTitle");
			String artiTimeStr = req.getParameter("artiTime");
			Timestamp artiTime = parseTimestamp(artiTimeStr);
			String artiText = req.getParameter("artiText");
			Integer artiLk = parseIntegerParameter(req.getParameter("artiLk"));
			Part artiImgStr2 = req.getPart("artiImgStr");
			if (artiImgStr2 != null && artiImgStr2.getSize() > 0) {
				InputStream inputStream = artiImgStr2.getInputStream();
				artiImg = inputStream.readAllBytes();
				inputStream.close();
			} else {
				ForumArticlesVO forumArticle = forumArticlesService.getByArticleId(articleId);
				if (forumArticle != null) {
				    artiImg = forumArticle.getArtiImg(); 
				}
			}
			ForumArticlesVO forum = new ForumArticlesVO(articleId, memId, msgId, artiTypeId, artiTitle, artiTime,
					artiText, artiLk, artiImg);
			forumArticlesService.update(forum);
			resp.sendRedirect(req.getContextPath() + "/forumArticles/list.jsp");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + e.getMessage());
		}
	}

	private void addForumArticles(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		byte[] artiImg = null;
		try {
			Integer memId = parseIntegerParameter(req.getParameter("memId"));
			Integer msgId = parseIntegerParameter(req.getParameter("msgId"));
			Integer artiTypeId = parseIntegerParameter(req.getParameter("artiTypeId"));
			String artiTitle = req.getParameter("artiTitle");
			String artiTimeStr = req.getParameter("artiTime");
			Timestamp artiTime = parseTimestamp(artiTimeStr);
			String artiText = req.getParameter("artiText");
			Integer artiLk = parseIntegerParameter(req.getParameter("artiLk"));
			Part artiImgStr = req.getPart("artiImgStr");
			if (artiImgStr != null && artiImgStr.getSize() > 0) {
				InputStream inputStream = artiImgStr.getInputStream();
				artiImg = inputStream.readAllBytes();
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				int read;
//				byte[] data = new byte[1024];
//				while ((read = inputStream.read(data, 0, data.length)) != -1) {
//					baos.write(data, 0, read);
//				}
//				baos.flush();
//				artiImg = baos.toByteArray();
				inputStream.close();
//				baos.close();
			}

			ForumArticlesVO forum = new ForumArticlesVO(null, memId, msgId, artiTypeId, artiTitle, artiTime, artiText,
					artiLk, artiImg);
			forumArticlesService.insert(forum);
			resp.sendRedirect(req.getContextPath() + "/forumArticles/list.jsp");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + e.getMessage());
		}
	}

	private Integer parseIntegerParameter(String param) {
		if (param != null && !param.trim().isEmpty()) {
			return Integer.parseInt(param);
		} else {
			return 0;
		}
	}

	private Timestamp parseTimestamp(String timestampStr) {
		if (timestampStr != null && !timestampStr.trim().isEmpty()) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				java.util.Date parsedDate = dateFormat.parse(timestampStr);
				return new Timestamp(parsedDate.getTime());
			} catch (ParseException e) {
				throw new IllegalArgumentException("Invalid timestamp format: " + timestampStr);
			}
		} else {
			return new Timestamp(System.currentTimeMillis());
		}
	}

}
