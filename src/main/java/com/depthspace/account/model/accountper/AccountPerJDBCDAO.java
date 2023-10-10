package com.depthspace.account.model.accountper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.depthspace.utils.DBUtil;

public class AccountPerJDBCDAO implements AccountPerDAO_Interface{
	private static final String INSERT_STMT = "INSERT INTO ACCOUNT_PER(ACCOUNT_PER_ID, ACCOUNT_PER_NAME ,ACCOUNT_ID,MONEY_SUM,ACCOUNT_SUM,DEBT) VALUES(?,?,?,?,?,?)";
	private static final String DELETE = "DELETE FROM ACCOUNT_PER WHERE ACCOUNT_PER_ID=?";
	private static final String UPDATE = "UPDATE ACCOUNT_PER SET ACCOUNT_PER_NAME=?, ACCOUNT_ID=?, MONEY_SUM=?, ACCOUNT_SUM=?, DEBT=? WHERE ACCOUNT_PER_ID=?";
	private static final String GET_ONE_STMT = "SELECT ACCOUNT_PER_ID, ACCOUNT_PER_NAME ,ACCOUNT_ID,MONEY_SUM,ACCOUNT_SUM,DEBT FROM ACCOUNT_PER WHERE ACCOUNT_PER_ID=?";
	private static final String GET_ALL_STMT = "SELECT ACCOUNT_PER_ID, ACCOUNT_PER_NAME ,ACCOUNT_ID,MONEY_SUM,ACCOUNT_SUM,DEBT FROM ACCOUNT_PER ORDER BY ACCOUNT_PER_ID";

	@Override
	public void insert(AccountPerVO tod) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(INSERT_STMT);
			ps.setInt(1, tod.getAccountPerId());
			ps.setString(2, tod.getAccountPerName());
			ps.setInt(3, tod.getAccountId());
			ps.setInt(4, tod.getMoneySum());
			ps.setInt(5, tod.getAccountSum());
			ps.setInt(6, tod.getDebt());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	@Override
	public void update(AccountPerVO tod) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(UPDATE);
			ps.setInt(6, tod.getAccountPerId());
			ps.setString(1, tod.getAccountPerName());
			ps.setInt(2, tod.getAccountId());
			ps.setInt(3, tod.getMoneySum());
			ps.setInt(4, tod.getAccountSum());
			ps.setInt(5, tod.getDebt());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	@Override
	public void delete(Integer accountPerId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(DELETE);
			ps.setInt(1, accountPerId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	@Override
	public AccountPerVO findByPrimaryKey(Integer accountPerId) {
		AccountPerVO tod = new AccountPerVO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(GET_ONE_STMT);
			ps.setInt(1, accountPerId);
			rs = ps.executeQuery();
			if (rs.next()) {
				tod.setAccountPerId(rs.getInt("ACCOUNT_PER_ID"));
				tod.setAccountPerName(rs.getString("ACCOUNT_PER_NAME"));
				tod.setAccountId(rs.getInt("ACCOUNT_ID"));
				tod.setMoneySum(rs.getInt("MONEY_SUM"));
				tod.setAccountSum(rs.getInt("ACCOUNT_SUM"));
				tod.setDebt(rs.getInt("DEBT"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return tod;
	}

	@Override
	public List<AccountPerVO> getAll() {
		ArrayList<AccountPerVO> tods = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(GET_ALL_STMT);
			rs = ps.executeQuery();
			while (rs.next()) {
				AccountPerVO tod = new AccountPerVO();
				tod.setAccountPerId(rs.getInt("ACCOUNT_PER_ID"));
				tod.setAccountPerName(rs.getString("ACCOUNT_PER_NAME"));
				tod.setAccountId(rs.getInt("ACCOUNT_ID"));
				tod.setMoneySum(rs.getInt("MONEY_SUM"));
				tod.setAccountSum(rs.getInt("ACCOUNT_SUM"));
				tod.setDebt(rs.getInt("DEBT"));
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
