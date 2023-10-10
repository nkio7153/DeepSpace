package com.depthspace.restaurant.model.membooking;

import java.sql.*;
import java.util.*;

import com.depthspace.utils.DBUtil;

public class MemBookingJDBCDAO implements MemBookingDAO_interface {
	private static final String INSERT_STMT = 
			"INSERT INTO MEM_BOOKING (REST_ID, MEM_ID, CHECK_STATUS, BOOKING_TIME, BOOKING_NUMBER, BOOKING_DATE) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT BOOKING_ID, REST_ID, MEM_ID, CHECK_STATUS, BOOKING_TIME, BOOKING_NUMBER, BOOKING_DATE FROM MEM_BOOKING ORDER BY BOOKING_ID";
	private static final String GET_ONE_STMT = 
			"SELECT BOOKING_ID, REST_ID, MEM_ID, CHECK_STATUS, BOOKING_TIME, BOOKING_NUMBER, BOOKING_DATE FROM MEM_BOOKING WHERE BOOKING_ID = ?";
	private static final String DELETE = 
			"DELETE FROM MEM_BOOKING WHERE BOOKING_ID = ?";
	private static final String UPDATE = 
			"UPDATE MEM_BOOKING SET REST_ID=?, MEM_ID=?, CHECK_STATUS=?, BOOKING_TIME=?, BOOKING_NUMBER=?, BOOKING_DATE=? WHERE BOOKING_ID = ?";
	
	@Override
	public void insert(MemBookingVO memBookingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, memBookingVO.getRestId());
			pstmt.setInt(2, memBookingVO.getMemId());
			pstmt.setInt(3, memBookingVO.getCheckStatus());
			pstmt.setInt(4, memBookingVO.getBookingTime());
			pstmt.setInt(5, memBookingVO.getBookingNumber());
			pstmt.setDate(6, memBookingVO.getBookingDate());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
	}
	@Override
	public void update(MemBookingVO memBookingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, memBookingVO.getRestId());
			pstmt.setInt(2, memBookingVO.getMemId());
			pstmt.setInt(3, memBookingVO.getCheckStatus());
			pstmt.setInt(4, memBookingVO.getBookingTime());
			pstmt.setInt(5, memBookingVO.getBookingNumber());
			pstmt.setDate(6, memBookingVO.getBookingDate());
			pstmt.setInt(7, memBookingVO.getBookingId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}		
	}
	@Override
	public void delete(Integer bookingId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, bookingId);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
	}
	@Override
	public MemBookingVO findByPrimaryKey(Integer bookingId) {
		MemBookingVO memBookingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, bookingId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memBookingVO = new MemBookingVO();
				memBookingVO.setBookingId(rs.getInt("BOOKING_ID"));
				memBookingVO.setRestId(rs.getInt("REST_ID"));
				memBookingVO.setMemId(rs.getInt("MEM_ID"));
				memBookingVO.setCheckStatus(rs.getInt("CHECK_STATUS"));
				memBookingVO.setBookingId(rs.getInt("BOOKING_TIME"));
				memBookingVO.setBookingNumber(rs.getInt("BOOKING_NUMBER"));
				memBookingVO.setBookingDate(rs.getDate("BOOKING_DATE"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
		return memBookingVO;
	}
	@Override
	public List<MemBookingVO> getAll() {
		List<MemBookingVO> list = new ArrayList<MemBookingVO>();
		MemBookingVO memBookingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memBookingVO = new MemBookingVO();
				memBookingVO.setBookingId(rs.getInt("BOOKING_ID"));
				memBookingVO.setRestId(rs.getInt("REST_ID"));
				memBookingVO.setMemId(rs.getInt("MEM_ID"));
				memBookingVO.setCheckStatus(rs.getInt("CHECK_STATUS"));
				memBookingVO.setBookingId(rs.getInt("BOOKING_TIME"));
				memBookingVO.setBookingNumber(rs.getInt("BOOKING_NUMBER"));
				memBookingVO.setBookingDate(rs.getDate("BOOKING_DATE"));
				list.add(memBookingVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		MemBookingJDBCDAO dao = new MemBookingJDBCDAO();
		
		// INSTERT
//		MemBookingVO memBookingVO1 = new MemBookingVO();
//		memBookingVO1.setRestId(1);
//		memBookingVO1.setMemId(1);
//		memBookingVO1.setCheckStatus(0);
//		memBookingVO1.setBookingTime(3);
//		memBookingVO1.setBookingNumber(2);
//		memBookingVO1.setBookingDate(java.sql.Date.valueOf("2023-10-10"));
//		dao.insert(memBookingVO1);
//		System.out.println("INSTERT DONE");
		
		// UPDATE
//		MemBookingVO memBookingVO2 = new MemBookingVO();
//		memBookingVO2.setRestId(2);
//		memBookingVO2.setMemId(2);
//		memBookingVO2.setCheckStatus(1);
//		memBookingVO2.setBookingTime(2);
//		memBookingVO2.setBookingNumber(5);
//		memBookingVO2.setBookingDate(java.sql.Date.valueOf("2023-10-11"));
//		memBookingVO2.setBookingId(21);
//		dao.update(memBookingVO2);
//		System.out.println("UPDATE DONE");
		
		// DELETE
//		dao.delete(21);
//		System.out.println("DELETE DONE");
//		
		// SELECT ID
//		MemBookingVO memBookingVO3 = dao.findByPrimaryKey(21);
//		System.out.println(memBookingVO3.getBookingId());
//		System.out.println(memBookingVO3.getRestId());
//		System.out.println(memBookingVO3.getMemId());
//		System.out.println(memBookingVO3.getCheckStatus());
//		System.out.println(memBookingVO3.getBookingTime());
//		System.out.println(memBookingVO3.getBookingNumber());
//		System.out.println(memBookingVO3.getBookingDate());
		
		// SELECT ALL
		List<MemBookingVO> list = dao.getAll();
		for (MemBookingVO memBooking: list) {
			System.out.println(memBooking.getBookingId() + ",");
			System.out.println(memBooking.getRestId() + ",");
			System.out.println(memBooking.getMemId() + ",");
			System.out.println(memBooking.getCheckStatus() + ",");
			System.out.println(memBooking.getBookingTime() + ",");
			System.out.println(memBooking.getBookingNumber() + ",");
			System.out.println(memBooking.getBookingDate() + ",");
		}
		
	
	}
}
