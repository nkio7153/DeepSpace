package com.depthspace.promotion.model.promotiondetails;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        PromotionDetailsJDBCDAO pdoDAO = new PromotionDetailsJDBCDAO();

        // 測試用插入一筆資料
//        PromotionDetailsVO pdoVO = new PromotionDetailsVO(4, 1, 10.5);
//        pdoDAO.insert(pdoVO);
//        System.out.println("插入成功");

        // 測試更新一筆資料
//         PromotionDetailsVO pdoVO2 = new PromotionDetailsVO(4, 1, 8.5);
//         pdoDAO.update(pdoVO2);
//         System.out.println("更新成功");

        // 測試刪除一筆資料
         pdoDAO.delete(4, 1);
         System.out.println("刪除成功");

        // 測試取得全部資料
//         List<PromotionDetailsVO> all = pdoDAO.getAll();
//         for (PromotionDetailsVO vo : all) {
//             System.out.println("PROMOTION_ID:" + vo.getPromotionId());
//             System.out.println("TICKET_ID:" + vo.getTicketId());
//             System.out.println("DISCOUNT:" + vo.getDiscount());
//             System.out.println("===================================");
//         }
//         System.out.println("已取得全部資料");

        // 測試用主鍵取得一列資料
//        PromotionDetailsVO vo = pdoDAO.findByPrimaryKey(1, 1);
//        if (vo != null) {
//            System.out.println("PROMOTION_ID:" + vo.getPromotionId());
//            System.out.println("TICKET_ID:" + vo.getTicketId());
//            System.out.println("DISCOUNT:" + vo.getDiscount());
//            System.out.println("===================================");
//        }
//        System.out.println("已取得一列資料");
    }
}
