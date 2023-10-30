package com.depthspace.promotion.model.promotiondetails;





import com.depthspace.promotion.model.promotion.PromotionTicketView;

import java.util.List;
import java.util.Map;

public interface HbProDeDao_Interface {

    int insert(PromotionDetailsVO entity);

    void insertBatch(List<PromotionDetailsVO> entities);

    int update(PromotionDetailsVO entity);

    int delete(PromotionDetailsVO.CompositeDetail id);

    PromotionDetailsVO getById(PromotionDetailsVO.CompositeDetail id);
    List<PromotionTicketView> getByProId(Integer proId);

    List<PromotionDetailsVO> getAll();

    List<PromotionDetailsVO> getAll(int currentPage);

    long getTotal();

    public int deleteByProId(Integer proId);


}
