package com.depthspace.ticketshoppingcart.service;

import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticket.model.old.TicketImagesJDBCDAO;
import com.depthspace.ticket.model.old.TicketJDBCDAO;
import com.depthspace.ticketshoppingcart.model.TicketInfoVO;
import com.depthspace.ticketshoppingcart.model.jdbc.TicketShoppingCartDAO_Interface;
import com.depthspace.ticketshoppingcart.model.jdbc.TicketShoppingCartJDBCDAO;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public TicketShoppingCartVO addTicketShoppingCart(TicketShoppingCartVO tsc){
        dao.insert(tsc);
        return tsc;
    }
//    購物車更新票券
    public TicketShoppingCartVO updateTicketShoppingCart(TicketShoppingCartVO tsc){
        dao.update(tsc);
        return tsc;
    }
    //會員購物車清空
    public void deleteTicketShoppingCart(Integer memId){

        dao.delete(memId);
    }
    //會員購物車刪除一列
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
    //取得 去票券及票券圖片表格
    //票券圖片、票券名稱、票券介紹、價格、數量、小計
    public List<TicketInfoVO> getList(Integer memId){
        return dao.getbyMemId(memId);
    }


}
