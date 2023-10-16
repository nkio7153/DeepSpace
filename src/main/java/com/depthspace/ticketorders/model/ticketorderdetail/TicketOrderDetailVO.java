package com.depthspace.ticketorders.model.ticketorderdetail;

import com.depthspace.promotion.model.promotiondetails.PromotionDetailsVO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="TICKET_ORDER_DETAIL")
@IdClass(TicketOrderDetailVO.CompositeDetail.class)
public class TicketOrderDetailVO implements Serializable {
    @Id
    @Column(name = "ORDER_ID", nullable = false ,insertable = false, updatable = false)
    private Integer orderId;
    @Id
    @Column(name="TICKET_ID", nullable = false ,insertable = false, updatable = false)
    private Integer ticketId;
    @Column(name="QUANTITY")
    private Integer quantity;
    @Column(name = "UNIT_PRICE")
    private Integer unitPrice;

    @Column(name = "DISCOUNT_PRICE")
    private Integer discountPrice;

    @Column(name = "SUBTOTAL")
    private Integer subtotal;

    @Column(name = "TICKET_REVIEWS", length = 100)
    private String ticketReviews;

    @Column(name = "STARS", columnDefinition = "TINYINT")
    private Byte stars;

    public TicketOrderDetailVO() {
    }

    public TicketOrderDetailVO(Integer orderId, Integer ticketId, Integer quantity, Integer unitPrice, Integer discountPrice, Integer subtotal, String ticketReviews, Byte stars) {
        this.orderId = orderId;
        this.ticketId = ticketId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discountPrice = discountPrice;
        this.subtotal = subtotal;
        this.ticketReviews = ticketReviews;
        this.stars = stars;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public String getTicketReviews() {
        return ticketReviews;
    }

    public void setTicketReviews(String ticketReviews) {
        this.ticketReviews = ticketReviews;
    }

    public Byte getStars() {
        return stars;
    }

    public void setStars(Byte stars) {
        this.stars = stars;
    }

    public static class CompositeDetail implements Serializable {
        private static final long serialVersionUID=1L;
        private Integer orderId;
        private Integer ticketId;

        public CompositeDetail() {
        }

        public CompositeDetail(Integer orderId, Integer ticketId) {
            this.orderId = orderId;
            this.ticketId = ticketId;
        }

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
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
            TicketOrderDetailVO.CompositeDetail that = (TicketOrderDetailVO.CompositeDetail) o;
            return Objects.equals(orderId, that.orderId) && Objects.equals(ticketId, that.ticketId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(orderId, ticketId);
        }
    }
}
