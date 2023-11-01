package com.depthspace.ticket.service;

import com.depthspace.ticket.dao.TicketImagesDAO;
import com.depthspace.ticket.dao.TicketImagesDAOImpl;
import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.utils.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TicketImagesServiceImpl implements TicketImagesService {

//	private TicketImagesDAO dao;
//
//	public TicketImagesServiceImpl() {
//		dao = new TicketImagesDAOImpl(HibernateUtil.getSessionFactory());
//	}

	private Session session;

	public TicketImagesServiceImpl(Session session) {
		this.session = session;
	}

	@Override
	public void save(TicketImagesVO ticketImage) {
//		dao.save(ticketImage);
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(ticketImage);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void saveAll(List<TicketImagesVO> ticketImages) {
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        for (TicketImagesVO ticketImage : ticketImages) {
	            session.save(ticketImage);
	        }
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	}

	@Override
	public TicketImagesVO getTicketImagesById(Integer ticketId) {
		// TODO Auto-generated method stub
		return null;
	}
}
