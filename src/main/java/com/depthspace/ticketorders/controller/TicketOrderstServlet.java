package com.depthspace.ticketorders.controller;

import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.ticketorders.service.TicketOrderService;
import com.depthspace.ticketorders.service.TicketOrderService_Interface;
import com.depthspace.ticketshoppingcart.model.TicketInfoVO;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;
import com.depthspace.ticketshoppingcart.service.TicketShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
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
//            case "/deleteAll":
//                doDeleteAll(req, resp);
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
//            case "/save":
//                doSave(req, resp);
//                break;
//            case "/modify":
//                doModify(req, resp);
//                break;
            default:
                // 在這裡處理所有其他情況
                break;
        }

    }
    //查出所有購物車清單
    protected void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TicketOrderService toSv = new TicketOrderService();
        List<TicketOrdersVO> list = toSv.getAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/ticketOrders/listAllOrders.jsp").forward(req, resp);
    }
    //查出會員購物車清單
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

    //刪除一列會員購物車內容
    protected void doDelete1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Integer memId;
//        Integer ticketId;
//        try {
//            memId = Integer.valueOf(req.getParameter("memId"));
//            ticketId = Integer.parseInt(req.getParameter("ticketId"));
//        } catch (NumberFormatException e) {
//            // 處理轉型錯誤
//            return;
//        }
//        TicketShoppingCartService tscSv = new TicketShoppingCartService();
//        if (memId != null && ticketId != null) {
//            tscSv.deleteTicketShoppingCart(memId, ticketId);
//        }
//        List<TicketShoppingCartVO> list = tscSv.getAllbyMemId(memId);
//        req.setAttribute("list", list);
//        req.setAttribute("memId",memId);
//        req.getRequestDispatcher("/ticketShoppingCart/memOrderList.jsp").forward(req, resp);
    }
    //會員購物車清空
    protected void doDeleteAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Integer memId;
//        try{
//            memId= Integer.valueOf(req.getParameter("memId"));
//        }catch (NumberFormatException e){
//            // 處理轉型錯誤
//            return;
//        }
//        TicketShoppingCartService tscSv = new TicketShoppingCartService();
//        if(memId!=null) {
//            tscSv.deleteTicketShoppingCart(memId);
//        }
//        List<TicketShoppingCartVO> list = tscSv.getAllbyMemId(memId);
//        req.setAttribute("list", list);
//        req.setAttribute("memId",memId);
//        req.getRequestDispatcher("/ticketShoppingCart/memOrderList.jsp").forward(req, resp);
    }
    protected void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Integer memId;
//        Integer ticketId;
//        Integer quantity;
//        TicketShoppingCartVO tscVO;
//        try{
//            memId= Integer.valueOf(req.getParameter("memId"));
//            ticketId=Integer.valueOf(req.getParameter("ticketId"));
//            quantity=Integer.valueOf(req.getParameter("quantity"));
//        }catch (NumberFormatException e){
//            // 處理轉型錯誤
//            e.printStackTrace();
//            return;
//        }
//        TicketShoppingCartService tscSv = new TicketShoppingCartService();
//        if(memId!=null && ticketId!=null && quantity!=null ) {
//            TicketShoppingCartVO tsc = new TicketShoppingCartVO(memId, ticketId, quantity);
//            tscSv.addTicketShoppingCart(tsc);
//        }
//        List<TicketShoppingCartVO> list = tscSv.getAllbyMemId(memId);
//        req.setAttribute("list", list);
//        req.setAttribute("memId",memId);
//        req.getRequestDispatcher("/ticketShoppingCart/memOrderList.jsp").forward(req, resp);
    }
    protected void doModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Integer memId;
//        Integer ticketId;
//        Integer quantity;
//        Date addedDate;
//        try{
//            memId= Integer.valueOf(req.getParameter("memId"));
//            ticketId=Integer.valueOf(req.getParameter("ticketId"));
//            quantity=Integer.valueOf(req.getParameter("quantity"));
//        }catch(NumberFormatException e){
//            e.printStackTrace();
//            return;
//        }
//        TicketShoppingCartService tscSv = new TicketShoppingCartService();
//        if(memId!=null && ticketId!=null && quantity!=null) {
//            TicketShoppingCartVO tsc = new TicketShoppingCartVO(memId, ticketId, quantity);
//            tscSv.updateTicketShoppingCart(tsc);
//        }
//        List<TicketShoppingCartVO> list = tscSv.getAllbyMemId(memId);
//        req.setAttribute("list", list);
//        req.setAttribute("memId",memId);
//        req.getRequestDispatcher("/ticketShoppingCart/memOrderList.jsp").forward(req, resp);
    }

}
