package com.depthspace.ticketorders.controller;

import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.ticketorders.service.TodServiceImpl;
import com.depthspace.ticketorders.service.TodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tod/*")
public class TicketOrderDetailServlet extends HttpServlet {
    private TodService todSv;

    @Override
    public void init() throws ServletException {
        todSv = new TodServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        switch (pathInfo) {
            case "/backList":
                backList(req, resp);
                break;
            case "/frontList":
                frontList(req, resp);
                break;
//            case "/delete1":
//                doDelete1(req, resp);
//                break;
//            case "/deleteAll":
//                doDeleteAll(req, resp);
//                break;
            default:
                // 在這裡處理所有其他情況
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        switch (pathInfo) {
//            case "/memOrderList":
//                doMemList(req, resp);
//                break;
            case "/save":
                doSave(req, resp);
                break;
            default:
                // 在這裡處理所有其他情況
                break;
        }

    }
    //後台用訂單編號查出所有訂單明細
    protected void backList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer orderId;
        Integer totalAmount;
        Integer amountPaid;

        try{
        orderId = Integer.valueOf(req.getParameter("orderId"));
        totalAmount=Integer.valueOf(req.getParameter("totalAmount"));
        amountPaid=Integer.valueOf(req.getParameter("amountPaid"));
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        System.out.println(orderId);
        //取得訂單明細列表
        List<TicketOrderDetailVO> list = todSv.getAllbyOrderId(orderId);
        System.out.println(list);
        req.setAttribute("totalAmount", totalAmount);
        req.setAttribute("amountPaid", amountPaid);
        req.setAttribute("list", list);
        req.getRequestDispatcher("/od/backOrderDetail.jsp").forward(req, resp);
    }
    //查出前台會員訂單明細
    protected void frontList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer orderId;
        Integer totalAmount;
        Integer amountPaid;

        try{
            orderId = Integer.valueOf(req.getParameter("orderId"));
            totalAmount=Integer.valueOf(req.getParameter("totalAmount"));
            amountPaid=Integer.valueOf(req.getParameter("amountPaid"));
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        System.out.println(orderId);
        //取得訂單明細列表
        List<TicketOrderDetailVO> list = todSv.getAllbyOrderId(orderId);
        System.out.println(list);
        req.setAttribute("totalAmount", totalAmount);
        req.setAttribute("amountPaid", amountPaid);
        req.setAttribute("list", list);
        req.getRequestDispatcher("/od/frontOrderDetail.jsp").forward(req, resp);
    }
    //進入會員訂單索引頁面(跳轉)


    //取消一筆訂單(後臺確認後取消功能)
    protected void doDelete1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Integer orderId;
//        Integer memId;
//        try {
//            orderId = Integer.parseInt(req.getParameter("orderId"));
//            memId = Integer.parseInt(req.getParameter("memId"));
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            return;
//        }
//        if(orderId != null) {
//            toSv.deleteTicektOrders(orderId);
//        }
//        List<TicketOrdersVO> list = toSv.getbyMemId(memId);
//        req.setAttribute("list", list);
//        req.setAttribute("memId",memId);
//        req.getRequestDispatcher("/ticketOrders/memOrderList.jsp").forward(req, resp);
    }
    //會員添加訂單(前台跳轉)
    protected void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Integer orderId=null;
//        Integer memId;
//        Timestamp orderDate;
//        Integer totalAmount;
//        Integer pointsFeedback;
//        Integer amountPaid;
//        Byte status=0;
//        Byte paymentMethod;
//        try{
//            memId= Integer.valueOf(req.getParameter("memId"));
//            orderDate=new Timestamp(System.currentTimeMillis());
//            totalAmount = Integer.valueOf(req.getParameter("totalAmount"));
//            pointsFeedback=Integer.valueOf(req.getParameter("pointsFeedback"));
//            amountPaid=Integer.valueOf(req.getParameter("amountPaid"));
//            paymentMethod=Byte.valueOf(req.getParameter("paymentMethod"));
//        }catch (NumberFormatException e){
//            e.printStackTrace();
//            return;
//        }
//        if(memId != null && totalAmount !=null && pointsFeedback !=null && amountPaid  !=null && paymentMethod !=null){
//
//            TicketOrdersVO to = new TicketOrdersVO(orderId, memId, orderDate, totalAmount, pointsFeedback, amountPaid, status, paymentMethod);
//            toSv.addTicektOrders(to);
//            System.out.println(to);
//        }
//        System.out.println();
//        List<TicketOrdersVO> list = toSv.getbyMemId(memId);
//        req.setAttribute("list", list);
//        req.setAttribute("memId",memId);
//        req.getRequestDispatcher("/ticketOrders/memOrderList.jsp").forward(req, resp);
    }

}
