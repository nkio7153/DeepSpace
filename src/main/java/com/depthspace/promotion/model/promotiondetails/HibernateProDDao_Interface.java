package com.depthspace.promotion.model.promotiondetails;

import com.depthspace.promotion.model.promotion.PromotionVO;

import java.util.List;
import java.util.Map;

public interface HibernateProDDao_Interface {

    int insert(PromotionDetailsVO entity);

    int update(PromotionDetailsVO entity);

    int delete(Integer id);

    PromotionDetailsVO getById(Integer id);

    List<PromotionDetailsVO> getAll();

    List<PromotionDetailsVO> getByCompositeQuery(Map<String, String> map);

    List<PromotionDetailsVO> getAll(int currentPage);

    long getTotal();
}
