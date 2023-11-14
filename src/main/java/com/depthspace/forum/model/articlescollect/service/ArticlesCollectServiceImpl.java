package com.depthspace.forum.model.articlescollect.service;

import java.util.List;

import com.depthspace.forum.model.articlescollect.ArticlesCollectVO;
import com.depthspace.forum.model.articlescollect.dao.ArticlesCollectDAO;
import com.depthspace.forum.model.articlescollect.dao.ArticlesCollectDAOImpl;
import com.depthspace.utils.HibernateUtil;

public class ArticlesCollectServiceImpl implements ArticlesCollectService{
	
	private ArticlesCollectDAO dao;
	
	public ArticlesCollectServiceImpl() {
		dao = new ArticlesCollectDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public void insert(ArticlesCollectVO tod) {
		dao.insert(tod);
	}

	@Override
	public void delete(ArticlesCollectVO tod) {		
		 dao.delete(new ArticlesCollectVO.CompositeDetail(tod.getArticleId(),tod.getMemId()));
	}

	@Override
	public List<ArticlesCollectVO> getByMemId(Integer memId) {
		return dao.getByMemId(memId);
	}

	@Override
	public boolean isCollect(Integer articleId, Integer memId) {
		return dao.isCollect(articleId, memId);
	}

}
