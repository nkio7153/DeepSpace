package com.depthspace.ticketorders.model.ticketorders;

import java.sql.Timestamp;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        TicketOrdersJDBCDAO toDAO = new TicketOrdersJDBCDAO();

        // 測試用插入一筆資料
         TicketOrdersVO tovo = new TicketOrdersVO(1, 9, new Timestamp(System.currentTimeMillis()), 1000, 100, 900, Byte.valueOf("1"), Byte.valueOf("1"));
         toDAO.insert(tovo);
         System.out.println("插入成功");

        // 測試更新一筆資料
//         TicketOrdersVO tovo2 = new TicketOrdersVO(3, 3, new Timestamp(System.currentTimeMillis()), 1100, 110, 990, Byte.valueOf("2"), Byte.valueOf("2"));
//         toDAO.update(tovo2);
//         System.out.println("更新成功");

        // 測試刪除一筆資料
//         toDAO.delete(1);
//         System.out.println("刪除成功");

        // 測試取得全部資料
//         List<TicketOrdersVO> all = toDAO.getAll();
//         for (TicketOrdersVO vo : all) {
//             System.out.println("ORDER_ID:" + vo.getOrderId());
//             System.out.println("MEM_ID:" + vo.getMemId());
//             System.out.println("ORDER_DATE:" + vo.getOrderDate());
//             System.out.println("TOTAL_AMOUNT:" + vo.getTotalAmount());
//             System.out.println("POINTS_FEEDBACK:" + vo.getPointsFeedback());
//             System.out.println("AMOUNT_PAID:" + vo.getAmountPaid());
//             System.out.println("STATUS:" + vo.getStatus());
//             System.out.println("PAYMENT_METHOD:" + vo.getPaymentMethod());
//             System.out.println("===================================");
//         }
//         System.out.println("已取得全部資料");

        // 測試用主鍵取得一列資料

//        TicketOrdersVO vo = toDAO.findByPrimaryKey(1);
//        if (vo != null) {
//            System.out.println("ORDER_ID:" + vo.getOrderId());
//            System.out.println("MEM_ID:" + vo.getMemId());
//            System.out.println("ORDER_DATE:" + vo.getOrderDate());
//            System.out.println("TOTAL_AMOUNT:" + vo.getTotalAmount());
//            System.out.println("POINTS_FEEDBACK:" + vo.getPointsFeedback());
//            System.out.println("AMOUNT_PAID:" + vo.getAmountPaid());
//            System.out.println("STATUS:" + vo.getStatus());
//            System.out.println("PAYMENT_METHOD:" + vo.getPaymentMethod());
//            System.out.println("===================================");
//        }
//        System.out.println("已取得一列資料");
    }
}
