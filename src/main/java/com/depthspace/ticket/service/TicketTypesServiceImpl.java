package com.depthspace.ticket.service;

import java.util.List;

import com.depthspace.ticket.dao.TicketTypesDAO;
import com.depthspace.ticket.dao.TicketTypesDAOImpl;
import com.depthspace.ticket.model.TicketTypesVO;
import com.depthspace.utils.HibernateUtil;

public class TicketTypesServiceImpl implements TicketTypesService {

	private TicketTypesDAO dao;
	public TicketTypesServiceImpl() {
		dao = new TicketTypesDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public Integer insert(TicketTypesVO ticketTypesVO) {
		return dao.insert(ticketTypesVO);
	}

	@Override
	public void update(TicketTypesVO ticketTypesVO) {
		dao.update(ticketTypesVO);
	}

	@Override
	public List<TicketTypesVO> getAll() {
		return dao.getAll();
	}

	@Override
	public TicketTypesVO getOneById(Integer ticketTyepId) {
		return dao.getOneById(ticketTyepId);
	}

}
