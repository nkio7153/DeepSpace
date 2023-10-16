package com.depthspace.ticketshoppingcart.model.hibernate;

import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;

import java.util.List;

public interface HbTscDAO_Interface {

    // 插入一個新的購物車項目
    int insert(TicketShoppingCartVO entity);

    // 更新現有的購物車項目
    int update(TicketShoppingCartVO entity);

    // 根據組合主鍵（會員ID和票券ID）刪除購物車項目
    int delete(TicketShoppingCartVO.CompositeDetail id);

    // 根據會員ID獲取會員購物車項目
    List<TicketShoppingCartVO> getByMemId(Integer memId);

    // 獲取所有購物車項目
    List<TicketShoppingCartVO> getAll();


    // 獲取指定頁面的所有購物車項目
    List<TicketShoppingCartVO> getAll(int currentPage);

    // 獲取購物車項目的總數量
    long getTotal();
}