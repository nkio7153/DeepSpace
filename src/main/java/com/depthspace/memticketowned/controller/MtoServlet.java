package com.depthspace.memticketowned.controller;

import com.depthspace.memticketowned.model.MemTicketDetails;
import com.depthspace.memticketowned.model.MemTicketOwnedVO;
import com.depthspace.memticketowned.model.hibernate.HbMtoDao;
import com.depthspace.memticketowned.service.MtoService;
import com.depthspace.memticketowned.service.MtoServiceImpl;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticket.service.TicketService;
import com.depthspace.ticket.service.TicketServiceImpl;
import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/mto/*")
public class MtoServlet extends HttpServlet {
    private MtoService mtoSv;
    private TicketService tiSv;
    public MtoServlet(){
        mtoSv=new MtoServiceImpl();
        tiSv=new TicketServiceImpl();
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
//            case "/pdfWriter":
//                doPdf(req,resp);
//                break;
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
        if (req.getParameter("memId")!=null){
            memId=Integer.parseInt(req.getParameter("memId"));
        }

        HttpSession session = req.getSession(false);
        if(session.getAttribute("memId")!=null) {
            memId = (Integer)session.getAttribute("memId");
        }
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
//    private void doPdf(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("進入pdf方法");
//        Integer ticketId = Integer.parseInt(req.getParameter("ticketId"));
//        System.out.println(ticketId);
//        TicketVO ticketInfo = tiSv.getTicketById(ticketId);
//        System.out.println(ticketInfo.getTicketName());
//        //設置響應內容類型為pdf
//        resp.setContentType("application/pdf");
//        //建議的文件名設置
//        resp.setHeader("Content-disposition","attachment; filename=ticket_"+ticketId+".pdf");
//        //初始化pdf文檔
//        Document document = new Document();
//        try {
//            PdfWriter.getInstance(document,resp.getOutputStream());
//        } catch (DocumentException e) {
//            throw new RuntimeException(e);
//        }
//        //打開文檔
//        document.open();
//        //添加內容
//        try {
//            document.add(new Paragraph("票券名稱:" + ticketInfo.getTicketName()));
//        } catch (DocumentException e) {
//            throw new RuntimeException(e);
//        }
//        //關閉文檔
//        document.close();
//        setJsonResponse(resp,ticketInfo);
//    }
//    //fetch返回json格式
//    private void setJsonResponse(HttpServletResponse resp, Object obj) throws IOException {
//        Gson gson = new Gson();
//        String jsonData = gson.toJson(obj);
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//        resp.getWriter().write(jsonData);
//    }
}
