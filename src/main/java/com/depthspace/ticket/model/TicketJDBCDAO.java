package com.depthspace.ticket.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.depthspace.utils.DBUtil;

public class TicketJDBCDAO implements TicketDAO_Interface {
	
	private static final String INSERT_STMT =
			"INSERT INTO TICKET (TICKET_TYPE_ID, TICKET_NAME, DESCRIPTION, PRICE, STOCK, VALID_DAYS, STATUS, PUBLISHED_DATE, TOTAL_STAR_RATINGS, TOTAL_STARS) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		//TICKET_ID是AI鍵，故不用放入insert建立	
	private static final String GET_ALL_STMT =
			"SELECT TICKET_ID, TICKET_TYPE_ID, TICKET_NAME, DESCRIPTION, PRICE, STOCK, VALID_DAYS, STATUS, PUBLISHED_DATE, TOTAL_STAR_RATINGS, TOTAL_STARS FROM TICKET ORDER BY TICKET_ID";		
	private static final String GET_ONE_STMT =
			"SELECT TICKET_ID, TICKET_TYPE_ID, TICKET_NAME, DESCRIPTION, PRICE, STOCK, VALID_DAYS, STATUS, PUBLISHED_DATE, TOTAL_STAR_RATINGS, TOTAL_STARS FROM TICKET WHERE TICKET_ID = ?";	
	private static final String DELETE = 
			"DELETE FROM TICKET WHERE TICKET_ID = ?";		
	private static final String UPDATE = 
			"UPDATE TICKET SET TICKET_TYPE_ID=?, TICKET_NAME=?, DESCRIPTION=?, PRICE=?, STOCK=?, VALID_DAYS=?, STATUS=?, PUBLISHED_DATE=?, TOTAL_STAR_RATINGS=?, TOTAL_STARS=? WHERE TICKET_ID=?";	
	
	@Override
	public void insert(TicketVO ticketVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, ticketVO.getTicketTypeId());
			pstmt.setString(2, ticketVO.getTicketName());
			pstmt.setString(3, ticketVO.getDescription());
			pstmt.setInt(4, ticketVO.getPrice());
			pstmt.setInt(5, ticketVO.getStock());
			pstmt.setInt(6, ticketVO.getValidDays());
			pstmt.setByte(7, ticketVO.getStatus());
			pstmt.setTimestamp(8, ticketVO.getPublishedDate());
//			pstmt.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));
			pstmt.setInt(9, ticketVO.getTotalStarRatings());
			pstmt.setInt(10, ticketVO.getTotalStars());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}
	}	

	@Override
	public void update(TicketVO ticketVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, ticketVO.getTicketTypeId());
			pstmt.setString(2, ticketVO.getTicketName());
			pstmt.setString(3, ticketVO.getDescription());
			pstmt.setInt(4, ticketVO.getPrice());
			pstmt.setInt(5, ticketVO.getStock());
			pstmt.setInt(6, ticketVO.getValidDays());
			pstmt.setByte(7, ticketVO.getStatus());
			pstmt.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));
			pstmt.setInt(9, ticketVO.getTotalStarRatings());
			pstmt.setInt(10, ticketVO.getTotalStars());
			pstmt.setInt(11, ticketVO.getTicketId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}
	}			
	
	@Override
	public void delete(Integer ticketId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, ticketId);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}		
	}

	@Override
	public TicketVO findByPrimaryKey(Integer ticketId) {
		// TODO Auto-generated method stub
		
		TicketVO ticketVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //ResultSet為查詢結果
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, ticketId);
			rs = pstmt.executeQuery();
			while(rs.next()) { //移動查詢
				ticketVO = new TicketVO();
				ticketVO.setTicketId(rs.getInt("TICKET_ID")); 
				ticketVO.setTicketTypeId(rs.getInt("TICKET_TYPE_ID")); 				
				ticketVO.setTicketName(rs.getString("TICKET_NAME")); 
				ticketVO.setDescription(rs.getString("DESCRIPTION")); 
				ticketVO.setPrice(rs.getInt("PRICE")); 
				ticketVO.setStock(rs.getInt("STOCK")); 
				ticketVO.setValidDays(rs.getInt("VALID_DAYS")); 	
				ticketVO.setStatus(rs.getByte("STATUS")); 
				ticketVO.setPublishedDate(rs.getTimestamp("PUBLISHED_DATE")); 
				ticketVO.setTotalStarRatings(rs.getInt("TOTAL_STAR_RATINGS")); 
				ticketVO.setTotalStars(rs.getInt("TOTAL_STARS")); 
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}
		return ticketVO;
	}
	
	@Override
	public List<TicketVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		List<TicketVO> list = new ArrayList<>();
		TicketVO ticketVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //ResultSet為查詢結果
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()) { //移動查詢
				ticketVO = new TicketVO();
				ticketVO.setTicketId(rs.getInt("TICKET_ID")); 
				ticketVO.setTicketTypeId(rs.getInt("TICKET_TYPE_ID")); 				
				ticketVO.setTicketName(rs.getString("TICKET_NAME")); 
				ticketVO.setDescription(rs.getString("DESCRIPTION")); 
				ticketVO.setPrice(rs.getInt("PRICE")); 
				ticketVO.setStock(rs.getInt("STOCK")); 
				ticketVO.setValidDays(rs.getInt("VALID_DAYS")); 	
				ticketVO.setStatus(rs.getByte("STATUS")); 
				ticketVO.setPublishedDate(rs.getTimestamp("PUBLISHED_DATE")); 
				ticketVO.setTotalStarRatings(rs.getInt("TOTAL_STAR_RATINGS")); 
				ticketVO.setTotalStars(rs.getInt("TOTAL_STARS")); 
				list.add(ticketVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}
		return list;
	}
	
	
	
