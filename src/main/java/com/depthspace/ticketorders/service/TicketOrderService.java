package com.depthspace.ticketorders.service;

import com.depthspace.ticketorders.model.ticketorderdetail.hibernate.HbTodDaoImpl;
import com.depthspace.ticketorders.model.ticketorderdetail.hibernate.HbTodDao_Interface;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.ticketorders.model.ticketorders.hibernate.HbToDaoImpl;
import com.depthspace.ticketorders.model.ticketorders.hibernate.HbToDao_Interface;
import com.depthspace.ticketshoppingcart.model.hibernate.HbTscDaoImpl;
import com.depthspace.utils.HibernateUtil;

import java.util.List;

public class TicketOrderService implements TicketOrderService_Interface{
    private HbToDao_Interface dao;
    private HbTodDao_Interface todDao;
    private HbTscDaoImpl tscDao;
    //創建票券訂單dao的方法
    public TicketOrderService(){

        dao=new HbToDaoImpl(HibernateUtil.getSessionFactory());
        todDao= new HbTodDaoImpl(HibernateUtil.getSessionFactory());
        tscDao= new HbTscDaoImpl(HibernateUtil.getSessionFactory());
    }


    //生成一筆訂單、會員購物車清空、查出最新一筆訂單資料
    @Override
    public TicketOrdersVO generateTicektOrders(TicketOrdersVO entity) {
        TicketOrdersVO vo = null;
        //進入訂單生成
        if(entity != null){
            //新增一筆訂單
            dao.insert(entity);
            //會員購物車清空
            tscDao.deleteAll(entity.getMemId());
            //查出最新一筆訂單資料
            vo = dao.getLatestOrderByMemId(entity.getMemId());
        }
        return vo;
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
