package com.depthspace.promotion.model.promotion;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@Table(name="PROMOTION")
public class PromotionVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PROMOTION_ID")
    private Integer promotionId;
    @Column(name="PROMO_NAME")
    private String promoName;
    @Column(name="START_DATE")
    private Timestamp startDate;
    @Column(name="END_DATE")
    private Timestamp endDate;
    @Column(name="DESCRIPTION")
    private String description;
    @Lob
    @Column(name="PICTURE",columnDefinition="mediumblob")
    private byte[] picture;

    public PromotionVO() {
    }

    public PromotionVO(Integer promotionId, String promoName, Timestamp startDate, Timestamp endDate, String description, byte[] picture) {
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {  // 改為Timestamp
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {  // 改為Timestamp
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {  // 改為Timestamp
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
