package com.depthspace.keywordqa.model;

import java.util.List;

public interface KeywordQaDAO {
	void insert(KeywordQaVO keywordQaVO);
	
	void update(KeywordQaVO keywordQaVO);
	
	void delete(Integer serialId);

	long getTotal();
	
    List<KeywordQaVO> getAll(int currentPage);
    
    List<KeywordQaVO> getAll();
    
    KeywordQaVO getById(Integer serialId);
    
}
