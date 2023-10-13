package com.depthspace.ticket.model;

import java.util.*;


public interface TicketDAO_Interface2 {
	int insert(TicketVO ticketVO); //新增要回傳id
	int update(TicketVO ticketVO); //返回更新的行數
	int delete(Integer ticketId); //測試用：返回刪除的行數
    TicketVO getById(Integer ticketId);
    List<TicketVO> getAll();
    List<TicketVO> getByCompositeQuery(Map<String, String> map); //複合查詢
    List<TicketVO> getAll(int currentPage); //分頁
    long getTotal(); //獲得票券總數量
}
