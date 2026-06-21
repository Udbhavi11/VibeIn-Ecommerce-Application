package com.vibein.dao;

import java.util.List;

import com.vibein.model.ProductVariant;

public interface ProductVariantDAO {

	boolean addVariant(ProductVariant variant);

	ProductVariant getVariantById(int variantId);

	List<ProductVariant> getVariantsByProductId(int productId);

	boolean updateVariant(ProductVariant variant);

	boolean deleteVariant(int variantId);
}