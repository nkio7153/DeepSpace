package com.depthspace.promotion.model.promotion.jdbctemplate;

import com.depthspace.promotion.model.promotion.PromotionVO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Repository("proDao")
public class ProDaoImpl implements ProDao{
    @Resource(name="jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    @Override
    public int insert(PromotionVO pro) {
        String sql="INSERT INTO PROMOTION(PROMOTION_ID, PROMO_NAME, START_DATE, END_DATE, DESCRIPTION, PICTURE) VALUES(?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, pro.getPromotionId(), pro.getPromoName(), pro.getStartDate(), pro.getEndDate(), pro.getDescription(), pro.getPicture());
        return update;
    }

    @Override
    public int update(PromotionVO pro) {
        String sql="UPDATE PROMOTION SET PROMO_NAME=?, START_DATE=?, END_DATE=?, DESCRIPTION=?, PICTURE=? WHERE PROMOTION_ID=?";
        int update = jdbcTemplate.update(sql,pro.getPromoName(), pro.getStartDate(), pro.getEndDate(), pro.getDescription(), pro.getPicture(),pro.getPromotionId());
        return update;
    }

    @Override
    public int delete(Integer promotionId) {
        String sql="DELETE FROM PROMOTION WHERE PROMOTION_ID=?";
        int update = jdbcTemplate.update(sql, promotionId);
        return update;
    }

    @Override
    public PromotionVO findByPrimaryKey(Integer promotionId) {
        String sql="SELECT PROMOTION_ID, PROMO_NAME, START_DATE, END_DATE, DESCRIPTION, PICTURE FROM PROMOTION WHERE PROMOTION_ID=?";
        PromotionVO proVo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(PromotionVO.class), promotionId);
        return proVo;
    }

    @Override
    public List<PromotionVO> getAll() {
        String sql="SELECT PROMOTION_ID, PROMO_NAME, START_DATE, END_DATE, DESCRIPTION, PICTURE FROM PROMOTION ORDER BY PROMOTION_ID";
        List<PromotionVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PromotionVO.class));
        return list;
    }
}
