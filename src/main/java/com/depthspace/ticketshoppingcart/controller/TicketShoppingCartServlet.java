package com.depthspace.ticketshoppingcart.controller;

import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;
import com.depthspace.ticketshoppingcart.service.TicketShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@WebServlet({"/tsc/listAll", "/tsc/memCartList","/tsc/delete1","/tsc/deleteAll"})
public class TicketShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if ("/tsc/listAll".equals(servletPath)) {
            doList(req, resp);
        }else if ("/tsc/delete1".equals(servletPath)) {
            doDelete1(req, resp);
        }else if ("/tsc/deleteAll".equals(servletPath)) {
            doDeleteAll(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if ("/tsc/memCartList".equals(servletPath)) {
            doMemList(req, resp);
        }
    }
    //查出所有購物車清單
    protected void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TicketShoppingCartService tscSv = new TicketShoppingCartService();
        List<TicketShoppingCartVO> list = tscSv.getAll();
        HashSet<Integer> uniqueMemIds = new HashSet<>();
        for (TicketShoppingCartVO vo: list){
            uniqueMemIds.add(vo.getMemId());
        }
        req.setAttribute("list", list);
        req.setAttribute("uniqueMemIds",uniqueMemIds);
        req.getRequestDispatcher("/ticket/listAllCart.jsp").forward(req, resp);
    }
    //查出會員購物車清單
    protected void doMemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkedList<String> errorMsgs = new LinkedList<>();
        req.setAttribute("errorMsgs", errorMsgs);
        String str = req.getParameter("memId");

        if (str == null || str.trim().length() == 0) {
            errorMsgs.add("請輸入會員編號");
        }

        Integer memId;
        try {
            memId = Integer.valueOf(str);
        } catch (Exception e) {
            errorMsgs.add("會員編號格式不正確");
            req.getRequestDispatcher("/ticketShoppingCart.jsp").forward(req, resp);
            return;
        }

        TicketShoppingCartService tscSv = new TicketShoppingCartService();
        List<TicketShoppingCartVO> list = tscSv.getAllbyMemId(memId);
        req.setAttribute("list", list);
        req.getRequestDispatcher("/ticket/listAllCart.jsp").forward(req, resp);
    }
    //刪除一列會員購物車內容
    protected void doDelete1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str1 = req.getParameter("memId");
        String str2 = req.getParameter("ticketId");
        Integer memId;
        Integer ticketId;
        try {
            memId = Integer.valueOf(str1);
            ticketId = Integer.parseInt(str2);
        } catch (NumberFormatException e) {
            // 處理轉型錯誤
            return;
        }
        TicketShoppingCartService tscSv = new TicketShoppingCartService();
        if (memId != null && ticketId != null) {
            tscSv.deleteTicketShoppingCart(memId, ticketId);
            resp.sendRedirect("/ticket/memAllCart.jsp");
        }
    }
    //會員購物車清空
    protected void doDeleteAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("memId");
        Integer memId;
        try{
            memId= Integer.valueOf(str);
        }catch (NumberFormatException e){
            // 處理轉型錯誤
            return;
        }
        TicketShoppingCartService tscSv = new TicketShoppingCartService();
        if(memId!=null) {
            tscSv.deleteTicketShoppingCart(memId);
            resp.sendRedirect("/ticket/memAllCart.jsp");
        }

    }

}
