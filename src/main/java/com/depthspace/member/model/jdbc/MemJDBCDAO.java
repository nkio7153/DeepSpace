package com.depthspace.member.model.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.depthspace.member.model.MemVO;
import com.depthspace.utils.DBUtil;

public class MemJDBCDAO implements MemDAO_Interface {
	private static final String INSERT_STMT = "INSERT INTO MEM(MEM_ACC, MEM_PWD, MEM_NAME, MEM_IDENTITY, MEM_BTH, MEM_SEX, MEM_EMAIL, MEM_TEL, MEM_ADD, ACC_STATUS, MEM_IMAGE) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
//	private static final String UPDATE_STMT = "UPDATE MEM SET MEM_ACC=? MEM_PWD=?, MEM_NAME=?, MEM_IDENTITY=?, MEM_BTH=?, MEM_SEX=?, MEM_EMAIL=?, MEM_TEL=?, MEM_ADD=?, ACC_STATUS=?, MEM_POINT=? WHERE MEM_ID=?";
	private static final String UPDATE_STMT = "UPDATE MEM SET MEM_ACC=?, MEM_PWD=?, MEM_NAME=?, MEM_IDENTITY=?, MEM_BTH=?, MEM_SEX=?, MEM_EMAIL=?, MEM_TEL=?, MEM_ADD=?, ACC_STATUS=?, MEM_POINT=?, MEM_IMAGE=? WHERE MEM_ID=?";
	private static final String DELETE_STMT = "DELETE FROM MEM WHERE MEM_ID=?";
	private static final String GET_ONE_STMT = "SELECT MEM_ID,MEM_ACC, MEM_PWD, MEM_NAME, MEM_IDENTITY, MEM_BTH, MEM_SEX, MEM_EMAIL, MEM_TEL, MEM_ADD, ACC_STATUS, MEM_POINT,MEM_IMAGE FROM MEM WHERE MEM_ID=?";
//	private static final String GET_ALL_STMT = "SELECT MEM_ACC, MEM_PWD, MEM_NAME, MEM_IDENTITY, MEM_BTH, MEM_SEX, MEM_EMAIL, MEM_TEL, MEM_ADD, ACC_STATUS, MEM_POINT FROM MEM";
	private static final String GET_ALL_STMT = "SELECT * FROM MEM";
	private static final String GET_EMAIL = "SELECT * FROM MEM";
	
	@Override
	public void insert(MemVO MemVO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(INSERT_STMT);
//			ps.setInt(1, MemVO.getMemId());
			ps.setString(1, MemVO.getMemAcc());
			ps.setString(2, MemVO.getMemPwd());
			ps.setString(3, MemVO.getMemName());
			ps.setString(4, MemVO.getMemIdentity());
			ps.setDate(5, MemVO.getMemBth());
			ps.setByte(6, MemVO.getMemSex());
			ps.setString(7, MemVO.getMemEmail());
			ps.setInt(8, MemVO.getMemTel());
			ps.setString(9, MemVO.getMemAdd());
			ps.setByte(10, MemVO.getAccStatus());
			ps.setBytes(11, MemVO.getMemImage());
//	System.out.println("--------------------------------------");		
//	System.out.println(MemVO.getMemImage()==null);		
//	System.out.println(MemVO.getMemImage().length);		
//	System.out.println("--------------------------------------");		
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	@Override
	public void update(MemVO memVO) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    try {
	        conn = DBUtil.getConnection();
	        ps = conn.prepareStatement(UPDATE_STMT);
	        ps.setString(1, memVO.getMemAcc());
	        ps.setString(2, memVO.getMemPwd());
	        ps.setString(3, memVO.getMemName());
	        ps.setString(4, memVO.getMemIdentity());
	        ps.setDate(5, memVO.getMemBth());
	        ps.setByte(6, memVO.getMemSex());
	        ps.setString(7, memVO.getMemEmail());
	        ps.setInt(8, memVO.getMemTel());
	        ps.setString(9, memVO.getMemAdd());
	        ps.setByte(10, memVO.getAccStatus());
	        ps.setInt(11, memVO.getMemPoint());
	        ps.setBytes(12, memVO.getMemImage());
	        ps.setInt(13, memVO.getMemId());

	        ps.executeUpdate();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    } finally {
	        DBUtil.close(conn, ps, null);
	    }
	}


