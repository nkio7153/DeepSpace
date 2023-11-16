package com.depthspace.forum.model.articleslike.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.forum.model.articleslike.ArticlesLikeVO;
import com.depthspace.forum.model.articleslike.ArticlesLikeVO.CompositeDetail;

public class ArticlesLikeDaoImpl implements ArticlesLikeDao {
	private SessionFactory factory;

	public ArticlesLikeDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(ArticlesLikeVO entity) {
		getSession().save(entity);

	}

	@Override
	public void delete(CompositeDetail id) {
		ArticlesLikeVO alvo = getSession().get(ArticlesLikeVO.class, id);
		// 0:失敗 1:成功
		int state;
		if (alvo != null) {
			getSession().delete(alvo);

			state = 1;
		} else {

			state = 0;
		}

	}

	@Override
	public List<ArticlesLikeVO> getByMemId(Integer memId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean islike(Integer articleId, Integer memId) {
		// TODO Auto-generated method stub
		return false;
	}

}
