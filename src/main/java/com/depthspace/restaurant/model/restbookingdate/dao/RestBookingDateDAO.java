package com.depthspace.restaurant.model.restbookingdate.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.restaurant.model.restbookingdate.RestBookingDateVO;
import com.depthspace.utils.DBUtil;

public class RestBookingDateDAO implements RestBookingDateDAO_interface{
	private static final String INSERT_STMT = 
			"INSERT INTO RESTAURANT_BOOKINGDATE (REST_ID, BOOKING_DATE, REST_OPEN, MORNING_NUM, NOON_NUM, EVENING_NUM) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT REST_ID, BOOKING_DATE, REST_OPEN, MORNING_NUM, NOON_NUM, EVENING_NUM FROM RESTAURANT_BOOKINGDATE ORDER BY REST_ID,BOOKING_DATE";
	private static final String GET_ONE_STMT = 
			"SELECT REST_ID, BOOKING_DATE, REST_OPEN, MORNING_NUM, NOON_NUM, EVENING_NUM FROM RESTAURANT_BOOKINGDATE WHERE REST_ID = ? AND BOOKING_DATE = ?";
	private static final String DELETE = 
			"DELETE FROM RESTAURANT_BOOKINGDATE WHERE REST_ID = ? AND BOOKING_DATE = ?";
	private static final String UPDATE = 
			"UPDATE RESTAURANT_BOOKINGDATE SET REST_OPEN=?, MORNING_NUM=?, NOON_NUM=?, EVENING_NUM=? WHERE REST_ID = ? AND BOOKING_DATE = ?";
	@Override
	public void insert(RestBookingDateVO restBookingDateVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, restBookingDateVO.getRestId());
			pstmt.setDate(2, restBookingDateVO.getBookingDate());
			pstmt.setInt(3, restBookingDateVO.getRestOpen());
			pstmt.setInt(4, restBookingDateVO.getMorningNum());
			pstmt.setInt(5, restBookingDateVO.getNoonNum());
			pstmt.setInt(6, restBookingDateVO.getEveningNum());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
	}
	@Override
	public void update(RestBookingDateVO restBookingDateVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, restBookingDateVO.getRestOpen());
			pstmt.setInt(2, restBookingDateVO.getMorningNum());
			pstmt.setInt(3, restBookingDateVO.getNoonNum());
			pstmt.setInt(4, restBookingDateVO.getEveningNum());
			pstmt.setInt(5, restBookingDateVO.getRestId());
			pstmt.setDate(6, restBookingDateVO.getBookingDate());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
	}
	@Override
	public void delete(RestBookingDateVO restBookingDateVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, restBookingDateVO.getRestId());
			pstmt.setDate(2, restBookingDateVO.getBookingDate());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
	}
	@Override
	public RestBookingDateVO findByPrimaryKey(RestBookingDateVO restBookingDateVO) {
		RestBookingDateVO restBookingDateVO1 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, restBookingDateVO1.getRestId());
			pstmt.setDate(2, restBookingDateVO1.getBookingDate());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				restBookingDateVO1 = new RestBookingDateVO();
				restBookingDateVO1.setRestId(rs.getInt("REST_ID"));
				restBookingDateVO1.setBookingDate(rs.getDate("BOOKING_DATE"));
				restBookingDateVO1.setRestOpen(rs.getInt("REST_OPEN"));
				restBookingDateVO1.setMorningNum(rs.getInt("MORNING_NUM"));
				restBookingDateVO1.setNoonNum(rs.getInt("NOON_NUM"));
				restBookingDateVO1.setEveningNum(rs.getInt("EVENING_NUM"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
		return restBookingDateVO1;
	}
	@Override
	public List<RestBookingDateVO> getAll() {
		List<RestBookingDateVO> list = new ArrayList<RestBookingDateVO>();
		RestBookingDateVO restBookingDateVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				restBookingDateVO = new RestBookingDateVO();
				restBookingDateVO.setRestId(rs.getInt("REST_ID"));
				restBookingDateVO.setBookingDate(rs.getDate("BOOKING_DATE"));
				restBookingDateVO.setRestOpen(rs.getInt("REST_OPEN"));
				restBookingDateVO.setMorningNum(rs.getInt("MORNING_NUM"));
				restBookingDateVO.setNoonNum(rs.getInt("NOON_NUM"));
				restBookingDateVO.setEveningNum(rs.getInt("EVENING_NUM"));
				list.add(restBookingDateVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		RestBookingDateDAO dao = new RestBookingDateDAO();
		
		// INSTERT
//		RestBookingDateVO restBookingDateVO = new RestBookingDateVO();
//		restBookingDateVO.setRestId(5);
//		restBookingDateVO.setBookingDate(java.sql.Date.valueOf("2023-10-10"));
//		restBookingDateVO.setRestOpen(1);
//		restBookingDateVO.setMorningNum(50);
//		restBookingDateVO.setNoonNum(50);
//		restBookingDateVO.setEveningNum(50);
//		dao.insert(restBookingDateVO);
//		System.out.println("INSTERT DONE");
		
		// UPDATE
//		RestBookingDateVO restBookingDateVO1 = new RestBookingDateVO();
//		restBookingDateVO1.setRestId(5);
//		restBookingDateVO1.setBookingDate(java.sql.Date.valueOf("2023-10-10"));
//		restBookingDateVO1.setRestOpen(1);
//		restBookingDateVO1.setMorningNum(30);
//		restBookingDateVO1.setNoonNum(30);
//		restBookingDateVO1.setEveningNum(30);
//		dao.update(restBookingDateVO1);
//		System.out.println("UPDATE DONE");
		
		// DELETE
//		RestBookingDateVO restBookingDateVO2 = new RestBookingDateVO();
//		restBookingDateVO2.setRestId(5);
//		restBookingDateVO2.setBookingDate(java.sql.Date.valueOf("2023-10-10"));
//		dao.delete(restBookingDateVO2);
//		System.out.println("DELETE DONE");
//		
		// SELECT ID
//		RestBookingDateVO restBookingDateVOVO3 = dao.findByPrimaryKey(1, java.sql.Date.valueOf("2023-09-27"));
//		System.out.println(restBookingDateVOVO3.getRestId());
//		System.out.println(restBookingDateVOVO3.getBookingDate());
//		System.out.println(restBookingDateVOVO3.getRestOpen());
//		System.out.println(restBookingDateVOVO3.getMorningNum());
//		System.out.println(restBookingDateVOVO3.getNoonNum());
//		System.out.println(restBookingDateVOVO3.getEveningNum());
		
		
		// SELECT ALL
		List<RestBookingDateVO> list = dao.getAll();
		for (RestBookingDateVO rest: list) {
			System.out.println(rest.getRestId() + ",");
			System.out.println(rest.getBookingDate() + ",");
			System.out.println(rest.getRestOpen() + ",");
			System.out.println(rest.getMorningNum() + ",");
			System.out.println(rest.getNoonNum() + ",");
			System.out.println(rest.getEveningNum());
		}
	}
}
