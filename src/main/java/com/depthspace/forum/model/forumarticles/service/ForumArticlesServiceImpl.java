package com.depthspace.forum.model.forumarticles.service;

import java.util.Base64;
import java.util.List;

import com.depthspace.forum.model.forumarticles.ForumArticlesVO;
import com.depthspace.forum.model.forumarticles.dao.ForumArticlesDAO;
import com.depthspace.forum.model.forumarticles.dao.ForumArticlesDAOImpl;
import com.depthspace.utils.HibernateUtil;

public class ForumArticlesServiceImpl implements ForumArticlesService {

	private ForumArticlesDAO dao;

	public ForumArticlesServiceImpl() {
		dao = new ForumArticlesDAOImpl(HibernateUtil.getSessionFactory());
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
				System.out.println("Base64 Encoded String : " + base64EncodedString);
				vo.setBase64Str(base64EncodedString);
			}
		}
		return list;
	}

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
				System.out.println("Base64 Encoded String : " + base64EncodedString);
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
		List<ForumArticlesVO> list = dao.getByMemId(artiTypeId);
		for (ForumArticlesVO vo : list) {
			if (vo.getArtiImg() != null) {
				// 獲取 Base64 編碼器
				Base64.Encoder encoder = Base64.getEncoder();
				// 將二進制數據編碼為字符串
				String base64EncodedString = encoder.encodeToString(vo.getArtiImg());
				// 輸出編碼後的字符串
				System.out.println("Base64 Encoded String : " + base64EncodedString);
				vo.setBase64Str(base64EncodedString);
			}
		}
		return list;
	}
}
