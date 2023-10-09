package com.depthspace.ticket.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticketcollection.model.TicketCollectionVO;
import com.depthspace.utils.DBUtil;

public class TicketTypesJDBCDAO implements TicketTypesDAO_Interface {

	private static final String INSERT_STMT ="INSERT INTO TICKET_TYPES (TYPE_NAME, DESCRIPTION) VALUES(?,?)";
	private static final String UPDATE_STMT ="UPDATE TICKET_TYPES SET TYPE_NAME=? , DESCRIPTION=? WHERE TICKET_TYPE_ID=?";
	private static final String DELETE_STMT ="DELETE FROM TICKET_TYPES WHERE TICKET_TYPE_ID=?";
	private static final String GET_ONE_STMT="SELECT  TICKET_TYPE_ID, TYPE_NAME, DESCRIPTION FROM TICKET_TYPES WHERE TICKET_TYPE_ID=?";
	private static final String GET_ALL_STMT="SELECT  TICKET_TYPE_ID, TYPE_NAME, DESCRIPTION FROM TICKET_TYPES ";
	@Override
	public void insert(TicketTypesVO ticketTypesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, ticketTypesVO.getTypeName());
			pstmt.setString(2, ticketTypesVO.getDescription());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}
	}
	
	@Override
	public void update(TicketTypesVO ticketTypesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, ticketTypesVO.getTypeName());
			pstmt.setString(2, ticketTypesVO.getDescription());
			pstmt.setInt(3, ticketTypesVO.getTicketTypeId());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}
	}
	@Override
	public void delete(Integer ticketTypeId) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, ticketTypeId);
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}    
		
	}
	
	@Override
	public TicketTypesVO findByPrimaryKey(Integer ticketTypeId) {

		TicketTypesVO ticketTypesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //ResultSet為查詢結果
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, ticketTypeId);
			rs = pstmt.executeQuery();
			if(rs.next()) { //移動查詢
				ticketTypesVO = new TicketTypesVO();
				ticketTypesVO.setTicketTypeId(rs.getInt("TICKET_TYPE_ID")); 
				ticketTypesVO.setTypeName(rs.getString("TYPE_NAME")); 				; 
				ticketTypesVO.setDescription(rs.getString("DESCRIPTION")); 
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}
		return ticketTypesVO;
	}
	
	@Override
	public List<TicketTypesVO> getAll() {
		Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<TicketTypesVO> list = new ArrayList<>();
	    
	try {
		con = DBUtil.getConnection();
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();
		
			while(rs.next()) {
				TicketTypesVO ticketTypesVO = new TicketTypesVO();
				ticketTypesVO.setTicketTypeId(rs.getInt("TICKET_TYPE_ID"));
				ticketTypesVO.setTypeName(rs.getString("TYPE_NAME"));
				ticketTypesVO.setDescription(rs.getString("DESCRIPTION"));
				list.add(ticketTypesVO);
			} 			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
	    } finally {
	        DBUtil.close(con, pstmt, rs);
	    }
	    return list;
	}	
	

	
//	public static void main(String[] args) {
//		TicketTypesJDBCDAO dao = new TicketTypesJDBCDAO();
//
//	//新增
////	TicketTypesVO ticketTypesVO1 = new TicketTypesVO();
////	ticketTypesVO1.setTypeName("餐券");
////	ticketTypesVO1.setDescription("餐券");
////	dao.insert(ticketTypesVO1);
////	System.out.println("完成新增");
//	
////	 修改
////	TicketTypesVO ticketTypesVO2 = new TicketTypesVO();
////	ticketTypesVO2.setTicketTypeId(1);  // 假設你要修改的 ID 為 1
////	ticketTypesVO2.setTypeName("新的門票類型");
////	ticketTypesVO2.setDescription("這是一個新的門票類型描述");
////	dao.update(ticketTypesVO2);
////	System.out.println("完成修改");
//	
//	// 刪除
////	dao.delete(1);
////	System.out.println("完成刪除");
////	
//	// 查詢一個
////	TicketTypesVO ticketTypesVO3 = dao.findByPrimaryKey(2);
////	System.out.print(ticketTypesVO3.getTicketTypeId() + ",");
////	System.out.println(ticketTypesVO3.getTypeName());
////	System.out.println("完成查詢");
////	
//	// 查詢全部
////	List<TicketTypesVO> list = dao.getAll();
////	for (TicketTypesVO all : list) {
////	    System.out.print(all.getTicketTypeId() + ",");
////	    System.out.println(all.getTypeName());
////	    System.out.println("--------------");
////	}
//
//	}
}


