package com.depthspace.admin.model.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.member.model.MemVO;
import com.depthspace.utils.DBUtil;

public class AdminJDBCDAO implements AdminDAO_Interface {
	private static final String INSERT_STMT = "INSERT INTO ADMIN(ADMIN_ACC, ADMIN_PWD, ADMIN_NAME, ADMIN_STATUS, ADMIN_VERIFY_STATUS, ADMIN_FUNC_NAME) VALUES(?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE ADMIN SET ADMIN_ACC=?, ADMIN_PWD=?, ADMIN_NAME=?, ADMIN_STATUS=?, ADMIN_VERIFY_STATUS=?, ADMIN_FUNC_NAME=? WHERE ADMIN_ID=?";
	private static final String DELETE_STMT = "DELETE FROM ADMIN WHERE ADMIN_ID=?";
	private static final String GET_ONE_STMT = "SELECT ADMIN_ID,ADMIN_ACC, ADMIN_PWD, ADMIN_NAME, ADMIN_STATUS, ADMIN_VERIFY_STATUS, ADMIN_FUNC_NAME FROM ADMIN WHERE ADMIN_ID=?";
	private static final String GET_ONE_ADMIN = "SELECT ADMIN_ID,ADMIN_ACC, ADMIN_PWD, ADMIN_NAME, ADMIN_STATUS, ADMIN_VERIFY_STATUS, ADMIN_FUNC_NAME FROM ADMIN WHERE ADMIN_ACC=?";
	private static final String GET_ALL_STMT = "SELECT * FROM ADMIN";
	
	@Override
	public void insert(AdminVO AdminVO) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection(); // 從 DBUtil 獲取數據庫連接
			ps = conn.prepareStatement(INSERT_STMT); // 創建一個 PreparedStatement 對象來執行 SQL 語句
			// 設置 PreparedStatement 參數
			ps.setString(1, AdminVO.getAdminAcc());
			ps.setString(2, AdminVO.getAdminPwd());
			ps.setString(3, AdminVO.getAdminName());
			ps.setByte(4, AdminVO.getAdminStatus());
			ps.setByte(5, AdminVO.getAdminVerifyStatus());
			ps.setByte(6, AdminVO.getAdminFuncName());
			
			ps.executeUpdate(); // 執行 SQL 語句進行數據插入
			
		} catch (SQLException e) {
			throw new RuntimeException(e); // 捕獲並拋出 SQL 異常
		} finally {
			DBUtil.close(conn, ps, null); // 最終塊中關閉資源
		}
	}

	@Override
	public void update(AdminVO adminVO) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    try {
	        conn = DBUtil.getConnection();
	        ps = conn.prepareStatement(UPDATE_STMT);
	        ps.setString(1, adminVO.getAdminAcc());
	        ps.setString(2, adminVO.getAdminPwd());
	        ps.setString(3, adminVO.getAdminName());
	        ps.setByte(4, adminVO.getAdminStatus());
			ps.setByte(5, adminVO.getAdminVerifyStatus());
			ps.setByte(6, adminVO.getAdminFuncName());
	        ps.setInt(7, adminVO.getAdminId());

	        ps.executeUpdate();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    } finally {
	        DBUtil.close(conn, ps, null);
	    }
	}


	@Override
	public void delete(Integer adminId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(DELETE_STMT);
			ps.setInt(1, adminId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	// 實現介面中的 findByPrimaryKey 方法，用於根據主鍵查詢單個管理員資料
	@Override
	public AdminVO findByPrimaryKey(Integer adminId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AdminVO adminVO = null; // 定義一個 AdminVO 對象來存儲查詢結果

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(GET_ONE_STMT);
			ps.setInt(1, adminId);
			rs = ps.executeQuery();

			if (rs.next()) {
				adminVO = new AdminVO(); // 初始化 AdminVO 對象
                // 從結果集中提取數據並設置到 AdminVO 對象中
				adminVO.setAdminAcc(rs.getString("ADMIN_ACC"));
				adminVO.setAdminPwd(rs.getString("ADMIN_PWD"));
				adminVO.setAdminName(rs.getString("ADMIN_NAME"));
				adminVO.setAdminStatus(rs.getByte("ADMIN_STATUS"));
				adminVO.setAdminVerifyStatus(rs.getByte("ADMIN_VERIFY_STATUS"));
				adminVO.setAdminFuncName(rs.getByte("ADMIN_FUNC_NAME"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}

		return adminVO;
	}

	// 實現介面中的 getAll 方法，用於獲取所有管理員資料
	@Override
	public List<AdminVO> getAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<AdminVO> adminList = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(GET_ALL_STMT);
			rs = ps.executeQuery();

			while (rs.next()) {
				AdminVO adminVO = new AdminVO();
				adminVO.setAdminId(rs.getInt("ADMIN_ID"));
				adminVO.setAdminAcc(rs.getString("ADMIN_ACC"));
				adminVO.setAdminPwd(rs.getString("ADMIN_PWD"));
				adminVO.setAdminName(rs.getString("ADMIN_NAME"));
				adminVO.setAdminStatus(rs.getByte("ADMIN_STATUS"));
				adminVO.setAdminVerifyStatus(rs.getByte("ADMIN_VERIFY_STATUS"));
				adminVO.setAdminFuncName(rs.getByte("ADMIN_FUNC_NAME"));
				adminList.add(adminVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}

		return adminList;
	}

	@Override
	public AdminVO findByAdminId(Integer adminId) {
		AdminVO adminVO = new AdminVO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(GET_ONE_STMT);
			ps.setInt(1, adminId);
			rs = ps.executeQuery();
			if (rs.next()) {
				
				adminVO.setAdminId(rs.getInt("ADMIN_ID"));
				adminVO.setAdminAcc(rs.getString("ADMIN_ACC"));
				adminVO.setAdminPwd(rs.getString("ADMIN_PWD"));
				adminVO.setAdminName(rs.getString("ADMIN_NAME"));
				adminVO.setAdminStatus(rs.getByte("ADMIN_STATUS"));
				adminVO.setAdminVerifyStatus(rs.getByte("ADMIN_VERIFY_STATUS"));
				adminVO.setAdminFuncName(rs.getByte("ADMIN_FUNC_NAME"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return adminVO;
	}

	

	@Override
	public AdminVO getAdminInfo(String adminAcc) {
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    AdminVO adminVO = null;  // 修改此處，預設為 null
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(GET_ONE_ADMIN);
			ps.setString(1, adminAcc);
			rs = ps.executeQuery();
			if (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdminId(rs.getInt("ADMIN_ID"));
				adminVO.setAdminAcc(rs.getString("ADMIN_ACC"));
				adminVO.setAdminPwd(rs.getString("ADMIN_PWD"));
				adminVO.setAdminName(rs.getString("ADMIN_NAME"));
				adminVO.setAdminStatus(rs.getByte("ADMIN_STATUS"));
				adminVO.setAdminVerifyStatus(rs.getByte("ADMIN_VERIFY_STATUS"));
				adminVO.setAdminFuncName(rs.getByte("ADMIN_FUNC_NAME"));
				
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		
		return adminVO;
	}
	
	

}
