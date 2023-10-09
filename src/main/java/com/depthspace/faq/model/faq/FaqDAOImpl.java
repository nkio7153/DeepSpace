package com.depthspace.faq.model.faq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.utils.DBUtil;

public class FaqDAOImpl implements FaqDAO {
	private static final String INSERT_STMT = "insert into FAQ values (?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE FAQ SET FAQ_NO = ?, FAQ_NAME = ?, FAQ_ANS = ? WHERE SERIAL_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM FAQ WHERE SERIAL_ID = ?";
	private static final String FIND_BY_SERIALID = "select * from FAQ where SERIAL_ID = ?";
	private static final String GET_ALL = "SELECT * FROM FAQ";

	@Override
	public void insertFaq(FaqVO faqVO) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(INSERT_STMT);
            pstmt.setInt(1, faqVO.getSerialId());
            pstmt.setInt(2, faqVO.getFaqNo());
            pstmt.setString(3, faqVO.getFaqName());
            pstmt.setString(4, faqVO.getFaqAns());
     
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DBUtil.close(conn, pstmt, null);
        }
	}

	@Override
	public void updateFaq(FaqVO faqVO) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(UPDATE_STMT);
            pstmt.setInt(1, faqVO.getSerialId());
            pstmt.setInt(2, faqVO.getFaqNo());
            pstmt.setString(3, faqVO.getFaqName());
            pstmt.setString(4, faqVO.getFaqAns());
     
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DBUtil.close(conn, pstmt, null);
        }
	}

	@Override
	public void deleteFaq(Integer serialId) {
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
