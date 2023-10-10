package com.depthspace.faq.model.faqtypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.depthspace.faq.model.faq.FaqDAO;
import com.depthspace.faq.model.faq.FaqVO;
import com.depthspace.utils.DBUtil;

public class FaqTypesDAOImpl implements FaqTypesDAO{

	private static final String INSERT_STMT = "insert into FAQ_TYPES values (?, ?)";
	private static final String UPDATE_STMT = "UPDATE FAQ_TYPES SET Q_TYPES = ? WHERE FAQ_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM FAQ_TYPES WHERE FAQ_NO = ?";
	private static final String FIND_BY_SERIALID = "select * from FAQ_TYPES where FAQ_NO = ?";
	private static final String GET_ALL = "SELECT * FROM FAQ_TYPES";

	@Override
	public void insert(FaqTypesVO faqtypesVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, faqtypesVO.getFaqNo());
			pstmt.setString(2, faqtypesVO.getqTypes());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, pstmt, null);
		}
	}

	@Override
	public void update(FaqTypesVO faqTypesVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, faqTypesVO.getqTypes());
			pstmt.setInt(2, faqTypesVO.getFaqNo());
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, pstmt, null);
		}
	}

	@Override
	public void delete(Integer faqNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, faqNo);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn, pstmt, null);
		}
	}

	@Override
	public FaqTypesVO findByPrimaryKey(Integer faqNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FaqTypesVO faqtypesVO = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(FIND_BY_SERIALID);
			pstmt.setInt(1, faqNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				faqtypesVO = new FaqTypesVO();
				faqtypesVO.setFaqNo(rs.getInt("FAQ_NO"));
				faqtypesVO.setqTypes(rs.getString("Q_TYPES"));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}

		return faqtypesVO;
	}

	@Override
	public List<FaqTypesVO> getAll() {
		ArrayList<FaqTypesVO> faqtypes = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				FaqTypesVO faqtype = new FaqTypesVO();
				faqtype.setFaqNo(rs.getInt("FAQ_NO"));
				faqtype.setqTypes(rs.getString("Q_TYPES"));
				faqtypes.add(faqtype);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}

		return faqtypes;
	}
}
