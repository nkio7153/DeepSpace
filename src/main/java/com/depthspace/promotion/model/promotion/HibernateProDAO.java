package com.depthspace.promotion.model.promotion;

import com.depthspace.promotion.model.promotion.PromotionVO;

import java.util.List;
import java.util.Map;

public interface HibernateProDAO {

    int insert(PromotionVO entity);

    int update(PromotionVO entity);

    int delete(Integer id);

    PromotionVO getById(Integer id);

    List<PromotionVO> getAll();

    List<PromotionVO> getByCompositeQuery(Map<String, String> map);

    List<PromotionVO> getAll(int currentPage);

    long getTotal();
}