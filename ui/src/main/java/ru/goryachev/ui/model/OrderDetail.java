package ru.goryachev.ui.model;

import java.util.Objects;

/**
 * Модель сущности "Детали заказа"
 * @author Lev Goryachev
 * @version 1.0
 */

public class OrderDetail {

    private Long itemNumber;

    private String serialNumber;

    private String productName;

    private Long qty;

    private Long orderId;

    public Long getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Long itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetail)) return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(getItemNumber(), that.getItemNumber()) &&
                Objects.equals(getSerialNumber(), that.getSerialNumber()) &&
                Objects.equals(getProductName(), that.getProductName()) &&
                Objects.equals(getQty(), that.getQty()) &&
                Objects.equals(getOrderId(), that.getOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemNumber(), getSerialNumber(), getProductName(), getQty(), getOrderId());
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "itemNumber=" + itemNumber +
                ", serialNumber='" + serialNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", qty=" + qty +
                ", orderId=" + orderId +
                '}';
    }
}

