package ru.goryachev.orderservice.model.compositekey;

import java.io.Serializable;

/**
 * Класс композитного ключа для сущности OrderDetail
 * @author Lev Goryachev
 * @version 1.0
 */

public class OrderDetailsCompositeKey implements Serializable {

    private Long itemNumber;

    private Long orderId;

    public OrderDetailsCompositeKey() {
    }

    public OrderDetailsCompositeKey(Long itemNumber, Long orderId) {
        this.itemNumber = itemNumber;
        this.orderId = orderId;
    }

    public Long getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Long itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
