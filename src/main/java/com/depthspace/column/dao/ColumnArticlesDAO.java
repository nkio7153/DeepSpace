package com.depthspace.column.dao;

import java.util.List;
import java.util.Map;

import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.ticket.model.TicketVO;

public interface ColumnArticlesDAO {
	
	void insert(ColumnArticlesVO columnArticlesVO);

	void update(ColumnArticlesVO columnArticlesVO);
	
	void delete(Integer artiId);
	
	long getTotal();
	
	List<ColumnArticlesVO> getAll(int currentPage);
	
	List<ColumnArticlesVO> getAll();

	ColumnArticlesVO getById(Integer artiId);
	
	List<ColumnArticlesVO> getByCompositeQuery(Map<String, List<String>> map);
		
}