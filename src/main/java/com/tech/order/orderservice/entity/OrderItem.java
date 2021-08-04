package com.tech.order.orderservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "order_item")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProductCode")
	private int productCode;
	@Column(name = "ProductName")
	@NotEmpty(message = "ProductName is mandatory")
	private String productName;
	@Column(name = "Quantity")
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order userOrder;

	public OrderItem() {

	}

	public OrderItem(int productCode, @NotEmpty(message = "ProductName is mandatory") String productName, int quantity,
			Order userOrder) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.quantity = quantity;
		this.userOrder = userOrder;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
