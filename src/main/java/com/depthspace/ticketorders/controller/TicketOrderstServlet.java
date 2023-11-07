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

        req.setAttribute("list", list);
        req.setAttribute("currentPage",currentPage);
//        req.setAttribute("items",itemsJson);
        req.getRequestDispatcher("/ticketOrders/listAllOrders.jsp").forward(req, resp);
    }
    //查出會員訂單
    protected void doMemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer memId = Integer.parseInt(req.getParameter("memId"));



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
    protected void doQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> map = req.getParameterMap();
        String page = req.getParameter("page");
//        if (map != null) {
//            List<TicketOrdersVO> empList = toSv.getToByCompositeQuery(map);
//            req.setAttribute("empList", empList);
//        } else {
//            return "/index.jsp";
//        }
//        return "/emp/listCompositeQueryEmps.jsp";
    }


//    Map<String, String[]> map = new HashMap<>(req.getParameterMap());
//
//    String page = req.getParameter("page");
//    int pageNow = (page == null) ? 1 : Integer.parseInt(page);
//
//    // 第一次進來
//		if (page == null) {
//        String itemNameSearch = req.getParameter("itemNameSearch");
//        req.getSession().setAttribute("itemNameSearch", itemNameSearch);
//        // 其他參數比照
//        String itemPriceSearchStart = req.getParameter("itemPriceSearchStart");
//        req.getSession().setAttribute("itemPriceSearchStart", itemPriceSearchStart);
//
//        String itemPriceSearchEnd = req.getParameter("itemPriceSearchEnd");
//        req.getSession().setAttribute("itemPriceSearchEnd", itemPriceSearchEnd);
//        // 後續切頁
//    } else {
//        String itemNameSearch = (String) req.getSession().getAttribute("itemNameSearch");
//        if (itemNameSearch != null) {
//            map.put("itemNameSearch", new String[] { itemNameSearch });
//        }
//
//        String itemPriceSearchStart = (String) req.getSession().getAttribute("itemPriceSearchStart");
//        if (itemPriceSearchStart != null) {
//            map.put("itemPriceSearchStart", new String[] { itemPriceSearchStart });
//        }
//
//        String itemPriceSearchEnd = (String) req.getSession().getAttribute("itemPriceSearchEnd");
//        if (itemPriceSearchEnd != null) {
//            map.put("itemPriceSearchEnd", new String[] { itemPriceSearchEnd });
//        }
//    }
//
//		if (map.size() != 0) {
//
//        List<Item> itemList = itemService.getItemByCompositeQuery(map, pageNow);
//        int total = itemService.getResultTotalCondition(map);
//
//        if (req.getAttribute("itemPageQty") == null) {
//            int pageQty = (int) (total % ITEM_PAGE_MAX_RESULT == 0 ? (total / ITEM_PAGE_MAX_RESULT)
//                    : (total / ITEM_PAGE_MAX_RESULT + 1));
//            req.setAttribute("itemPageQty", pageQty);
//        }
//        req.setAttribute("pageNow", pageNow);
//        req.setAttribute("itemList", itemList);
//    } else {
//        System.out.println("map.sizes()==0");



    //點數計算公式
    public Integer pointCal(Integer totalAmount){
        Integer points =0;
        if(totalAmount >= 100){
            points=totalAmount/100;
        }
        return points;
    }

}
