package com.depthspace.memticketowned.controller;

import com.depthspace.memticketowned.model.MemTicketDetails;
import com.depthspace.memticketowned.model.MemTicketOwnedVO;
import com.depthspace.memticketowned.model.hibernate.HbMtoDao;
import com.depthspace.memticketowned.service.MtoService;
import com.depthspace.memticketowned.service.MtoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/mto/*")
public class MtoServlet extends HttpServlet {
    private MtoService mtoSv;
    public MtoServlet(){
        mtoSv=new MtoServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        switch (pathInfo){
            case "/index":
                doIndex(req,resp);
                break;
            case "/memList":
                doMemList(req,resp);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        switch (pathInfo){
            case "/memList":
                doMemList(req,resp);
                break;
        }
    }
    //查出會員編號導到假的索引頁面
    protected void doIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Integer> uniqueMemIds = mtoSv.getUniqueMemIds();
        req.setAttribute("uniqueMemIds",uniqueMemIds);
        req.getRequestDispatcher("/frontend/memticketowned/index.jsp").forward(req,resp);
    }
    //查出會員擁有的所有票券
    private void doMemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer memId=Integer.parseInt(req.getParameter("memId"));

        //用會員id取得當前頁面的list
        String page=req.getParameter("page");
        int currentPage=(page==null) ? 1 : Integer.parseInt(page);

        List<MemTicketDetails> list = mtoSv.getByMemId(memId, currentPage);

        if (req.getSession().getAttribute("mtoPageQty") == null) {
            int mtoPageQty=mtoSv.getTotalByMemId(memId);
            req.getSession().setAttribute("mtoPageQty", mtoPageQty);
        }

        req.setAttribute("currentPage",currentPage);
        req.setAttribute("list",list);
        req.setAttribute("memId",memId);
        req.getRequestDispatcher("/frontend/memticketowned/mto.jsp").forward(req,resp);
    }
}
