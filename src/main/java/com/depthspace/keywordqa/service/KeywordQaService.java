package com.depthspace.keywordqa.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.depthspace.faqtypes.model.model.FaqTypesDAOImpl;
import com.depthspace.faqtypes.model.model.FaqTypesVO;
import com.depthspace.keywordqa.model.KeywordQaDAO;
import com.depthspace.keywordqa.model.KeywordQaDAOImpl;
import com.depthspace.keywordqa.model.KeywordQaVO;
import com.depthspace.utils.HibernateUtil;

public class KeywordQaService {
	 private KeywordQaDAO dao;
	   
	 public KeywordQaService() {
			dao = new KeywordQaDAOImpl();
		}

		public KeywordQaVO addKeywordQa(String kwTypes, String kwAns) {

			KeywordQaVO keywordQaVO = new KeywordQaVO();

			keywordQaVO.setKwTypes(kwTypes);
			keywordQaVO.setKwAns(kwAns);
			dao.insert(keywordQaVO);

			return keywordQaVO;
		}

		public KeywordQaVO updateKeywordQa(Integer serialId, String kwTypes, String kwAns) {

			KeywordQaVO keywordQaVO = new KeywordQaVO();

			keywordQaVO.setSerialId(serialId);
			keywordQaVO.setKwTypes(kwTypes);
			keywordQaVO.setKwAns(kwAns);
			dao.update(keywordQaVO);

			return keywordQaVO;
		}

		public void deleteKeywordQa(Integer serialId) {
			dao.delete(serialId);
		}

		public KeywordQaVO getOneKeywordQa(Integer serialId) {
			return dao.findByPrimaryKey(serialId);
		}

		public List<KeywordQaVO> getAll() {
			return dao.getAll();
		}
	 
}
