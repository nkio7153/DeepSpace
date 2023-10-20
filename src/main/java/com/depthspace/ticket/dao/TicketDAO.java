package com.depthspace.ticket.dao;

import java.util.*;

import com.depthspace.ticket.model.TicketVO;


public interface TicketDAO {
	
		int insert(TicketVO ticketVO); //新增票券物件

		int update(TicketVO ticketVO); //更新票券物件
		
		int delete(Integer id); //刪除票券物件
		 
		TicketVO getById(Integer id); //取得票券根據ID
		
		List<TicketVO> getAll();
		
		List<TicketVO> getAll(int currentPage); //取得所有票券物件(分頁查詢)
		
		List<TicketVO> getByCompositeQuery(Map<String, String> map); //根據複雜條件查詢取得票券
		
		List<TicketVO> findTicketsByPartialName(String partialName); //模糊查詢
	
		int getTotal();
}
