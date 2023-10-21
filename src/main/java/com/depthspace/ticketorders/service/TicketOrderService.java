package com.depthspace.ticketorders.service;

import com.depthspace.ticketorders.model.ticketorderdetail.hibernate.HbTodDaoImpl;
import com.depthspace.ticketorders.model.ticketorderdetail.hibernate.HbTodDao_Interface;
import com.depthspace.ticketorders.model.ticketorderdetail.jdbc.TicketOrderDetailDAO_Interface;
import com.depthspace.ticketorders.model.ticketorderdetail.jdbc.TicketOrderDetailJDBCDAO;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.ticketorders.model.ticketorders.hibernate.HbToDaoImpl;
import com.depthspace.ticketorders.model.ticketorders.hibernate.HbToDao_Interface;
import com.depthspace.ticketorders.model.ticketorders.jdbc.TicketOrdersDAO_Interface;
import com.depthspace.ticketorders.model.ticketorders.jdbc.TicketOrdersJDBCDAO;
import com.depthspace.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class TicketOrderService implements TicketOrderService_Interface{
    private HbToDao_Interface dao;
    private HbTodDao_Interface todDao;
    //創建票券訂單dao的方法
    public TicketOrderService(){

        dao=new HbToDaoImpl(HibernateUtil.getSessionFactory());
        todDao= new HbTodDaoImpl(HibernateUtil.getSessionFactory());
    }



    @Override
    public TicketOrdersVO addTicektOrders(TicketOrdersVO entity) {
        dao.insert(entity);
        return entity;
    }

    @Override
    public TicketOrdersVO updateTicektOrders(TicketOrdersVO entity) {
        dao.update(entity);
        return null;
    }

    @Override
    public TicketOrdersVO deleteTicektOrders(Integer orderId) {
        dao.delete(orderId);
        return null;
    }

    @Override
    public List<TicketOrdersVO> getbyMemId(Integer MemId) {

        return dao.getByMemId(MemId);
    }

    @Override
    public List<TicketOrdersVO> getAll() {
        return dao.getAll();
    }

    @Override
    public List<TicketOrdersVO> getAll(int currentPage) {
        return dao.getAll(currentPage);
    }
    //取得一筆訂單列表總數
    @Override
    public long getTotal(Integer orderId) {
        return todDao.getTotal(orderId);
    }
}
