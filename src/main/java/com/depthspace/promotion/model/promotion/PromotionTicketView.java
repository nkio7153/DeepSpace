package com.depthspace.promotion.model.promotion;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Immutable
@Table(name="PromotionTicketView")
@IdClass(PromotionTicketView.CompositeDetail.class)
public class PromotionTicketView {
    @Id
    @Column(name="PROMOTION_ID")
    private Integer promotionId;
    @Id
    @Column(name="TICKET_ID")
    private Integer ticketId;
    @Column(name="TICKET_NAME")
    private String ticketName;
    @Column(name="DISCOUNT")
    private BigDecimal discount;
    public PromotionTicketView(){};

    public PromotionTicketView(Integer promotionId, Integer ticketId, String ticketName, BigDecimal discount) {
        this.promotionId = promotionId;
        this.ticketId = ticketId;
        this.ticketName = ticketName;
        this.discount = discount;
    }
    public Integer getPromotionId(){
        return promotionId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setPromotionId(Integer promotionId){
        this.promotionId=promotionId;
    }

    @Override
    public String toString() {
        return "PromotionTicketView{" +
                "promotionId=" + promotionId +
                ", ticketId=" + ticketId +
                ", ticketName='" + ticketName + '\'' +
                ", discount=" + discount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionTicketView that = (PromotionTicketView) o;
        return Objects.equals(promotionId, that.promotionId) && Objects.equals(ticketId, that.ticketId) && Objects.equals(ticketName, that.ticketName) && Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotionId, ticketId, ticketName, discount);
    }

    public static class CompositeDetail implements Serializable {
        private Integer promotionId;
        private Integer ticketId;

        public CompositeDetail() {
        }

        public CompositeDetail(Integer promotionId, Integer ticketId) {
            this.promotionId = promotionId;
            this.ticketId = ticketId;
        }

        // 覆寫equals和hashCode方法
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeDetail that = (CompositeDetail) o;
            return Objects.equals(this.promotionId, that.promotionId) && Objects.equals(this.ticketId, that.ticketId);
        }


        @Override
        public int hashCode() {
            return Objects.hash(promotionId, ticketId);
        }
    }
}


