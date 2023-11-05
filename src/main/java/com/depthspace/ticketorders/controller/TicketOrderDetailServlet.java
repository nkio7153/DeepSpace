package com.depthspace.ticketorders.controller;

import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.ticketorders.service.TodServiceImpl;
import com.depthspace.ticketorders.service.TodService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

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
            case "/update":
                updateTod(req, resp);
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
            case "/update":
                updateTod(req, resp);
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

        //取得訂單明細列表
        List<Object[]> list = todSv.getResult(orderId);

        req.setAttribute("orderId",orderId);
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
        List<Object[]> list = todSv.getResult(orderId);

        System.out.println(list);
        req.setAttribute("orderId",orderId);
        req.setAttribute("totalAmount", totalAmount);
        req.setAttribute("amountPaid", amountPaid);
        req.setAttribute("list", list);
        req.getRequestDispatcher("/od/frontOrderDetail.jsp").forward(req, resp);
    }
    protected void updateTod(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer orderId;
        Integer ticketId;
        String ticketReviews=req.getParameter("ticketReviews");
        Byte stars;

        try{
            orderId=parseInt(req.getParameter("orderId"));
            ticketId=parseInt(req.getParameter("ticketId"));
            stars=Byte.parseByte(req.getParameter("stars"));


        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        System.out.println(orderId);
        System.out.println(ticketId);
        System.out.println(ticketReviews);
        System.out.println(stars);
        //取得欲修改的訂單明細
        TicketOrderDetailVO todVo = todSv.getById(orderId, ticketId);
        TicketOrderDetailVO todVo2 = new TicketOrderDetailVO(todVo.getOrderId(), todVo.getTicketId(), todVo.getQuantity(), todVo.getUnitPrice(), todVo.getDiscountPrice(), todVo.getSubtotal(), ticketReviews, stars);
        todSv.updateTod(todVo2);
        setJsonResponse(resp,"評價及星星數更新成功");
    }

    //fetch返回json格式
    private void setJsonResponse(HttpServletResponse resp, Object obj) throws IOException {
        Gson gson = new Gson();
        String jsonData = gson.toJson(obj);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonData);
    }


}
