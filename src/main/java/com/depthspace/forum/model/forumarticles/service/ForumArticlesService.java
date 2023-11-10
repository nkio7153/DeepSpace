package com.depthspace.forum.model.forumarticles.service;

import java.util.List;

import com.depthspace.forum.model.forumarticles.ForumArticlesVO;

public interface ForumArticlesService {
	 int insert(ForumArticlesVO tod);
	 
	 int update(ForumArticlesVO tod);
	 
	 int delete(Integer articleId);
	 
	 List<ForumArticlesVO> getAll();
	 
	 List<ForumArticlesVO> getByMemId(Integer memId);
	 
	 ForumArticlesVO getByArticleId(Integer articleId);
	 
	 List<ForumArticlesVO> getByArtiTypeId(Integer artiTypeId);
}
