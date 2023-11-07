package com.depthspace.ticketcollection.model.old;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticketcollection.model.TicketCollectionVO;
import com.depthspace.utils.DBUtil;

public class TicketCollectionJDBCDAO implements TicketCollectionDAO_Interface {

	private static final String INSERT_STMT =
			"INSERT INTO TICKET_COLLECTION (MEM_ID, TICKET_ID) VALUES(?,?)";
	private static final String DELETE_STMT=
			"DELETE FROM TICKET_COLLECTION WHERE MEM_ID=? AND TICKET_ID=?";
	private static final String GET_ONE_STMT =
			"SELECT  MEM_ID, TICKET_ID FROM TICKET_COLLECTION WHERE MEM_ID=? AND TICKET_ID=?";
	private static final String GET_ALL_STMT =
			"SELECT  MEM_ID, TICKET_ID FROM TICKET_COLLECTION ";
	private static final String GET_TICKETS_BY_MEMID_STMT =
			"SELECT  TICKET_ID FROM TICKET_COLLECTION WHERE MEM_ID=?";	
	
	
	@Override
	public void insert(TicketCollectionVO ticketCollectionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, ticketCollectionVO.getMemId());
			pstmt.setInt(2, ticketCollectionVO.getTicketId());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}
	}		

	@Override
	public void delete(TicketCollectionVO ticketCollectionVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, ticketCollectionVO.getMemId());
			pstmt.setInt(2, ticketCollectionVO.getTicketId());
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}    
	}

	@Override
	public TicketCollectionVO findByPrimaryKey(Integer memId, Integer ticketId) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    TicketCollectionVO ticketCollectionVO = null;
	    
	    try {
	        con = DBUtil.getConnection();
	        pstmt = con.prepareStatement(GET_ONE_STMT);
	        
	        pstmt.setInt(1, memId);
	        pstmt.setInt(2, ticketId);
	        rs = pstmt.executeQuery();
	        if(rs.next()) {
	        	ticketCollectionVO = new TicketCollectionVO();
	        	ticketCollectionVO.setMemId(rs.getInt("MEM_ID"));
	        	ticketCollectionVO.setTicketId(rs.getInt("TICKET_ID"));
	        }
	    } catch (SQLException se) {
	        throw new RuntimeException("A database error occurred. " + se.getMessage());
	    } finally {
	        DBUtil.close(con, pstmt, rs);
	    }
	    return ticketCollectionVO;
	}

	@Override
	public List<TicketCollectionVO> getAll() {
		Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<TicketCollectionVO> list = new ArrayList<>();
	    
	try {
		con = DBUtil.getConnection();
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();
		
			while(rs.next()) {
	            TicketCollectionVO ticketCollectionVO = new TicketCollectionVO();
	            ticketCollectionVO.setMemId(rs.getInt("MEM_ID"));
	            ticketCollectionVO.setTicketId(rs.getInt("TICKET_ID"));
	            list.add(ticketCollectionVO);
			} 
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
	    } finally {
	        DBUtil.close(con, pstmt, rs);
	    }
	    return list;
	}

	@Override
	public List<TicketVO> getTicketsByMemID(Integer memId) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<TicketVO> list = new ArrayList<>();  

	    try {
	        con = DBUtil.getConnection();
	        pstmt = con.prepareStatement(GET_TICKETS_BY_MEMID_STMT);
	        pstmt.setInt(1, memId);
	        rs = pstmt.executeQuery();
	        
	        while(rs.next()) {
	            TicketVO ticketVO = new TicketVO();
	            ticketVO.setTicketId(rs.getInt("TICKET_ID"));
	            list.add(ticketVO);
	        }
	    } catch (SQLException se) {
	        throw new RuntimeException("A database error occurred. " + se.getMessage());
	    } finally {
	        DBUtil.close(con, pstmt, rs);
	    }
	    return list;
	}
	
//	public static void main(String[] args) {
//		TicketCollectionJDBCDAO dao = new TicketCollectionJDBCDAO();
//
//	//新增
////	TicketCollectionVO ticketCollectionVO1 = new TicketCollectionVO();
////	ticketCollectionVO1.setMemId(3);
////	ticketCollectionVO1.setTicketId(324001);
////	dao.insert(ticketCollectionVO1);
////	System.out.println("完成新增");
//	
//	// 刪除
////	TicketCollectionVO ticketCollectionVO2 = new TicketCollectionVO();
////	ticketCollectionVO2.setMemId(2);
////	ticketCollectionVO2.setTicketId(1);	
////	dao.delete(ticketCollectionVO2);
////	System.out.println("完成刪除");
//	
//	// 查詢一個
////	TicketCollectionVO ticketCollectionVO3 = dao.findByPrimaryKey(1,324001);
////	System.out.print(ticketCollectionVO3.getMemId() + ",");
////	System.out.println(ticketCollectionVO3.getTicketId());
////	System.out.println("完成查詢");
//	
//	// 查詢全部
////	List<TicketCollectionVO> list = dao.getAll();
////	for (TicketCollectionVO all : list) {
////		System.out.print(all.getMemId() + ",");
////		System.out.println(all.getTicketId());
////		System.out.println("--------------");
//		
//	// 查詢會員的收藏票券
////	List<TicketVO> list = dao.getTicketsByMemID(3);
////	for(TicketVO memAll : list) {
////		System.out.println(memAll.getTicketId());
////		System.out.println("--------------");
////	}
//	
//	}

}


