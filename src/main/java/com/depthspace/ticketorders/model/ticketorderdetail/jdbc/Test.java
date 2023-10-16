package com.depthspace.ticketorders.model.ticketorderdetail.jdbc;

import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.ticketorders.model.ticketorderdetail.jdbc.TicketOrderDetailJDBCDAO;

public class Test {
    public static void main(String[] args) {
        TicketOrderDetailJDBCDAO todDAO = new TicketOrderDetailJDBCDAO();

        //測試用插入一筆資料
//        TicketOrderDetailVO todvo = new TicketOrderDetailVO(6, 7, 4, 600, 700, 900, "很好", Byte.valueOf("4"));
//        todDAO.insert(todvo);
//        System.out.println("插入成功");

        //測試更新一筆資料
//        TicketOrderDetailVO todvo2 = new TicketOrderDetailVO(3, 4,5, 700, 300, 500, "不好", Byte.valueOf("1"));
//        todDAO.update(todvo2);
//        System.out.println("更新成功");

        //測試刪除一筆資料
//        todDAO.delete(2,1);
//        System.out.println("刪除成功");

        //測試取得全部資料
//        List<TicketOrderDetailVO> all = todDAO.getAll();
//        for (TicketOrderDetailVO vo: all) {
//            System.out.println("ORDER_ID:"+vo.getOrderId());
//            System.out.println("TICKET_ID:"+vo.getTicketId());
//            System.out.println("QUANTITY:"+vo.getQuantity());
//            System.out.println("UNIT_PRICE:"+vo.getUnitPrice());
//            System.out.println("DISCOUNT_PRICE:"+vo.getDiscountPrice());
//            System.out.println("SUBTOTAL:"+vo.getSubtotal());
//            System.out.println("TICKET_REVIEWS:"+vo.getTicketReviews());
//            System.out.println("STARS:"+vo.getStars());
//            System.out.println("===================================");
//        }
//        System.out.println("已取得全部資料");

        //測試用主鍵取得一列資料
        TicketOrderDetailVO vo = todDAO.findByPrimaryKey(3, 4);
        if(vo!=null) {
            System.out.println("ORDER_ID:"+vo.getOrderId());
            System.out.println("TICKET_ID:"+vo.getTicketId());
            System.out.println("QUANTITY:"+vo.getQuantity());
            System.out.println("UNIT_PRICE:"+vo.getUnitPrice());
            System.out.println("DISCOUNT_PRICE:"+vo.getDiscountPrice());
            System.out.println("SUBTOTAL:"+vo.getSubtotal());
            System.out.println("TICKET_REVIEWS:"+vo.getTicketReviews());
            System.out.println("STARS:"+vo.getStars());
            System.out.println("===================================");
        }
        System.out.println("已取得一列資料");
    }
}
