package com.depthspace.admin.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.depthspace.utils.HibernateUtil;

import java.util.List;
import java.util.Map;


public class AdminDAOImpl implements AdminDAO {

	private SessionFactory sessionFactory;

    public  AdminDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public int insert(AdminVO entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        return 1;
    }

    @Override
    public int update(AdminVO entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
        return 1;
    }

    @Override
    public int delete(AdminVO id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(id);
            transaction.commit();
        }
        return 1;
    }


    @Override
    public AdminVO getById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(AdminVO.class, id);
    }

    @Override
    public List<AdminVO> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<AdminVO> query = session.createQuery("from AdminVO", AdminVO.class);
        return query.list();
    }

    @Override
    public List<AdminVO> getByCompositeQuery(Map<String, String> map) {
        // 實現複合查詢的邏輯
        return null;
    }

    @Override
    public List<AdminVO> getAll(int currentPage) {
        Session session = sessionFactory.getCurrentSession();
        Query<AdminVO> query = session.createQuery("from AdminVO", AdminVO.class);
        query.setFirstResult((currentPage - 1) * 10);
        query.setMaxResults(10);
        return query.list();
    }

    @Override
    public long getTotal() {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery("select count(*) from AdminVO", Long.class);
        return query.uniqueResult();
    }
}
