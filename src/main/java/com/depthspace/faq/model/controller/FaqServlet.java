package com.depthspace.faq.model.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.depthspace.faq.model.model.*;
import com.depthspace.faq.model.service.*;
import com.depthspace.faq.model.controller.*;

public class FaqServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("serialId");
			
			Integer serialId = null;
			try {
				serialId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("員工編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/faq/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			Integer faqNo = null;
			try {
				faqNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("員工編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/faq/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			FaqService faqSvc = new FaqService();
			FaqVO faqVO = faqSvc.getOneFaq(serialId);
			if (faqVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/faq/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("FaqVO", faqVO); // 資料庫取出的empVO物件,存入req
			String url = "/Faq/listOneFaq.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneFaq.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllFaq.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer serialId = Integer.valueOf(req.getParameter("serialId"));

			/*************************** 2.開始查詢資料 ****************************************/
			FaqService FaqSvc = new FaqService();
			FaqVO FaqVO = FaqSvc.getOneFaq(serialId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("FaqVO", FaqVO); // 資料庫取出的FaqVO物件,存入req
			String url = "/faq/update_Faq_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Faq_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer serialId = Integer.valueOf(req.getParameter("serialId").trim());

			String faqNoString = req.getParameter("faqNo").trim();
			String faqName = req.getParameter("faqName").trim();
			String faqAns = req.getParameter("faqAns").trim();
			List<String> errorMsgs1 = new ArrayList<>();

			Integer faqNo = null;

			// 檢查 faqNo 是否為空
			if (faqNoString == null || faqNoString.length() == 0) {
				errorMsgs1.add("FAQ編號請勿空白");
			} else {
				try {
					// 將字串轉換為整數
					faqNo = Integer.parseInt(faqNoString);
				} catch (NumberFormatException e) {
					errorMsgs1.add("FAQ編號請輸入有效的數字");
				}
			}

			// 檢查 faqName 是否為空
			if (faqName == null || faqName.length() == 0) {
				errorMsgs1.add("職位請勿空白");
			}

			// 檢查 faqAns 是否為空
			if (faqAns == null || faqAns.length() == 0) {
				errorMsgs1.add("答案請勿空白");
			}

			// 如果所有欄位都不為空，可以在這裡進行相應的操作，例如將資料存入資料庫中
			if (errorMsgs1.isEmpty()) {
				// 在這裡執行資料庫操作，使用 faqNo、faqName 和 faqAns 的值
			} else {
				// 如果有錯誤訊息，處理錯誤訊息，例如返回錯誤訊息給前端顯示
			}

//			Double sal = null;
//			try {
//				sal = Double.valueOf(req.getParameter("sal").trim());
//			} catch (NumberFormatException e) {
//				sal = 0.0;
//				errorMsgs.add("薪水請填數字.");
//			}
//
//			Double comm = null;
//			try {
//				comm = Double.valueOf(req.getParameter("comm").trim());
//			} catch (NumberFormatException e) {
//				comm = 0.0;
//				errorMsgs.add("獎金請填數字.");
//			}

			Integer faq = Integer.valueOf(req.getParameter("faq").trim());

			FaqVO faqVO = new FaqVO();
			faqVO.setSerialId(serialId);
			faqVO.setFaqNo(faqNo);
			faqVO.setFaqName(faqName);
			faqVO.setFaqAns(faqAns);

			// Send the use back to the form, if there were errors
			if (!errorMsgs1.isEmpty()) {
				req.setAttribute("faqVO", faqVO); // 含有輸入格式錯誤的faqVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/faq/update_faq_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			FaqService faqSvc = new FaqService();
			faqVO = faqSvc.updateFaq(serialId, faqNo, faqName, faqAns);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("faqVO", faqVO); // 資料庫update成功後,正確的的faqVO物件,存入req
			String url = "/faq/listOnefaq.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFaq.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer serialId = Integer.valueOf(req.getParameter("serialId").trim());

			String faqNoString = req.getParameter("faqNo").trim();
			String faqName = req.getParameter("faqName").trim();
			String faqAns = req.getParameter("faqAns").trim();
			List<String> errorMsgs1 = new ArrayList<>();

			Integer faqNo = null;

			// 檢查 faqNo 是否為空
			if (faqNoString == null || faqNoString.length() == 0) {
				errorMsgs1.add("FAQ編號請勿空白");
			} else {
				try {
					// 將字串轉換為整數
					faqNo = Integer.parseInt(faqNoString);
				} catch (NumberFormatException e) {
					errorMsgs1.add("FAQ編號請輸入有效的數字");
				}
			}

			// 檢查 faqName 是否為空
			if (faqName == null || faqName.length() == 0) {
				errorMsgs1.add("職位請勿空白");
			}

			// 檢查 faqAns 是否為空
			if (faqAns == null || faqAns.length() == 0) {
				errorMsgs1.add("答案請勿空白");
			}

			// 如果所有欄位都不為空，可以在這裡進行相應的操作，例如將資料存入資料庫中
			if (errorMsgs1.isEmpty()) {
				// 在這裡執行資料庫操作，使用 faqNo、faqName 和 faqAns 的值
			} else {
				// 如果有錯誤訊息，處理錯誤訊息，例如返回錯誤訊息給前端顯示
			}

//			Double sal = null;
//			try {
//				sal = Double.valueOf(req.getParameter("sal").trim());
//			} catch (NumberFormatException e) {
//				sal = 0.0;
//				errorMsgs.add("薪水請填數字.");
//			}
//
//			Double comm = null;
//			try {
//				comm = Double.valueOf(req.getParameter("comm").trim());
//			} catch (NumberFormatException e) {
//				comm = 0.0;
//				errorMsgs.add("獎金請填數字.");
//			}

			Integer faq = Integer.valueOf(req.getParameter("faq").trim());

			FaqVO faqVO = new FaqVO();
			faqVO.setSerialId(serialId);
			faqVO.setFaqNo(faqNo);
			faqVO.setFaqName(faqName);
			faqVO.setFaqAns(faqAns);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("faqVO", faqVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/faq/addFaq.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			FaqService faqSvc = new FaqService();
			faqVO = faqSvc.updateFaq(serialId, faqNo, faqName, faqAns);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("faqVO", faqVO); // 資料庫update成功後,正確的的faqVO物件,存入req
			String url = "/faq/listOnefaq.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFaq.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer Faqno = Integer.valueOf(req.getParameter("Faqno"));

			/*************************** 2.開始刪除資料 ***************************************/
			FaqService faqSvc = new FaqService();
			faqSvc.deleteFaq(Faqno);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/faq/listAllFaq.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}