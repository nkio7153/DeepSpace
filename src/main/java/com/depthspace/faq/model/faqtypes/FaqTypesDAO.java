package com.depthspace.faq.model.faqtypes;

import java.util.List;

public interface FaqTypesDAO {
    void insert(FaqTypesVO faqTypesVO);
    void update(FaqTypesVO faqTypesVO);
    void delete(Integer faqNo);

    FaqTypesVO findByPrimaryKey(Integer faqNo);
    List<FaqTypesVO> getAll();
}
