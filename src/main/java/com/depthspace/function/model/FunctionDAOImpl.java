package com.depthspace.function.model;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.depthspace.utils.HibernateUtil;

public class FunctionDAOImpl implements FunctionDAO{

	private SessionFactory sessionFactory;

    public  FunctionDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public int insert(FunctionVO entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        return 1;
    }

    @Override
    public int update(FunctionVO entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
        return 1;
    }

    @Override
    public int delete(FunctionVO id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(id);
            transaction.commit();
        }
        return 1;
    }


    @Override
    public FunctionVO getById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(FunctionVO.class, id);
    }

    @Override
    public List<FunctionVO> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<FunctionVO> query = session.createQuery("from FunctionVO", FunctionVO.class);
        return query.list();
    }

    @Override
    public List<FunctionVO> getByCompositeQuery(Map<String, String> map) {
        // 實現複合查詢的邏輯
        return null;
    }

    @Override
    public List<FunctionVO> getAll(int currentPage) {
        Session session = sessionFactory.getCurrentSession();
        Query<FunctionVO> query = session.createQuery("from FunctionVO", FunctionVO.class);
        query.setFirstResult((currentPage - 1) * 10);
        query.setMaxResults(10);
        return query.list();
    }

    @Override
    public long getTotal() {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery("select count(*) from FunctionVO", Long.class);
        return query.uniqueResult();
    }
}
