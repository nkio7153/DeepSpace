package com.depthspace.keywordqa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.faqtypes.model.model.FaqTypesVO;
import com.depthspace.utils.DBUtil;

public class KeywordQaDAOImpl implements KeywordQaDAO {
	private static final String INSERT_STMT = "INSERT INTO KEYWORD_QA(KW_TYPES, KW_ANS) values (?, ?)";
	private static final String UPDATE_STMT = "UPDATE KEYWORD_QA SET KW_TYPES = ?, KW_ANS = ? WHERE SERIAL_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM KEYWORD_QA WHERE SERIAL_ID = ?";
	private static final String FIND_BY_SERIALID = "SELECT * FROM KEYWORD_QA WHERE SERIAL_ID = ?";
	private static final String GET_ALL = "SELECT * FROM KEYWORD_QA order by SERIAL_ID";

	@Override
	public void insert(KeywordQaVO keywordQaVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, keywordQaVO.getKwTypes());
			pstmt.setString(2, keywordQaVO.getKwAns());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, pstmt, null);
		}
	}

	@Override
	public void update(KeywordQaVO keywordQaVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, keywordQaVO.getKwTypes());
			pstmt.setString(2, keywordQaVO.getKwAns());
			pstmt.setInt(3, keywordQaVO.getSerialId());
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, pstmt, null);
		}
	}

	@Override
	public void delete(Integer serialId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, serialId);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, pstmt, null);
		}
	}

	@Override
	public KeywordQaVO findByPrimaryKey(Integer serialId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		KeywordQaVO keywordQaVO = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(FIND_BY_SERIALID);
			pstmt.setInt(1, serialId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				keywordQaVO = new KeywordQaVO();
				keywordQaVO.setSerialId(rs.getInt("SERIAL_ID"));
				keywordQaVO.setKwTypes(rs.getString("KW_TYPES"));
				keywordQaVO.setKwAns(rs.getString("KW_ANS"));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}

		return keywordQaVO;
	}

	@Override
	public List<KeywordQaVO> getAll() {
		ArrayList<KeywordQaVO> keywordQas = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				KeywordQaVO keywordQa = new KeywordQaVO();
				keywordQa.setSerialId(rs.getInt("SERIAL_ID"));
				keywordQa.setKwTypes(rs.getString("KW_TYPES"));
				keywordQa.setKwAns(rs.getString("KW_ANS"));
				keywordQas.add(keywordQa);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}

		return keywordQas;
	}
}