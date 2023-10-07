package com.depthspace.ticket.model;

import java.util.List;
import java.util.Map;

public class TicketJDBCDAO implements TicketDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cha103g5?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "88888888";
	
	private static final String INSERT_STMT =
			"INSERT INTO ticket (TICKET_TYPE_ID, TICKET_NAME, DESCRIPTION, PRICE, STOCK, VALID_DAYS, STATUS, PUBLISHED_DATE, TOTAL_STAR_RATINGS, TOTAL_STARS) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		//TICKET_ID是AI鍵，故不用放入insert建立
	
	private static final String GET_ALL_STMT =
			"SELECT TICKET_ID, TICKET_TYPE_ID, TICKET_NAME, DESCRIPTION, PRICE, STOCK, VALID_DAYS, STATUS, PUBLISHED_DATE, TOTAL_STAR_RATINGS, TOTAL_STARS FROM ticket ORDER BY TICKET_ID";	
	
	private static final String GET_ONE_STMT =
			"SELECT TICKET_ID, TICKET_TYPE_ID, TICKET_NAME, DESCRIPTION, PRICE, STOCK, VALID_DAYS, STATUS, PUBLISHED_DATE, TOTAL_STAR_RATINGS, TOTAL_STARS FROM ticket WHERE TICKET_ID = ?";	

	private static final String DELETE = 
			"DELETE FROM ticket WHERE TICKET_ID = ?";
		
	private static final String UPDATE = 
			"UPDATE ticket SET TICKET_TYPE_ID=?, TICKET_NAME=?, DESCRIPTION=?, PRICE=?, STOCK=?, VALID_DAYS=?, STATUS=?, PUBLISHED_DATE=?, TOTAL_STAR_RATINGS=?, TOTAL_STARS=? WHERE TICKET_ID=?";
	
	
	@Override
	public void insert(TicketVO ticketVO) {
		// TODO Auto-generated method stub
	}	
	 
	@Override
	public void update(TicketVO ticketVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Integer ticketId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public TicketVO findByPrimaryKey(Integer ticketId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<TicketVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
