package ru.goryachev.ui.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Модель сущности "Заказ"
 * @author Lev Goryachev
 * @version 1.0
 */

public class Order {

    private Long id;

    private String customerName;

    private String address;

    private BigDecimal summ;

    private LocalDateTime createdDate;

    private Set<OrderDetail> orderDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) &&
                Objects.equals(getCustomerName(), order.getCustomerName()) &&
                Objects.equals(getAddress(), order.getAddress()) &&
                Objects.equals(getSumm(), order.getSumm()) &&
                Objects.equals(getCreatedDate(), order.getCreatedDate()) &&
                Objects.equals(getOrderDetails(), order.getOrderDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomerName(), getAddress(), getSumm(), getCreatedDate(), getOrderDetails());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", summ=" + summ +
                ", createdDate=" + createdDate +
                ", orderDetails=" + orderDetails +
                '}';
    }
}

