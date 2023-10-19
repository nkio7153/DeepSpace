package com.depthspace.adminfunclist.model;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.utils.HibernateUtil;

public class AdminFuncListDAOImpl implements AdminFuncListDAO{
	private SessionFactory sessionFactory;

    public  AdminFuncListDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public int insert(AdminFuncListVO entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        return 1;
    }

    @Override
    public int update(AdminFuncListVO entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
        return 1;
    }

    @Override
    public int delete(AdminFuncListVO id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(id);
            transaction.commit();
        }
        return 1;
    }


    @Override
    public AdminFuncListVO getById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(AdminFuncListVO.class, id);
    }

    @Override
    public List<AdminFuncListVO> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<AdminFuncListVO> query = session.createQuery("from AdminFuncListVO", AdminFuncListVO.class);
        return query.list();
    }

    @Override
    public List<AdminFuncListVO> getByCompositeQuery(Map<String, String> map) {
        // 實現複合查詢的邏輯
        return null;
    }

    @Override
    public List<AdminFuncListVO> getAll(int currentPage) {
        Session session = sessionFactory.getCurrentSession();
        Query<AdminFuncListVO> query = session.createQuery("from AdminFuncListVO", AdminFuncListVO.class);
        query.setFirstResult((currentPage - 1) * 10);
        query.setMaxResults(10);
        return query.list();
    }

    @Override
    public long getTotal() {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery("select count(*) from AdminFuncListVO", Long.class);
        return query.uniqueResult();
    }

}
