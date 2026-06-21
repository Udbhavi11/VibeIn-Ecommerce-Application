package com.vibein.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vibein.dao.ProductVariantDAO;
import com.vibein.model.ProductVariant;
import com.vibein.util.DBConnection;

public class ProductVariantDAOImpl implements ProductVariantDAO {

	private static final String INSERT_VARIANT =
			"INSERT INTO product_variants(product_id,size,color,price,stock_quantity,image_url) VALUES(?,?,?,?,?,?)";

	private static final String GET_VARIANT_BY_ID =
			"SELECT * FROM product_variants WHERE variant_id=?";

	private static final String GET_VARIANTS_BY_PRODUCT_ID =
			"SELECT * FROM product_variants WHERE product_id=?";

	private static final String UPDATE_VARIANT =
			"UPDATE product_variants SET product_id=?, size=?, color=?, price=?, stock_quantity=?, image_url=? WHERE variant_id=?";

	private static final String DELETE_VARIANT =
			"DELETE FROM product_variants WHERE variant_id=?";

	@Override
	public boolean addVariant(ProductVariant variant) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(INSERT_VARIANT)) {

			pstmt.setInt(1, variant.getProductId());
			pstmt.setString(2, variant.getSize());
			pstmt.setString(3, variant.getColor());
			pstmt.setBigDecimal(4, variant.getPrice());
			pstmt.setInt(5, variant.getStockQuantity());
			pstmt.setString(6, variant.getImageUrl());

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public ProductVariant getVariantById(int variantId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_VARIANT_BY_ID)) {

			pstmt.setInt(1, variantId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				ProductVariant variant = new ProductVariant();

				variant.setVariantId(rs.getInt("variant_id"));
				variant.setProductId(rs.getInt("product_id"));
				variant.setSize(rs.getString("size"));
				variant.setColor(rs.getString("color"));
				variant.setPrice(rs.getBigDecimal("price"));
				variant.setStockQuantity(rs.getInt("stock_quantity"));
				variant.setImageUrl(rs.getString("image_url"));
				variant.setCreatedAt(rs.getTimestamp("created_at"));

				return variant;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<ProductVariant> getVariantsByProductId(int productId) {

		List<ProductVariant> variants = new ArrayList<>();

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_VARIANTS_BY_PRODUCT_ID)) {

			pstmt.setInt(1, productId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				ProductVariant variant = new ProductVariant();

				variant.setVariantId(rs.getInt("variant_id"));
				variant.setProductId(rs.getInt("product_id"));
				variant.setSize(rs.getString("size"));
				variant.setColor(rs.getString("color"));
				variant.setPrice(rs.getBigDecimal("price"));
				variant.setStockQuantity(rs.getInt("stock_quantity"));
				variant.setImageUrl(rs.getString("image_url"));
				variant.setCreatedAt(rs.getTimestamp("created_at"));

				variants.add(variant);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return variants;
	}

	@Override
	public boolean updateVariant(ProductVariant variant) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(UPDATE_VARIANT)) {

			pstmt.setInt(1, variant.getProductId());
			pstmt.setString(2, variant.getSize());
			pstmt.setString(3, variant.getColor());
			pstmt.setBigDecimal(4, variant.getPrice());
			pstmt.setInt(5, variant.getStockQuantity());
			pstmt.setString(6, variant.getImageUrl());
			pstmt.setInt(7, variant.getVariantId());

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteVariant(int variantId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(DELETE_VARIANT)) {

			pstmt.setInt(1, variantId);

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}