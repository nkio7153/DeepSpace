package com.depthspace.forum.model.articlereport.sevice;

import java.util.List;

import com.depthspace.forum.model.articlereport.ArticleReportVO;

public interface ArticleReportService {
	int insert(ArticleReportVO tod);

	int update(ArticleReportVO tod);

	List<ArticleReportVO> getAll();
	
	ArticleReportVO getByArticleReportId(Integer articleReportId);
}
