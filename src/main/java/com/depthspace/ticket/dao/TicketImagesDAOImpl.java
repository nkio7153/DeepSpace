package com.depthspace.ticket.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.depthspace.ticket.model.TicketImagesVO;

public class TicketImagesDAOImpl implements TicketImagesDAO {
	private SessionFactory factory;

	public TicketImagesDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

//	private Session getSession() {
//		return factory.getCurrentSession();
//	}

//	@Override
//	public TicketImagesVO saveImage(byte[] imageBytes) {
//		Session session = factory.openSession();
//		TicketImagesVO ticketImage = new TicketImagesVO();
//		try {
//
//			ticketImage.setImage(imageBytes);
//			session.save(ticketImage);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ticketImage;
//	}

	@Override
	public List<TicketImagesVO> findImagesByTicketId(Integer ticketId) {

		List<TicketImagesVO> images = null;

		Session session = factory.openSession();

		try {
			Query query = session.createQuery("FROM TicketImagesVO WHERE ticketId = :ticketId", TicketImagesVO.class);
			query.setParameter("ticketId", ticketId);
			images = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return images;
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

	@Override
	public void TicketIsMainImage(byte isMainImage) {
		// TODO Auto-generated method stub

	}

}
