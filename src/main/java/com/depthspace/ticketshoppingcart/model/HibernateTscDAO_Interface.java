package com.depthspace.ticketshoppingcart.model;

import java.util.List;
import java.util.Map;

public interface HibernateTscDAO_Interface {

    // 插入一個新的購物車項目
    int insert(TicketShoppingCartVO entity);

    // 更新現有的購物車項目
    int update(TicketShoppingCartVO entity);

    // 根據組合主鍵（會員ID和票券ID）刪除購物車項目
    int delete(TicketShoppingCartVO.CompositeDetail id);

    // 根據組合主鍵（會員ID和票券ID）獲取購物車項目
    TicketShoppingCartVO getById(TicketShoppingCartVO.CompositeDetail id);

    // 獲取所有購物車項目
    List<TicketShoppingCartVO> getAll();

    // 通過複合查詢獲取購物車項目
    List<TicketShoppingCartVO> getByCompositeQuery(Map<String, String> map);

    // 獲取指定頁面的所有購物車項目
    List<TicketShoppingCartVO> getAll(int currentPage);

    // 獲取購物車項目的總數量
    long getTotal();
}