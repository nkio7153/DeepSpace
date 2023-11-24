package com.depthspace.forum.model.articleslike.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.forum.model.articlescollect.ArticlesCollectVO;
import com.depthspace.forum.model.articleslike.ArticlesLikeVO;
import com.depthspace.forum.model.articleslike.ArticlesLikeVO.CompositeDetail;
import com.depthspace.forum.model.forumarticles.ForumArticlesVO;

public class ArticlesLikeDaoImpl implements ArticlesLikeDao {
	private SessionFactory factory;

	public ArticlesLikeDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(ArticlesLikeVO entity) {
		getSession().save(entity);

	}

	@Override
	public void delete(CompositeDetail id) {
		ArticlesLikeVO alvo = getSession().get(ArticlesLikeVO.class, id);
		// 0:失敗 1:成功
		int state;
		if (alvo != null) {
			getSession().delete(alvo);

			state = 1;
		} else {

			state = 0;
		}

	}

	@Override
	public List<ArticlesLikeVO> getByMemId(Integer memId) {
		return getSession()
			.createQuery("from ArticlesLikeVO where memId= :memId", ArticlesLikeVO.class)
			.setParameter("memId", memId)
			.list();
	}

	@Override
	public boolean islike(Integer articleId, Integer memId) {
		List<ArticlesLikeVO> results = getSession()		
				.createQuery("from ArticlesLikeVO where articleId= :articleId AND memId= :memId", ArticlesLikeVO.class)
				.setParameter("articleId", articleId)
				.setParameter("memId", memId)
				.list();
		return !results.isEmpty();
	}

	@Override
	public void deleteByArticleId(Integer articleId) {
	    List<ArticlesLikeVO> results = getSession()
	            .createQuery("from ArticlesLikeVO where articleId= :articleId", ArticlesLikeVO.class)
	            .setParameter("articleId", articleId)
	            .list();
	    
	    // 遍歷結果並刪除對象
	    for (ArticlesLikeVO collect : results) {
	        getSession().delete(collect);
	    }
	}

}
