package com.depthspace.forum.model.forumarticles.service;

import java.util.Base64;
import java.util.List;

import com.depthspace.forum.model.forumarticles.ForumArticlesVO;
import com.depthspace.forum.model.forumarticles.dao.ForumArticlesDAO;
import com.depthspace.forum.model.forumarticles.dao.ForumArticlesDAOImpl;
import com.depthspace.utils.HibernateUtil;

public class ForumArticlesServiceImpl implements ForumArticlesService{

	private ForumArticlesDAO dao;
	
	public ForumArticlesServiceImpl() {
		dao = new ForumArticlesDAOImpl(HibernateUtil.getSessionFactory());
	}
	@Override
	public int insert(ForumArticlesVO tod) {
		return dao.insert(tod);
	}

	@Override
	public int update(ForumArticlesVO tod) {
		return dao.update(tod);
	}

	@Override
	public int delete(Integer articleId) {
		return dao.delete(articleId);
	}

	@Override
	public List<ForumArticlesVO> getAll() {
		List<ForumArticlesVO> list = dao.getAll();
		for (ForumArticlesVO vo : list) {
			vo.setBase64Str(Base64.getEncoder().encodeToString(vo.getAtriImg()));
		}
		return list;
	}
	
}
