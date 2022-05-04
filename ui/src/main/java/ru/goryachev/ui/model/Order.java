package ru.goryachev.ui.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdDate;

    //@JsonManagedReference
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
}

