package com.depthspace.forum.model.articlestype.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.forum.model.articlestype.ArticlesTypeVO;

public class ArticlesTypeDAOImpl implements ArticlesTypeDAO {

	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public ArticlesTypeDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(ArticlesTypeVO entity) {
		int count = (int) getSession().save(entity);
		return count;
	}

	@Override
	public int delete(Integer id) {
		ArticlesTypeVO atvo = getSession().get(ArticlesTypeVO.class, id);
		// 0:失敗 1:成功
		int state;
		if (atvo != null) {
			getSession().delete(atvo);

			state = 1;
		} else {

			state = 0;
		}
		return state;
	}

	@Override
	public List<ArticlesTypeVO> getAll() {
		return getSession().createQuery("FROM ArticlesTypeVO", ArticlesTypeVO.class).getResultList();
	}

}
