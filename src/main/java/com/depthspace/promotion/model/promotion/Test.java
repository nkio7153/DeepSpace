package com.depthspace.promotion.model.promotion;

import com.depthspace.promotion.model.promotion.jdbc.ProDaoImpl;

public class Test {
    public static void main(String[] args) {
        ProDaoImpl proDAO = new ProDaoImpl();

        // 測試用插入一筆資料
//        PromotionVO proVO = new PromotionVO(4, "Promo1", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), "This is a test promotion", null);
//        proDAO.insert(proVO);
//        System.out.println("插入成功");

        // 測試更新一筆資料
//         PromotionVO proVO2 = new PromotionVO(2, "Promo2", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), "This is another test promotion", null);
//         proDAO.update(proVO2);
//         System.out.println("更新成功");

        // 測試刪除一筆資料
//         proDAO.delete(4);
//         System.out.println("刪除成功");

        // 測試取得全部資料
//         List<PromotionVO> all = proDAO.getAll();
//         for (PromotionVO vo : all) {
//             System.out.println("PROMOTION_ID:" + vo.getPromotionId());
//             System.out.println("PROMO_NAME:" + vo.getPromoName());
//             System.out.println("START_DATE:" + vo.getStartDate());
//             System.out.println("END_DATE:" + vo.getEndDate());
//             System.out.println("DESCRIPTION:" + vo.getDescription());
//             System.out.println("===================================");
//         }
//         System.out.println("已取得全部資料");

        // 測試用主鍵取得一列資料
         PromotionVO vo = proDAO.findByPrimaryKey(1);
         if (vo != null) {
             System.out.println("PROMOTION_ID:" + vo.getPromotionId());
             System.out.println("PROMO_NAME:" + vo.getPromoName());
             System.out.println("START_DATE:" + vo.getStartDate());
             System.out.println("END_DATE:" + vo.getEndDate());
             System.out.println("DESCRIPTION:" + vo.getDescription());
             System.out.println("===================================");
         }
         System.out.println("已取得一列資料");
    }
}