//	public static void main(String[] args) {
//		TicketJDBCDAO dao = new TicketJDBCDAO();
//
//		//新增
////		TicketVO ticketVO1 = new TicketVO();
////		ticketVO1.setTicketTypeId(1);
////		ticketVO1.setTicketName("Xpark 都會型水生公園門票");
////		ticketVO1.setDescription("Xpark 為台灣首座新都會型水生公園。將生活在地球上各種地域的生物們的環境，透過空間演出與科技的融合，加以忠實的重現。");
////		ticketVO1.setPrice(600);
////		ticketVO1.setStock(500);
////		ticketVO1.setValidDays(365);
////		ticketVO1.setStatus((byte)1);
////		ticketVO1.setPublishedDate(new java.sql.Timestamp(System.currentTimeMillis()));
////		ticketVO1.setTotalStarRatings(20);
////		ticketVO1.setTotalStars(100);
////		dao.insert(ticketVO1);
////		System.out.println("完成新增");
//	
//		//修改
////		TicketVO ticketVO2 = new TicketVO();
////		ticketVO2.setTicketId(324001);
////		ticketVO2.setTicketTypeId(1);
////		ticketVO2.setTicketName("劍湖山");
////		ticketVO2.setDescription("台灣遊樂園！");
////		ticketVO2.setPrice(799);
////		ticketVO2.setStock(800);
////		ticketVO2.setValidDays(365);
////		ticketVO2.setStatus((byte)1);
////		ticketVO2.setPublishedDate(new java.sql.Timestamp(System.currentTimeMillis()));
////		ticketVO2.setTotalStarRatings(10);
////		ticketVO2.setTotalStars(450);
////		dao.update(ticketVO2);
////		System.out.println("完成修改");
//		
//		// 刪除
////		dao.delete(1);
////		System.out.println("完成刪除");
//		
//		// 查詢一個
////		TicketVO ticketVO3 = dao.findByPrimaryKey(324001);
////		System.out.print(ticketVO3.getTicketId() + ",");
////		System.out.print(ticketVO3.getTicketTypeId() + ",");
////		System.out.print(ticketVO3.getTicketName() + ",");
////		System.out.print(ticketVO3.getDescription() + ",");
////		System.out.print(ticketVO3.getPrice() + ",");
////		System.out.print(ticketVO3.getStock() + ",");
////		System.out.print(ticketVO3.getValidDays() + ",");
////		System.out.print(ticketVO3.getStatus() + ",");
////		System.out.print(ticketVO3.getPublishedDate() + ",");
////		System.out.print(ticketVO3.getTotalStarRatings() + ",");
////		System.out.println(ticketVO3.getTotalStars());
////		System.out.println("完成查詢");
//		
//		// 查詢全部
////		List<TicketVO> list = dao.getAll(null);
////		for (TicketVO allTicket : list) {
////			System.out.print(allTicket.getTicketId() + ",");
////			System.out.print(allTicket.getTicketTypeId() + ",");
////			System.out.print(allTicket.getTicketName() + ",");
////			System.out.print(allTicket.getDescription() + ",");
////			System.out.print(allTicket.getPrice() + ",");
////			System.out.print(allTicket.getStock() + ",");
////			System.out.print(allTicket.getValidDays() + ",");
////			System.out.print(allTicket.getStatus() + ",");
////			System.out.print(allTicket.getPublishedDate() + ",");
////			System.out.print(allTicket.getTotalStarRatings() + ",");
////			System.out.println(allTicket.getTotalStars());
////			System.out.println("--------------");
////		}
//	}
}


