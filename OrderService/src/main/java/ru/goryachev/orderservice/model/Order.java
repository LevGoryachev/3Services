package ru.goryachev.orderservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


/**
 * Модель сущности "Заказ"
 * @author Lev Goryachev
 * @version 1.0
 */

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "address")
    private String address;

    @Column(name = "summ")
    private BigDecimal summ;

    @Column(name = "created_date")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.MERGE, fetch = FetchType.LAZY/*, orphanRemoval = true*/)
    @JsonManagedReference
    private List<OrderDetail> orderDetails;

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

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
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
                Objects.equals(getCreatedDate(), order.getCreatedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomerName(), getAddress(), getSumm(), getCreatedDate());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", summ=" + summ +
                ", createdDate=" + createdDate +
                '}';
    }
}

