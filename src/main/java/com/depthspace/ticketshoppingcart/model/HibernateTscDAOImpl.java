package com.depthspace.ticketshoppingcart.model;

import com.depthspace.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HibernateTscDAOImpl implements HibernateTscDAO_Interface {
    private SessionFactory factory;
    public HibernateTscDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }
    private Session getSession() {
        return factory.getCurrentSession();
    }
    @Override
    public int insert(TicketShoppingCartVO entity) {
        return (Integer) getSession().save(entity);
    }

    @Override
    public int update(TicketShoppingCartVO entity) {
        try {
            getSession().update(entity);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int delete(TicketShoppingCartVO.CompositeDetail id) {
        TicketShoppingCartVO tsc = getSession().get(TicketShoppingCartVO.class, id);
        if (tsc != null) {
            getSession().delete(tsc);
            // 回傳給 service，1代表刪除成功
            return 1;
        } else {
            // 回傳給 service，-1代表刪除失敗
            return -1;
        }
    }

    @Override
    public TicketShoppingCartVO getById(TicketShoppingCartVO.CompositeDetail id) {
        return null;
    }

//    @Override
//    public TicketShoppingCartVO getById(TicketShoppingCartVO.CompositeDetail id) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        TicketShoppingCartVO entity = session.get(TicketShoppingCartVO.class, id);
//        session.getTransaction().commit();
//        return entity;
//    }


    @Override
    public List<TicketShoppingCartVO> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<TicketShoppingCartVO> query = session.createQuery("from TicketShoppingCartVO", TicketShoppingCartVO.class);
        List<TicketShoppingCartVO> list = query.list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public List<TicketShoppingCartVO> getByCompositeQuery(Map<String, String> map) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TicketShoppingCartVO> cq = cb.createQuery(TicketShoppingCartVO.class);
        Root<TicketShoppingCartVO> root = cq.from(TicketShoppingCartVO.class);

        map.forEach((key, value) -> cq.where(cb.equal(root.get(key), value)));

        Query<TicketShoppingCartVO> query = session.createQuery(cq);
        List<TicketShoppingCartVO> list = query.list();

        session.getTransaction().commit();
        return list;
    }

    @Override
    public List<TicketShoppingCartVO> getAll(int currentPage) {
        int pageSize = 10; // 每頁的數量，你可以根據需要更改
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<TicketShoppingCartVO> query = session.createQuery("from TicketShoppingCartVO", TicketShoppingCartVO.class);
        query.setFirstResult((currentPage - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<TicketShoppingCartVO> list = query.list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public long getTotal() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Long> query = session.createQuery("select count(*) from TicketShoppingCartVO", Long.class);
        long total = query.uniqueResult();
        session.getTransaction().commit();
        return total;
    }
}
