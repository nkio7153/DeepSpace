package com.depthspace.faq.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.depthspace.faq.model.*;
import com.depthspace.faq.service.*;

public class FaqServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// 設置請求的字符編碼為UTF-8，以處理中文字符
		req.setCharacterEncoding("UTF-8");
		// 從請求中獲取名為'action'的參數，以判斷要執行什麼操作
		String action = req.getParameter("action");

		// 判斷action是否為"getOne_For_Display"，即是否來自select_page.jsp的請求
		if ("getOne_For_Display".equals(action)) {

			// 創建一個用於存儲錯誤信息的List
			List<String> errorMsgs = new LinkedList<String>();
			// 將錯誤信息列表添加到請求範圍，以便在發送錯誤頁面時使用
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			// 從請求中獲取名為'serialId'的參數
			String str = req.getParameter("serialId");
			
			// 檢查serialId參數是否為空或者無效（去除前後空格後長度為0）
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入FAQ編號");
			}
			// 如果有錯誤信息，將用戶重定向回表單頁面
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/faq/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			// 定義一個整型變量serialId，用於存儲從請求參數轉換來的整數值
			Integer serialId = null;
			try {
				serialId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("2員工編號格式不正確");
			}
			// 如果有錯誤信息，再次將用戶重定向回表單頁面
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/faq/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			// 創建FaqService的實例，以便執行查詢操作
			FaqService faqSvc = new FaqService();
			// 使用FaqService來獲取指定的FaqVO物件
			FaqVO faqVO = faqSvc.getOneFaq(serialId);
			// 如果查詢結果為空，則添加錯誤信息
			if (faqVO == null) {
				errorMsgs.add("查無資料");
			}
			// 如果有錯誤信息，再次將用戶重定向回表單頁面
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/faq/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			// 將查詢到的FaqVO物件存入請求範圍
			req.setAttribute("faqVO", faqVO); 
			// 設置成功頁面的URL
			String url = "/faq/listOneFaq.jsp";
			 // 獲取轉發器並轉發到成功頁面
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
			FaqVO faqVO = FaqSvc.getOneFaq(serialId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("faqVO", faqVO); // 資料庫取出的FaqVO物件,存入req
			String url = "/faq/update_faq_input.jsp";
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

			Integer faqNo = Integer.valueOf(req.getParameter("faqNo").trim());
			
			String faqName = req.getParameter("faqName");
			String faqNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,1000}$";
			if (faqName == null || faqName.trim().length() == 0) {
				errorMsgs.add("3員工姓名: 請勿空白");
			} else if(!faqName.trim().matches(faqNameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("3員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			String faqAns = req.getParameter("faqAns");
			String faqAnsReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,1000}$";
			if (faqAns == null || faqAns.trim().length() == 0) {
				errorMsgs.add("4員工姓名: 請勿空白");
			} else if(!faqName.trim().matches(faqAnsReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("4員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			

			

			FaqVO faqVO = new FaqVO();
			faqVO.setSerialId(serialId);
			faqVO.setFaqNo(faqNo);
			faqVO.setFaqName(faqName);
			faqVO.setFaqAns(faqAns);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("faqVO", faqVO); // 含有輸入格式錯誤的faqVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/faq/update_faq_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			System.out.println("111");
			/*************************** 2.開始修改資料 *****************************************/
			FaqService faqSvc = new FaqService();
			faqVO = faqSvc.updateFaq(serialId, faqNo, faqName, faqAns);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("faqVO", faqVO); // 資料庫update成功後,正確的的faqVO物件,存入req
			String url = "/faq/listOneFaq.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFaq.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//			Integer serialId = Integer.valueOf(req.getParameter("serialId").trim());

			Integer faqNo = Integer.valueOf(req.getParameter("faqNo").trim());
			
			String faqName = req.getParameter("faqName");
			String faqNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,1000}$";
			if (faqName == null || faqName.trim().length() == 0) {
				errorMsgs.add("1");
			} else if(!faqName.trim().matches(faqNameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("1員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			String faqAns = req.getParameter("faqAns");
			String faqAnsReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,1000}$";
			if (faqAns == null || faqAns.trim().length() == 0) {
				errorMsgs.add("2");
			} else if(!faqAns.trim().matches(faqAnsReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("2員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

			FaqVO faqVO = new FaqVO();
//			faqVO.setSerialId(serialId);
			faqVO.setFaqNo(faqNo);
			faqVO.setFaqName(faqName);
			faqVO.setFaqAns(faqAns);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("faqVO", faqVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/faq/addFaq.jsp");
				failureView.forward(req, res);
				return ;
			}
			/*************************** 2.開始新增資料 ***************************************/
			FaqService faqSvc = new FaqService();
			faqVO = faqSvc.addFaq(faqNo, faqName, faqAns);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

			String url = "/faq/listAllFaq.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFaq.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer serialId = Integer.valueOf(req.getParameter("serialId"));

			/*************************** 2.開始刪除資料 ***************************************/
			FaqService faqSvc = new FaqService();
			faqSvc.deleteFaq(serialId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/faq/listAllFaq.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}