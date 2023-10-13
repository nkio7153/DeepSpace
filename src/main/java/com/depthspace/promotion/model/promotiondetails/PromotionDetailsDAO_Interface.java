package com.depthspace.promotion.model.promotiondetails;

import java.util.List;

public interface PromotionDetailsDAO_Interface {
    public void insert(PromotionDetailsVO pdo);
    public void update(PromotionDetailsVO pdo);
    public void delete(Integer promotionId, Integer ticketId);
    public PromotionDetailsVO findByPrimaryKey(Integer promotionId, Integer ticketId);
    public List<PromotionDetailsVO> getAll();
}
