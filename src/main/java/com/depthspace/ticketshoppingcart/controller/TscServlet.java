package com.depthspace.ticketshoppingcart.controller;

import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.ticket.oscardao.HbTiDao;
import com.depthspace.ticket.service.TicketService;
import com.depthspace.ticket.service.TicketServiceImpl;
import com.depthspace.ticketshoppingcart.model.CartInfo;
import com.depthspace.ticketshoppingcart.model.RedisCart;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;
import com.depthspace.ticketshoppingcart.service.RedisCartServiceImpl;
import com.depthspace.ticketshoppingcart.service.TscServiceImpl;
import com.depthspace.utils.HibernateUtil;
import com.depthspace.utils.JedisUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet("/tsc/*")
public class TscServlet extends HttpServlet {
    private TscServiceImpl tscSv;
    private TicketService tiSv;
    RedisCartServiceImpl carSv;

    @Override
    public void init() throws ServletException {
        tscSv = new TscServiceImpl();
        tiSv = new TicketServiceImpl();
        carSv = new RedisCartServiceImpl(JedisUtil.getJedisPool());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        switch (pathInfo) {
            case "/deleteAll":
                doDeleteAll(req, resp);
                break;
            case "/index":
                doIndex(req, resp);
                break;
            case "/image":
                doImage(req, resp);
                break;
            case "/save":
                doSave(req, resp);
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
            case "/flush":
                doFlush(req, resp);
                break;
            case "/deleteByFetch":
                doDeleteByFetch(req, resp);
                break;
            case "/checkout":
                checkout(req, resp);
                break;
            default:
                // 在這裡處理所有其他情況
                break;
        }

    }


