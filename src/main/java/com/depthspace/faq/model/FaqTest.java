package com.depthspace.faq.model;

import java.util.List;
import java.util.Scanner;

public class FaqTest {

	public static void main(String[] args) {
		FaqDAO dao = new FaqDAOImpl();
		Scanner sc = new Scanner(System.in);

		// 新增FAQ
//        System.out.println("請輸入新增的FAQ資料：");
//        System.out.print("流水號：");
//        int serialId = sc.nextInt();
//        System.out.print("答案編號：");
//        int faqNo = sc.nextInt();
//        System.out.print("答案名稱：");
//        String faqName = sc.next();
//        System.out.print("答案：");
//        String faqAns = sc.next();
//
//        FaqVO newFaq = new FaqVO(serialId, faqNo, faqName, faqAns);
//        dao.insertFaq(newFaq);
//        System.out.println("FAQ 已新增至資料庫。");

		// 更新FAQ
//		System.out.println("請輸入要更新的FAQ資料：");
//		System.out.print("流水號：");
//		int serialId = sc.nextInt();
//		System.out.print("問題編號：");
//		int faqNo = sc.nextInt();
//		System.out.print("新的答案名稱：");
//		String faqName = sc.next();
//		System.out.print("新的答案：");
//		String faqAns = sc.next();
//
//		FaqVO updatedFaq = new FaqVO(serialId, faqNo, faqName, faqAns);
//		dao.updateFaq(updatedFaq);
//		System.out.println("FAQ 已更新至資料庫。");

		// 刪除FAQ
//        System.out.print("請輸入要刪除的FAQ的流水號：");
//        int serialId = sc.nextInt();
//        dao.deleteFaq(serialId);
//        System.out.println("FAQ 已從資料庫刪除。");

		// 查詢FAQ
//        System.out.print("請輸入要查詢的FAQ的流水號：");
//        int serialId = sc.nextInt();
//        FaqVO foundFaq = dao.findByPrimaryKey(serialId);
//        if (foundFaq != null) {
//            System.out.println("找到了以下的FAQ：");
//            System.out.println(foundFaq);
//        } else {
//            System.out.println("找不到指定流水號的FAQ。");
//        }

		// 取得所有FAQ
		System.out.println("所有的FAQ列表：");
		List<FaqVO> allFaqs = dao.getAllFaqs();
		for (FaqVO faq : allFaqs) {
			System.out.println(faq);
		}

		// 關閉Scanner
		sc.close();
	}
}
