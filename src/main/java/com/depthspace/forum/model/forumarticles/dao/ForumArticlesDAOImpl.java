package com.depthspace.forum.model.forumarticles.dao;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.depthspace.forum.model.forumarticles.ForumArticlesVO;

public class ForumArticlesDAOImpl implements ForumArticlesDAO {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public ForumArticlesDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(ForumArticlesVO entity) {
		int count = (int) getSession().save(entity);
		return count;
	}

	@Override
	public int update(ForumArticlesVO entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer id) {
		ForumArticlesVO favo = getSession().get(ForumArticlesVO.class, id);
		// 0:失敗 1:成功
		int state;
		if (favo != null) {
			getSession().delete(favo);

			state = 1;
		} else {

			state = 0;
		}
		return state;
	}

	@Override
	public List<ForumArticlesVO> getAll() {
		List<ForumArticlesVO> dataList = getSession().createQuery("FROM ForumArticlesVO", ForumArticlesVO.class)
				.getResultList();
		return dataList;
	}

	@Override
	public List<ForumArticlesVO> getByMemId(Integer memId) {
		return getSession().createQuery("FROM ForumArticlesVO WHERE memId= :memId", ForumArticlesVO.class)
				.setParameter("memId", memId).list();
	}

	@Override
	public ForumArticlesVO getByArticleId(Integer articleId) {
		return getSession().get(ForumArticlesVO.class, articleId);
	}

	@Override
	public List<ForumArticlesVO> getByArtiTypeId(Integer artiTypeId) {
		return getSession().createQuery("FROM ForumArticlesVO WHERE artiTypeId= :artiTypeId", ForumArticlesVO.class)
				.setParameter("artiTypeId", artiTypeId).list();
	}

	@Override
	public List<ForumArticlesVO> getByArticleIds(List<Integer> articleIds) {
		return getSession().createQuery("FROM ForumArticlesVO WHERE articleId IN (:articleIds)", ForumArticlesVO.class)
				.setParameterList("articleIds", articleIds).list();
	}
}
