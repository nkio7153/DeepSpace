package com.depthspace.forum.model.articlescollect.service;

import java.util.List;

import com.depthspace.forum.model.articlescollect.ArticlesCollectVO;

public interface ArticlesCollectService {
	void insert(ArticlesCollectVO tod);

	void delete(ArticlesCollectVO tod);

	List<ArticlesCollectVO> getByMemId(Integer memId);

	boolean isCollect(Integer articleId, Integer memId);
	
	void deleteByArticleId(Integer articleId);
}
