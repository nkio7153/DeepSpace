package com.depthspace.promotion.model.promotion;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "PromotionVO{" +
                "promotionId=" + promotionId +
                ", promoName='" + promoName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", picture=" + Arrays.toString(picture) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionVO that = (PromotionVO) o;
        return Objects.equals(promotionId, that.promotionId) && Objects.equals(promoName, that.promoName) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(description, that.description) && Arrays.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(promotionId, promoName, startDate, endDate, description);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }
}
