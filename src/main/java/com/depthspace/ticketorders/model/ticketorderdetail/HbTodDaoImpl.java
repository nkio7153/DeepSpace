package com.depthspace.ticketorders.model.ticketorderdetail;

import com.depthspace.promotion.model.promotiondetails.HbProDeDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HbTodDaoImpl implements HbTodDao_Interface{
    private SessionFactory factory;
    public HbTodDaoImpl(SessionFactory factory){
        this.factory=factory;
    }
    private Session getSession(){
        return factory.getCurrentSession();
    }
    //插入一筆資料
    @Override
    public int insert(TicketOrderDetailVO entity) {
        return (Integer)getSession().save(entity);
    }
    //更新一筆資料
    @Override
    public int update(TicketOrderDetailVO entity) {
        try{
            getSession().update(entity);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    //刪除一筆資料
    @Override
    public int delete(TicketOrderDetailVO.CompositeDetail id) {
        TicketOrderDetailVO tod = getSession().get(TicketOrderDetailVO.class, id);
        if(tod!=null){
            getSession().delete(tod);
            return 1;
        }else {
            return -1;
        }
    }
    //取得一筆資料
    @Override
    public TicketOrderDetailVO getById(TicketOrderDetailVO.CompositeDetail id) {
        return getSession().get(TicketOrderDetailVO.class, id);
    }
    //取得一筆訂單的所有明細
    @Override
    public List<TicketOrderDetailVO> getByOrderId(Integer orderId) {
        return getSession()
                .createQuery("from TicketOrderDetailVO where orderId= :orderId", TicketOrderDetailVO.class)
                .setParameter("orderId", orderId)
                .list();
    }
    //取得所有訂單明細
    @Override
    public List<TicketOrderDetailVO> getAll() {
        return getSession()
                .createQuery("from TicketOrderDetailVO", TicketOrderDetailVO.class)
                .list();
    }
    //取得當前頁面資料
    @Override
    public List<TicketOrderDetailVO> getAll(int currentPage) {
        return null;
    }
    //
    @Override
    public long getTotal() {
        return getSession().createQuery("select count(*) from TicketOrderDetailVO", Long.class).uniqueResult();
    }
}
