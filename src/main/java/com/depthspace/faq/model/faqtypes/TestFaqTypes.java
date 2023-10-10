package com.depthspace.faq.model.faqtypes;

import java.util.List;
import java.util.Scanner;


public class TestFaqTypes {

    public static void main(String[] args) {
    	FaqTypesDAO dao = new FaqTypesDAOImpl();
        Scanner sc = new Scanner(System.in);

//        // 新增FaqTypes
//        System.out.println("請輸入新增的FaqTypes資料：");
//        System.out.print("問題編號：");
//        int faqNo = sc.nextInt();
//        System.out.print("問題類型：");
//        String qTypes = sc.next();
//
//        FaqTypesVO newFaqTypes = new FaqTypesVO(faqNo, qTypes);
//        dao.insert(newFaqTypes);
//        System.out.println("FaqTypes 已新增至資料庫。");

//        // 更新FaqTypes
        System.out.println("請輸入要更新的FaqTypes資料：");
        System.out.print("問題編號：");
        int faqNo = sc.nextInt();
        System.out.print("問題類型：");
        String qTypes = sc.next();

        FaqTypesVO updatedFaqTypes = new FaqTypesVO(faqNo, qTypes);
        dao.update(updatedFaqTypes);
        System.out.println("FaqTypes 已更新至資料庫。");

//        // 刪除FaqTypes
//        System.out.print("請輸入要刪除的FaqTypes的流水號：");
//        int faqNo = sc.nextInt();
//        dao.delete(faqNo);
//        System.out.println("FaqTypes 已從資料庫刪除。");

//        // 查詢FaqTypes
//        System.out.print("請輸入要查詢的FaqTypes的流水號：");
//        int faqNo = sc.nextInt();
//        FaqTypesVO foundFaqTypes = dao.findByPrimaryKey(faqNo);
//        if (foundFaqTypes != null) {
//            System.out.println("找到了以下的FAQTYPES：");
//            System.out.println(foundFaqTypes);
//        } else {
//            System.out.println("找不到指定流水號的FAQTYPES。");
//        }

        // 取得所有FAQ
        System.out.println("所有的FaqTypes列表：");
        List<FaqTypesVO> allFaqTypes = dao.getAll();
        for (FaqTypesVO faqTypes : allFaqTypes) {
            System.out.println(faqTypes);
        }

        // 關閉Scanner
        sc.close();
    }
}
