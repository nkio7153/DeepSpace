package com.depthspace.promotion.service;

import com.depthspace.promotion.model.promotion.PromotionTicketView;
import com.depthspace.promotion.model.promotion.PromotionVO;
import com.depthspace.promotion.model.promotiondetails.PromotionDetailsVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface ProService {
    void addPromotion(PromotionVO entity, String[] ticketIds, BigDecimal discount);
    PromotionVO update(PromotionVO entity);
    PromotionVO delete(Integer promotionId);
    List<PromotionVO> getAll();
    //取得促銷明細列表
    List<PromotionTicketView> getAllByProId(Integer proId);
    //取得一筆促銷資料
    PromotionVO getById(Integer proId);

    //更新促銷活動  用原本的促銷編號刪除對應的所有促銷明細 用新的物件更新促銷，並遍歷生成對應的多筆促銷明細
    void updatePromotion(PromotionVO entity, String[] ticketIds, BigDecimal discount);
    List<Integer> getOnSale(List<Integer> ticketIds);

}
