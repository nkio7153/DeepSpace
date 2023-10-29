package com.depthspace.ticketshoppingcart.controller;
import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.ticket.oscardao.HbTiDao;
import com.depthspace.ticketshoppingcart.model.TicketInfoVO;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;
import com.depthspace.ticketshoppingcart.service.TicketShoppingCartService;
import com.depthspace.utils.HibernateUtil;
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
public class TicketShoppingCartServlet extends HttpServlet {
    private TicketShoppingCartService tscSv;

    @Override
    public void init() throws ServletException {
        tscSv = new TicketShoppingCartService();
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

    //查出會員購物車清單
    protected void doMemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
//            System.out.println(memId);
//            System.out.println(ticketId);
//            System.out.println(quantity);
        } catch (NumberFormatException e) {
            // 處理轉型錯誤
            e.printStackTrace();
            return;
        }
        TicketShoppingCartService tscSv = new TicketShoppingCartService();
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
