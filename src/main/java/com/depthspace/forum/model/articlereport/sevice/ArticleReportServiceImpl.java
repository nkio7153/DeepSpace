package com.depthspace.forum.model.articlereport.sevice;

import java.util.List;

import com.depthspace.forum.model.articlereport.ArticleReportVO;
import com.depthspace.forum.model.articlereport.dao.ArticleReportDAO;
import com.depthspace.forum.model.articlereport.dao.ArticleReportDAOImpl;
import com.depthspace.utils.HibernateUtil;

public class ArticleReportServiceImpl implements ArticleReportService{
	private ArticleReportDAO dao;
	
	public ArticleReportServiceImpl() {
		dao = new ArticleReportDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public int insert(ArticleReportVO tod) {
		return dao.insert(tod);
	}

	@Override
	public int update(ArticleReportVO tod) {
		return dao.update(tod);
	}

	@Override
	public List<ArticleReportVO> getAll() {
		return dao.getAll();	
	}

	@Override
	public ArticleReportVO getByArticleReportId(Integer articleReportId) {
		return dao.getByArticleReportId(articleReportId);
	}

	@Override
	public List<ArticleReportVO> getByReportStatus(Byte reportStatus) {
		return dao.getByReportStatus(reportStatus);
	}

}
