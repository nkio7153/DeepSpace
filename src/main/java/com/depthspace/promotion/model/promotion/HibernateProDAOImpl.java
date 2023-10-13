package com.depthspace.promotion.model.promotion;
import com.depthspace.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;

public class HibernateProDAOImpl implements HibernateProDAO {

    private SessionFactory sessionFactory;

    public  HibernateProDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public int insert(PromotionVO entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        return 1;
    }

    @Override
    public int update(PromotionVO entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
        return 1;
    }

    @Override
    public int delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        PromotionVO entity = session.get(PromotionVO.class, id);
        session.delete(entity);
        return 1;
    }

    @Override
    public PromotionVO getById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(PromotionVO.class, id);
    }

    @Override
    public List<PromotionVO> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<PromotionVO> query = session.createQuery("from PromotionVO", PromotionVO.class);
        return query.list();
    }

    @Override
    public List<PromotionVO> getByCompositeQuery(Map<String, String> map) {
        // 實現複合查詢的邏輯
        return null;
    }

    @Override
    public List<PromotionVO> getAll(int currentPage) {
        Session session = sessionFactory.getCurrentSession();
        Query<PromotionVO> query = session.createQuery("from PromotionVO", PromotionVO.class);
        query.setFirstResult((currentPage - 1) * 10);
        query.setMaxResults(10);
        return query.list();
    }

    @Override
    public long getTotal() {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery("select count(*) from PromotionVO", Long.class);
        return query.uniqueResult();
    }
}