    //查出會員購物車清單(目前非會員已加入cookie，還沒實現頁面跳轉取值，還需考慮促銷顯示及計算，庫存量顯示
    protected void doMemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Cookie[] cookies = req.getCookies();
//        String cartJson=null;
//        for (Cookie cookie: cookies) {
//            if("cart".equals(cookie.getName())){
//                cartJson=URLEncoder.encode(cookie.getValue(),"UTF-8");
//                break;
//            }
//        }
//
//        //如果cookie中含有購物車資料執行以下
//        if(cartJson != null){
//            Gson gson = new Gson();
//            Type type = new TypeToken<HashMap<Integer, CartVO>>() {}.getType();
//            HashMap<Integer, CartVO> carts = gson.fromJson(cartJson, type);
//            List<CartVO> list = new ArrayList<>();
//            for(Map.Entry<Integer, CartVO> entry: carts.entrySet()){
//                CartVO cart = entry.getValue();
//                list.add(cart);
//            }
//            req.setAttribute("list",list);
//
//        }else {
        //如果cookie中不含有購物車資料執行以下
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Integer memId;
        try {
            memId = Integer.valueOf(req.getParameter("memId"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.println(memId);
        //從redis中取出會員編號對應的購物車 票券編號及數量
        RedisCart cart = carSv.getCart(memId);
        //再取出票券id及數量的map集合
        Map<Integer, Integer> items = cart.getItems();
        //取出票勸id集合
        Set<Integer> ticketIds = items.keySet();
        //用票券編號集合調用hibernate的方法取得資料庫所需要的所有資訊
        //1.票券id
        //1.票券圖片的serialId
        //2.票券名稱
        //3.票券介紹
        //4.價格
        //5.促銷折扣//如果在限時促銷範圍內的話就取得折扣
        //6.折扣價(如果有折扣的話換算成折扣價)
        //7.數量
        //8.庫存
        //9.小計(不需要join)
        List<CartInfo> list = tscSv.getByTicketIds(ticketIds, items);

        req.setAttribute("list", list);
        req.setAttribute("memId", memId);
        System.out.println(memId);
        req.getRequestDispatcher("/ticketShoppingCart/RedisCart.jsp").forward(req, resp);

        //資料庫版
//        List<TicketInfoVO> list = tscSv.getList(memId);
//        for (TicketInfoVO vo : list) {
//            byte[] imageBytes = vo.getImage();
//            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//            vo.setBase64Image(base64Image);
//        }
//
//        req.setAttribute("list", list);
//        req.setAttribute("memId", memId);
//        req.getRequestDispatcher("/ticketShoppingCart/memAllCart.jsp").forward(req, resp);

    }

    //修改數量後刷新資料庫
    protected void doFlush(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer memId;
        Integer ticketId;
        Integer quantity;
        try {
            memId = Integer.valueOf(req.getParameter("memId"));
            ticketId = Integer.parseInt(req.getParameter("ticketId"));
            quantity = Integer.valueOf(req.getParameter("quantity"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        //在redis更新數量
        carSv.updateCart(memId, ticketId, quantity);

        setJsonResponse(resp, "更新購物車數量成功");
//        存在資料庫版
//        TicketShoppingCartVO tscVo = new TicketShoppingCartVO(memId, ticketId, quantity);
//        tscSv.updateTicketShoppingCart(tscVo);
//        setJsonResponse(resp, quantity);
    }

    protected void doIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TscServiceImpl tscSv = new TscServiceImpl();
        List<TicketShoppingCartVO> list = tscSv.getAll();
        HashSet<Integer> uniqueMemIds = new HashSet<>();
        for (TicketShoppingCartVO vo : list) {
            uniqueMemIds.add(vo.getMemId());
        }
        req.setAttribute("uniqueMemIds", uniqueMemIds);
        req.getRequestDispatcher("/ticketShoppingCart/index.jsp").forward(req, resp);
    }

    //購物車刪除一種票券
    protected void doDeleteByFetch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer memId;
        Integer ticketId;
        try {
            memId = Integer.valueOf(req.getParameter("memId"));
            ticketId = Integer.parseInt(req.getParameter("ticketId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        //redis版
        carSv.deleteCart(memId, ticketId);
        //資料庫版
//        tscSv.deleteTicketShoppingCart(memId, ticketId);
        setJsonResponse(resp, "刪除成功");
    }

    //會員購物車清空(ajax傳遞過來需要改)
    protected void doDeleteAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer memId;
        try {
            memId = Integer.valueOf(req.getParameter("memId"));
        } catch (NumberFormatException e) {
            // 處理轉型錯誤
            return;
        }
        TscServiceImpl tscSv = new TscServiceImpl();
        if (memId != null) {
            carSv.deleteAllCart(memId);
            //資料庫版
//            tscSv.deleteTicketShoppingCart(memId);
            setJsonResponse(resp, "購物車清空成功");
        }
//        List<TicketShoppingCartVO> list = tscSv.getAllbyMemId(memId);
//        req.setAttribute("list", list);
//        req.setAttribute("memId", memId);
//        req.getRequestDispatcher("/ticketShoppingCart/memOrderList.jsp").forward(req, resp);
    }

    //接收添加購物車的ajax請求，並返回添加成功
    protected void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer memId = 2;
        Integer ticketId;
        Integer quantity = 1;
        try {
//            memId = Integer.valueOf(req.getParameter("memId"));
            ticketId = Integer.valueOf(req.getParameter("ticketId"));

        } catch (NumberFormatException e) {
            // 處理轉型錯誤
            e.printStackTrace();
            return;
        }

        RedisCart redisCart = new RedisCart();
        redisCart.addItem(ticketId, quantity);
        RedisCartServiceImpl carSv = new RedisCartServiceImpl(JedisUtil.getJedisPool());
        carSv.saveCart(memId, redisCart);
        setJsonResponse(resp, "添加購物車成功");


//        if (memId != null) {
//            //會員登入的情況，第一版先存在資料庫
//            if (memId != null && ticketId != null && quantity != null) {
//                TicketShoppingCartVO tsc = new TicketShoppingCartVO(memId, ticketId, quantity);
//                tscSv.addTicketShoppingCart(tsc);
//            }
//            List<TicketInfoVO> list = tscSv.getList(memId);
//            for (TicketInfoVO vo : list) {
//                byte[] imageBytes = vo.getImage();
//                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//                vo.setBase64Image(base64Image);
//            }
//            req.setAttribute("list", list);
//            req.setAttribute("memId", memId);
//            req.getRequestDispatcher("/ticketShoppingCart/memAllCart.jsp").forward(req, resp);
//            //第二版存在redis
//            Jedis jedis = new Jedis("localhost", 6379);
//
//
//        } else {
//            //非會員購物車資料存在cookie
//            Gson gson = new Gson();
//            //用票券id取得票券資料
//            TicketVO vo = tiSv.getTicketById(ticketId);
//            //用票券id取得票券主圖序列號
//            HbTiDao hbTiDao = new HbTiDao(HibernateUtil.getSessionFactory());
//            TicketImagesVO tiVo = hbTiDao.getByTicketId(ticketId);
//            //先檢查cookie是否已經存在，如果已存在，就用原本的集合把新的物件把進去，不存在，就用新的集合放進去
//            Cookie[] cookies = req.getCookies();
//            HashMap<Integer, CartVO> carts = new HashMap<>();
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
//                    if ("cart".equals(cookie.getName())) {
//                        Type type = new TypeToken<HashMap<Integer, CartVO>>() {
//                        }.getType();
//                        carts = gson.fromJson(URLEncoder.encode(cookie.getValue(), "UTF-8"), type);
//                    }
//                }
//            }
//            //更新或添加購物車項目
//            if (carts.containsKey(ticketId)) {
//                //如果商品已在購物車中，更新數量
//                CartVO existingCart = carts.get(ticketId);
//                existingCart.setQuantity(existingCart.getQuantity() + 1);
//                carts.put(ticketId, existingCart);
//            } else {
//                //創立一個專門存進非會員購物車資訓的物件
//                CartVO cartVO = new CartVO(vo.getTicketId(), tiVo.getSerialId(), vo.getTicketName(), vo.getDescription(), vo.getPrice(), 1);
//                carts.put(ticketId, cartVO);
//            }
//            //序列化更新後的購物車並加到Cookie中
//            String cartJson = gson.toJson(carts);
//            //創建一個cookie保存購物車資料
//            Cookie cartCookie = new Cookie("cart", URLEncoder.encode(cartJson, "UTF-8"));
//            cartCookie.setMaxAge(604800);//設置為7天保存時間
//            //將cookie添加到響應中
//            resp.addCookie(cartCookie);
//            //返回成功響應
//            setJsonResponse(resp, "非會員購物車資訊儲存成功");
//        }
    }

    protected void doImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        ServletOutputStream outputStream = resp.getOutputStream();
        try {
            Integer serialId = Integer.parseInt(req.getParameter("serialId"));
            System.out.println(serialId);
            HbTiDao hbTiDao = new HbTiDao(HibernateUtil.getSessionFactory());
            TicketImagesVO tiVo = hbTiDao.getBySid(serialId);
            if (tiVo != null) {
                byte[] image = tiVo.getImage();
                BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(image));
                byte[] buf = new byte[4 * 1024]; // 4K buffer
                int len;
                while ((len = in.read(buf)) != -1) {
                    outputStream.write(buf, 0, len);
                }
                in.close();
                outputStream.close();
            } else {
                noImage(outputStream);
            }
        } catch (Exception e) {
            noImage(outputStream);
        }
    }

    //跳轉至結帳頁面
    protected void checkout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer memId = null;
        Integer coupen=null;
        try {
            memId = Integer.valueOf(req.getParameter("memId"));
            String coupenStr = req.getParameter("coupen");
            if(coupenStr == null ||coupenStr.isEmpty()){
                coupen=0;
            }else{
                coupen=Integer.valueOf(coupenStr);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
////        List<TicketInfoVO> list = tscSv.getList(memId);
//        RedisCart cart = carSv.getCart(memId);
//        Map<Integer, Integer> items = cart.getItems();
//        Set<Integer> ticketIds = items.keySet();
//        HbTscDaoImpl hbTscDao = new HbTscDaoImpl(HibernateUtil.getSessionFactory());
//        List<CartInfo> list = hbTscDao.getByTicketIds(ticketIds);

        //從redis中取出會員編號對應的購物車 票券編號及數量
        RedisCart cart = carSv.getCart(memId);
        //再取出票券id及數量的map集合
        Map<Integer, Integer> items = cart.getItems();
        //取出票勸id集合
        Set<Integer> ticketIds = items.keySet();
        //用票券編號集合調用hibernate的方法取得資料庫所需要的所有資訊
        List<CartInfo> list = tscSv.getByTicketIds(ticketIds, items);

        //遍歷會員購物車資料

        req.setAttribute("list", list);
        req.setAttribute("memId", memId);
        req.setAttribute("coupen",coupen);
        req.getRequestDispatcher("/ticketShoppingCart/checkout.jsp").forward(req, resp);
    }

    //fetch返回json格式
    private void setJsonResponse(HttpServletResponse resp, Object obj) throws IOException {
        Gson gson = new Gson();
        String jsonData = gson.toJson(obj);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonData);
    }

    //錯誤圖片顯示
    protected void noImage(ServletOutputStream outputStream) throws IOException {
        InputStream is = getServletContext().getResourceAsStream("/images/error.jpg");
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
//        outputStream.write(bytes);
        is.close();
        outputStream.close();

    }
}
