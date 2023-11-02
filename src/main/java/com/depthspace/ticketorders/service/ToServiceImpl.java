package com.depthspace.ticketorders.service;

import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.ticketorders.model.ticketorderdetail.hibernate.HbTodDaoImpl;
import com.depthspace.ticketorders.model.ticketorderdetail.hibernate.HbTodDao;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.ticketorders.model.ticketorders.hibernate.HbToDaoImpl;
import com.depthspace.ticketorders.model.ticketorders.hibernate.HbToDao;
import com.depthspace.ticketshoppingcart.model.CartInfo;
import com.depthspace.ticketshoppingcart.model.hibernate.HbTscDaoImpl;
import com.depthspace.ticketshoppingcart.service.RedisCartServiceImpl;
import com.depthspace.utils.HibernateUtil;
import com.depthspace.utils.JedisUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ToServiceImpl implements ToService {
    private HbToDao dao;
    private HbTodDao todDao;
    private HbTscDaoImpl tscDao;
    //創建票券訂單dao的方法
    public ToServiceImpl(){

        dao=new HbToDaoImpl(HibernateUtil.getSessionFactory());
        todDao= new HbTodDaoImpl(HibernateUtil.getSessionFactory());
        tscDao= new HbTscDaoImpl(HibernateUtil.getSessionFactory());
        RedisCartServiceImpl carSv = new RedisCartServiceImpl(JedisUtil.getJedisPool());
    }


    //生成一筆訂單、會員購物車清空、查出最新一筆訂單資料
    @Override
    public TicketOrdersVO generateTicektOrders(TicketOrdersVO entity, List<CartInfo> ciList) {
        TicketOrdersVO vo = null;
        //進入訂單生成
        if(entity != null){
            //新增一筆訂單
            dao.insert(entity);
            //查出最新一筆訂單資料
            vo = dao.getLatestOrderByMemId(entity.getMemId());
            //生成一筆訂單及多對應的多筆訂單明細
            List<TicketOrderDetailVO> todList = new ArrayList<>();
            for (CartInfo cart:ciList){
                Integer discountPrice;
                //取得原價
                Integer price = cart.getPrice();
                BigDecimal price2 = new BigDecimal(price);
                //取得折扣
                BigDecimal discount = cart.getDiscount();
                //計算折扣價
                if(discount == null){
                    discountPrice=price;
                }else{
                    discountPrice = price2.multiply(discount).intValue()/10;
                }
                Integer subtotal=discountPrice * cart.getQuantity();
                //把購物車資訊一一放入訂單明細中
                TicketOrderDetailVO todVo = new TicketOrderDetailVO();
                todVo.setOrderId(vo.getOrderId());
                todVo.setTicketId(cart.getTicketId());
                todVo.setQuantity(cart.getQuantity());
                todVo.setUnitPrice(price);
                todVo.setDiscountPrice(discountPrice);
                todVo.setSubtotal(subtotal);
                //訂單明細放入訂單明細集合
                todList.add(todVo);
            }
            //調用訂單明細dao生成多筆訂單明細
            todDao.insertBatch(todList);
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
