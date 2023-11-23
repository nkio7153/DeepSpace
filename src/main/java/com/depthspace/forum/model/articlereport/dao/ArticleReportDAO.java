package com.depthspace.forum.model.articlereport.dao;

import java.util.List;

import com.depthspace.forum.model.articlereport.ArticleReportVO;

public interface ArticleReportDAO {
	int insert(ArticleReportVO entity);

	int update(ArticleReportVO entity);

	List<ArticleReportVO> getAll();
}
