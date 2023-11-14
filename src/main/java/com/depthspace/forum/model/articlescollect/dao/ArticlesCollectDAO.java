package com.depthspace.forum.model.articlescollect.dao;

import java.util.List;

import com.depthspace.forum.model.articlescollect.ArticlesCollectVO;

public interface ArticlesCollectDAO {
	void insert(ArticlesCollectVO entity);

	void delete(ArticlesCollectVO.CompositeDetail id);

	List<ArticlesCollectVO> getByMemId(Integer memId);
	
	boolean isCollect(Integer articleId, Integer memId);
}
