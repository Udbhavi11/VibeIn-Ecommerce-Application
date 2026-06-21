package com.vibein.model;

import java.sql.Timestamp;

public class Category {

	private int categoryId;
	private String categoryName;
	private Timestamp createdAt;

	public Category() {
		super();
	}

	public Category(int categoryId, String categoryName, Timestamp createdAt) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.createdAt = createdAt;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", createdAt=" + createdAt
				+ "]";
	}
}