package com.depthspace.keywordqa.service;

import java.util.List;
import com.depthspace.keywordqa.model.KeywordQaVO;

public interface KeywordQaService {
	KeywordQaVO addKeywordQa(KeywordQaVO keywordQaVO);

	KeywordQaVO updateKeywordQa(KeywordQaVO keywordQaVO);

	void deleteKeywordQa(Integer serialId);

	KeywordQaVO getKeywordQaiBySerialId(Integer serialId);		

    long getTotal();   	

	List<KeywordQaVO> getAllKeywordQa();	//取得所有專欄VO

	List<KeywordQaVO> getAllKeywordQa2(int currentPage);	//取得所有專欄VO根據分頁

	List<KeywordQaVO> getAllSortById(String sort);	//根據id排序
	
	int getPageTotal();
}
