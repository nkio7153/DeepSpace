package com.depthspace.admin.model.jdbc;

import java.util.List;

import com.depthspace.admin.model.AdminVO;

public interface AdminDAO_Interface { // 定義一個名為 AdminDAO_Interface 的介面
	
	 public void insert(AdminVO adminVO); // 定義一個插入 AdminVO 對象到資料庫的方法
	 public void update(AdminVO adminVO); // 定義一個更新資料庫中 AdminVO 對象的方法
	 public void delete(Integer adminId); // 定義一個根據 adminId 刪除資料庫中 AdminVO 對象的方法
	 public AdminVO findByPrimaryKey(Integer adminId); // 定義一個根據主鍵 adminId 查找 AdminVO 對象的方法
	 public List<AdminVO> getAll(); // 定義一個獲取所有 AdminVO 對象的方法
	 public AdminVO findByAdminId(Integer adminId); // 定義一個根據 adminId 查找 AdminVO 對象的方法
	 public AdminVO getAdminInfo(String adminAcc); // 定義一個根據 adminAcc 獲取單個 AdminVO 對象的方法
}
