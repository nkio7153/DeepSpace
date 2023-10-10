package com.depthspace.account.model.account;

import com.depthspace.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountJDBCDAO implements AccountDAO_Interface {
	private static final String INSERT_STMT = "INSERT INTO ACCOUNT(ACCOUNT_ID, ACCOUNT_NAME ,MEM_ID) VALUES(?,?,?)";
	private static final String DELETE = "DELETE FROM ACCOUNT WHERE ACCOUNT_ID=?";
	private static final String UPDATE = "UPDATE ACCOUNT SET ACCOUNT_NAME=?, MEM_ID=? WHERE ACCOUNT_ID=?";
	private static final String GET_ONE_STMT = "SELECT ACCOUNT_ID, ACCOUNT_NAME, MEM_ID FROM ACCOUNT WHERE ACCOUNT_ID=?";
	private static final String GET_ALL_STMT = "SELECT ACCOUNT_ID, ACCOUNT_NAME, MEM_ID FROM ACCOUNT ORDER BY ACCOUNT_ID";

	@Override
	public void insert(AccountVO tod) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(INSERT_STMT);
			ps.setInt(1, tod.getAccountId());
			ps.setString(2, tod.getAccountName());
			ps.setInt(3, tod.getMemId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	@Override
	public void update(AccountVO tod) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(UPDATE);
			ps.setInt(1, tod.getAccountId());
			ps.setString(2, tod.getAccountName());
			ps.setInt(3, tod.getMemId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	@Override
	public void delete(Integer accountId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(DELETE);
			ps.setInt(1, accountId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	@Override
	public AccountVO findByPrimaryKey(Integer accountId) {
		AccountVO tod = new AccountVO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(GET_ONE_STMT);
			ps.setInt(1, accountId);
			rs = ps.executeQuery();
			if (rs.next()) {
				tod.setAccountId(rs.getInt("ACCOUNT_ID"));
				tod.setAccountName(rs.getString("ACCOUNT_NAME"));
				tod.setMemId(rs.getInt("MEM_ID"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return tod;
	}

	@Override
	public List<AccountVO> getAll() {
		ArrayList<AccountVO> tods = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(GET_ALL_STMT);
			rs = ps.executeQuery();
			while (rs.next()) {
				AccountVO tod = new AccountVO();
				tod.setAccountId(rs.getInt("ACCOUNT_ID"));
				tod.setAccountName(rs.getString("ACCOUNT_NAME"));
				tod.setMemId(rs.getInt("MEM_ID"));
				tods.add(tod);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return tods;
	}
}
