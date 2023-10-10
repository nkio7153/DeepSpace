package com.depthspace.faq.model.keywordqa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.depthspace.faq.model.faqtypes.FaqTypesDAO;
import com.depthspace.faq.model.faqtypes.FaqTypesVO;
import com.depthspace.utils.DBUtil;

public class KeywordQaDAOImpl implements KeywordQaDAO {

	private static final String INSERT_STMT = "insert into KEYWORD_QA values (?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE KEYWORD_QA SET KW_TYPES = ?, KW_ANS = ? WHERE SERIAL_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM KEYWORD_QA WHERE SERIAL_ID = ?";
	private static final String FIND_BY_SERIALID = "select * from KEYWORD_QA where SERIAL_ID = ?";
	private static final String GET_ALL = "SELECT * FROM KEYWORD_QA";

	@Override
	public void insert(KeywordQaVO keywordqaVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, keywordqaVO.getSerialId());
			pstmt.setString(2, keywordqaVO.getKwTypes());
			pstmt.setString(3, keywordqaVO.getKwAns());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, pstmt, null);
		}
	}

	@Override
	public void update(KeywordQaVO keywordqaVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, keywordqaVO.getKwTypes());
			pstmt.setString(2, keywordqaVO.getKwAns());
			pstmt.setInt(3, keywordqaVO.getSerialId());

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
		KeywordQaVO keywordqaVO = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(FIND_BY_SERIALID);
			pstmt.setInt(1, serialId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				keywordqaVO = new KeywordQaVO();
				keywordqaVO.setSerialId(rs.getInt("SERIAL_ID"));
				keywordqaVO.setKwTypes(rs.getString("KW_TYPES"));
				keywordqaVO.setKwAns(rs.getString("KW_ANS"));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}

		return keywordqaVO;
	}

	@Override
	public List<KeywordQaVO> getAll() {
		ArrayList<KeywordQaVO> keywordqas = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				KeywordQaVO keywordqa = new KeywordQaVO();
				keywordqa.setSerialId(rs.getInt("SERIAL_ID"));
				keywordqa.setKwTypes(rs.getString("KW_TYPES"));
				keywordqa.setKwAns(rs.getString("KW_ANS"));
				keywordqas.add(keywordqa);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}

		return keywordqas;
	}
}