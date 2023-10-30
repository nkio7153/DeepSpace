package com.depthspace.column.service;

import java.util.List;

import com.depthspace.column.dao.ColumnArticlesDAO;
import com.depthspace.column.model.ColumnArticlesVO;


public interface ColumnArticlesService {
	
	ColumnArticlesVO addArt(ColumnArticlesVO columnArticlesVO);
	
	ColumnArticlesVO updateEmp(ColumnArticlesVO columnArticlesVO);
	
	void deleteEmp(Integer artiId);
	
	ColumnArticlesVO getArtiByArtiId(Integer artiId);
	
	List<ColumnArticlesVO> getAllArti();
	
	List<ColumnArticlesVO> getAllArti2(int currentPage);
	
	int getPageTotal();

}
