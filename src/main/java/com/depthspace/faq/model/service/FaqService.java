package com.depthspace.faq.model.service;

import java.util.List;

import com.depthspace.faq.model.model.FaqDAO;
import com.depthspace.faq.model.model.FaqDAOImpl;
import com.depthspace.faq.model.model.FaqVO;

public class FaqService {
	private FaqDAO dao;

	public FaqService() {
		dao = new FaqDAOImpl();
	}

	public FaqVO addFaq(Integer serialId, Integer faqNo, String faqName, String faqAns) {

		FaqVO faqVO = new FaqVO();

		faqVO.setSerialId(serialId);
		faqVO.setFaqNo(faqNo);
		faqVO.setFaqName(faqName);
		faqVO.setFaqAns(faqAns);
		dao.insert(faqVO);

		return faqVO;
	}

	public FaqVO updateFaq(Integer serialId, Integer faqNo, String faqName, String faqAns) {

		FaqVO faqVO = new FaqVO();

		faqVO.setSerialId(serialId);
		faqVO.setFaqNo(faqNo);
		faqVO.setFaqName(faqName);
		faqVO.setFaqAns(faqAns);
		dao.update(faqVO);

		return faqVO;
	}

	public void deleteFaq(Integer serialId) {
		dao.delete(serialId);
	}

	public FaqVO getOneFaq(Integer serialId) {
		return dao.findByPrimaryKey(serialId);
	}

	public List<FaqVO> getAll() {
		return dao.getAllFaqs();
	}
}