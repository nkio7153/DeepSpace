package com.depthspace.ticket.dao;

import java.util.*;

import com.depthspace.ticket.model.TicketVO;


public interface TicketDAO {
	
		int insert(TicketVO ticketVO); //新增票券物件

		TicketVO update(TicketVO ticketVO); //更新票券物件
		
		int delete(Integer ticketId); //刪除票券物件
		 
		TicketVO getTicketById(Integer ticketId); //取得票券根據ID
		
		List<TicketVO> getAll();
		
		List<TicketVO> getAll(int currentPage); //取得所有票券物件(分頁查詢)
		
		List<TicketVO> getAllTicketsWithCity(); //取得縣市名
		
		List<TicketVO> getAllTicketsWithMainImages();
		
		long getTotal();

		List<TicketVO> getByCompositeQuery(Map<String, String> query); //根據複雜條件查詢取得票券

}
