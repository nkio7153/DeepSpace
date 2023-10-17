package com.depthspace.ticketshoppingcart.controller;

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
        } else if ("/modify".equals(pathInfo)) {
            doModify(req,resp);
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
        String str = req.getParameter("memId");
        Integer memId;
        try {
            memId = Integer.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        TicketShoppingCartService tscSv = new TicketShoppingCartService();
//        List<TicketShoppingCartVO> list = tscSv.getAllbyMemId(memId);//正常VO
        //TicketInfoVO
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
        req.getRequestDispatcher("/ticketShoppingCart/memAllCart.jsp").forward(req, resp);
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
        req.getRequestDispatcher("/ticketShoppingCart/memAllCart.jsp").forward(req, resp);
    }
    protected void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("memId");
        String str1 = req.getParameter("ticketId");
        String str2 = req.getParameter("quantity");
        Integer memId;
        Integer ticketId;
        Integer quantity;
        Date addedDate;
        TicketShoppingCartVO tscVO;
        try{
            memId= Integer.valueOf(str);
            ticketId=Integer.valueOf(str1);
            quantity=Integer.valueOf(str2);
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
        req.getRequestDispatcher("/ticketShoppingCart/memAllCart.jsp").forward(req, resp);
    }
    protected void doModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("memId");
        String str1 = req.getParameter("ticketId");
        String str2 = req.getParameter("quantity");
        Integer memId;
        Integer ticketId;
        Integer quantity;
        Date addedDate;
        try{
            memId= Integer.valueOf(str.trim());
            ticketId=Integer.valueOf(str1.trim());
            quantity=Integer.valueOf(str2.trim());
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
        req.getRequestDispatcher("/ticketShoppingCart/memAllCart.jsp").forward(req, resp);
    }

}
