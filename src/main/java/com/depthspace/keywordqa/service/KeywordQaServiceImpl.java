package com.depthspace.keywordqa.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.depthspace.account.model.account.AccountVO;
import com.depthspace.account.model.account.dao.AccountDAO;
import com.depthspace.account.model.account.dao.AccountDAOImpl;
import com.depthspace.column.dao.ColumnArticlesDAO;
import com.depthspace.column.dao.ColumnArticlesDAOImpl;
import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.keywordqa.model.KeywordQaDAO;
import com.depthspace.keywordqa.model.KeywordQaDAOImpl;
import com.depthspace.keywordqa.model.KeywordQaVO;
import com.depthspace.utils.HibernateUtil;

public class KeywordQaServiceImpl implements KeywordQaService {

	public static final int PAGE_MAX_RESULT = 8;
	private KeywordQaDAO dao;

	public KeywordQaServiceImpl() {
		dao = new KeywordQaDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public KeywordQaVO addKeywordQa(KeywordQaVO keywordQaVO) {
		dao.insert(keywordQaVO);
		return keywordQaVO;
	}

	@Override
	public KeywordQaVO updateKeywordQa(KeywordQaVO keywordQaVO) {
		dao.update(keywordQaVO);
		return keywordQaVO;
	}

	@Override
	public void deleteKeywordQa(Integer serialId) {
		dao.delete(serialId);
	}

	@Override
	public KeywordQaVO getKeywordQaiBySerialId(Integer serialId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<KeywordQaVO> getAllKeywordQa() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<KeywordQaVO> criteriaQuery = criteriaBuilder.createQuery(KeywordQaVO.class);
			Root<KeywordQaVO> root = criteriaQuery.from(KeywordQaVO.class);
			criteriaQuery.select(root);

			Query<KeywordQaVO> query = session.createQuery(criteriaQuery);

			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Error", e);
		}
	}

	@Override
	public List<KeywordQaVO> getAllKeywordQa2(int currentPage) {
		List<KeywordQaVO> KeywordQa = new ArrayList<>();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<KeywordQaVO> criteriaQuery = criteriaBuilder.createQuery(KeywordQaVO.class);
			Root<KeywordQaVO> root = criteriaQuery.from(KeywordQaVO.class);
			criteriaQuery.select(root);

			Query<KeywordQaVO> query = session.createQuery(criteriaQuery);

			// 分頁
			query.setFirstResult((currentPage - 1) * PAGE_MAX_RESULT);
			query.setMaxResults(PAGE_MAX_RESULT);

			KeywordQa = query.list();
		} catch (Exception e) {
			throw new RuntimeException("Error", e);
		}

		return KeywordQa;
	}

	@Override
	public List<KeywordQaVO> getAllAdmins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KeywordQaVO> getAllSortById(String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();

		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
	
}
