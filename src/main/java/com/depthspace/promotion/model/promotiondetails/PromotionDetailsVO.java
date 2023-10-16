package com.depthspace.promotion.model.promotiondetails;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="PROMOTION_DETAILS")
@IdClass(PromotionDetailsVO.CompositeDetail.class)
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
    public static class CompositeDetail implements Serializable {
        private static final long SerialVersionUID=1L;
        private Integer promotionId;
        private Integer ticketId;

        public CompositeDetail() {
        }

        public CompositeDetail(Integer promotionId, Integer ticketId) {
            this.promotionId = promotionId;
            this.ticketId = ticketId;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeDetail that = (CompositeDetail) o;
            return Objects.equals(promotionId, that.promotionId) && Objects.equals(ticketId, that.ticketId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(promotionId, ticketId);
        }
    }



}
