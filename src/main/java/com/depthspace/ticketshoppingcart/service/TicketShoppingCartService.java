package com.depthspace.ticketshoppingcart.service;

import com.depthspace.ticketorders.model.ticketorders.TicketOrdersDAO_Interface;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersJDBCDAO;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartDAO_Interface;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartJDBCDAO;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;

import java.sql.Timestamp;
import java.util.List;

public class TicketShoppingCartService {
    private TicketShoppingCartDAO_Interface dao;

    public TicketShoppingCartService() {
        dao=new TicketShoppingCartJDBCDAO();
    }
//    private Integer memId;
//    private Integer ticketId;
//    private Integer quantity;
//    private Timestamp addedDate;
    //購物車添加票券
    public TicketShoppingCartVO addTicketShoppingCart(Integer memId, Integer ticketId, Integer quantity, Timestamp addedDate){
        TicketShoppingCartVO tsc = new TicketShoppingCartVO();
        tsc.setMemId(memId);
        tsc.setTicketId(ticketId);
        tsc.setQuantity(quantity);
        tsc.setAddedDate(addedDate);
        dao.insert(tsc);
        return tsc;
    }
//    購物車更新票券
    public TicketShoppingCartVO updateTicketShoppingCart(Integer memId, Integer ticketId, Integer quantity, Timestamp addedDate){
        TicketShoppingCartVO tsc = new TicketShoppingCartVO();
        tsc.setMemId(memId);
        tsc.setTicketId(ticketId);
        tsc.setQuantity(quantity);
        tsc.setAddedDate(addedDate);
        dao.update(tsc);
        return tsc;
    }
    //購物車清空
    public void deleteTicketShoppingCart(Integer memId, Integer ticketId){
        dao.delete(memId, ticketId);
    }
    //取得購物車資訊
    public TicketShoppingCartVO getOneTicketShoppingCart(Integer memId, Integer ticketId){
        return dao.findByPrimaryKey(memId, ticketId);
    }
    //取得所有購物車資訊列表
    public List<TicketShoppingCartVO> getAll() {
        return dao.getAll();
    }
    //取得會員購物車列表
    public List<TicketShoppingCartVO> getAllbyMemId(Integer memId) {
        return dao.findByMemId(memId);
    }
}
