package com.depthspace.promotion.model.promotion.jdbctemplate;

import com.depthspace.promotion.model.promotion.PromotionVO;

import java.util.List;

public interface ProDao {
    public int insert(PromotionVO pro);
    public int update(PromotionVO pro);
    public int delete(Integer promotionId);
    public PromotionVO findByPrimaryKey(Integer promotionId);
    public List<PromotionVO> getAll();
}
