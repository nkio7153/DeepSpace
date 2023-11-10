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
		
		List<TicketVO> getAllTicketsWithCity(); //取得縣市名
		
		List<TicketTypesVO> getAllTicketTypeIds(Integer ticketTypeId); //取得所有票券類型		
		
		long getTotal(); //取得總票券數
		
		List<TicketVO> getByCompositeQuery(Map<String, List<String>> map); //複合查詢取得票券

		public List<TicketOrderDetailVO> findTicketOrderDetailsByTicketId(Integer ticketId);
//		//評價相關
//		
//		int countOrderRatingsByTicketId(Integer ticketId); //取得有評價該票券的單數
//
//		List<Integer> getStarsByTicketId(Integer ticketId); //取得該票券的總星星評價數
//		
//		List<String> getReviewsByTicketId(Integer ticketId); //取得該票券評價內容
		

}
