package com.depthspace.keywordqa.controller;

import com.depthspace.utils.MailService; // 引入 MailService
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class QuestionSubmissionServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// 設定請求對象的字符編碼為UTF-8
        request.setCharacterEncoding("UTF-8");
    	
        // 從請求中獲取問題和電子郵件地址
        String question = request.getParameter("question");
        String email = request.getParameter("email");
        
        // 創建郵件內容
        String mailContent = "問題內容: " + question + "\n會員電子郵件: " + email;

        try {
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(mailContent, "UTF-8", "html"); // 使用 UTF-8 編碼和 HTML 格式
            MimeMultipart emailContent = new MimeMultipart();
            emailContent.addBodyPart(textPart);

            // 使用 MailService 來發送郵件
            MailService mailService = new MailService();
            mailService.sendMail("tibame.cha103@gmail.com", "會員問題提交", emailContent); // 設定收件人、主題、郵件內容

            // 重定向到提交成功的 JSP 頁面
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/keywordQa/submission_success.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // 發送錯誤回應
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "發送郵件時出現錯誤。");
        }
    }
}
