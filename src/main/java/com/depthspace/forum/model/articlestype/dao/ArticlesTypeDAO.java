package com.depthspace.forum.model.articlestype.dao;

import java.util.List;

import com.depthspace.forum.model.articlestype.ArticlesTypeVO;

public interface ArticlesTypeDAO {
	int insert(ArticlesTypeVO entity);

	int delete(Integer id);

	List<ArticlesTypeVO> getAll();

}
