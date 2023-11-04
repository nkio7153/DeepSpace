package com.depthspace.ticketorders.service;

import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.ticketshoppingcart.model.CartInfo;

import java.util.List;

public interface ToService {
    //生成一筆訂單
    public TicketOrdersVO generateTicektOrders(TicketOrdersVO entity, List<CartInfo> ciList);
    //更新一筆訂單
    public TicketOrdersVO updateTicektOrders(TicketOrdersVO entity);
    //刪除一筆訂單
    public TicketOrdersVO deleteTicektOrders(Integer orderId);
    //取得一筆訂單資料
    public List<TicketOrdersVO> getbyMemId(Integer MemId);
    //取得所有訂單資料
    public List<TicketOrdersVO> getAll();
    //取得當前頁面資料
    public List<TicketOrdersVO> getAll(int currentPage);
    //取得訂單對應明細總數
    public long getTotal(Integer orderId);


}