package com.depthspace.ticket.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.ticket.model.TicketImagesVO;

public abstract class TicketImagesDAOImpl  implements TicketImagesDAO {

	private SessionFactory factory;
	private TicketImagesVO ticketImage;
	
	public TicketImagesDAOImpl(SessionFactory factory) {
		this.factory = factory;		
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	public TicketImagesDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	@Override
	public TicketImagesVO saveImage(byte[] imageBytes) {
        Session session = factory.openSession();

        try {
            
            TicketImagesVO ticketImage = new TicketImagesVO();
          
            ticketImage.setImage(imageBytes);

            session.save(ticketImage);

        } catch (Exception e) {
            e.printStackTrace(); 
            }
		return ticketImage;
 
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
