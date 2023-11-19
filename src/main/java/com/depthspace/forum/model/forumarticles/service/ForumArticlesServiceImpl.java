package com.depthspace.forum.model.forumarticles.service;

import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import com.depthspace.forum.model.articlescollect.ArticlesCollectVO;
import com.depthspace.forum.model.articlescollect.dao.ArticlesCollectDAO;
import com.depthspace.forum.model.articlescollect.dao.ArticlesCollectDAOImpl;
import com.depthspace.forum.model.forumarticles.ForumArticlesVO;
import com.depthspace.forum.model.forumarticles.dao.ForumArticlesDAO;
import com.depthspace.forum.model.forumarticles.dao.ForumArticlesDAOImpl;
import com.depthspace.utils.HibernateUtil;

public class ForumArticlesServiceImpl implements ForumArticlesService {

	private ForumArticlesDAO dao;
	private ArticlesCollectDAO acdao;

	public ForumArticlesServiceImpl() {
		dao = new ForumArticlesDAOImpl(HibernateUtil.getSessionFactory());
		acdao = new ArticlesCollectDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public int insert(ForumArticlesVO tod) {
		return dao.insert(tod);
	}

	@Override
	public int update(ForumArticlesVO tod) {
		return dao.update(tod);
	}

	@Override
	public int delete(Integer articleId) {
		return dao.delete(articleId);
	}

	@Override
	public List<ForumArticlesVO> getAll() {
		List<ForumArticlesVO> list = dao.getAll();
		for (ForumArticlesVO vo : list) {
			if (vo.getArtiImg() != null) {
				// 獲取 Base64 編碼器
				Base64.Encoder encoder = Base64.getEncoder();
				// 將二進制數據編碼為字符串
				String base64EncodedString = encoder.encodeToString(vo.getArtiImg());
				// 輸出編碼後的字符串
				vo.setBase64Str(base64EncodedString);
			}
		}
		return list;
	}

	// 查出這個會員有那些文章
	@Override
	public List<ForumArticlesVO> getByMemId(Integer memId) {
		List<ForumArticlesVO> list = dao.getByMemId(memId);
		for (ForumArticlesVO vo : list) {
			if (vo.getArtiImg() != null) {
				// 獲取 Base64 編碼器
				Base64.Encoder encoder = Base64.getEncoder();
				// 將二進制數據編碼為字符串
				String base64EncodedString = encoder.encodeToString(vo.getArtiImg());
				// 輸出編碼後的字符串
				vo.setBase64Str(base64EncodedString);
			}
		}
		return list;
	}

	@Override
	public ForumArticlesVO getByArticleId(Integer articleId) {
		return dao.getByArticleId(articleId);
	}

	@Override
	public List<ForumArticlesVO> getByArtiTypeId(Integer artiTypeId) {
		List<ForumArticlesVO> list = dao.getByArtiTypeId(artiTypeId);
		for (ForumArticlesVO vo : list) {
			if (vo.getArtiImg() != null) {
				// 獲取 Base64 編碼器
				Base64.Encoder encoder = Base64.getEncoder();
				// 將二進制數據編碼為字符串
				String base64EncodedString = encoder.encodeToString(vo.getArtiImg());
				// 輸出編碼後的字符串
				vo.setBase64Str(base64EncodedString);
			}
		}
		return list;
	}

	// 查出這個會員有收藏那些文章
	@Override
	public List<ForumArticlesVO> getByArticleIds(Integer memId) {
		// 根據會員ID取得該會員收藏的文章列表
		List<ArticlesCollectVO> aclist = acdao.getByMemId(memId);

		// 創建一個文章列表集合,用於存儲收藏文章的ID
		List<Integer> articleIds = new ArrayList<>();

		// 遍歷aclist中的每個ArticlesCollectVO對象。
		for (ArticlesCollectVO acvo : aclist) {

			// 將每個ArticlesCollectVO對象的articleId添加到articleIds列表中，以便稍後使用。
			articleIds.add(acvo.getArticleId());
		}

		// 一旦獲得了會員收藏的文章ID列表，這一行調用了dao的getByArticleIds方法，並傳遞了articleIds，以獲取相應的論壇文章的信息，將結果存儲在list中。
		List<ForumArticlesVO> list = dao.getByArticleIds(articleIds);
		for (ForumArticlesVO vo : list) {
			if (vo.getArtiImg() != null) {
				// 獲取 Base64 編碼器
				Base64.Encoder encoder = Base64.getEncoder();
				// 將二進制數據編碼為字符串
				String base64EncodedString = encoder.encodeToString(vo.getArtiImg());
				// 輸出編碼後的字符串
				vo.setBase64Str(base64EncodedString);
			}
		}
		return list;
	}
	// 對文章進行點讚
	@Override
    public void likeArticle(Integer articleId) {
        ForumArticlesVO article = dao.getByArticleId(articleId);
        if (article != null) {
            article.incrementLikes();
            dao.update(article);
        }
    }
	// 對文章取消點讚
	@Override
    public void unlikeArticle(Integer articleId) {
        ForumArticlesVO article = dao.getByArticleId(articleId);
        if (article != null) {
            article.decrementLikes();
            dao.update(article);
        }
    }
}
