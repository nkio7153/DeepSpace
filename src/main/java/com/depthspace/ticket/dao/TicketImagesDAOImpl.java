package com.depthspace.ticket.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.depthspace.column.model.ColumnImagesVO;
import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.utils.HibernateUtil;

public class TicketImagesDAOImpl implements TicketImagesDAO {
	
	private SessionFactory factory;

	public TicketImagesDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

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
	public void save(TicketImagesVO ticketImage) {
		getSession().save(ticketImage);
	}
	
	@Override
	public void save(List<TicketImagesVO> ticketImages) {
		getSession().save(ticketImages);
	}

	@Override
	public void update(TicketImagesVO ticketImagesVO) {
		getSession().merge(ticketImagesVO);
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        if (ticketImagesVO.getSerialId() != null) { // 如果serialId存在，則更新圖片
//            session.update(ticketImagesVO);
//        } else { // 否則新增圖片
//            session.save(ticketImagesVO);
//        }
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
	
//    @Override
//    public void updata(TicketImagesVO imageVO) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        try {
//            session.beginTransaction();
//            session.updata(imageVO);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//            e.printStackTrace();
//        }
//    }

	@Override
	public TicketImagesVO getImageById(Integer serialId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	String hql = "FROM TicketImagesVO WHERE serialId = :serialId";
            Query<TicketImagesVO> query = session.createQuery(hql, TicketImagesVO.class);
            query.setParameter("serialId", serialId);
            TicketImagesVO image = query.uniqueResult();
            return query.uniqueResult();
        } catch (Exception e) {
        }
        return null;
	}

}
