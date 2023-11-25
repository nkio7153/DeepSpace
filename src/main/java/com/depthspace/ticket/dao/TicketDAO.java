package com.depthspace.ticket.dao;

import java.util.*;

import com.depthspace.ticket.model.TicketTypesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;


public interface TicketDAO {
	
		int insert(TicketVO ticketVO); //新增票券物件

		TicketVO update(TicketVO ticketVO); //更新票券物件
		
		int delete(Integer ticketId); //刪除票券物件
		 
		List<TicketVO> getTicketById2(Integer ticketId); //取得票券根據ID
		
		TicketVO getTicketById(Integer ticketId); //取得票券根據ID
		
		List<TicketVO> getAll();
		
		List<TicketVO> getAll2(int currentPage); //取得所有票券物件(分頁查詢)
		List<TicketVO> getAllDESC(int currentPage);
		
		List<TicketVO> getAllTicketsWithCity(); //取得縣市名
		
		List<TicketTypesVO> getAllTicketTypeIds(Integer ticketTypeId); //取得所有票券類型		
		
		long getTotal(); //取得總票券數
		
		long getStatusTotal(); //取得總票券數(上架)
		
		List<TicketVO> getByCompositeQuery(Map<String, List<String>> map); //複合查詢取得票券

		List<TicketOrderDetailVO> findTicketOrderDetailsByTicketId(Integer ticketId);
		
		List<TicketVO> findAllWithOrder(String sortField, String sortOrder);
		
	    List<TicketVO> findAllWithOrder(int currentPage, String sortField, String sortOrder);

//		List<TicketVO> getAll2(int currentPage, String sortId, String sortOrder);
//		//評價相關
//		
//		int countOrderRatingsByTicketId(Integer ticketId); //取得有評價該票券的單數
//
//		List<Integer> getStarsByTicketId(Integer ticketId); //取得該票券的總星星評價數
//		
//		List<String> getReviewsByTicketId(Integer ticketId); //取得該票券評價內容
	    List<TicketVO> findTickets(int currentPage, String sortField, String sortOrder, Map<String, List<String>> filterMap);

}
