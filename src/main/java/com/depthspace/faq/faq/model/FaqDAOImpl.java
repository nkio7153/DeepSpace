package com.depthspace.faq.faq.model;

import java.sql.Connection;
import java.util.List;

public class FaqDAOImpl implements FaqDAO {
	public static final String INSERT_STMT = "insert into department values (?, ?, ?)";
	public static final String FIND_BY_DEPTNO = "select * from department where deptno = ?";
	public static final String GET_ALL = "select * from department order by deptno";
	@Override
	public void insertFaq(FaqVO faqVO) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<FaqVO> getAllFaqs() {
		// TODO Auto-generated method stub
		return null;
	}
}

