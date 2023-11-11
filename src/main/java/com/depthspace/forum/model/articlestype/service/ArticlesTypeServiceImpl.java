package com.depthspace.forum.model.articlestype.service;

import java.util.List;

import com.depthspace.forum.model.articlestype.ArticlesTypeVO;
import com.depthspace.forum.model.articlestype.dao.ArticlesTypeDAO;
import com.depthspace.forum.model.articlestype.dao.ArticlesTypeDAOImpl;
import com.depthspace.utils.HibernateUtil;

public class ArticlesTypeServiceImpl implements ArticlesTypeService {

	private ArticlesTypeDAO dao;

	public ArticlesTypeServiceImpl() {
		dao = new ArticlesTypeDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public int insert(ArticlesTypeVO tod) {
		return dao.insert(tod);
	}

	@Override
	public int delete(Integer id) {
		return dao.delete(id);
	}

	@Override
	public List<ArticlesTypeVO> getAll() {
		return dao.getAll();
	}

}
