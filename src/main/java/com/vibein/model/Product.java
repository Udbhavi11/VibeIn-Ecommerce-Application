package com.vibein.model;

import java.sql.Timestamp;

public class Product {

	private int productId;
	private int categoryId;
	private String productName;
	private String description;
	private String brand;
	private String imageUrl;
	private Timestamp createdAt;

	public Product() {
		super();
	}

	public Product(int productId, int categoryId, String productName, String description, String brand,
			String imageUrl, Timestamp createdAt) {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
		this.productName = productName;
		this.description = description;
		this.brand = brand;
		this.imageUrl = imageUrl;
		this.createdAt = createdAt;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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
		return "Product [productId=" + productId + ", categoryId=" + categoryId + ", productName=" + productName
				+ ", description=" + description + ", brand=" + brand + ", imageUrl=" + imageUrl + ", createdAt="
				+ createdAt + "]";
	}
}