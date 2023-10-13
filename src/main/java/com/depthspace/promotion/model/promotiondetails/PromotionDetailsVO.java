package com.depthspace.promotion.model.promotiondetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
@Entity
@Table(name="PROMOTION_DETAILS")
public class PromotionDetailsVO implements Serializable {
    @Id
    @Column(name="PROMOTION_ID")
    private Integer promotionId;
    @Id
    @Column(name="TICKET_ID")
    private Integer ticketId;
    @Column(name = "DISCOUNT", nullable = false, precision = 5, scale = 2)
    private BigDecimal discount;

    public PromotionDetailsVO() {
    }

    public PromotionDetailsVO(Integer promotionId, Integer ticketId, BigDecimal discount) {
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
