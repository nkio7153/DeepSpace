package com.depthspace.ticket.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.ticket.model.TicketImagesVO;

public class TicketImagesDAOImpl  implements TicketImagesDAO {

	private SessionFactory factory;
	
	public TicketImagesDAOImpl(SessionFactory factory) {
		this.factory = factory;		
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public void insert(TicketImagesVO ticketImagesVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TicketImagesVO ticketImagesVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer serialId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TicketImagesVO findByPrimaryKey(Integer serialId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TicketImagesVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
