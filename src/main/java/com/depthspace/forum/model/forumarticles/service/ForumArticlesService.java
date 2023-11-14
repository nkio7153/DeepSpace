package com.depthspace.forum.model.forumarticles.service;

import java.util.List;
import java.util.Set;

import com.depthspace.forum.model.forumarticles.ForumArticlesVO;

public interface ForumArticlesService {
	int insert(ForumArticlesVO tod);

	int update(ForumArticlesVO tod);

	int delete(Integer articleId);

	// 取得所有文章
	List<ForumArticlesVO> getAll();

	// 根據會員ID取得符合的所有文章
	List<ForumArticlesVO> getByMemId(Integer memId);

	// 取得單筆文章資料
	ForumArticlesVO getByArticleId(Integer articleId);

	// 根據文章類型取得符合的所有文章
	List<ForumArticlesVO> getByArtiTypeId(Integer artiTypeId);

	// 根據會員ID取得他收藏的所有文章
	List<ForumArticlesVO> getByArticleIds(Integer memId);
}
