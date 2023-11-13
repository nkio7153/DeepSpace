package com.depthspace.ticket.service;

import java.util.List;
import java.util.Map;

import com.depthspace.attractions.model.CityVO;
import com.depthspace.ticket.model.TicketTypesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;


public interface TicketService {
	
	//新增票券
    void addTicket(TicketVO ticketVO);
    //更新票券
    TicketVO updateTicket(TicketVO ticketVO);
    //刪除票券
    TicketVO deleteTicket(Integer ticketId);
    //根據票券ID取得票券
    TicketVO getTicketById(Integer ticketId);  
    //根據票券ID取得票券
    List<TicketVO> getTicketById2(Integer ticketId);  
    //取得所有票券
	List<TicketVO> getAllTickets();   
    //取得所有票券(根據頁面)
    List<TicketVO> getAllTickets2(int currentPage);  
    //取得所有票券總頁面數量
    int getPageTotal();
    //取得總票券數 
    long getTotalTickets(); 
    //取得總票券數 (上架)
    long getStatusTotalTickets(); 
    //取得票券主圖
    List<TicketVO> getAllTicketsWithMainImages(); 
    //萬用查詢
    List<TicketVO> getTicketsByCompositeQuery(Map<String, String[]> queryMap);
    //查詢所有票券類型
    List<TicketTypesVO> getAllTicketTypes();
    List<TicketVO> getAllTicketTypeIds(Integer ticketTypeId);
    //查詢所有票券區域
    List<CityVO> getAllCities();
    List<TicketVO> getAllTicketAreaId(Integer areaId);
    
    //查詢票券的總星星數
    Integer getTotalStars(Integer ticketId);
    //查詢票券的評價單數
    Integer getTotalRatingCount(Integer ticketId);
    //查詢票券的評價內容
    List<TicketOrderDetailVO> findTicketOrderDetailsByTicketId(Integer ticketId);

    //排序
//    List<TicketVO> getAllTickets2(int currentPage, String sortId, String sortOrder);
//    List<TicketVO> getTicketsByCompositeQuery(Map<String, String[]> queryMap, String sortId, String sortOrder);

    List<TicketVO> getAllTicketsSorted(String sortField, String sortOrder);
    List<TicketVO> findAllWithOrder(int currentPage, String sortField, String sortOrder);
    List<TicketVO> findTickets(int currentPage, String sortField, String sortOrder, String searchQuery);
    
}

