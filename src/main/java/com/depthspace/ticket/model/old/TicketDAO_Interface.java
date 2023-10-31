package com.depthspace.ticket.model.old;

import java.util.*;

import com.depthspace.ticket.model.TicketVO;

public interface TicketDAO_Interface {
	public void insert(TicketVO ticketVO);
	public void update(TicketVO ticketVO);
	public void delete(Integer ticketId); //測試用
    public TicketVO findByPrimaryKey(Integer ticketId);
//    public List<TicketVO> getAll(); //此方法適合少量 每次都要查詢
    //下列適合萬用複合查詢(傳入參數型態Map)(回傳 List)
	public List<TicketVO> getAll(Map<String, String[]> map);
	public List<TicketInfo> getTicketsInfo(Map<String, String[]> map);
}
