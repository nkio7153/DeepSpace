package com.depthspace.ticketorders.model.ticketorderdetail.hibernate;

import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HbTodDaoImpl implements HbTodDao {
    private SessionFactory factory;
    public HbTodDaoImpl(SessionFactory factory){
        this.factory=factory;
    }
    private Session getSession(){
        return factory.getCurrentSession();
    }

    @Override
    public void insertBatch(List<TicketOrderDetailVO> todList) {
        for(TicketOrderDetailVO todVo:todList){
            getSession().save(todVo);
        }
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
    //刪除一筆2訂單對應訂單明細
    @Override
    public int delete(Integer orderId) {
        List<TicketOrderDetailVO> tods=getSession()
                .createQuery("from TicketOrderDetailVO where orderId= :orderId", TicketOrderDetailVO.class)
                .setParameter("orderId", orderId)
                .list();
        if(tods!=null){
            getSession().delete(tods);
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
    //取得一筆訂單對應訂單明細總數
    @Override
    public long getTotal(Integer orderId) {
        return getSession()
                .createQuery("select count(*) from TicketOrderDetailVO where orderId= :orderId", Long.class)
                .setParameter("orderId", orderId)
                .uniqueResult();
    }
}
