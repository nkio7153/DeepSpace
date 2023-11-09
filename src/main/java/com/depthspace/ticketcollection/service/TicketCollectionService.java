package com.depthspace.ticketcollection.service;

import java.util.List;

import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticketcollection.model.TicketCollectionVO;

public interface TicketCollectionService {
	//新增
	TicketCollectionVO add(TicketCollectionVO ticketCollection);
	//更新
	TicketCollectionVO update(TicketCollectionVO ticketCollection);
	//刪除一筆(組合主鍵)
	boolean deleteByCom(Integer memId, Integer ticketId);
	//刪除(根據會員)
	int deleteBymemId(Integer memId);
	//取得一筆(組合主鍵)
	TicketCollectionVO getOne(Integer memId, Integer ticketId);
	//取得(會員)
	List<TicketCollectionVO> getOne(Integer memId);	
	//取得全部
	List<TicketCollectionVO> getAll();
    //取得總票券數
    long getTotalTickets(Integer memId); 
	
}
