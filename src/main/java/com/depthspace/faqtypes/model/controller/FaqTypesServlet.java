package com.depthspace.faqtypes.model.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.depthspace.faqtypes.model.model.*;
import com.depthspace.faqtypes.model.service.*;
import com.depthspace.faqtypes.model.controller.*;

public class FaqTypesServlet extends HttpServlet{

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
			String str = req.getParameter("faqNo");
			
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入常見問題類型編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/faqtypes/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			Integer faqNo = null;
			try {
				faqNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("常見問題類型編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/faqtypes/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			FaqTypesService faqSvc = new FaqTypesService();
			FaqTypesVO faqTypesVO = faqSvc.getOneFaqTypes(faqNo);
			if (faqTypesVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/faqtypes/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("faqTypesVO", faqTypesVO); // 資料庫取出的empVO物件,存入req
			String url = "/faqtypes/listOneFaq.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneFaq.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllFaq.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer faqNo = Integer.valueOf(req.getParameter("faqNo"));

			/*************************** 2.開始查詢資料 ****************************************/
			FaqTypesService FaqSvc = new FaqTypesService();
			FaqTypesVO faqTypesVO = FaqSvc.getOneFaqTypes(faqNo);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("faqTypesVO", faqTypesVO); // 資料庫取出的FaqVO物件,存入req
			String url = "/faqtypes/update_faq_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Faq_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer faqNo = Integer.valueOf(req.getParameter("faqNo").trim());

			
			String qTypes = req.getParameter("qTypes");
			String qTypesReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,1000}$";
			if (qTypes == null || qTypes.trim().length() == 0) {
				errorMsgs.add("3員工姓名: 請勿空白");
			} else if(!qTypes.trim().matches(qTypesReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("3員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			FaqTypesVO faqTypesVO = new FaqTypesVO();
			faqTypesVO.setFaqNo(faqNo);
			faqTypesVO.setQTypes(qTypes);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("faqTypesVO", faqTypesVO); // 含有輸入格式錯誤的faqVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/faqtypes/update_faq_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			System.out.println("111");
			/*************************** 2.開始修改資料 *****************************************/
			FaqTypesService faqSvc = new FaqTypesService();
			faqTypesVO = faqSvc.updateFaqTypes(faqNo, qTypes);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("faqTypesVO", faqTypesVO); // 資料庫update成功後,正確的的faqVO物件,存入req
			String url = "/faqtypes/listOneFaq.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFaq.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String qTypes = req.getParameter("qTypes");
			String qTypesReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,1000}$";
			if (qTypes == null || qTypes.trim().length() == 0) {
				errorMsgs.add("1");
			} else if(!qTypes.trim().matches(qTypesReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("1員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			FaqTypesVO faqTypesVO = new FaqTypesVO();
			faqTypesVO.setQTypes(qTypes);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("faqTypesVO", faqTypesVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/faqtypes/addFaq.jsp");
				failureView.forward(req, res);
				return ;
			}
			/*************************** 2.開始新增資料 ***************************************/
			FaqTypesService faqSvc = new FaqTypesService();
			faqTypesVO = faqSvc.addFaqTypes(qTypes);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

			String url = "/faqtypes/listAllFaq.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFaq.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer faqNo = Integer.valueOf(req.getParameter("faqNo"));

			/*************************** 2.開始刪除資料 ***************************************/
			FaqTypesService faqSvc = new FaqTypesService();
			faqSvc.deleteFaqTypes(faqNo);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/faqtypes/listAllFaq.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
