package com.depthspace.promotion.model.promotion;

import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;

import java.util.List;

public interface PromotionDAO_Interface {
    public void insert(PromotionVO pro);
    public void update(PromotionVO pro);
    public void delete(Integer promotionId);
    public PromotionVO findByPrimaryKey(Integer promotionId);
    public List<PromotionVO> getAll();
}
