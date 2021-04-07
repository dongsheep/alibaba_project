package com.dong.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 界面与服务端传输的dto对象
 *
 * @author xiedongxiao
 */

public class OrderDto implements Serializable {

    private String orderCode;

    private BigDecimal totalAmount;

    private Integer buyerId;

    private String buyer;

    private Date createTime;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderCode='" + orderCode + '\'' +
                ", totalAmount=" + totalAmount +
                ", buyerId=" + buyerId +
                ", buyer='" + buyer + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
