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
//		Transaction tx = null;
//		try {
//			tx = getSession().beginTransaction();  // 開始事務
//			int id = (Integer) getSession().save(columnImagesVO);
//			tx.commit();  // 提交事務
//			return id;
//		} catch (RuntimeException e) {
//			if (tx != null) {
//				tx.rollback();  // 如果出現異常，回滾事務
//			}
//			throw e;  // 再次拋出異常
//		}
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
