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

import com.depthspace.account.model.account.AccountVO;
import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.utils.DBUtil;

public class KeywordQaDAOImpl implements KeywordQaDAO {
	public static final int PAGE_MAX_RESULT = 10;
	private SessionFactory factory;

	public KeywordQaDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public void insert(KeywordQaVO keywordQaVO) {
		getSession().save(keywordQaVO);
	}

	@Override
	public void update(KeywordQaVO keywordQaVO) {
		getSession().update(keywordQaVO);		
	}

	@Override
	public void delete(Integer id) {
		KeywordQaVO keywordQa = getSession().get(KeywordQaVO.class, id);
		getSession().delete(keywordQa);
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from KeywordQaVO", Long.class).uniqueResult();
	}

	@Override
	public List<KeywordQaVO> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT; // 計算當前頁面第一條索引
		return getSession().createQuery("from KeywordQaVO", KeywordQaVO.class).setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT) // 每頁紀錄數量
				.list(); // 查出的資料存於此列表
	}

	@Override
	public List<KeywordQaVO> getAll() {
		return getSession().createQuery("from KeywordQaVO", KeywordQaVO.class).list();
	}

	@Override
	public KeywordQaVO getById(Integer serialId) {
		return getSession().get(KeywordQaVO.class, serialId);
	}

}