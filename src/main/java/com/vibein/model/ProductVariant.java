package com.vibein.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProductVariant {

	private int variantId;
	private int productId;
	private String size;
	private String color;
	private BigDecimal price;
	private int stockQuantity;
	private String imageUrl;
	private Timestamp createdAt;

	public ProductVariant() {
		super();
	}

	public ProductVariant(int variantId, int productId, String size, String color, BigDecimal price,
			int stockQuantity, String imageUrl, Timestamp createdAt) {
		super();
		this.variantId = variantId;
		this.productId = productId;
		this.size = size;
		this.color = color;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.imageUrl = imageUrl;
		this.createdAt = createdAt;
	}

	public int getVariantId() {
		return variantId;
	}

	public void setVariantId(int variantId) {
		this.variantId = variantId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "ProductVariant [variantId=" + variantId + ", productId=" + productId + ", size=" + size
				+ ", color=" + color + ", price=" + price + ", stockQuantity=" + stockQuantity
				+ ", imageUrl=" + imageUrl + ", createdAt=" + createdAt + "]";
	}
}