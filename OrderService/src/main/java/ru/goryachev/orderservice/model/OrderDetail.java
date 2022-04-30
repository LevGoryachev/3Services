package ru.goryachev.orderservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ru.goryachev.orderservice.model.compositekey.OrderDetailsCompositeKey;

import javax.persistence.*;

/**
 * Модель сущности "Детали заказа"
 * @author Lev Goryachev
 * @version 1.0
 */

@Entity
@Table(name = "order_details")
@IdClass(OrderDetailsCompositeKey.class)
public class OrderDetail {
    @Id
    @Column(name = "item_number")
    private Long itemNumber;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "qty")
    private Long qty;

    @Id
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, insertable = false, updatable = false)
    @JsonBackReference
    private Order order;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

