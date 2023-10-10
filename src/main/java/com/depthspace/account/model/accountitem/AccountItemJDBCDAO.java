package com.depthspace.account.model.accountitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.depthspace.utils.DBUtil;

public class AccountItemJDBCDAO implements AccountItemDAO_Interface{
	private static final String INSERT_STMT = "INSERT INTO ACCOUNT_ITEM(ACCOUNT_ITEM_ID, ACCOUNT_ITEM ,DATE,CONSUME_ACCOUNT) VALUES(?,?,?,?)";
	private static final String DELETE = "DELETE FROM ACCOUNT_ITEM WHERE ACCOUNT_ITEM_ID=?";
	private static final String UPDATE = "UPDATE ACCOUNT_ITEM SET ACCOUNT_ITEM=?, DATE=?, CONSUME_ACCOUNT=? WHERE ACCOUNT_ITEM_ID=?";
	private static final String GET_ONE_STMT = "SELECT ACCOUNT_ITEM_ID, ACCOUNT_ITEM, DATE, CONSUME_ACCOUNT FROM ACCOUNT_ITEM WHERE ACCOUNT_ITEM_ID=?";
	private static final String GET_ALL_STMT = "SELECT ACCOUNT_ITEM_ID, ACCOUNT_ITEM, DATE, CONSUME_ACCOUNT FROM ACCOUNT_ITEM ORDER BY ACCOUNT_ITEM_ID";

	@Override
	public void insert(AccountItemVO tod) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(INSERT_STMT);
			ps.setInt(1, tod.getAccountItemId());
			ps.setString(2, tod.getAccountItem());
			ps.setDate(3, tod.getDate());
			ps.setInt(4, tod.getConsumeAccount());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	@Override
	public void update(AccountItemVO tod) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(UPDATE);
			ps.setInt(4, tod.getAccountItemId());
			ps.setString(1, tod.getAccountItem());
			ps.setDate(2, tod.getDate());
			ps.setInt(3, tod.getConsumeAccount());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	@Override
	public void delete(Integer accountItemId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(DELETE);
			ps.setInt(1, accountItemId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	@Override
	public AccountItemVO findByPrimaryKey(Integer accountItemId) {
		AccountItemVO tod = new AccountItemVO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(GET_ONE_STMT);
			ps.setInt(1, accountItemId);
			rs = ps.executeQuery();
			if (rs.next()) {
				tod.setAccountItemId(rs.getInt("ACCOUNT_ITEM_ID"));
				tod.setAccountItem(rs.getString("ACCOUNT_ITEM"));
				tod.setDate(rs.getDate("DATE"));
				tod.setConsumeAccount(rs.getInt("CONSUME_ACCOUNT"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return tod;
	}

	@Override
	public List<AccountItemVO> getAll() {
		ArrayList<AccountItemVO> tods = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(GET_ALL_STMT);
			rs = ps.executeQuery();
			while (rs.next()) {
				AccountItemVO tod = new AccountItemVO();
				tod.setAccountItemId(rs.getInt("ACCOUNT_ITEM_ID"));
				tod.setAccountItem(rs.getString("ACCOUNT_ITEM"));
				tod.setDate(rs.getDate("DATE"));
				tod.setConsumeAccount(rs.getInt("CONSUME_ACCOUNT"));
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
