package com.depthspace.promotion.service;

import com.depthspace.promotion.model.promotion.PromotionVO;
import com.depthspace.promotion.model.promotion.hibernate.HbProDaoImpl;
import com.depthspace.promotion.model.promotion.hibernate.HbProDao_Interface;
import com.depthspace.ticket.oscardao.HbTiDao;
import com.depthspace.ticketshoppingcart.model.TicketInfoVO;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;
import com.depthspace.ticketshoppingcart.model.jdbc.TicketShoppingCartDAO_Interface;
import com.depthspace.ticketshoppingcart.model.jdbc.TicketShoppingCartJDBCDAO;

import java.util.List;

public class ProService {
    private HbProDao_Interface dao;
    public ProService(){
        dao=new HbProDaoImpl();
    }
    //新增促銷活動並取得最新一筆促銷明細編號
    public PromotionVO addPromotion(PromotionVO entity){
        PromotionVO vo = null;
        if(entity != null){
            dao.insert(entity);
            vo = dao.getLatestOrder();
        }
        return vo;
    }
    public PromotionVO update(PromotionVO entity){
        if(entity!=null){
            dao.update(entity);
        }
        return entity;
    }
    public PromotionVO delete(Integer promotionId){
        dao.delete(promotionId);
        return null;
    }
    public List<PromotionVO> getAll(){
        List<PromotionVO> list = dao.getAll();
        return list;
    }
}
