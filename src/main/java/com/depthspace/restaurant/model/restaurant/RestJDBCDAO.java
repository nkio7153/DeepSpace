package com.depthspace.restaurant.model.restaurant;

import java.sql.*;
import java.util.*;

import com.depthspace.utils.DBUtil;

public class RestJDBCDAO implements RestDAO_interface {
	private static final String INSERT_STMT = 
		"INSERT INTO RESTAURANT (REST_NAME, REST_TEL, REST_ADDRESS, REST_OPEN, REST_STATUS, BOOKING_LIMIT, ADMIN_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
		"SELECT REST_ID, REST_NAME, REST_TEL, REST_ADDRESS, REST_OPEN, REST_STATUS, BOOKING_LIMIT, ADMIN_ID FROM RESTAURANT ORDER BY REST_ID";
	private static final String GET_ONE_STMT =
		"SELECT REST_ID, REST_NAME, REST_TEL, REST_ADDRESS, REST_OPEN, REST_STATUS, BOOKING_LIMIT, ADMIN_ID FROM RESTAURANT WHERE REST_ID = ?";
	private static final String DELETE = 
		"DELETE FROM RESTAURANT WHERE REST_ID = ?";
	private static final String UPDATE =
		"UPDATE RESTAURANT SET REST_NAME=?, REST_TEL=?, REST_ADDRESS=?, REST_OPEN=?, REST_STATUS=?, BOOKING_LIMIT=?, ADMIN_ID=? WHERE REST_ID = ?";
	
	@Override
	public void insert(RestVO restVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, restVO.getRestName());
			pstmt.setString(2, restVO.getRestTel());
			pstmt.setString(3, restVO.getRestAddress());
			pstmt.setString(4, restVO.getRestOpen());
			pstmt.setInt(5, restVO.getRestStatus());
			pstmt.setInt(6, restVO.getBookingLimit());
			pstmt.setInt(7, restVO.getAdminId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
	}
	@Override
	public void update(RestVO restVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, restVO.getRestName());
			pstmt.setString(2, restVO.getRestTel());
			pstmt.setString(3, restVO.getRestAddress());
			pstmt.setString(4, restVO.getRestOpen());
			pstmt.setInt(5, restVO.getRestStatus());
			pstmt.setInt(6, restVO.getBookingLimit());
			pstmt.setInt(7, restVO.getAdminId());
			pstmt.setInt(8, restVO.getRestId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
	}
	@Override
	public void delete(Integer restId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, restId);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
	}
	@Override
	public RestVO findByPrimaryKey(Integer restId) {
		RestVO restVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, restId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				restVO = new RestVO();
				restVO.setRestId(rs.getInt("rest_Id"));
				restVO.setRestName(rs.getString("rest_Name"));
				restVO.setRestTel(rs.getString("rest_Tel"));
				restVO.setRestAddress(rs.getString("rest_Address"));
				restVO.setRestOpen(rs.getString("rest_Open"));
				restVO.setRestStatus(rs.getInt("rest_Status"));
				restVO.setBookingLimit(rs.getInt("booking_Limit"));
				restVO.setAdminId(rs.getInt("admin_Id"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
		return restVO;
	}
	@Override
	public List<RestVO> getAll() {
		List<RestVO> list = new ArrayList<RestVO>();
		RestVO restVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				restVO = new RestVO();
				restVO.setRestId(rs.getInt("rest_Id"));
				restVO.setRestName(rs.getString("rest_Name"));
				restVO.setRestTel(rs.getString("rest_Tel"));
				restVO.setRestAddress(rs.getString("rest_Address"));
				restVO.setRestOpen(rs.getString("rest_Open"));
				restVO.setRestStatus(rs.getInt("rest_Status"));
				restVO.setBookingLimit(rs.getInt("booking_Limit"));
				restVO.setAdminId(rs.getInt("admin_Id"));
				list.add(restVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		RestJDBCDAO dao = new RestJDBCDAO();
		
		// INSTERT
//		RestVO restVO1 = new RestVO();
//		restVO1.setRestName("緯育TibaMe");
//		restVO1.setRestTel("034251108");
//		restVO1.setRestAddress("320桃園市中壢區復興路46號9樓");
//		restVO1.setRestOpen("08:30–22:00");
//		restVO1.setRestStatus(1);
//		restVO1.setBookingLimit(30);
//		restVO1.setAdminId(1);
//		dao.insert(restVO1);
//		System.out.println("INSTERT DONE");
//		
//		// UPDATE
//		RestVO restVO2 = new RestVO();
//		restVO2.setRestName("緯育TibaMe中壢");
//		restVO2.setRestTel("034251108");
//		restVO2.setRestAddress("320桃園市中壢區復興路46號9樓-1");
//		restVO2.setRestOpen("08:30–22:00");
//		restVO2.setRestStatus(0);
//		restVO2.setBookingLimit(30);
//		restVO2.setAdminId(2);
//		restVO2.setRestId(21);
//		dao.update(restVO2);
//		System.out.println("UPDATE DONE");
		
//		// DELETE
//		dao.delete(21);
//		System.out.println("DELETE DONE");
//		
//		// SELECT ID
//		RestVO restVO3 = dao.findByPrimaryKey(21);
//		System.out.println(restVO3.getRestId());
//		System.out.println(restVO3.getRestName());
//		System.out.println(restVO3.getRestTel());
//		System.out.println(restVO3.getRestAddress());
//		System.out.println(restVO3.getRestOpen());
//		System.out.println(restVO3.getRestStatus());
//		System.out.println(restVO3.getBookingLimit());
//		System.out.println(restVO3.getAdminId());
		
		// SELECT ALL
		List<RestVO> list = dao.getAll();
		for (RestVO rest: list) {
			System.out.println(rest.getRestId() + ",");
			System.out.println(rest.getRestName() + ",");
			System.out.println(rest.getRestTel() + ",");
			System.out.println(rest.getRestAddress() + ",");
			System.out.println(rest.getRestOpen() + ",");
			System.out.println(rest.getRestStatus() + ",");
			System.out.println(rest.getBookingLimit() + ",");
			System.out.println(rest.getAdminId());
		}
		
	}
	
}
