package com.depthspace.ticketorders.service;

import com.depthspace.member.model.MemVO;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.ticketshoppingcart.model.CartInfo;

import java.util.List;
import java.util.Map;

public interface ToService {
    //生成一筆訂單
    public TicketOrdersVO generateTicektOrders(TicketOrdersVO entity, List<CartInfo> ciList, MemVO memVO, Integer point);
    //更新一筆訂單
    public TicketOrdersVO updateTicektOrders(TicketOrdersVO entity);
    //刪除一筆訂單
    public TicketOrdersVO deleteTicektOrders(Integer orderId);
    //取得一筆訂單資料
    public List<TicketOrdersVO> getbyMemId(Integer MemId);
    //取得當前頁面所有訂單資料
    public TicketOrdersVO getbyId(Integer orderId);
    //取得當前頁面所有訂單資料
    public List<TicketOrdersVO> getAll(int currentPage);
    //取得所有訂單資料
    public List<TicketOrdersVO> getAll();
    //取得當前頁面資料
    public List<TicketOrdersVO> getAllByMemId(int currentPage, Integer memId);
    //取得訂單對應明細總數
    public long getTotal();

    public int getMemPageTotal(Integer memId);
    //join票券表格取得訂單明細中的票券名稱
    List<TicketOrdersVO> getToByCompositeQuery(Map<String, String[]> map, int page);
    int getToByCompositeQueryTotal(Map<String, String[]> map);
}
