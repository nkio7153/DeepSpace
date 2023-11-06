//package com.depthspace.column.dao;
//
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//
//import com.depthspace.column.model.ColumnArticlesVO;
//
//public class ColumnArticlesDAOImpl implements ColumnArticlesDAO {
//	public static final int PAGE_MAX_RESULT = 10;
//	private SessionFactory factory;
//
//	public ColumnArticlesDAOImpl(SessionFactory factory) {
//		this.factory = factory;
//	}
//
//	private Session getSession() {
//		return factory.getCurrentSession();
//	}
//
//	@Override
//	public void insert(ColumnArticlesVO columnArticlesVO) {
////		getSession().save(columnArticlesVO);
//
//		Session session = getSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			session.save(columnArticlesVO);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw e;
//		}
//	}
//
//	@Override
//	public void update(ColumnArticlesVO columnArticlesVO) {
//		getSession().update(columnArticlesVO);
//	}
//
//	@Override
//	public void delete(Integer id) {
//		ColumnArticlesVO columnArticles = getSession().get(ColumnArticlesVO.class, id);
//		getSession().delete(columnArticles);
//	}
//
//	@Override
//	public ColumnArticlesVO getById(Integer artiId) {
//		return getSession().get(ColumnArticlesVO.class, artiId);
//	}
//
//	@Override
//	public long getTotal() {
////		return getSession().createQuery("select count(*) from ColumnArticlesVO", Long.class).uniqueResult();
//
//		Session session = getSession();
//		Transaction tx = null; // org.hibernate.Transaction
//		try {
//			tx = session.beginTransaction();
//			long count = session.createQuery("select count(*) from ColumnArticlesVO", Long.class).uniqueResult();
//			tx.commit();
//			return count;
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw e;
//		}
//
//	}
//
//	@Override
//	public List<ColumnArticlesVO> getAll(int currentPage) {
//		int first = (currentPage - 1) * PAGE_MAX_RESULT; // 計算當前頁面第一條索引
//		return getSession().createQuery("from ColumnArticlesVO", ColumnArticlesVO.class).setFirstResult(first)
//				.setMaxResults(PAGE_MAX_RESULT) // 每頁紀錄數量
//				.list(); // 查出的資料存於此列表
//	}
//
//	@Override
//	public List<ColumnArticlesVO> getAll() {
//		return getSession().createQuery("from ColumnArticlesVO", ColumnArticlesVO.class).list();
//	}
//
//}
