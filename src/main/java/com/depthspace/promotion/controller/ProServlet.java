package com.depthspace.promotion.controller;

import com.depthspace.promotion.model.promotion.PromotionTicketView;
import com.depthspace.promotion.model.promotion.PromotionVO;
import com.depthspace.promotion.model.promotion.hibernate.HbProDaoImpl;
import com.depthspace.promotion.model.promotion.jdbc.PromotionDAO_Interface;
import com.depthspace.promotion.model.promotiondetails.HbProDeDaoImpl;
import com.depthspace.promotion.model.promotiondetails.PromotionDetailsVO;
import com.depthspace.promotion.service.ProService;
import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticket.oscardao.HbTiDao;
import com.depthspace.ticket.service.TicketService;
import com.depthspace.ticket.service.TicketServiceImpl;
import com.depthspace.ticketshoppingcart.service.TicketShoppingCartService;
import com.depthspace.utils.HibernateUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import static java.awt.SystemColor.text;

@WebServlet("/pro/*")
@MultipartConfig
public class ProServlet extends HttpServlet {
    private ProService proSv;
    private TicketService tiSv;

    @Override
    public void init() throws ServletException {
        proSv = new ProService();
        tiSv = new TicketServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        switch(pathInfo){
            case "/getAll":
                getAll(req,resp);
                break;
            case"/getAllTid":
                doGetAllTid(req,resp);
                break;
            case"/doEdit":
                edit(req,resp);
                break;
            case"/image":
                doImage(req,resp);
                break;
            case"/showDetail":
                showDetail(req,resp);
                break;
            default:
                break;
        }
    }
    //獲取折扣及明細
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        switch(pathInfo){
            case "/save":
                doSave(req,resp);
                break;
            default:
                break;
        }
    }


    //重新查出欲修改的促銷導入編輯頁面
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TicketVO> list = tiSv.getAllTickets();
        TicketVO ticketVO = new TicketVO();
        ticketVO.setTicketName("適用所有票券");
        ticketVO.setTicketId(999);
        list.add(ticketVO);
        ArrayList<Object> discountList = new ArrayList<>();
        double i=9.5;
        while(i>=3){
            discountList.add(i+"折");
            i-=0.5;
        }
        req.setAttribute("list",list);
        req.setAttribute("discountList",discountList);
        req.getRequestDispatcher("/promotion/proAdd.jsp").forward(req,resp);

    }
    //取得所有票券名稱並導入添加促銷添加頁面
    private void doGetAllTid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TicketVO> list = tiSv.getAllTickets();
        TicketVO ticketVO = new TicketVO();
        ticketVO.setTicketName("適用所有票券");
        ticketVO.setTicketId(999);
        list.add(ticketVO);
        ArrayList<Object> discountList = new ArrayList<>();
        double i=9.5;
        while(i>=3){
            discountList.add(i+"折");
            i-=0.5;
        }
        req.setAttribute("list",list);
        req.setAttribute("discountList",discountList);
        req.getRequestDispatcher("/promotion/proAdd.jsp").forward(req,resp);

    }
    //新增促銷及對應的票券促銷明細
    private void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        System.out.println("進入save方法");
        List<String> errorMsgs = new LinkedList<String>();
        req.setAttribute("errorMsgs", errorMsgs);
        String promoName=null;
        Timestamp startDate = null;
        Timestamp endDate = null;
        String description = null;
        String base64Image;
        byte[] pic=null;
        //取值並檢查
        try{
            //促銷
            promoName=req.getParameter("promoName");
            if (promoName == null || promoName.trim().length() == 0) {
                errorMsgs.add("票券名稱請勿空白");
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            String start=req.getParameter("startDate");
            if(start!=null && !start.isEmpty()){
                try{
                    java.util.Date parsedDate = dateFormat.parse(start);
                    startDate = new Timestamp(parsedDate.getTime());
                    System.out.println("startDate=" + startDate);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                errorMsgs.add("開始日期請勿空白");
            }

            String end=req.getParameter("endDate");
            if(end!=null && !end.isEmpty()){
                try{
                    java.util.Date parsedDate2 = dateFormat.parse(end);
                    endDate = new Timestamp(parsedDate2.getTime());
                    System.out.println("endDate: " + endDate);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                errorMsgs.add("結束日期請勿空白");
            }

            if(endDate.before(startDate)){
                errorMsgs.add("結束日期有誤");
            }
            description=req.getParameter("description");
            if(description == null || description.trim().length() == 0){
                errorMsgs.add("描述請勿空白");
            }

            Part picture = req.getPart("picture");
            if(picture!=null && picture.getSize()>0){
                InputStream inputStream = picture.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int read;
                byte[] data=new byte[1024];
                while((read=inputStream.read(data,0,data.length)) != -1){
                    baos.write(data, 0, read);
                }
                baos.flush();
                pic = baos.toByteArray();
                inputStream.close();
                baos.close();
            }else{
                String webAppPath=getServletContext().getRealPath("/");
                String relativePath="promotion/images/1.png";
                String absoultePath=webAppPath+relativePath;

                File defaultImageFile = new File(absoultePath);
                String defaultImagePath = defaultImageFile.getPath();

                if(defaultImageFile.exists()){
                    byte[] localImagePath = Files.readAllBytes(Path.of(defaultImagePath));
                    base64Image = Base64.getEncoder().encodeToString(localImagePath);

                    resp.setContentType("text/plain");
                    resp.getWriter().write(base64Image);
                    req.setAttribute("base64Image",base64Image);
                }else{
                    System.out.println("圖不存在");
                }

            }

        }catch(NumberFormatException e){
            e.printStackTrace();
            return;
        }

        PromotionVO proVo=null;
        if(errorMsgs.isEmpty()){
            //新增一筆促銷物件
            proVo=new PromotionVO(null, promoName, startDate, endDate, description, pic);
            System.out.println(proVo);
        }
        //創建存放促銷明細的的票券編號集合及折扣
        String[] ticketIds=null;
        BigDecimal discount=null;
        try{
            //取得票券編號集合
            ticketIds = req.getParameterValues("ticketId");
            //取得折扣
            String dis = req.getParameter("discount").replace("折", "");
            discount = new BigDecimal(dis);
        }catch(Exception e){
            e.printStackTrace();
        }

        if(proVo!=null && ticketIds !=null && discount !=null) {
            proSv.addPromotion(proVo, ticketIds, discount);
            System.out.println("成功插入一筆促銷資料");
        }

        resp.sendRedirect(req.getContextPath()+"/pro/getAll");


    }
    //取得所有促銷資訊並導向促銷列表
    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PromotionVO> list = proSv.getAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/promotion/pro.jsp").forward(req,resp);
    }
    //促銷列表導向促銷明細頁面
    private void showDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String promotionId = req.getParameter("proId");
        Integer proId=null;
        try{
            proId = Integer.valueOf(promotionId);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("proId"+proId);
        //取得促銷明細中票券編號對應的票券名稱列表
        List<PromotionTicketView> list = proSv.getAllByProId(proId);
//        List<Integer> proIds = new ArrayList();
//        for(PromotionTicketView vo: list){
//            proIds.add(vo.getTicketId());
//        }
        setJsonResponse(resp, list);

    }

    protected void doImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        ServletOutputStream outputStream = resp.getOutputStream();
        try {
            Integer promotionId = Integer.parseInt(req.getParameter("promotionId"));
            System.out.println(promotionId);
            PromotionVO vo = proSv.getById(promotionId);
            if (vo != null) {
                byte[] image = vo.getPicture();
                BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(image));
                byte[] buf = new byte[4 * 1024]; // 4K buffer
                int len;
                while ((len = in.read(buf)) != -1) {
                    outputStream.write(buf, 0, len);
                }
                outputStream.close();
            } else {
                noImage(outputStream);
            }
        } catch (Exception e) {
            noImage(outputStream);
        }
    }
    //錯誤圖片顯示
    protected void noImage(ServletOutputStream outputStream) throws IOException {
        InputStream is = getServletContext().getResourceAsStream("/images/error.jpg");
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        outputStream.write(bytes);
        is.close();
        outputStream.close();

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
