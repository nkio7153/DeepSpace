package com.depthspace.faq.model.keywordqa;

import java.util.List;

public interface KeywordDAO {
    void insert(KeywordQaVO keywordqaVO);
    void update(KeywordQaVO keywordqaVO);
    void delete(Integer serialId);

    KeywordQaVO findByPrimaryKey(Integer serialId);
    List<KeywordQaVO> getAll();
}
