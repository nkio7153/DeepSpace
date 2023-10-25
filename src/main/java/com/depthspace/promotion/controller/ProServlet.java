package com.depthspace.promotion.controller;

import com.depthspace.promotion.model.promotion.PromotionVO;
import com.depthspace.promotion.service.ProService;
import com.depthspace.ticketshoppingcart.service.TicketShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/pro/*")
public class ProServlet extends HttpServlet {
    private ProService proSv;

    @Override
    public void init() throws ServletException {
        proSv = new ProService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        switch(pathInfo){
            case "/getAll":
                getAll(req,resp);
            break;
            default:
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PromotionVO> list = proSv.getAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/promotion/pro.jsp").forward(req,resp);
    }

}
