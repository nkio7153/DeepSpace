package com.depthspace.promotion.model.promotiondetails.hibernate;





import com.depthspace.promotion.model.promotion.PromotionTicketView;
import com.depthspace.promotion.model.promotion.PromotionVO;
import com.depthspace.promotion.model.promotiondetails.PromotionDetailsVO;

import java.util.List;

public interface HbProDeDao {

    int insert(PromotionDetailsVO entity);

    void insertBatch(List<PromotionDetailsVO> entities);

    int update(PromotionDetailsVO entity);

    int delete(PromotionDetailsVO.CompositeDetail id);

    PromotionDetailsVO getById(PromotionDetailsVO.CompositeDetail id);
    List<PromotionTicketView> getByProId(Integer proId);

    List<PromotionDetailsVO> getAll();

    List<PromotionDetailsVO> getAll(int currentPage);

    long getTotal();

    int deleteByProId(Integer proId);
    List<Integer> getOnSale(List<Integer> ticketIds);


}
