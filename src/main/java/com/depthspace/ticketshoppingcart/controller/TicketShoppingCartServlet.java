package com.depthspace.ticketshoppingcart.controller;

import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;
import com.depthspace.ticketshoppingcart.service.TicketShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@WebServlet({"/tsc/*"})
public class TicketShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if ("/listAll".equals(pathInfo)) {
            doList(req, resp);
        }else if ("/delete1".equals(pathInfo)) {
            doDelete1(req, resp);
        }else if ("/deleteAll".equals(pathInfo)) {
            doDeleteAll(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if ("/memCartList".equals(pathInfo)) {
            doMemList(req, resp);
        } else if ("/save".equals(pathInfo)) {
            doSave(req,resp);
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
        req.setAttribute("memId",memId);
        req.getRequestDispatcher("/ticket/memAllCart.jsp").forward(req, resp);
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
        }
        List<TicketShoppingCartVO> list = tscSv.getAllbyMemId(memId);
        req.setAttribute("list", list);
        req.setAttribute("memId",memId);
        req.getRequestDispatcher("/ticket/memAllCart.jsp").forward(req, resp);
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
        }
        List<TicketShoppingCartVO> list = tscSv.getAllbyMemId(memId);
        req.setAttribute("list", list);
        req.setAttribute("memId",memId);
        req.getRequestDispatcher("/ticket/memAllCart.jsp").forward(req, resp);
    }
    protected void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("memId");
        String str1 = req.getParameter("ticketId");
        String str2 = req.getParameter("quantity");
        String str3 = req.getParameter("addedDate");
        Integer memId;
        Integer ticketId;
        Integer quantity;
        Date addedDate;
        TicketShoppingCartVO tscVO;
        try{
            memId= Integer.valueOf(str);
            ticketId=Integer.valueOf(str1);
            quantity=Integer.valueOf(str2);
            addedDate = Date.valueOf(str3);
        }catch (NumberFormatException e){
            // 處理轉型錯誤
            return;
        }
        TicketShoppingCartService tscSv = new TicketShoppingCartService();
        if(memId!=null && ticketId!=null && quantity!=null && addedDate!=null) {
            tscSv.addTicketShoppingCart(memId,ticketId,quantity,addedDate);
        }
        List<TicketShoppingCartVO> list = tscSv.getAllbyMemId(memId);
        req.setAttribute("list", list);
        req.setAttribute("memId",memId);
        req.getRequestDispatcher("/ticket/memAllCart.jsp").forward(req, resp);
    }

}
