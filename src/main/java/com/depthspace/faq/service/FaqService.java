package com.depthspace.faq.service;

import java.util.List;

import com.depthspace.faq.model.FaqDAO;
import com.depthspace.faq.model.FaqDAOImpl;
import com.depthspace.faq.model.FaqVO;

public class FaqService {
	private FaqDAO dao;

	public FaqService() {
		dao = new FaqDAOImpl();
	}
	
	public List<String> getAllQTypes() {
        return dao.getAllQTypes();
    }

	public FaqVO addFaq(Integer faqNo, String faqName, String faqAns) {

		FaqVO faqVO = new FaqVO();

//		faqVO.setSerialId(serialId);
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
	
	public List<FaqVO> getFaqsCompleteInfo() {
	    return dao.findAllWithFaqTypes();
	}
}