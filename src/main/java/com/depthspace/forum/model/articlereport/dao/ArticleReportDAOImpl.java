package com.depthspace.forum.model.articlereport.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.forum.model.articlereport.ArticleReportVO;

public class ArticleReportDAOImpl implements ArticleReportDAO{
	private SessionFactory factory;
	
	public ArticleReportDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
		// 以避免請求執行緒共用了同個 Session
		private Session getSession() {
			return factory.getCurrentSession();
	}
		
	@Override
	public int insert(ArticleReportVO entity) {
		int count = (int) getSession().save(entity);
		return count;
	}

	@Override
	public int update(ArticleReportVO entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public List<ArticleReportVO> getAll() {
		List<ArticleReportVO> dataList = getSession().createQuery("FROM ArticleReportVO", ArticleReportVO.class)
				.getResultList();
		return dataList;
	}

	@Override
	public ArticleReportVO getByArticleReportId(Integer articleReportId) {
		return getSession().get(ArticleReportVO.class, articleReportId);
	}

	@Override
	public List<ArticleReportVO> getByReportStatus(Byte reportStatus) {
		return getSession().createQuery("FROM ArticleReportVO WHERE reportStatus= :reportStatus", ArticleReportVO.class)
				.setParameter("reportStatus", reportStatus).list();
	}

}
