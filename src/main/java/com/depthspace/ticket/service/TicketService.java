package com.depthspace.ticket.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.depthspace.ticket.model.TicketDAO_Interface;
import com.depthspace.ticket.model.TicketImagesDAO_Interface;
import com.depthspace.ticket.model.TicketImagesJDBCDAO;
import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.ticket.model.TicketInfo;
import com.depthspace.ticket.model.TicketJDBCDAO;
import com.depthspace.ticket.model.TicketVO;

public class TicketService {
	
	private TicketDAO_Interface dao; 
	private TicketImagesDAO_Interface imgDao;
	
	public TicketService() {
		dao = new TicketJDBCDAO(); //創建實體
	}

	/*****新增票券******/
	public TicketVO addTicket(TicketVO ticketVO) {
		dao.insert(ticketVO);
		return ticketVO;
	}
	
	/*****取得所有票券資料******/
	public List<TicketInfo> getTicketsInfo(Map<String, String[]> map) {
        return dao.getTicketsInfo(null);
	}


}
