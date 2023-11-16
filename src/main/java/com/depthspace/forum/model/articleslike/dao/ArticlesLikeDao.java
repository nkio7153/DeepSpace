package com.depthspace.forum.model.articleslike.dao;

import java.util.List;

import com.depthspace.forum.model.articleslike.ArticlesLikeVO;

public interface ArticlesLikeDao {
	void insert(ArticlesLikeVO entity);
	
	void delete(ArticlesLikeVO.CompositeDetail id);
	
	List<ArticlesLikeVO> getByMemId(Integer memId);
	
	boolean islike(Integer articleId, Integer memId);
}
