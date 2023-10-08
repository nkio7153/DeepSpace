package com.depthspace.faq.faqtypes.model;

import java.util.List;

public interface FaqTypesDAO {
    void insert(FaqTypesVO faqtypesVO);
    void update(FaqTypesVO faqtypesVO);
    void delete(Integer faqNo);

    FaqTypesVO findByPrimaryKey(Integer faqNo);
    List<FaqTypesVO> getAll();
}
