package com.depthspace.forum.model.forumarticles.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson2.JSON;
import com.depthspace.forum.model.articlescollect.ArticlesCollectVO;
import com.depthspace.forum.model.articlescollect.service.ArticlesCollectService;
import com.depthspace.forum.model.articlescollect.service.ArticlesCollectServiceImpl;
import com.depthspace.forum.model.articleslike.ArticlesLikeVO;
import com.depthspace.forum.model.articleslike.service.ArticlesLikeService;
import com.depthspace.forum.model.articleslike.service.ArticlesLikeServiceImpl;
import com.depthspace.forum.model.articlestype.ArticlesTypeVO;
import com.depthspace.forum.model.articlestype.service.ArticlesTypeService;
import com.depthspace.forum.model.articlestype.service.ArticlesTypeServiceImpl;
import com.depthspace.forum.model.forumarticles.ForumArticlesVO;
import com.depthspace.forum.model.forumarticles.service.ForumArticlesService;
import com.depthspace.forum.model.forumarticles.service.ForumArticlesServiceImpl;
import com.google.gson.Gson;

@MultipartConfig
public class ForumArticlesServlet extends HttpServlet {
	private ForumArticlesService forumArticlesService;
	private ArticlesTypeService articlesTypeService;
	private ArticlesCollectService articlesCollectService;
	private ArticlesLikeService articlesLikeService;

