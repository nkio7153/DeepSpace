//package com.depthspace.column.service;
//
//import java.util.List;
//
//import com.depthspace.admin.model.model.AdminVO;
//import com.depthspace.column.dao.ColumnArticlesDAO;
//import com.depthspace.column.model.ColumnArticlesVO;
//import com.depthspace.column.model.ColumnTypesVO;
//
//
//public interface ColumnArticlesService {
//	//新增
//	ColumnArticlesVO addArt(ColumnArticlesVO columnArticlesVO);
//	//更新
//	ColumnArticlesVO updateColumnArticles(ColumnArticlesVO columnArticlesVO);
//	//刪除
//	void deleteEmp(Integer artiId);
//	//根據文章ID取得專欄VO
//	ColumnArticlesVO getArtiByArtiId(Integer artiId);
//	//取得所有專欄VO
//	List<ColumnArticlesVO> getAllArti();
//	//取得所有專欄VO根據分頁
//	List<ColumnArticlesVO> getAllArti2(int currentPage);
//	//取得所有專欄類型的類型VO
//	List<ColumnTypesVO> getAllColumnTypes();
//	//取得所有管理者的管理者VO
//	List<AdminVO> getAllAdmins();
//	
//	int getPageTotal();
//
//}
