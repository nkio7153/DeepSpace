package com.depthspace.forum.model.articlestype.service;

import java.util.List;

import com.depthspace.forum.model.articlestype.ArticlesTypeVO;

public interface ArticlesTypeService {
	int insert(ArticlesTypeVO tod);

	int delete(Integer id);

	List<ArticlesTypeVO> getAll();
}
