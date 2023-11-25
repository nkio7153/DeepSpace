package com.depthspace.keywordqa.model;

import java.util.List;

import com.depthspace.faqtypes.model.model.FaqTypesVO;

public interface KeywordQaDAO {
	void insert(KeywordQaVO keywordQaVO);
    void update(KeywordQaVO keywordQaVO);
    void delete(Integer serialId);

    KeywordQaVO findByPrimaryKey(Integer serialId);
    List<KeywordQaVO> getAll();
    
}
