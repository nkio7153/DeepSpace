package com.depthspace.ticketorders.service;

import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.ticketorders.model.ticketorderdetail.hibernate.HbTodDaoImpl;
import com.depthspace.ticketorders.model.ticketorderdetail.hibernate.HbTodDao;
import com.depthspace.utils.HibernateUtil;

import java.util.List;

public class TodServiceImpl implements TodService {
    private HbTodDao dao;
    //創建票券訂單dao的方法
    public TodServiceImpl(){

        dao=new HbTodDaoImpl(HibernateUtil.getSessionFactory());
    }
    //新增一筆訂單明細
    @Override
    public void addTod(TicketOrderDetailVO entity) {
        dao.insert(entity);
    }
    //取消一筆訂單對應訂單明細
    @Override
    public void deleteTod(Integer orderId) {
        dao.delete(orderId);
    }
    //取出一筆訂單對應訂單明細
    @Override
    public List<TicketOrderDetailVO> getAllbyOrderId(Integer orderId) {
        return dao.getByOrderId(orderId);
    }

    @Override
    public List<TicketOrderDetailVO> getAll(int currentPage, Integer orderId) {

        return null;
    }
    //取得一筆訂單列表總數
    @Override
    public long getTotal(Integer orderId) {

        return dao.getTotal(orderId);
    }
    //join票券表格取得訂單明細中的票券名稱
    @Override
    public List<Object[]> getResult(Integer orderId) {
        return dao.getResult(orderId);
    }
    //更新訂單明細
    @Override
    public void updateTod(TicketOrderDetailVO entity){
         dao.update(entity);
    }
//    取得一筆訂單明細
    @Override
    public TicketOrderDetailVO getById(Integer orderId, Integer ticketId){
        TicketOrderDetailVO.CompositeDetail id = new TicketOrderDetailVO.CompositeDetail(orderId, ticketId);
        return dao.getById(id);
    }
}
