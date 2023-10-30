package com.depthspace.ticketshoppingcart.controller;
import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticket.oscardao.HbTiDao;
import com.depthspace.ticket.service.TicketService;
import com.depthspace.ticket.service.TicketServiceImpl;
import com.depthspace.ticketshoppingcart.CartVO;
import com.depthspace.ticketshoppingcart.model.TicketInfoVO;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;
import com.depthspace.ticketshoppingcart.service.TicketShoppingCartService;
import com.depthspace.utils.HibernateUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.*;

@WebServlet("/tsc/*")
public class TicketShoppingCartServlet extends HttpServlet {
    private TicketShoppingCartService tscSv;
    private TicketService tiSv;

    @Override
    public void init() throws ServletException {
        tscSv = new TicketShoppingCartService();
        tiSv = new TicketServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        switch (pathInfo) {
            case "/listAll":
                doList(req, resp);
                break;
            case "/deleteAll":
                doDeleteAll(req, resp);
                break;
            case "/index":
                doIndex(req, resp);
                break;
            case "/image":
                doImage(req, resp);
                break;
            case "/checkout":
                checkout(req, resp);
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
            case "/flush":
                doFlush(req, resp);
                break;
            case "/deleteByFetch":
                doDeleteByFetch(req, resp);
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
        for (TicketShoppingCartVO vo : list) {
            uniqueMemIds.add(vo.getMemId());
        }
        req.setAttribute("list", list);
        req.setAttribute("uniqueMemIds", uniqueMemIds);
        req.getRequestDispatcher("/ticketShoppingCart/listAllCart.jsp").forward(req, resp);
    }

    //查出會員購物車清單(目前非會員已加入cookie，還沒實現頁面跳轉取值，還需考慮促銷顯示及計算，庫存量顯示
    protected void doMemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String cartJson=null;
        for (Cookie cookie: cookies) {
            if("cart".equals(cookie.getName())){
                cartJson=URLEncoder.encode(cookie.getValue(),"UTF-8");
                break;
            }
        }

        //如果cookie中含有購物車資料執行以下
        if(cartJson != null){
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<Integer, CartVO>>() {}.getType();
            HashMap<Integer, CartVO> carts = gson.fromJson(cartJson, type);
            List<CartVO> list = new ArrayList<>();
            for(Map.Entry<Integer, CartVO> entry: carts.entrySet()){
                CartVO cart = entry.getValue();
                list.add(cart);
            }
            req.setAttribute("list",list);

            //如果cookie中不含有購物車資料執行以下
        }else {
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

//            String imageName = "image_" + vo.getSerialId() + ".jpg"; // 隨便取一個唯一的名字
//            String imagePath = getServletContext().getRealPath("/images/") + imageName;
//            try (FileOutputStream fos = new FileOutputStream(imagePath)) {
//                fos.write(imageBytes);
//            }
//
//            vo.setBase64Image("/images/" + imageName);

                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                vo.setBase64Image(base64Image);
            }

            req.setAttribute("list", list);
            req.setAttribute("memId", memId);
            req.getRequestDispatcher("/ticketShoppingCart/memAllCart.jsp").forward(req, resp);
        }
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
        TicketShoppingCartVO tscVo = new TicketShoppingCartVO(memId, ticketId, quantity);
        tscSv.updateTicketShoppingCart(tscVo);
        setJsonResponse(resp, quantity);
    }

    protected void doIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TicketShoppingCartService tscSv = new TicketShoppingCartService();
        List<TicketShoppingCartVO> list = tscSv.getAll();
        HashSet<Integer> uniqueMemIds = new HashSet<>();
        for (TicketShoppingCartVO vo : list) {
            uniqueMemIds.add(vo.getMemId());
        }
        req.setAttribute("uniqueMemIds", uniqueMemIds);
        req.getRequestDispatcher("/ticketShoppingCart/index.jsp").forward(req, resp);
    }

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
        tscSv.deleteTicketShoppingCart(memId, ticketId);
        setJsonResponse(resp, "刪除成功");
    }

    //會員購物車清空
    protected void doDeleteAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer memId;
        try {
            memId = Integer.valueOf(req.getParameter("memId"));
        } catch (NumberFormatException e) {
            // 處理轉型錯誤
            return;
        }
        TicketShoppingCartService tscSv = new TicketShoppingCartService();
        if (memId != null) {
            tscSv.deleteTicketShoppingCart(memId);
        }
        List<TicketShoppingCartVO> list = tscSv.getAllbyMemId(memId);
        req.setAttribute("list", list);
        req.setAttribute("memId", memId);
        req.getRequestDispatcher("/ticketShoppingCart/memOrderList.jsp").forward(req, resp);
    }

    protected void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer memId;
        Integer ticketId;
        Integer quantity;
        try {
            memId = Integer.valueOf(req.getParameter("memId"));
            ticketId = Integer.valueOf(req.getParameter("ticketId"));
            quantity = Integer.valueOf(req.getParameter("quantity"));

        } catch (NumberFormatException e) {
            // 處理轉型錯誤
            e.printStackTrace();
            return;
        }
        if(memId != null) {
            //會員登入的情況，第一版先存在資料庫
//            TicketShoppingCartService tscSv = new TicketShoppingCartService();
            if (memId != null && ticketId != null && quantity != null) {
                TicketShoppingCartVO tsc = new TicketShoppingCartVO(memId, ticketId, quantity);
                tscSv.addTicketShoppingCart(tsc);
            }
            List<TicketInfoVO> list = tscSv.getList(memId);
            for (TicketInfoVO vo : list) {
                byte[] imageBytes = vo.getImage();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                vo.setBase64Image(base64Image);
            }
            req.setAttribute("list", list);
            req.setAttribute("memId", memId);
            req.getRequestDispatcher("/ticketShoppingCart/memAllCart.jsp").forward(req, resp);
        }else{
            //非會員購物車資料存在cookie
            Gson gson = new Gson();
            //用票券id取得票券資料
            TicketVO vo = tiSv.getTicketById(ticketId);
            //用票券id取得票券主圖序列號
            HbTiDao hbTiDao = new HbTiDao(HibernateUtil.getSessionFactory());
            TicketImagesVO tiVo = hbTiDao.getByTicketId(ticketId);
            //先檢查cookie是否已經存在，如果已存在，就用原本的集合把新的物件把進去，不存在，就用新的集合放進去
            Cookie[] cookies = req.getCookies();
            HashMap<Integer, CartVO> carts = new HashMap<>();
            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("cart".equals(cookie.getName())) {
                        Type type = new TypeToken<HashMap<Integer, CartVO>>() {}.getType();
                        carts = gson.fromJson(URLEncoder.encode(cookie.getValue(), "UTF-8"), type);
                    }
                }
            }
            //更新或添加購物車項目
            if(carts.containsKey(ticketId)){
                //如果商品已在購物車中，更新數量
                CartVO existingCart = carts.get(ticketId);
                existingCart.setQuantity(existingCart.getQuantity()+1);
                carts.put(ticketId, existingCart);
            }else{
                //創立一個專門存進非會員購物車資訓的物件
                CartVO cartVO = new CartVO(vo.getTicketId(), tiVo.getSerialId(), vo.getTicketName(), vo.getDescription(), vo.getPrice(), 1);
                carts.put(ticketId,cartVO);
            }
            //序列化更新後的購物車並加到Cookie中
            String cartJson = gson.toJson(carts);
            //創建一個cookie保存購物車資料
            Cookie cartCookie=new Cookie("cart", URLEncoder.encode(cartJson,"UTF-8"));
            cartCookie.setMaxAge(604800);//設置為7天保存時間
            //將cookie添加到響應中
            resp.addCookie(cartCookie);
            //返回成功響應
            setJsonResponse(resp,"非會員購物車資訊儲存成功");
        }
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
        Integer memId;
        try {
            memId = Integer.valueOf(req.getParameter("memId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        List<TicketInfoVO> list = tscSv.getList(memId);
        req.setAttribute("list", list);
        req.setAttribute("memId", memId);
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
        outputStream.write(bytes);
        is.close();
        outputStream.close();

    }
}
