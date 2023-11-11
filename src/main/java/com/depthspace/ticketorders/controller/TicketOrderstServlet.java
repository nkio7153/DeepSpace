package com.depthspace.ticketorders.controller;

import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.ticketorders.service.ToServiceImpl;
import com.depthspace.ticketorders.service.ToService;
import com.depthspace.ticketshoppingcart.model.CartInfo;
import com.depthspace.ticketshoppingcart.model.RedisCart;
import com.depthspace.ticketshoppingcart.service.RedisCartServiceImpl;
import com.depthspace.ticketshoppingcart.service.TscServiceImpl;
import com.depthspace.utils.JedisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@WebServlet("/to/*")
public class TicketOrderstServlet extends HttpServlet {
    private ToService toSv;
    private RedisCartServiceImpl cartSv;
    private TscServiceImpl tscSv;


    @Override
    public void init() throws ServletException {
        toSv = new ToServiceImpl();
        cartSv = new RedisCartServiceImpl(JedisUtil.getJedisPool());
        tscSv=new TscServiceImpl();
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
            case "/save":
                doSave(req, resp);
                break;
            case "/memOrderList":
                doMemList(req, resp);
                break;
            case "/search":
                search(req, resp);
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
            case "/search":
                search(req, resp);
                break;
            default:
                // 在這裡處理所有其他情況
                break;
        }

    }
    //查出所有訂單(後台功能)
    protected void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String page=req.getParameter("page");
        int currentPage=(page==null) ? 1 : Integer.parseInt(page);

        List<TicketOrdersVO> list = toSv.getAll(currentPage);
        if(req.getSession().getAttribute("toPageQty")==null){
            int toPageQty = (int)toSv.getTotal();
            System.out.println(toPageQty);
            req.getSession().setAttribute("toPageQty",toPageQty);
        }
        List<TicketOrdersVO> all = toSv.getAll();
        //取得所有會員編號、去除重複編號
        HashSet<String> uniqueMemIds = new HashSet<>();
        uniqueMemIds.add("請選擇");
        for (TicketOrdersVO vo : all) {
            uniqueMemIds.add(String.valueOf(vo.getMemId()));
        }
        //所有狀態
        List<String> uqStatus = new ArrayList<>();
        uqStatus.add("請選擇");
        uqStatus.add("已完成");
        uqStatus.add("已取消");
        uqStatus.add("已退貨");

        req.setAttribute("uqStatus", uqStatus);
        req.setAttribute("uniqueMemIds", uniqueMemIds);
        req.setAttribute("list", list);
        req.setAttribute("currentPage",currentPage);
//        req.setAttribute("items",itemsJson);
        req.getRequestDispatcher("/ticketOrders/listAllOrders.jsp").forward(req, resp);
    }
    //查出會員訂單
    protected void doMemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        List<TicketOrdersVO> list = toSv.getAllByMemId(currentPage, memId);

        if (req.getSession().getAttribute("toMemPageQty") == null) {
            int toMemPageQty=toSv.getMemPageTotal(memId);
            req.getSession().setAttribute("toMemPageQty", toMemPageQty);
        }

        req.setAttribute("list", list);
        req.setAttribute("currentPage",currentPage);
        req.setAttribute("memId",memId);

        req.getRequestDispatcher("/ticketOrders/memOrderList.jsp").forward(req, resp);
//        System.out.println(list);
    }
    //進入會員訂單索引頁面(跳轉)
    protected void doIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doIndex執行了");
        List<TicketOrdersVO> list;
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
        //訂單欄位
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
            pointsFeedback = pointCal(totalAmount);
            amountPaid=Integer.valueOf(req.getParameter("amountPaid"));
            paymentMethod=Byte.valueOf(req.getParameter("paymentMethod"));
        }catch (NumberFormatException e){
            e.printStackTrace();
            return;
        }
        TicketOrdersVO to2=null;
        //從redis中取出會員編號對應的購物車 票券編號及數量
        RedisCart cart = cartSv.getCart(memId);
        //再取出票券id及數量的map集合
        Map<Integer, Integer> items = cart.getItems();
        //取出票勸id集合
        Set<Integer> ticketIds = items.keySet();
        //用票券編號集合調用hibernate的方法取得資料庫所需要的所有資訊
        List<CartInfo> list = tscSv.getByTicketIds(ticketIds, items);

        if(memId != null && totalAmount !=null && pointsFeedback !=null && amountPaid  !=null && paymentMethod !=null){

            TicketOrdersVO to = new TicketOrdersVO(orderId, memId, orderDate, totalAmount, pointsFeedback, amountPaid, status, paymentMethod);
            //redis會員購物車清空
            cartSv.deleteAllCart(to.getMemId());
            //生成一筆訂單及多對應的多筆訂單明細
            to2 = toSv.generateTicektOrders(to, list);
        }
