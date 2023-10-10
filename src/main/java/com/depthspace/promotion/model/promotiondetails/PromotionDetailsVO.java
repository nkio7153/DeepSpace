package com.depthspace.promotion.model.promotiondetails;

import java.io.Serializable;
import java.sql.Timestamp;

public class PromotionDetailsVO implements Serializable {
    private Integer promotionId;
    private Integer ticketId;
    private Double discount;

    public PromotionDetailsVO() {
    }

    public PromotionDetailsVO(Integer promotionId, Integer ticketId, Double discount) {
        this.promotionId = promotionId;
        this.ticketId = ticketId;
        this.discount = discount;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