	@Override
	public void init() throws ServletException {
		forumArticlesService = new ForumArticlesServiceImpl();
		articlesTypeService = new ArticlesTypeServiceImpl();
		articlesCollectService = new ArticlesCollectServiceImpl();
		articlesLikeService = new ArticlesLikeServiceImpl();
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
		case "addArticle":
			addArticle(req, resp);
			break;
		case "getArtiTypeList":
			getArtiTypeList(req, resp);
			break;
		case "doArtiTypeList":
			doArtiTypeList(req, resp);
			break;
		case "doArtiCollectList":
			doArtiCollectList(req, resp);
			break;
		case "edit":
			editForumArticles(req, resp);
			break;
		case "delCollect":
			delCollect(req, resp);
			break;
		case "delLike":
			delLike(req, resp);
			break;
		}
	}
	
	//文章刪除連帶刪除按讚紀錄
	private void delLike(HttpServletRequest req, HttpServletResponse resp) {
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
		ArticlesLikeVO alvo = new ArticlesLikeVO(articleId, memId);
		articlesLikeService.delete(alvo);		
	}

	//文章刪除連帶刪除收藏紀錄
	private void delCollect(HttpServletRequest req, HttpServletResponse resp) {
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
		ArticlesCollectVO acvo = new ArticlesCollectVO(articleId, memId);
		articlesCollectService.delete(acvo);
	}

	//取得文章類型並轉發到修改表單
	private void editForumArticles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ArticlesTypeVO> atvo = articlesTypeService.getAll();
	    String json = new Gson().toJson(atvo);
	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    resp.getWriter().write(json);
	}

	// 取得該會員的收藏文章列表
	private void doArtiCollectList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer memId = null;
		HttpSession session = req.getSession(false);
		if (session.getAttribute("memId") != null) {
			memId = (Integer) session.getAttribute("memId");
		}
		List<ForumArticlesVO> list = forumArticlesService.getByArticleIds(memId);
		req.setAttribute("list", list);
		req.setAttribute("memId", memId);
		req.getRequestDispatcher("/forumArticles/collectList.jsp").forward(req, resp);
	}

	// 選擇符合類型文章
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
//		System.out.println(list);
		req.getRequestDispatcher("/forumArticles/articlesTypeList.jsp").forward(req, resp);
	}

	// 取得類型列表
	private void getArtiTypeList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<ArticlesTypeVO> atvo = articlesTypeService.getAll();

		// 將 List 轉換為 JSON 字串
		String json = new Gson().toJson(atvo);

		// 設置響應內容類型為 JSON
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		// 發送 JSON 響應
		resp.getWriter().write(json);
	}

	// 取得所有類型有哪些
	private void addArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ArticlesTypeVO> atvo = articlesTypeService.getAll();
		req.setAttribute("atvo", atvo);
		req.getRequestDispatcher("/forumArticles/add.jsp").forward(req, resp);
	}

	// 取得該會員的文章列表
	private void getMemListForumArticles(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		Integer memId = null;
		HttpSession session = req.getSession(false);
		if (session.getAttribute("memId") != null) {
			memId = (Integer) session.getAttribute("memId");
		}
		List<ForumArticlesVO> list = forumArticlesService.getByMemId(memId);
		req.setAttribute("list", list);
		req.setAttribute("memId", memId);
		req.getRequestDispatcher("/forumArticles/memList.jsp").forward(req, resp);
	}

	// 取得所有文章列表
	private void listForumArticles(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer memId = null;
		HttpSession session = req.getSession(false);
		if (session.getAttribute("memId") != null) {
			memId = (Integer) session.getAttribute("memId");
			List<ArticlesCollectVO> list = articlesCollectService.getByMemId(memId);
			List<ArticlesLikeVO> like = articlesLikeService.getByMemId(memId);
			List<ForumArticlesVO> forum = forumArticlesService.getAll();
			// 創建一個包含三個列表的 JSON 物件
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("articlesCollectList", list); // 第一個收藏列表
			jsonObject.put("articlesLikeList", like); // 第二個按讚列表
			jsonObject.put("forumArticlesList", forum); // 第三個全部列表
			

			// 設置響應內容類型
			resp.setContentType("application/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();

			// 將 JSON 物件轉換為字串並發送給前端
			out.print(jsonObject.toString());
		} else {
			List<ForumArticlesVO> forum = forumArticlesService.getAll();
			JSONArray arr = JSONArray.parseArray(JSON.toJSONString(forum));
			resp.setContentType("application/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(arr.toString());
		}
	}

	// 刪除文章
	private void deleteForumArticles(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Integer articleId = Integer.parseInt(req.getParameter("articleId"));
		forumArticlesService.delete(articleId);
		delCollect(req,resp);
		delLike(req,resp);
	}

	// 修改文章
	private void updateForumArticles(HttpServletRequest req, HttpServletResponse resp)
	        throws IOException, ServletException {
	    System.out.println("執行Update方法");

	    try {
	        Integer articleId = parseIntegerParameter(req.getParameter("articleId"));

	        // 從數據庫獲取現有的文章
	        ForumArticlesVO forumArticle = forumArticlesService.getByArticleId(articleId);


	        // 更新文章的其他欄位
	        forumArticle.setMemId(parseIntegerParameter(req.getParameter("memId")));
	        forumArticle.setMsgId(parseIntegerParameter(req.getParameter("msgId")));
	        forumArticle.setArtiTypeId(parseIntegerParameter(req.getParameter("artiTypeId")));
	        forumArticle.setArtiTitle(req.getParameter("artiTitle"));
	        forumArticle.setArtiTime(parseTimestamp(req.getParameter("artiTime")));
	        forumArticle.setArtiText(req.getParameter("artiText"));
	        forumArticle.setArtiLk(parseIntegerParameter(req.getParameter("artiLk")));

	        // 處理圖片更新
	        Part artiImgStr2 = req.getPart("artiImgStr");
	        if (artiImgStr2 != null && artiImgStr2.getSize() > 0) {
	            System.out.println("進到新圖片");
	            InputStream inputStream = artiImgStr2.getInputStream();
	            byte[] artiImg = inputStream.readAllBytes();
	            forumArticle.setArtiImg(artiImg); // 設置新圖片
	            inputStream.close();
	        } else {
	            System.out.println("保持舊圖片");
	        }

	        // 執行更新操作
	        forumArticlesService.update(forumArticle);

	        // 重新獲取並顯示文章列表
	        getMemListForumArticles(req, resp);
	    } catch (NumberFormatException | IOException | ServletException e) {
	        e.printStackTrace();
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + e.getMessage());
	    }
	}


	// 新增文章
	private void addForumArticles(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		byte[] artiImg = null;
		// 獲取預設圖片的實際路徑
		String defaultImagePath = req.getServletContext().getRealPath("/indexpage/images/nopic.jpg");
		try {
			Integer memId = null;
			HttpSession session = req.getSession(false);
			if (session.getAttribute("memId") != null) {
				memId = (Integer) session.getAttribute("memId");
			}
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
			}else {
				  // 使用者沒有上傳圖片，使用預設圖片
			    InputStream inputStream = new FileInputStream(defaultImagePath);
			    artiImg = inputStream.readAllBytes();
			    inputStream.close();
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
