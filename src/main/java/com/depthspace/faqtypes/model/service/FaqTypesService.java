package com.depthspace.faqtypes.model.service;

import java.util.List;

import com.depthspace.faqtypes.model.model.FaqTypesDAO;
import com.depthspace.faqtypes.model.model.FaqTypesDAOImpl;
import com.depthspace.faqtypes.model.model.FaqTypesVO;

public class FaqTypesService {
	private FaqTypesDAO dao;

	public FaqTypesService() {
		dao = new FaqTypesDAOImpl();
	}

	public FaqTypesVO addFaqTypes(String qTypes) {

		FaqTypesVO faqTypesVO = new FaqTypesVO();

		faqTypesVO.setQTypes(qTypes);
		dao.insert(faqTypesVO);

		return faqTypesVO;
	}

	public FaqTypesVO updateFaqTypes(Integer faqNo, String qTypes) {

		FaqTypesVO faqTypesVO = new FaqTypesVO();

		faqTypesVO.setFaqNo(faqNo);
		faqTypesVO.setQTypes(qTypes);
		dao.update(faqTypesVO);

		return faqTypesVO;
	}

	public void deleteFaqTypes(Integer faqNo) {
		dao.delete(faqNo);
	}

	public FaqTypesVO getOneFaqTypes(Integer faqNo) {
		return dao.findByPrimaryKey(faqNo);
	}

	public List<FaqTypesVO> getAll() {
		return dao.getAll();
	}
}
