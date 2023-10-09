package com.depthspace.ticketorders.model.ticketorders;

import java.io.Serializable;
import java.sql.Timestamp;

public class TicketOrdersVO implements Serializable {
    private Integer orderId;
    private Integer memId;
    private Timestamp orderDate;
    private Integer totalAmount;
    private Integer pointsFeedback;
    private Integer amountPaid;
    private Byte status;
    private Byte paymentMethod;

    public TicketOrdersVO() {
    }

    public TicketOrdersVO(Integer orderId, Integer memId, Timestamp orderDate, Integer totalAmount, Integer pointsFeedback, Integer amountPaid, Byte status, Byte paymentMethod) {
        this.orderId = orderId;
        this.memId = memId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.pointsFeedback = pointsFeedback;
        this.amountPaid = amountPaid;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {  // Date 改為 Timestamp
        this.orderDate = orderDate;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getPointsFeedback() {
        return pointsFeedback;
    }

    public void setPointsFeedback(Integer pointsFeedback) {
        this.pointsFeedback = pointsFeedback;
    }

    public Integer getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Integer amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Byte paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}