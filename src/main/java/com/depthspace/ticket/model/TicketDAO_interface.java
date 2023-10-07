package com.depthspace.ticket.model;

import java.util.*;

public interface TicketDAO_interface {
	public void insert(TicketVO ticketVO);
	public void update(TicketVO ticketVO);
	public void delete(Integer ticketId); //測試用
    public TicketVO findByPrimaryKey(Integer ticketId);
    //public List<TicketVO> getAll(); 此方法適合少量 每次都要查詢
    //下列適合萬用複合查詢(傳入參數型態Map)(回傳 List)
	public List<TicketVO> getAll(Map<String, String[]> map); 
}
