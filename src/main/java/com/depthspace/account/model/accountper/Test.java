package com.depthspace.account.model.accountper;

import java.util.List;

public class Test {
	public static void main(String[] args) {
		AccountPerJDBCDAO accountPerJDBCDAO = new AccountPerJDBCDAO();
		AccountPerVO accountPerVO = new AccountPerVO();
		
		// 測試用插入一筆資料
//		accountPerVO.setAccountPerId(4);
//		accountPerVO.setAccountPerName("買票");
//		accountPerVO.setAccountId(1);
//		accountPerVO.setMoneySum(450);
//		accountPerVO.setAccountSum(450);
//		accountPerVO.setDebt(450);
//		accountPerJDBCDAO.insert(accountPerVO);
//	    System.out.println("插入成功");

		// 測試更新一筆資料
//		accountPerVO.setAccountPerId(4);
//		accountPerVO.setAccountPerName("羅裕璋");
//		accountPerVO.setAccountId(1);
//		accountPerVO.setMoneySum(450);
//		accountPerVO.setAccountSum(450);
//		accountPerVO.setDebt(450);
//		accountPerJDBCDAO.update(accountPerVO);
//	    System.out.println("更新成功");

		// 測試刪除一筆資料
//		accountPerJDBCDAO.delete(4);
//	    System.out.println("刪除成功");

		// 測試取得全部資料
//		List<AccountPerVO> all = accountPerJDBCDAO.getAll();
//		for (AccountPerVO vo : all) {
//			System.out.println("ACCOUNT_PER_ID:" + vo.getAccountPerId());
//			System.out.println("ACCOUNT_PER_NAME:" + vo.getAccountPerName());
//			System.out.println("ACCOUNT_ID:" + vo.getAccountId());
//			System.out.println("MONEY_SUM:" + vo.getMoneySum());
//			System.out.println("ACCOUNT_SUM:" + vo.getAccountSum());
//			System.out.println("DEBT:" + vo.getDebt());
//			System.out.println("===================================");
//		}
//		System.out.println("已取得全部資料");

		// 測試用主鍵取得一列資料
		AccountPerVO vo = accountPerJDBCDAO.findByPrimaryKey(20002);
		if (vo != null) {
			System.out.println("ACCOUNT_PER_ID:" + vo.getAccountPerId());
			System.out.println("ACCOUNT_PER_NAME:" + vo.getAccountPerName());
			System.out.println("ACCOUNT_ID:" + vo.getAccountId());
			System.out.println("MONEY_SUM:" + vo.getMoneySum());
			System.out.println("ACCOUNT_SUM:" + vo.getAccountSum());
			System.out.println("DEBT:" + vo.getDebt());
			System.out.println("===================================");
		}
		System.out.println("已取得一列資料");
	}
}
