package com.depthspace.ticketcollection.dao;

import java.util.List;

import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticketcollection.model.TicketCollectionVO;

public interface TicketCollectionDAO {
	
	//新增
	void insert(TicketCollectionVO ticketCollection);
	//更新
	int update(TicketCollectionVO ticketCollection);	
	//刪除(根據主鍵)
	boolean delete(Integer memId, Integer ticektId);
	//刪除全部(根據會員)
	int deleteAll(Integer memId);
	//取得(根據會員)
	List<TicketCollectionVO> getByMemId(Integer memId);
	//取得所有
	List<TicketCollectionVO> getAll();
	//取得總票券數
	long getTotal(Integer memId); 
	//查找(根據會員+票券)
    TicketCollectionVO findByMemberAndTicket(Integer memId, Integer ticektId);
}
