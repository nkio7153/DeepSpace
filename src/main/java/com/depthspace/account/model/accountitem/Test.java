package com.depthspace.account.model.accountitem;

import java.util.List;
import java.sql.Date;

public class Test {
	public static void main(String[] args) {
		AccountItemJDBCDAO accountItemJDBCDAO = new AccountItemJDBCDAO();
		AccountItemVO accountItemVO = new AccountItemVO();
		
		// 測試用插入一筆資料
		accountItemVO.setAccountItemId(3);
	    accountItemVO.setAccountItem("買票");
	    accountItemVO.setDate(Date.valueOf("2023-01-04"));
	    accountItemVO.setConsumeAccount(450);
	    accountItemJDBCDAO.insert(accountItemVO);
	    System.out.println("插入成功");

		// 測試更新一筆資料
//		accountItemVO.setAccountItemId(2);
//	    accountItemVO.setAccountItem("唱歌");
//	    accountItemVO.setDate(Date.valueOf("2023-01-03"));
//	    accountItemVO.setConsumeAccount(900);
//	    accountItemJDBCDAO.update(accountItemVO);
//	    System.out.println("更新成功");

		// 測試刪除一筆資料
//		accountItemJDBCDAO.delete(2);
//	    System.out.println("刪除成功");

		// 測試取得全部資料
		List<AccountItemVO> all = accountItemJDBCDAO.getAll();
		for (AccountItemVO vo : all) {
			System.out.println("ACCOUNT_ITEM_ID:" + vo.getAccountItemId());
			System.out.println("ACCOUNT_ITEM:" + vo.getAccountItem());
			System.out.println("DATE:" + vo.getDate());
			System.out.println("CONSUME_ACCOUNT:" + vo.getConsumeAccount());
			System.out.println("===================================");
		}
		System.out.println("已取得全部資料");

		// 測試用主鍵取得一列資料
//		AccountItemVO vo = accountItemJDBCDAO.findByPrimaryKey(1);
//		if (vo != null) {
//			System.out.println("ACCOUNT_ITEM_ID:" + vo.getAccountItemId());
//			System.out.println("ACCOUNT_ITEM:" + vo.getAccountItem());
//			System.out.println("DATE:" + vo.getDate());
//			System.out.println("CONSUME_ACCOUNT:" + vo.getConsumeAccount());
//			System.out.println("===================================");
//		}
//		System.out.println("已取得一列資料");
	}
}