	@Override
	public void delete(Integer memId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(DELETE_STMT);
			ps.setInt(1, memId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	@Override
	public MemVO findByPrimaryKey(Integer memId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemVO memVO = null;

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(GET_ONE_STMT);
			ps.setInt(1, memId);
			rs = ps.executeQuery();

			if (rs.next()) {
				memVO = new MemVO();
//				memVO.setMemId(memId);
				memVO.setMemAcc(rs.getString("MEM_ACC"));
				memVO.setMemPwd(rs.getString("MEM_PWD"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemIdentity(rs.getString("MEM_IDENTITY"));
				memVO.setMemBth(rs.getDate("MEM_BTH"));
				memVO.setMemSex(rs.getByte("MEM_SEX"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemTel(rs.getInt("MEM_TEL"));
				memVO.setMemAdd(rs.getString("MEM_ADD"));
				memVO.setAccStatus(rs.getByte("ACC_STATUS"));
				memVO.setMemPoint(rs.getInt("MEM_POINT"));
				memVO.setMemImage(rs.getBytes("MEM_IMAGE"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}

		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MemVO> memList = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(GET_ALL_STMT);
			rs = ps.executeQuery();

			while (rs.next()) {
				MemVO memVO = new MemVO();
				memVO.setMemId(rs.getInt("MEM_ID"));
				memVO.setMemAcc(rs.getString("MEM_ACC"));
				memVO.setMemPwd(rs.getString("MEM_PWD"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemIdentity(rs.getString("MEM_IDENTITY"));
				memVO.setMemBth(rs.getDate("MEM_BTH"));
				memVO.setMemSex(rs.getByte("MEM_SEX"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemTel(rs.getInt("MEM_TEL"));
				memVO.setMemAdd(rs.getString("MEM_ADD"));
				memVO.setAccStatus(rs.getByte("ACC_STATUS"));
				memVO.setMemPoint(rs.getInt("MEM_POINT"));
				memVO.setMemImage(rs.getBytes("MEM_IMAGE"));
				memList.add(memVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}

		return memList;
	}

	@Override
	public MemVO findByMemId(Integer memId) {
		MemVO memVO = new MemVO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(GET_ONE_STMT);
			ps.setInt(1, memId);
			rs = ps.executeQuery();
			if (rs.next()) {
				
				memVO.setMemId(rs.getInt("MEM_ID"));
				memVO.setMemAcc(rs.getString("MEM_ACC"));
				memVO.setMemPwd(rs.getString("MEM_PWD"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemIdentity(rs.getString("MEM_IDENTITY"));
				memVO.setMemBth(rs.getDate("MEM_BTH"));
				memVO.setMemSex(rs.getByte("MEM_SEX"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemTel(rs.getInt("MEM_TEL"));
				memVO.setMemAdd(rs.getString("MEM_ADD"));
				memVO.setAccStatus(rs.getByte("ACC_STATUS"));
				memVO.setMemPoint(rs.getInt("MEM_POINT"));
				memVO.setMemImage(rs.getBytes("MEM_IMAGE"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return memVO;
	}

	@Override
	public List<MemVO> getEmail(String memEmail) {
		ArrayList<MemVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(GET_EMAIL);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemVO memVO = new MemVO();
				memVO.setMemId(rs.getInt("MEM_ID"));
				memVO.setMemAcc(rs.getString("MEM_ACC"));
				memVO.setMemPwd(rs.getString("MEM_PWD"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemIdentity(rs.getString("MEM_IDENTITY"));
				memVO.setMemBth(rs.getDate("MEM_BTH"));
				memVO.setMemSex(rs.getByte("MEM_SEX"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemTel(rs.getInt("MEM_TEL"));
				memVO.setMemAdd(rs.getString("MEM_ADD"));
				memVO.setAccStatus(rs.getByte("ACC_STATUS"));
				memVO.setMemPoint(rs.getInt("MEM_POINT"));
				list.add(memVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

}
