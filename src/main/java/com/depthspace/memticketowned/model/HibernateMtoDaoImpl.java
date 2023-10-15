package com.depthspace.memticketowned.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HibernateMtoDaoImpl implements HibernateMtoDao_Interface {
    private SessionFactory factory;

    public HibernateMtoDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }
    private Session getSession(){
        return factory.getCurrentSession();
    }

    @Override
    public int insert(MemTicketOwnedVO entity) {
        return (Integer)getSession().save(entity);
    }

    @Override
    public int update(MemTicketOwnedVO entity) {
        try{
            getSession().update(entity);
            return 1;
        }catch(Exception e){
            return -1;
        }
    }

    @Override
    public int delete(Integer id) {
        MemTicketOwnedVO mto = getSession().get(MemTicketOwnedVO.class, id);
        if(mto!=null){
            getSession().delete(mto);
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public MemTicketOwnedVO getById(Integer id) {
        return getSession().get(MemTicketOwnedVO.class, id);
    }

    @Override
    public List<MemTicketOwnedVO> getAll() {
        return getSession().createQuery("from MemTicketOwnedVO ", MemTicketOwnedVO.class).list();
    }

    @Override
    public List<MemTicketOwnedVO> getAll(int currentPage) {
        return null;
    }

    @Override
    public long getTotal() {
        return getSession().createQuery("select count(*) from MemTicketOwnedVO", Long.class).uniqueResult();
    }
}
