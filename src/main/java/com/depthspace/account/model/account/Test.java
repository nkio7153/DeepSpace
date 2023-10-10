package com.depthspace.account.model.account;

import java.util.List;

public class Test {
	public static void main(String[] args) {
		AccountJDBCDAO accountDAO = new AccountJDBCDAO();
		AccountVO accountVO = new AccountVO();

		// 測試用插入一筆資料
//	    accountVO.setAccountId(4);
//	    accountVO.setAccountName("彥廷的分帳表");
//	    accountVO.setMemId(6666);
//	    accountDAO.insert(accountVO);
//	    System.out.println("插入成功");

		// 測試更新一筆資料
//		accountVO.setAccountId(4);
//		accountVO.setAccountName("閔萱超極棒");
//		accountVO.setMemId(9453);
//		accountDAO.update(accountVO);
//		System.out.println("更新成功");

		// 測試刪除一筆資料
//		accountDAO.delete(2);
//	    System.out.println("刪除成功");

		// 測試取得全部資料
		List<AccountVO> all = accountDAO.getAll();
		for (AccountVO vo : all) {
			System.out.println("ACCOUNT_ID:" + vo.getAccountId());
			System.out.println("ACCOUNT_NAME:" + vo.getAccountName());
			System.out.println("MEM_ID:" + vo.getMemId());
			System.out.println("===================================");
		}
		System.out.println("已取得全部資料");

		// 測試用主鍵取得一列資料
		AccountVO vo = accountDAO.findByPrimaryKey(4);
		if (vo != null) {
			System.out.println("ACCOUNT_ID:" + vo.getAccountId());
			System.out.println("ACCOUNT_NAME:" + vo.getAccountName());
			System.out.println("MEM_ID:" + vo.getMemId());
			System.out.println("===================================");
		}
		System.out.println("已取得一列資料");
	}
}
