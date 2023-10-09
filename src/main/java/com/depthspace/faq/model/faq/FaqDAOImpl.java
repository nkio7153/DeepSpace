package com.depthspace.faq.model.faq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.depthspace.utils.DBUtil;

public class FaqDAOImpl implements FaqDAO {
	public static final String INSERT_STMT = "insert into FAQ values (?, ?, ?)";
	public static final String FIND_BY_SERIALID = "select * from FAQ where SERIAL_ID = ?";

	@Override
	public void insertFaq(FaqVO faqVO) {
		// 省略插入方法的程式碼
	}

	@Override
	public void updateFaq(FaqVO faqVO) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteFaq(Integer serialId) {
		// TODO Auto-generated method stub
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
				faqVO.setSerialId(serialId);
				faqVO.setFaqNo(rs.getInt("FAQ_NO"));
				faqVO.setFaqName(rs.getString("FAQ_NAME"));
				faqVO.setFaqAns(rs.getString("FAQ_ANS"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBUtil.close(con, pstmt, rs);
		}

		return faqVO;
	}

	@Override
	public List<FaqVO> getAllFaqs() {
		// TODO Auto-generated method stub
		return null;
	}
}
