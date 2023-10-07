package com.depthspace.promotion.model;

import java.io.Serializable;
import java.sql.Date;


public class PromotionVO implements Serializable {
    private Integer promotionId;
    private String promoName;
    private Date startDate;
    private Date endDate;
    private String description;
    private byte[] picture;

    public PromotionVO() {
    }

    public PromotionVO(Integer promotionId, String promoName, Date startDate, Date endDate, String description, byte[] picture) {
        this.promotionId = promotionId;
        this.promoName = promoName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.picture = picture;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