//        List<TicketOrdersVO> list = toSv.getbyMemId(memId);
//        req.setAttribute("list", list);
        req.setAttribute("memId",memId);
        req.setAttribute("ticketOrder",to2);
        req.getRequestDispatcher("/ticketOrders/afterPay.jsp").forward(req, resp);
    }
    //複合查詢
    protected void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Map<String, String[]> map=new HashMap<>(req.getParameterMap());

        System.out.println(map);
        System.out.println("抓到Map資料");

        String page = req.getParameter("page");

        int pageNow = (page == null) ? 1 : Integer.parseInt(page);
        //第一次載入頁面
        if(page==null){
            String selectedMemId = req.getParameter("selectedMemId");
            req.getSession().setAttribute("selectedMemId",selectedMemId);
            String selectedStatus = req.getParameter("selectedStatus");
            req.getSession().setAttribute("selectedStatus",selectedStatus);
            String startOrderDate = req.getParameter("startOrderDate");
            req.getSession().setAttribute("startOrderDate",startOrderDate);
            String endOrderDate = req.getParameter("endOrderDate");
            req.getSession().setAttribute("endOrderDate",endOrderDate);
        }else{
            //分頁
            String memId = (String)req.getSession().getAttribute("selectedMemId");
            if (memId != "請選擇"){
                map.put("selectedMemId",new String[]{memId});
            }
            String status = (String)req.getSession().getAttribute("selectedStatus");
            if (status != "請選擇"){
                System.out.println(status);
                map.put("selectedStatus",new String[]{status});
            }
            String startOrderDate = (String)req.getSession().getAttribute("startOrderDate");
            if (startOrderDate != null){
                map.put("startOrderDate",new String[]{startOrderDate});
            }
            String endOrderDate = (String)req.getSession().getAttribute("endOrderDate");
            if (endOrderDate != null){
                map.put("endOrderDate",new String[]{endOrderDate});
            }
        }
        if(map.size() != 0){
            List<TicketOrdersVO> list = toSv.getToByCompositeQuery(map, pageNow);
            int toPageQty;
            if(req.getAttribute("toPageQty") == null) {
                toPageQty = toSv.getToByCompositeQueryTotal(map);
                req.setAttribute("toPageQty",toPageQty);
            }
            req.setAttribute("currentPage",pageNow);
            req.setAttribute("list",list);
        }else{
            System.out.println("map.size()==0");
        }
        List<TicketOrdersVO> all = toSv.getAll();
        //取得所有會員編號、去除重複編號
        HashSet<String> uniqueMemIds = new HashSet<>();
        uniqueMemIds.add("請選擇");
        for (TicketOrdersVO vo : all) {
            uniqueMemIds.add(String.valueOf(vo.getMemId()));
        }
        //所有狀態
        List<String> uqStatus = new ArrayList<>();
        uqStatus.add("請選擇");
        uqStatus.add("已完成");
        uqStatus.add("已取消");
        uqStatus.add("已退貨");
        req.setAttribute("uniqueMemIds",uniqueMemIds);
        req.setAttribute("uqStatus",uqStatus);
        req.getRequestDispatcher("/ticketOrders/queryOrder.jsp").forward(req,resp);
    }


    //點數計算公式
    public Integer pointCal(Integer totalAmount){
        Integer points =0;
        if(totalAmount >= 100){
            points=totalAmount/100;
        }
        return points;
    }

}
