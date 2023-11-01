package com.depthspace.ticketshoppingcart.model.hibernate;

import com.depthspace.ticketshoppingcart.model.CartInfo;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;

import java.util.List;
import java.util.Set;

public interface HbTscDao {

    // 插入一個新的購物車項目
    int insert(TicketShoppingCartVO entity);

    // 更新現有的購物車項目
    int update(TicketShoppingCartVO entity);

    // 根據組合主鍵（會員ID和票券ID）刪除購物車項目
    int delete(TicketShoppingCartVO.CompositeDetail id);

    //根據會員編號清空購物車
    int deleteAll(Integer memId);

    // 根據會員ID獲取會員購物車項目
    List<TicketShoppingCartVO> getByMemId(Integer memId);

    // 獲取所有購物車項目
    List<TicketShoppingCartVO> getAll();


    // 獲取指定頁面的所有購物車項目
    List<TicketShoppingCartVO> getAll(int currentPage);

    // 獲取購物車項目的總數量
    long getTotal();

    //根據ticketId集合取得對應的購物車清單
    List<CartInfo> getByTicketIds(Set<Integer> ticketIds);
}