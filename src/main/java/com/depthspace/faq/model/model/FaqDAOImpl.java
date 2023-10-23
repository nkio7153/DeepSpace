package com.depthspace.faq.model.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.depthspace.utils.DBUtil;

public class FaqDAOImpl implements FaqDAO {
	private static final String INSERT_STMT = "INSERT INTO FAQ (FAQ_NO,FAQ_NAME,FAQ_ANS) values (?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE FAQ SET FAQ_NO = ?, FAQ_NAME = ?, FAQ_ANS = ? WHERE SERIAL_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM FAQ WHERE SERIAL_ID = ?";
	private static final String FIND_BY_SERIALID = "SELECT SERIAL_ID,FAQ_NO,FAQ_NAME,FAQ_ANS FROM FAQ where SERIAL_ID = ?";
	private static final String GET_ALL = "SELECT SERIAL_ID,FAQ_NO,FAQ_NAME,FAQ_ANS FROM FAQ order by SERIAL_ID";

	@Override
	public void insert(FaqVO faqVO) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(INSERT_STMT);
            
            pstmt.setInt(1, faqVO.getFaqNo());
            pstmt.setString(2, faqVO.getFaqName());
            pstmt.setString(3, faqVO.getFaqAns());
     
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DBUtil.close(conn, pstmt, null);
        }
	}

	@Override
	public void update(FaqVO faqVO) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(UPDATE_STMT);
            pstmt.setInt(1, faqVO.getFaqNo());
            pstmt.setString(2, faqVO.getFaqName());
            pstmt.setString(3, faqVO.getFaqAns());
            pstmt.setInt(4, faqVO.getSerialId()); // 這是WHERE條件，確保只更新特定的FAQ記錄
     
            pstmt.executeUpdate();
            
        } catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
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
        }finally{
            DBUtil.close(conn, pstmt, null);
        }
	}

	@Override
	public FaqVO findByPrimaryKey(Integer serialId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FaqVO faqVO = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(FIND_BY_SERIALID);
			pstmt.setInt(1, serialId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				faqVO = new FaqVO();
				faqVO.setSerialId(rs.getInt("SERIAL_ID"));
				faqVO.setFaqNo(rs.getInt("FAQ_NO"));
				faqVO.setFaqName(rs.getString("FAQ_NAME"));
				faqVO.setFaqAns(rs.getString("FAQ_ANS"));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}

		return faqVO;
	}

	@Override
	public List<FaqVO> getAllFaqs() {
	    ArrayList<FaqVO> faqs = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        con = DBUtil.getConnection();
	        pstmt = con.prepareStatement(GET_ALL);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            FaqVO faq = new FaqVO();
	            faq.setSerialId(rs.getInt("SERIAL_ID"));
	            faq.setFaqNo(rs.getInt("FAQ_NO"));
	            faq.setFaqName(rs.getString("FAQ_NAME"));
	            faq.setFaqAns(rs.getString("FAQ_ANS"));
	            faqs.add(faq);
	        }

	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    } finally {
	        DBUtil.close(con, pstmt, rs);
	    }

	    return faqs;
	}

}