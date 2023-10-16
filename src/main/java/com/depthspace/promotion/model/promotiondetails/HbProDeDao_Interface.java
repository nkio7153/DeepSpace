package com.depthspace.promotion.model.promotiondetails;

import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;

import java.util.List;
import java.util.Map;

public interface HbProDeDao_Interface {

    int insert(PromotionDetailsVO entity);

    int update(PromotionDetailsVO entity);

    int delete(PromotionDetailsVO.CompositeDetail id);

    PromotionDetailsVO getById(PromotionDetailsVO.CompositeDetail id);
    List<PromotionDetailsVO> getByProId(Integer id);

    List<PromotionDetailsVO> getAll();

    List<PromotionDetailsVO> getAll(int currentPage);

    long getTotal();
}
