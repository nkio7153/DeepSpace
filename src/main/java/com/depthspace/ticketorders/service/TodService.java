package com.depthspace.ticketorders.service;

import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;

import java.util.List;

public interface TodService {
    //新增多筆訂單明細
    public void addTod(TicketOrderDetailVO entity);
    //取消一筆訂單相關訂單明細
    public void deleteTod(Integer orderId);
//    //取得一筆訂單相關訂單明細
    public List<TicketOrderDetailVO> getAllbyOrderId(Integer orderId);
//    //取得當前頁面資料
    public List<TicketOrderDetailVO> getAll(int currentPage, Integer orderId);
//    //取得一筆訂單列表總數
    public long getTotal(Integer orderId);
    //join票券表格取得訂單明細中的票券名稱
    List<Object[]> getResult(Integer orderId);

    void updateTod(TicketOrderDetailVO entity);
    //    取得一筆訂單明細
    TicketOrderDetailVO getById(Integer orderId, Integer ticketId);
}
