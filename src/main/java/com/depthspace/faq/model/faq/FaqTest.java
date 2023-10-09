package com.depthspace.faq.model.faq;

import java.util.Scanner;

public class FaqTest {

	public static void main(String[] args) {
		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("要新增的流水號");
//		int serialId = sc.nextInt();
//		System.out.println("要新增的答案編號");
//		int faqNo = sc.nextInt();
//		System.out.println("要新增的答案名稱");
//		String faqName = sc.next();
//		System.out.println("要新增的答案");
//		String faqAns = sc.next();
//		
//		sc.close();
//		
//		FaqDAO dao = new FaqDAOImpl();
//		FaqVO faqVO = new FaqVO(serialId, faqNo, faqName, faqAns);
//		dao.insertFaq(faqVO);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("要新增(查詢)的流水號");
		int serialId = sc.nextInt();
		
		FaqDAO dao = new FaqDAOImpl();
		FaqVO faq = dao.findByPrimaryKey(serialId);
		System.out.println(faq);
	}
}
