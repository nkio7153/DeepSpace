package com.depthspace.column.service;

import java.util.List;
import java.util.Map;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.column.model.ColumnTypesVO;


public interface ColumnArticlesService {
	
	ColumnArticlesVO addArt(ColumnArticlesVO columnArticlesVO);

	ColumnArticlesVO updateColumnArticles(ColumnArticlesVO columnArticlesVO);

	void deleteEmp(Integer artiId);

	ColumnArticlesVO getArtiByArtiId(Integer artiId);		

    long getTotal();   	

	List<ColumnArticlesVO> getAllArti();	//取得所有專欄VO

	List<ColumnArticlesVO> getAllArti2(int currentPage);	//取得所有專欄VO根據分頁

	List<ColumnTypesVO> getAllColumnTypes();	//取得所有專欄類型的類型VO
	
	List<ColumnArticlesVO> getAllColumnTypes(Integer colTypeId);

	List<AdminVO> getAllAdmins();	//取得所有管理者的管理者VO

	List<ColumnArticlesVO> getAllSortById(String sort);	//根據id排序
	
	int getPageTotal();
	
	List<ColumnArticlesVO> getColumnArticlesByCompositeQuery(Map<String, String[]> queryMap);

}