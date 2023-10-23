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
			
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入FAQ編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/faq/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			Integer serialId = null;
			try {
				serialId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("2員工編號格式不正確");
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
			req.setAttribute("faqVO", faqVO); // 資料庫取出的empVO物件,存入req
			String url = "/faq/listOneFaq.jsp";
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