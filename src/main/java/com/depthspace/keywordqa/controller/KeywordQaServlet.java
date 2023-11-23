package com.depthspace.keywordqa.controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.depthspace.faqtypes.model.model.FaqTypesVO;
import com.depthspace.faqtypes.model.service.FaqTypesService;
import com.depthspace.keywordqa.model.KeywordQaVO;
import com.depthspace.keywordqa.service.KeywordQaService;
import com.depthspace.keywordqa.service.KeywordQaService;

public class KeywordQaServlet extends HttpServlet {
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
				errorMsgs.add("請輸入常見問題類型編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/keywordQa/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			Integer serialId = null;
			try {
				serialId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("常見問題類型編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/keywordQa/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			KeywordQaService keywordQaSvc = new KeywordQaService();
			KeywordQaVO keywordQaVO = keywordQaSvc.getOneKeywordQa(serialId);
			if (keywordQaVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/keywordQa/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("keywordQaVO", keywordQaVO);
			String url = "/keywordQa/listOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllFaq.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer serialId = Integer.valueOf(req.getParameter("serialId"));

			/*************************** 2.開始查詢資料 ****************************************/
			KeywordQaService keywordQaSvc = new KeywordQaService();
			KeywordQaVO keywordQaVO = keywordQaSvc.getOneKeywordQa(serialId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("keywordQaVO", keywordQaVO); // 資料庫取出的FaqVO物件,存入req
			String url = "/keywordQa/update.jsp";
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

			
			String kwTypes = req.getParameter("kwTypes");
			String kwTypesReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,1000}$";
			if (kwTypes == null || kwTypes.trim().length() == 0) {
				errorMsgs.add("3員工姓名: 請勿空白");
			} else if(!kwTypes.trim().matches(kwTypesReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("3員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			String kwAns = req.getParameter("kwAns");
			String kwAnsReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,1000}$";
			if (kwAns == null || kwAns.trim().length() == 0) {
				errorMsgs.add("3員工姓名: 請勿空白");
			} else if(!kwAns.trim().matches(kwAnsReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("3員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			
			KeywordQaVO keywordQaVO = new KeywordQaVO();
			keywordQaVO.setSerialId(serialId);
			keywordQaVO.setKwTypes(kwTypes);
			keywordQaVO.setKwAns(kwAns);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("keywordQaVO", keywordQaVO); // 含有輸入格式錯誤的faqVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/keywordQa/update.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			System.out.println("111");
			/*************************** 2.開始修改資料 *****************************************/
			KeywordQaService keywordQaSvc = new KeywordQaService();
			keywordQaVO = keywordQaSvc.updateKeywordQa(serialId, kwTypes, kwAns);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("keywordQaVO", keywordQaVO); // 資料庫update成功後,正確的的faqVO物件,存入req
			String url = "/keywordQa/listOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFaq.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String kwTypes = req.getParameter("kwTypes");
			String kwTypesReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,1000}$";
			if (kwTypes == null || kwTypes.trim().length() == 0) {
				errorMsgs.add("1");
			} else if(!kwTypes.trim().matches(kwTypesReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("1員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			String kwAns = req.getParameter("kwAns");
			String kwAnsReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,1000}$";
			if (kwAns == null || kwAns.trim().length() == 0) {
				errorMsgs.add("1");
			} else if(!kwAns.trim().matches(kwAnsReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("1員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			
			KeywordQaVO keywordQaVO = new KeywordQaVO();
			keywordQaVO.setKwTypes(kwTypes);
			keywordQaVO.setKwAns(kwAns);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("keywordQaVO", keywordQaVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/keywordQa/add.jsp");
				failureView.forward(req, res);
				return ;
			}
			/*************************** 2.開始新增資料 ***************************************/
			KeywordQaService keywordQaSvc = new KeywordQaService();
			keywordQaVO = keywordQaSvc.addKeywordQa(kwTypes, kwAns);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

			String url = "/keywordQa/listAll.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFaq.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer serialId = Integer.valueOf(req.getParameter("serialId"));

			/*************************** 2.開始刪除資料 ***************************************/
			KeywordQaService keywordQaSvc = new KeywordQaService();
			keywordQaSvc.deleteKeywordQa(serialId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/keywordQa/listAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
	
}
