package com.depthspace.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.depthspace.utils.DBUtil;

public class MemJDBCDAO implements MemDAO_Interface {
	private static final String INSERT_STMT = "INSERT INTO MEM(MEM_ACC, MEM_PWD, MEM_NAME, MEM_IDENTITY, MEM_BTH,	MEM_SEX, MEM_EMAIL, MEM_TEL, MEM_ADD, ACC_STATUS, MEM_POINT) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE MEM SET MEM_ACC=? MEM_PWD=?, MEM_NAME=?, MEM_IDENTITY=?, MEM_BTH=?,	MEM_SEX=?, MEM_EMAIL=?, MEM_TEL=?, MEM_ADD=?, ACC_STATUS=?, MEM_POINT=? WHERE MEM_ID=?";
	private static final String DELETE_STMT = "DELETE FROM MEM WHERE MEM_ID=?";
	private static final String GET_ONE_STMT = "SELECT MEM_ACC, MEM_PWD, MEM_NAME, MEM_IDENTITY, MEM_BTH,	MEM_SEX, MEM_EMAIL, MEM_TEL, MEM_ADD, ACC_STATUS, MEM_POINT FROM MEM WHERE MEM_ID=?";
	private static final String GET_ALL_STMT = "SELECT MEM_ACC, MEM_PWD, MEM_NAME, MEM_IDENTITY, MEM_BTH,	MEM_SEX, MEM_EMAIL, MEM_TEL, MEM_ADD, ACC_STATUS, MEM_POINT FROM MEM";

	@Override
	public void insert(MemVO MemVO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(INSERT_STMT);
			ps.setInt(1, MemVO.getMemId());
			ps.setString(2, MemVO.getMemAcc());
			ps.setString(3, MemVO.getMemPwd());
			ps.setString(4, MemVO.getMemName());
			ps.setString(5, MemVO.getMemIdentity());
			ps.setTimestamp(6, MemVO.getMemBth());
			ps.setByte(7, MemVO.getMemSex());
			ps.setString(8, MemVO.getMemEmail());
			ps.setInt(8, MemVO.getMemTel());
			ps.setString(8, MemVO.getMemAdd());
			ps.setByte(8, MemVO.getAccStatus());
			ps.setInt(8, MemVO.getMemPoint());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	@Override
	public void update(MemVO MemVO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(UPDATE_STMT);
			ps.setInt(1, MemVO.getMemId());
			ps.setString(2, MemVO.getMemAcc());
			ps.setString(3, MemVO.getMemPwd());
			ps.setString(4, MemVO.getMemName());
			ps.setString(5, MemVO.getMemIdentity());
			ps.setTimestamp(6, MemVO.getMemBth());
			ps.setByte(7, MemVO.getMemSex());
			ps.setString(8, MemVO.getMemEmail());
			ps.setInt(8, MemVO.getMemTel());
			ps.setString(8, MemVO.getMemAdd());
			ps.setByte(8, MemVO.getAccStatus());
			ps.setInt(8, MemVO.getMemPoint());
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
				memVO.setMemId(memId);
				memVO.setMemAcc(rs.getString("MEM_ACC"));
				memVO.setMemPwd(rs.getString("MEM_PWD"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemIdentity(rs.getString("MEM_IDENTITY"));
				memVO.setMemBth(rs.getTimestamp("MEM_BTH"));
				memVO.setMemSex(rs.getByte("MEM_SEX"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemTel(rs.getInt("MEM_TEL"));
				memVO.setMemAdd(rs.getString("MEM_ADD"));
				memVO.setAccStatus(rs.getByte("ACC_STATUS"));
				memVO.setMemPoint(rs.getInt("MEM_POINT"));
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
				memVO.setMemBth(rs.getTimestamp("MEM_BTH"));
				memVO.setMemSex(rs.getByte("MEM_SEX"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemTel(rs.getInt("MEM_TEL"));
				memVO.setMemAdd(rs.getString("MEM_ADD"));
				memVO.setAccStatus(rs.getByte("ACC_STATUS"));
				memVO.setMemPoint(rs.getInt("MEM_POINT"));
				memList.add(memVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}

		return memList;
	}

}
