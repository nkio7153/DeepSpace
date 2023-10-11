package com.depthspace.restaurant.model.restcollection;

import java.sql.*;
import java.util.*;

import com.depthspace.utils.DBUtil;

public class RestCollectionDAO implements RestCollectionDAO_interface {
	private static final String INSERT_STMT = 
			"INSERT INTO RESTAURANT_COLLECTION (REST_ID, MEM_ID) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT REST_ID, MEM_ID FROM RESTAURANT_COLLECTION ORDER BY REST_ID,MEM_ID";
	private static final String GET_ONE_STMT = 
			"SELECT REST_ID, MEM_ID FROM RESTAURANT_COLLECTION WHERE REST_ID = ? AND MEM_ID = ?";
	private static final String DELETE = 
			"DELETE FROM RESTAURANT_COLLECTION WHERE REST_ID = ? AND MEM_ID = ?";
	private static final String UPDATE = 
			"UPDATE RESTAURANT_COLLECTION SET REST_ID=?, MEM_ID=? WHERE REST_ID = ? AND MEM_ID = ?";
	
	@Override
	public void insert(RestCollectionVO restCollectionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, restCollectionVO.getRestId());
			pstmt.setInt(2, restCollectionVO.getMemId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
	}
	@Override
	public void update(Integer restId1, Integer memId1, Integer restId2, Integer memId2) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, restId1);
			pstmt.setInt(2, memId1);
			pstmt.setInt(3, restId2);
			pstmt.setInt(4, memId2);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
	}
	@Override
	public void delete(RestCollectionVO restCollectionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, restCollectionVO.getRestId());
			pstmt.setInt(2, restCollectionVO.getMemId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
	}
	@Override
	public RestCollectionVO findByPrimaryKey(Integer restId, Integer memId) {
		RestCollectionVO restCollectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, restId);
			pstmt.setInt(2, memId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				restCollectionVO = new RestCollectionVO();
				restCollectionVO.setRestId(rs.getInt("REST_ID"));
				restCollectionVO.setMemId(rs.getInt("MEM_ID"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
		return restCollectionVO;
	}
	@Override
	public List<RestCollectionVO> getAll() {
		List<RestCollectionVO> list = new ArrayList<RestCollectionVO>();
		RestCollectionVO restCollectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				restCollectionVO = new RestCollectionVO();
				restCollectionVO.setRestId(rs.getInt("REST_ID"));
				restCollectionVO.setMemId(rs.getInt("MEM_ID"));
				list.add(restCollectionVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("SQL ERROR" + e.getMessage());
		} finally {
			DBUtil.close(con, pstmt, null);
		}
		
		return list;
	}

	public static void main(String[] args) {
		RestCollectionDAO dao = new RestCollectionDAO();
		
		// INSTERT
//		RestCollectionVO restCollectionVO1 = new RestCollectionVO();
//		restCollectionVO1.setRestId(1);
//		restCollectionVO1.setMemId(3);
//		dao.insert(restCollectionVO1);
//		System.out.println("INSTERT DONE");
		
		// UPDATE
//		dao.update(1,5,1,3);
//		System.out.println("UPDATE DONE");
		
		// DELETE
//		RestCollectionVO restCollectionVO3 = new RestCollectionVO();
//		restCollectionVO3.setRestId(1);
//		restCollectionVO3.setMemId(5);
//		dao.delete(restCollectionVO3);
//		System.out.println("DELETE DONE");
		
		// SELECT ID
//		RestCollectionVO restCollectionVO4 = dao.findByPrimaryKey(2, 3);
//		System.out.print(restCollectionVO4.getRestId() + ",");
//		System.out.println(restCollectionVO4.getMemId());
		
		// SELECT ALL
		List<RestCollectionVO> list = dao.getAll();
		for (RestCollectionVO restCollection: list) {
			System.out.print(restCollection.getRestId() + ",");
			System.out.println(restCollection.getMemId());
		}
	}
}
