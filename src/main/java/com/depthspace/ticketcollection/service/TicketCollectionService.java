package com.depthspace.ticketcollection.service;

import java.util.List;

import com.depthspace.ticketcollection.model.TicketCollectionVO;

public interface TicketCollectionService {
	//新增
	public TicketCollectionVO add(TicketCollectionVO ticketCollection);
	//更新
	public TicketCollectionVO update(TicketCollectionVO ticketCollection);
	//刪除一筆(組合主鍵)
	public int deleteByCom(Integer memId, Integer ticketId);
	//刪除(根據會員)
	public int deleteBymemId(Integer memId);
//	//取得一筆(組合主鍵)
//	public TicketCollectionVO getOne(Integer memId, Integer ticketId);
	//取得(會員)
	public List<TicketCollectionVO> getOne(Integer memId);	
	//取得全部
	public List<TicketCollectionVO> getAll();
    //取得總票券數
    long getTotalTickets(Integer memId); 
	
}
