package ru.goryachev.ui.model;

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

    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long orderId;

    /*@ApiModelProperty(required = false, hidden = true)
    @JsonBackReference
    private Order order;*/

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
}

