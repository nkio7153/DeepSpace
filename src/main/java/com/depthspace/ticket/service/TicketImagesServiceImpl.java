package com.depthspace.ticket.service;

import com.depthspace.ticket.model.TicketImagesVO;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TicketImagesServiceImpl implements TicketImagesService {

	private Session session;

	public TicketImagesServiceImpl(Session session) {
	        this.session = session;
	    }

	@Override
	public void save(TicketImagesVO ticketImage) {
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

		} finally {

			session.close();
		}
	}
}
