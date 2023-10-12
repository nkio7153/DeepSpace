package com.depthspace.keywordqa.model;

import java.util.List;
import java.util.Scanner;


public class TestKeywordQa {
	public static void main(String[] args) {
		KeywordQaDAO dao = new KeywordQaDAOImpl();
		Scanner sc = new Scanner(System.in);

//      // 新增KeywordQa
//		System.out.println("請輸入新增的KeywordQa資料：");
//		System.out.print("流水號：");
//		int serialId = sc.nextInt();
//		System.out.print("關鍵問題類型：");
//		String kwTypes = sc.next();
//		System.out.print("關鍵字答案：");
//		String kwAns = sc.next();
//
//		KeywordQaVO newKeywordQa = new KeywordQaVO(serialId, kwTypes, kwAns);
//		dao.insert(newKeywordQa);
//		System.out.println("KeywordQa 已新增至資料庫。");

//    // 更新KeywordQa
//		System.out.println("請輸入要更新的KeywordQa資料：");
//		System.out.print("流水號：");
//		int serialId = sc.nextInt();
//		System.out.print("關鍵問題類型：");
//		String kwTypes = sc.next();
//		System.out.print("關鍵字答案：");
//		String kwAns = sc.next();
//
//		KeywordQaVO updatedKeywordQa = new KeywordQaVO(serialId, kwTypes, kwAns);
//		dao.update(updatedKeywordQa);
//		System.out.println("KeywordQa 已更新至資料庫。");

//    // 刪除KeywordQa
//		System.out.print("請輸入要刪除的KeywordQa的流水號：");
//		int serialId = sc.nextInt();
//		dao.delete(serialId);
//		System.out.println("KeywordQa 已從資料庫刪除。");

//    // 查詢KeywordQa
//		System.out.print("請輸入要查詢的KeywordQa的流水號：");
//		int serialId = sc.nextInt();
//		KeywordQaVO foundKeywordQa = dao.findByPrimaryKey(serialId);
//		if (foundKeywordQa != null) {
//			System.out.println("找到了以下的KeywordQa：");
//			System.out.println(foundKeywordQa);
//		} else {
//			System.out.println("找不到指定流水號的KeywordQa。");
//		}

		// 取得所有KeywordQa
		System.out.println("所有的KeywordQa列表：");
		List<KeywordQaVO> allKeywordQa = dao.getAll();
		for (KeywordQaVO keywordQa : allKeywordQa) {
			System.out.println(keywordQa);
		}

		// 關閉Scanner
		sc.close();
	}
}
