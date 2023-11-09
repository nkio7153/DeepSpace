//package com.depthspace.admin.model.controller;
//
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.servlet.http.Part;
//
//import com.depthspace.admin.model.service.AdminService;
//import com.depthspace.admin.model.model.AdminVO;
//import com.depthspace.admin.model.service.*;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.Date;
//import java.util.*;
//
//@WebServlet("/admin/admin.do")
//@MultipartConfig
//public class AdminServlet extends HttpServlet {
//    public void doGet(HttpServletRequest req, HttpServletResponse res)
//            throws ServletException, IOException {
//        doPost(req, res);
//    }
//
//    public void doPost(HttpServletRequest req, HttpServletResponse res)
//            throws ServletException, IOException {
//
//        req.setCharacterEncoding("UTF-8");
//        String action = req.getParameter("action");
//
//        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//        	System.out.println("成功getOne_For_Display");
//            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//            String str = req.getParameter("adminNo");
//            if (str == null || (str.trim()).length() == 0) {
//                errorMsgs.put("adminNo", "請輸入員工編號");
//            }
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher("/admin/adminSystem.jsp"); // admin/selectPage.jsp
//                failureView.forward(req, res);
//                return;//程式中斷
//            }
//
//            Integer adminNo = null;
//            try {
//                adminNo = Integer.valueOf(str);
//            } catch (Exception e) {
//                errorMsgs.put("adminNo", "員工編號格式不正確");
//            }
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher("/admin/adminSystem.jsp"); // admin/selectPage.jsp
//                failureView.forward(req, res);
//                return;//程式中斷
//            }
//
//            /***************************2.開始查詢資料*****************************************/
//            AdminService adminSvc = new AdminService();
//            AdminVO adminVO = adminSvc.getOneAdmin(adminNo);
//            if (adminVO == null) {
//                errorMsgs.put("adminNo", "查無資料");
//            }
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher("/admin/adminSystem.jsp"); // admin/selectPage.jsp
//                failureView.forward(req, res);
//                return;//程式中斷
//            }
//
//            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
//            req.setAttribute("AdminVO", adminVO); // 資料庫取出的AdminVO物件,存入req
//            String url = "/admin/listOneAdminNew.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAdmin.jsp
//            successView.forward(req, res);
//        }
//
//
//        if ("getOne_For_Update".equals(action)) { // 來自listAllAdmin.jsp的請求
//        	System.out.println("成功getOne_For_Update");
//            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***************************1.接收請求參數****************************************/
//            Integer adminNo = Integer.valueOf(req.getParameter("adminNo"));
//
//            /***************************2.開始查詢資料****************************************/
//            AdminService adminSvc = new AdminService();
//            AdminVO adminVO = adminSvc.getOneAdmin(adminNo);
//
//            /***************************3.查詢完成,準備轉交(Send the Success view)************/
//            String param = "?adminNo=" + adminVO.getAdminNo() +
//                    "&adminAccount=" + adminVO.getAdminAccount() +
//                    "&adminPassword=" + adminVO.getAdminPassword() +
//                    "&adminName=" + adminVO.getAdminName() +
//                    "&createDate=" + adminVO.getCreateDate() +
//                    "&adminStat=" + adminVO.getAdminStat() +
//                    "&adminEmail=" + adminVO.getAdminEmail() +
//                    "&adminPhone=" + adminVO.getAdminPhone();
//
//            String url = "/admin/updateAdminNew.jsp" + param;
//            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateAdmin.jsp
//            successView.forward(req, res);
//        }
//
//
//        if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//        	System.out.println("成功進update");
//            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//            String adminName = req.getParameter("adminName");
//            String adminNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//            if (adminName == null || adminName.trim().length() == 0) {
//                errorMsgs.put("adminName", "姓名請勿空白");
//            } else if (!adminName.trim().matches(adminNameReg)) { //以下練習正則(規)表示式(regular-expression)
//                errorMsgs.put("adminName", "不得特殊符號且長度2~10");
//            }
//            
//            Integer adminNo = Integer.valueOf(req.getParameter("adminNo").trim());
//
////            Integer adminNo = null;
////            try {
////                adminNo = Integer.valueOf(req.getParameter("adminNo").trim());
////            } catch (NumberFormatException e) {
////                errorMsgs.put("adminNo", "請填數字");
////            }
//
//            String adminAccount = req.getParameter("adminAccount").trim();
//            String adminAccountReg = "[a-zA-Z0-9_]+";
//            String adminAccoutReg2 = "^[(a-zA-Z0-9_)]{6,12}$";
//            if (adminAccount == null || adminAccount.trim().length() == 0) {
//                errorMsgs.put("adminAccount", "帳號請勿空白");
//            } else if (!adminAccount.trim().matches(adminAccountReg)) {
//                errorMsgs.put("adminAccount", "只能是英文、數字和_");
//            } else if (!adminAccount.trim().matches(adminAccoutReg2)) {
//                errorMsgs.put("adminAccount", "長度需在6到12之間");
//            }
//
//            String adminPassword = req.getParameter("adminPassword").trim();
//            String adminPasswordReg = "^[a-zA-Z0-9!@]+$";
//            String adminPasswordReg2 = "^[a-zA-Z0-9!@]{8,16}$";
//
//            if (adminPassword == null || adminPassword.length() == 0) {
//                errorMsgs.put("adminPassword", "密碼請勿空白");
//            } else if (!adminPassword.matches(adminPasswordReg)) {
//                errorMsgs.put("adminPassword", "只能是英文、數字、@、!");
//            } else if (!adminPassword.matches(adminPasswordReg2)) {
//                errorMsgs.put("adminPassword", "長度需在8到16之間");
//            }
//
//
//            Date createDate = null;
//            try {
//                createDate = java.sql.Date.valueOf(req.getParameter("createDate").trim());
//            } catch (IllegalArgumentException e) {
//                errorMsgs.put("createDate", "請輸入日期");
//            }
//            
//            Integer adminStat = Integer.valueOf(req.getParameter("adminStat").trim());
//
////            Integer adminStat = null;
////            try {
////                adminStat = Integer.valueOf(req.getParameter("adminStat").trim());
////            } catch (NumberFormatException e) {
////                errorMsgs.put("adminStat", "請填數字");
////            }
//
//            String adminEmail = req.getParameter("adminEmail").trim();
//            if (adminEmail == null || adminEmail.trim().length() == 0) {
//                errorMsgs.put("adminEmail", "信箱請勿空白");
//            }
//
//            String adminPhone = req.getParameter("adminPhone").trim();
//            if (adminPhone == null || adminPhone.trim().length() == 0) {
//                errorMsgs.put("adminPhone", "電話請勿空白");
//            }
//            
//            Part image = null;
//            try {
//                Collection<Part> parts = req.getParts();
//                for (Part part : parts) {
//                    if ("image".equals(part.getName())) image = part;
//                }
//            } catch (IllegalArgumentException e){
//                e.printStackTrace();
//            }
//            
//            AdminService adminSvc = new AdminService();
//            byte[] adminPic = null;
//            int length = image.getInputStream().available() ;
//            if(length != 0){
//                try (InputStream inputStream = image.getInputStream()){
//                    adminPic = inputStream.readAllBytes();
//                    inputStream.close();
//                } catch (IOException e){
//                    e.printStackTrace();
//                }
//            }else {
//            	adminPic=adminSvc.getOneAdmin(adminNo).getAdminPic();
//            }
//
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher("/admin/updateAdminNew.jsp");
//                failureView.forward(req, res);
//                return; //程式中斷
//            }
//
//            /***************************2.開始修改資料*****************************************/
//           
//            AdminVO adminVO = adminSvc.updateAdmin(adminNo,adminAccount,adminPassword,adminName,createDate,adminStat, adminEmail,adminPhone,adminPic);
//
//            /***************************3.修改完成,準備轉交(Send the Success view)*************/
//            req.setAttribute("AdminVO", adminVO); // 資料庫update成功後,正確的的AdminVO物件,存入req
//            String url = "/admin/listOneAdminNew.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAdmin.jsp
//            successView.forward(req, res);
//        }
//
//        if ("insert".equals(action)) { // 來自addAdmin.jsp的請求
//        	System.out.println("成功進insert");
//            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//            String adminName = req.getParameter("adminName");
//            String adminNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//            if (adminName == null || adminName.trim().length() == 0) {
//                errorMsgs.put("adminName", "姓名請勿空白");
//            } else if (!adminName.trim().matches(adminNameReg)) { //以下練習正則(規)表示式(regular-expression)
//                errorMsgs.put("adminName", "不得特殊符號且長度2~10");
//            }
//
//            String adminAccount = req.getParameter("adminAccount").trim();
//            String adminAccountReg = "[a-zA-Z0-9_]+";
//            String adminAccoutReg2 = "^[(a-zA-Z0-9_)]{6,12}$";
//            if (adminAccount == null || adminAccount.trim().length() == 0) {
//                errorMsgs.put("adminAccount", "帳號請勿空白");
//            } else if (!adminAccount.trim().matches(adminAccountReg)) {
//                errorMsgs.put("adminAccount", "只能是英文、數字和_");
//            } else if (!adminAccount.trim().matches(adminAccoutReg2)) {
//                errorMsgs.put("adminAccount", "長度需在6到12之間");
//            }
//
//            String adminPassword = req.getParameter("adminPassword").trim();
//            String adminPasswordReg = "^[a-zA-Z0-9!@]+$";
//            String adminPasswordReg2 = "^[a-zA-Z0-9!@]{8,16}$";
//
//            if (adminPassword == null || adminPassword.length() == 0) {
//                errorMsgs.put("adminPassword", "密碼請勿空白");
//            } else if (!adminPassword.matches(adminPasswordReg)) {
//                errorMsgs.put("adminPassword", "只能是英文、數字、@、!");
//            } else if (!adminPassword.matches(adminPasswordReg2)) {
//                errorMsgs.put("adminPassword", "長度需在8到16之間");
//            }
//
//
//            Date createDate = null;
//            try {
//                createDate = java.sql.Date.valueOf(req.getParameter("createDate").trim());
//            } catch (IllegalArgumentException e) {
//                errorMsgs.put("createDate", "請輸入日期");
//            }
//
//            Integer adminStat = null;
//            try {
//                adminStat = Integer.valueOf(req.getParameter("adminStat").trim());
//            } catch (NumberFormatException e) {
//                errorMsgs.put("adminStat", "請填數字");
//            }
//
//            String adminEmail = req.getParameter("adminEmail").trim();
//            String adminEmailReg = "^[a-zA-Z0-9._%+-]+@.+\\.[a-zA-Z]{2,}$";
//            if (adminEmail == null || adminEmail.trim().length() == 0) {
//                errorMsgs.put("adminEmail", "信箱請勿空白");
//            } else if (!adminEmail.trim().matches(adminEmailReg)) {
//                errorMsgs.put("adminEmail", "信箱格式錯誤");
//            }
//
//
//            String adminPhone = req.getParameter("adminPhone").trim();
//            String adminPhoneReg = "^09[0-9]{8}$";
//            if (adminPhone == null || adminPhone.trim().length() == 0) {
//                errorMsgs.put("adminPhone", "手機請勿空白");
//            } else if (!adminPhone.trim().matches(adminPhoneReg)) {
//                errorMsgs.put("adminPhone", "手機格式09開頭共10碼");
//            }
//
//
//            Part image = null;
//            try {
//                Collection<Part> parts = req.getParts();
//                for (Part part : parts) {
//                    if ("image".equals(part.getName())) image = part;
//                }
//            } catch (IllegalArgumentException e){
//                e.printStackTrace();
//            }
//            
//            
//            byte[] adminPic = null;
//            if(image != null){
//                try (InputStream inputStream = image.getInputStream()){
//                    adminPic = inputStream.readAllBytes();
//                    inputStream.close();
//                } catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
//
//            // Send the use back to the form, if there were errors
//            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher("/admin/addadminNew.jsp");
//                failureView.forward(req, res);
//                return;
//            }
//
//            /***************************2.開始新增資料***************************************/
//            AdminService adminSvc = new AdminService();
//            adminSvc.addAdmin(adminAccount, adminPassword, adminName, createDate, adminStat, adminEmail, adminPhone,adminPic);
//
//            /***************************3.新增完成,準備轉交(Send the Success view)***********/
//            String url = "/admin/adminSystem.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAdmin.jsp
//            successView.forward(req, res);
//        }
//
//
//        if ("delete".equals(action)) { // 來自listAllAdmin.jsp
//        	System.out.println("成功進delete");
//            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***************************1.接收請求參數***************************************/
//            Integer adminNo = Integer.valueOf(req.getParameter("adminNo"));
//
//            /***************************2.開始刪除資料***************************************/
//            AdminService adminSvc = new AdminService();
//            adminSvc.deleteAdmin(adminNo);
//
//            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
//            String url = "/admin/adminSystem.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//            successView.forward(req, res);
//        }
//        
//        if ("backendlogout".equals(action)) {
//            HttpSession session = req.getSession(false);
//            if (session != null) {
//                session.invalidate(); // 登出，终止会话
//                System.out.println("成功登出");
//            }
//            res.sendRedirect("./adminLogin.jsp");
//        }
//
//    }
//}
