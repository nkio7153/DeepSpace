package com.depthspace.ticketorders.controller;

import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.ticketorders.service.TicketOrderDetailService;
import com.depthspace.ticketorders.service.TicketOrderDetailService_Interface;
import com.depthspace.ticketorders.service.TicketOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tod/*")
public class TicketOrderDetailServlet extends HttpServlet {
    private TicketOrderDetailService_Interface todSv;

    @Override
    public void init() throws ServletException {
        todSv = new TicketOrderDetailService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        switch (pathInfo) {
            case "/list":
                doList(req, resp);
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
    //用訂單編號查出所有訂單明細
    protected void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer orderId;
        try{
        orderId = Integer.valueOf(req.getParameter("orderId"));
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        System.out.println(orderId);
        List<TicketOrderDetailVO> list = todSv.getAllbyOrderId(orderId);
        System.out.println(list);
        req.setAttribute("list", list);
        req.getRequestDispatcher("/od/orderDetail.jsp").forward(req, resp);
    }
    //查出會員訂單
    protected void doMemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Integer memId;
//        try {
//            memId = Integer.valueOf(req.getParameter("memId"));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//        List<TicketOrdersVO> list = toSv.getbyMemId(memId);
//        req.setAttribute("list", list);
//        req.setAttribute("memId",memId);
//        req.getRequestDispatcher("/ticketOrders/memOrderList.jsp").forward(req, resp);
//        System.out.println(list);
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
