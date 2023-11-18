package com.depthspace.forum.model.articleslike.service;

import java.util.List;

import com.depthspace.forum.model.articleslike.ArticlesLikeVO;
import com.depthspace.forum.model.articleslike.dao.ArticlesLikeDao;
import com.depthspace.forum.model.articleslike.dao.ArticlesLikeDaoImpl;
import com.depthspace.utils.HibernateUtil;

public class ArticlesLikeServiceImpl implements ArticlesLikeService{
	
	private ArticlesLikeDao dao;
	
	

	public ArticlesLikeServiceImpl() {
		dao = new ArticlesLikeDaoImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public void insert(ArticlesLikeVO tod) {
		dao.insert(tod);	
	}

	@Override
	public void delete(ArticlesLikeVO tod) {
		dao.delete(new ArticlesLikeVO.CompositeDetail(tod.getArticleId(),tod.getMemId()));	
	}

	@Override
	public List<ArticlesLikeVO> getByMemId(Integer memId) {
		return dao.getByMemId(memId);
	}

	@Override
	public boolean islike(Integer articleId, Integer memId) {
		return dao.islike(articleId, memId);
	}
	

}
