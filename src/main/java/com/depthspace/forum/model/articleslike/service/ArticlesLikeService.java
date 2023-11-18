package com.depthspace.forum.model.articleslike.service;

import java.util.List;

import com.depthspace.forum.model.articleslike.ArticlesLikeVO;

public interface ArticlesLikeService {
	void insert(ArticlesLikeVO tod);

	void delete(ArticlesLikeVO tod);

	List<ArticlesLikeVO> getByMemId(Integer memId);

	boolean islike(Integer articleId, Integer memId);
}
