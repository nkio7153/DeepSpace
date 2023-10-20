package com.depthspace.ticketorders.controller;

import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.ticketorders.service.TicketOrderDetailService;
import com.depthspace.ticketorders.service.TicketOrderDetailService_Interface;
import com.depthspace.ticketorders.service.TicketOrderService;
import com.depthspace.ticketorders.service.TicketOrderService_Interface;
import com.depthspace.ticketshoppingcart.model.TicketInfoVO;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;
import com.depthspace.ticketshoppingcart.service.TicketShoppingCartService;
import com.google.gson.Gson;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Status;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;

@WebServlet("/to/*")
public class TicketOrderstServlet extends HttpServlet {
    private TicketOrderService_Interface toSv;


    @Override
    public void init() throws ServletException {
        toSv = new TicketOrderService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        switch (pathInfo) {
            case "/listAll":
                doList(req, resp);
                break;
//            case "/delete1":
//                doDelete1(req, resp);
//                break;
            case "/index":
                doIndex(req, resp);
                break;
            default:
                // 在這裡處理所有其他情況
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        switch (pathInfo) {
            case "/memOrderList":
                doMemList(req, resp);
                break;
            case "/save":
                doSave(req, resp);
                break;
            default:
                // 在這裡處理所有其他情況
                break;
        }

    }
    //查出所有訂單(後台功能)
    protected void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TicketOrderService toSv = new TicketOrderService();
        List<TicketOrdersVO> list = toSv.getAll();
        //創建一個集合存放訂單總品項
//        List<Long> orderItems = new ArrayList<>();
//        for(TicketOrdersVO vo: list) {
//            long total = toSv.getTotal(vo.getOrderId());
//            orderItems.add(total);
//        }
//        System.out.println(orderItems);
//        Gson gson = new Gson();
//        String itemsJson = gson.toJson(orderItems);
        req.setAttribute("list", list);
//        req.setAttribute("items",itemsJson);
        req.getRequestDispatcher("/ticketOrders/listAllOrders.jsp").forward(req, resp);
    }
    //查出會員訂單
    protected void doMemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer memId;
        try {
            memId = Integer.valueOf(req.getParameter("memId"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        List<TicketOrdersVO> list = toSv.getbyMemId(memId);
        req.setAttribute("list", list);
        req.setAttribute("memId",memId);
        req.getRequestDispatcher("/ticketOrders/memOrderList.jsp").forward(req, resp);
        System.out.println(list);
    }
    //進入會員訂單索引頁面(跳轉)
    protected void doIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TicketOrdersVO> list = null;
        list = toSv.getAll();
        System.out.println(list);
        HashSet<Integer> uniqueMemIds = new HashSet<>();
//        if(list!=null) {
            for (TicketOrdersVO vo : list) {
                uniqueMemIds.add(vo.getMemId());
            }
//        }
        req.setAttribute("uniqueMemIds",uniqueMemIds);
        req.getRequestDispatcher("/ticketOrders/index.jsp").forward(req, resp);
    }

    //取消一筆訂單(後臺確認後取消功能)
    protected void doDelete1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer orderId;
        Integer memId;
        try {
            orderId = Integer.parseInt(req.getParameter("orderId"));
            memId = Integer.parseInt(req.getParameter("memId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        if(orderId != null) {
            toSv.deleteTicektOrders(orderId);
        }
        List<TicketOrdersVO> list = toSv.getbyMemId(memId);
        req.setAttribute("list", list);
        req.setAttribute("memId",memId);
        req.getRequestDispatcher("/ticketOrders/memOrderList.jsp").forward(req, resp);
    }
    //會員添加訂單(前台跳轉)
    protected void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer orderId=null;
        Integer memId;
        Timestamp orderDate;
        Integer totalAmount;
        Integer pointsFeedback;
        Integer amountPaid;
        Byte status=0;
        Byte paymentMethod;
        try{
            memId= Integer.valueOf(req.getParameter("memId"));
            orderDate=new Timestamp(System.currentTimeMillis());
            totalAmount = Integer.valueOf(req.getParameter("totalAmount"));
            pointsFeedback=Integer.valueOf(req.getParameter("pointsFeedback"));
            amountPaid=Integer.valueOf(req.getParameter("amountPaid"));
            paymentMethod=Byte.valueOf(req.getParameter("paymentMethod"));
        }catch (NumberFormatException e){
            e.printStackTrace();
            return;
        }
        if(memId != null && totalAmount !=null && pointsFeedback !=null && amountPaid  !=null && paymentMethod !=null){

            TicketOrdersVO to = new TicketOrdersVO(orderId, memId, orderDate, totalAmount, pointsFeedback, amountPaid, status, paymentMethod);
            toSv.addTicektOrders(to);
            System.out.println(to);
        }
        System.out.println();
        List<TicketOrdersVO> list = toSv.getbyMemId(memId);
        req.setAttribute("list", list);
        req.setAttribute("memId",memId);
        req.getRequestDispatcher("/ticketOrders/memOrderList.jsp").forward(req, resp);
    }

}
