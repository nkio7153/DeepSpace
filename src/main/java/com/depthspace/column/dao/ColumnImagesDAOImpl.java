package com.depthspace.column.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.depthspace.column.model.ColumnImagesVO;
import com.depthspace.utils.HibernateUtil;

public class ColumnImagesDAOImpl implements ColumnImagesDAO {

	private SessionFactory factory;

	public ColumnImagesDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int add(ColumnImagesVO columnImagesVO) {
		return (Integer) getSession().save(columnImagesVO);
	}


    @Override
    public ColumnImagesVO findByArticleId(Integer artiId) {
        // 假設只有一張主圖片
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM ColumnImagesVO WHERE columnArticles.artiId = :artiId AND isMainImage = 1";
            Query<ColumnImagesVO> query = session.createQuery(hql, ColumnImagesVO.class);
            query.setParameter("artiId", artiId);
            return query.uniqueResult();
        } catch (Exception e) {
            // 處理異常
        }
        return null;
    }

    @Override
    public void updateImg(ColumnImagesVO colImg) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(colImg);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            // 處理異常
        }
    }

}