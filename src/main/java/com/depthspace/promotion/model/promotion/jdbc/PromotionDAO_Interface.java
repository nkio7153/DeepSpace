package com.depthspace.promotion.model.promotion.jdbc;

import com.depthspace.promotion.model.promotion.PromotionVO;

import java.util.List;

public interface PromotionDAO_Interface {
    public void insert(PromotionVO pro);
    public void update(PromotionVO pro);
    public void delete(Integer promotionId);
    public PromotionVO findByPrimaryKey(Integer promotionId);
    public List<PromotionVO> getAll();
}
