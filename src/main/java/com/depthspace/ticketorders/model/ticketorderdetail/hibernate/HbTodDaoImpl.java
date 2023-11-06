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
            getSession().merge(entity);
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

    //join票券表格取得訂單明細中的票券名稱
    public List<Object[]> getResult(Integer orderId){
        String hql="select a.orderId, a.ticketId, b.ticketName, a.unitPrice, a.discountPrice, a.quantity, a.subtotal, a.ticketReviews, a.stars " +
                "from TicketOrderDetailVO a, TicketVO b " +
                "where a.orderId = :orderId AND a.ticketId = b.ticketId";
        return getSession().createQuery(hql)
                .setParameter("orderId",orderId)
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
    
    //取得一個票券的所有訂單明細
    @Override
    public List<TicketOrderDetailVO> findByTicketId(Integer ticketId) {
        
    return getSession()
                .createQuery("from TicketOrderDetailVO where ticketId = :ticketId", TicketOrderDetailVO.class)
                .setParameter("ticketId", ticketId)
                .list();
    }
}
