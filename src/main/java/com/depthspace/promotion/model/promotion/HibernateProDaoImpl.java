package com.depthspace.promotion.model.promotion;
import com.depthspace.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HibernateProDaoImpl implements HibernateProDao_Interface {

    private SessionFactory factory;

    public HibernateProDaoImpl() {
        this.factory = HibernateUtil.getSessionFactory();
    }
    private Session getSession(){
        return factory.getCurrentSession();
    }
    @Override
    public int insert(PromotionVO entity) {
        return (Integer)getSession().save(entity);
    }

    @Override
    public int update(PromotionVO entity) {
        try{
            getSession().update(entity);
            return 1;
        }catch (Exception e){
            return -1;
        }
    }


    @Override
    public int delete(Integer id) {

        PromotionVO pro = getSession().get(PromotionVO.class, id);
        if(pro!=null){
            getSession().delete(pro);
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public PromotionVO getById(Integer id) {
        return getSession().get(PromotionVO.class, id);
    }

    @Override
    public List<PromotionVO> getAll() {
        return getSession().createQuery("from PromotionVO", PromotionVO.class).list();
    }

    @Override
    public List<PromotionVO> getAll(int currentPage) {
        return null;
    }

    @Override
    public long getTotal() {
        return getSession().createQuery("select count(*) from PromotionVO", Long.class).uniqueResult();
    }
}
