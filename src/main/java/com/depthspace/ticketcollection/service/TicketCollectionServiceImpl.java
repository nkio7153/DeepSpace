package com.depthspace.ticketcollection.service;

import java.util.List;

import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticketcollection.dao.TicketCollectionDAO;
import com.depthspace.ticketcollection.dao.TicketCollectionDAOImpl;
import com.depthspace.ticketcollection.model.TicketCollectionVO;
import com.depthspace.utils.HibernateUtil;

public class TicketCollectionServiceImpl implements TicketCollectionService{
	
	private TicketCollectionDAO dao;
	public TicketCollectionServiceImpl() {
		dao = new TicketCollectionDAOImpl(HibernateUtil.getSessionFactory());
	}
	//新增
	@Override
	public TicketCollectionVO add(TicketCollectionVO ticketCollection) {
		dao.insert(ticketCollection);
		return ticketCollection;
	}
	//更新
	@Override
	public TicketCollectionVO update(TicketCollectionVO ticketCollection) {
		dao.update(ticketCollection);
		return ticketCollection;
	}
	//刪除一筆(根據主鍵)
	@Override
	public void deleteByCom(Integer memId, TicketVO ticketVO) {
		TicketCollectionVO.CompositeDetail id = new TicketCollectionVO.CompositeDetail(memId, ticketVO);
        dao.delete(id);
	}
	//刪除(根據會員)
	@Override
	public int deleteBymemId(Integer memId) {
		return dao.deleteAll(memId);		
	}
	//取得(會員)
	@Override
	public List<TicketCollectionVO> getOne(Integer memId) {
		return dao.getByMemId(memId);
	}
	//取得全部
	@Override
	public List<TicketCollectionVO> getAll() {
		return dao.getAll();
	}
	//取得會員收藏數量
	@Override
	public long getTotalTickets(Integer memId) {
		return dao.getTotal(memId);
	}
}
