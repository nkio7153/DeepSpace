package com.depthspace.memticketowned.controller;

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
        Integer memId=null;
        try{
            memId=Integer.valueOf(req.getParameter("memId"));
        }catch(Exception e){
            e.printStackTrace();
        }
        List<MemTicketOwnedVO> list = mtoSv.getByMemId(memId);
        req.setAttribute("list",list);
        req.setAttribute("memId",memId);
        req.getRequestDispatcher("/frontend/memticketowned/mto.jsp").forward(req,resp);
    }
}
