package com.depthspace.forum.model.articlereport.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.depthspace.forum.model.articlereport.ArticleReportVO;
import com.depthspace.forum.model.articlereport.sevice.ArticleReportService;
import com.depthspace.forum.model.articlereport.sevice.ArticleReportServiceImpl;

@WebServlet("/report/*")
public class ArticleReportServlet extends HttpServlet {
	private ArticleReportService articleReportService;

	@Override
	public void init() throws ServletException {
		articleReportService = new ArticleReportServiceImpl();
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
			addReport(req, resp);
			break;
		case "update":
			updateReport(req, resp);
			break;
		}

	}

	// 修改檢舉資料
	private void updateReport(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}

	// 新增檢舉資料
	private void addReport(HttpServletRequest req, HttpServletResponse resp) {
		Integer articleId = parseIntegerParameter(req.getParameter("articleId"));
		Integer reportId = null;
		HttpSession session = req.getSession(false);
		if (session.getAttribute("memId") != null) {
			reportId = (Integer) session.getAttribute("memId");
		}
		String reportContent = req.getParameter("reportContent");
		String artiTimeStr = req.getParameter("reportTime");
		Timestamp reportTime = parseTimestamp(artiTimeStr);
		Byte reportStatus = Byte.valueOf(req.getParameter("reportStatus"));

		ArticleReportVO report = new ArticleReportVO(null, articleId, reportId, null, reportContent, reportTime,
				reportStatus);
		articleReportService.insert(report);
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