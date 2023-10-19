package com.depthspace.ticketshoppingcart.controller;

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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/tsc/*")
public class TicketShoppingCartServlet extends HttpServlet {
    private TicketShoppingCartService tscSv;

    @Override
    public void init() throws ServletException {
        tscSv = new TicketShoppingCartService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        switch (pathInfo) {
            case "/listAll":
                doList(req, resp);
                break;
            case "/delete1":
                doDelete1(req, resp);
                break;
            case "/deleteAll":
                doDeleteAll(req, resp);
                break;
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
            case "/memCartList":
                doMemList(req, resp);
                break;
            case "/save":
                doSave(req, resp);
                break;
            case "/modify":
                doModify(req, resp);
                break;
            default:
                // 在這裡處理所有其他情況
                break;
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
        req.getRequestDispatcher("/ticketShoppingCart/listAllCart.jsp").forward(req, resp);
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
        List<TicketInfoVO> list = tscSv.getList(memId);
        for (TicketInfoVO vo : list) {
            byte[] imageBytes = vo.getImage();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            vo.setBase64Image(base64Image);
        }

        req.setAttribute("list", list);
        req.setAttribute("memId",memId);
        req.getRequestDispatcher("/ticketShoppingCart/memAllCart.jsp").forward(req, resp);
    }
    protected void doIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TicketShoppingCartService tscSv = new TicketShoppingCartService();
        List<TicketShoppingCartVO> list = tscSv.getAll();
        HashSet<Integer> uniqueMemIds = new HashSet<>();
        for (TicketShoppingCartVO vo: list){
            uniqueMemIds.add(vo.getMemId());
        }
        req.setAttribute("uniqueMemIds",uniqueMemIds);
        req.getRequestDispatcher("/ticketShoppingCart/index.jsp").forward(req, resp);
    }

    //刪除一列會員購物車內容
    protected void doDelete1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer memId;
        Integer ticketId;
        try {
            memId = Integer.valueOf(req.getParameter("memId"));
            ticketId = Integer.parseInt(req.getParameter("ticketId"));
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
        req.getRequestDispatcher("/ticketShoppingCart/memOrderList.jsp").forward(req, resp);
    }
    //會員購物車清空
    protected void doDeleteAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer memId;
        try{
            memId= Integer.valueOf(req.getParameter("memId"));
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
        req.getRequestDispatcher("/ticketShoppingCart/memOrderList.jsp").forward(req, resp);
    }
    protected void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer memId;
        Integer ticketId;
        Integer quantity;
        TicketShoppingCartVO tscVO;
        try{
            memId= Integer.valueOf(req.getParameter("memId"));
            ticketId=Integer.valueOf(req.getParameter("ticketId"));
            quantity=Integer.valueOf(req.getParameter("quantity"));
        }catch (NumberFormatException e){
            // 處理轉型錯誤
            e.printStackTrace();
            return;
        }
        TicketShoppingCartService tscSv = new TicketShoppingCartService();
        if(memId!=null && ticketId!=null && quantity!=null ) {
            TicketShoppingCartVO tsc = new TicketShoppingCartVO(memId, ticketId, quantity);
            tscSv.addTicketShoppingCart(tsc);
        }
        List<TicketShoppingCartVO> list = tscSv.getAllbyMemId(memId);
        req.setAttribute("list", list);
        req.setAttribute("memId",memId);
        req.getRequestDispatcher("/ticketShoppingCart/memOrderList.jsp").forward(req, resp);
    }
    protected void doModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer memId;
        Integer ticketId;
        Integer quantity;
        Date addedDate;
        try{
            memId= Integer.valueOf(req.getParameter("memId"));
            ticketId=Integer.valueOf(req.getParameter("ticketId"));
            quantity=Integer.valueOf(req.getParameter("quantity"));
        }catch(NumberFormatException e){
            e.printStackTrace();
            return;
        }
        TicketShoppingCartService tscSv = new TicketShoppingCartService();
        if(memId!=null && ticketId!=null && quantity!=null) {
            TicketShoppingCartVO tsc = new TicketShoppingCartVO(memId, ticketId, quantity);
            tscSv.updateTicketShoppingCart(tsc);
        }
        List<TicketShoppingCartVO> list = tscSv.getAllbyMemId(memId);
        req.setAttribute("list", list);
        req.setAttribute("memId",memId);
        req.getRequestDispatcher("/ticketShoppingCart/memOrderList.jsp").forward(req, resp);
    }

}
