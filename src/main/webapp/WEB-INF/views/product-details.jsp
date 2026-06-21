<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.vibein.model.Product"%>
<%@ page import="com.vibein.model.ProductVariant"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Details</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/product-details.css">

</head>
<body>

	<jsp:include page="partials/navbar.jsp" />

	<%
	Product product =
		(Product) request.getAttribute("product");

	List<ProductVariant> variants =
		(List<ProductVariant>) request.getAttribute("variants");
	%>

	<section class="product-details-section">

		<div class="container">

			<div class="product-container">

				<div class="product-image">

					<img src="<%=product.getImageUrl()%>"
						alt="<%=product.getProductName()%>">

				</div>

				<div class="product-info">

					<h1><%=product.getProductName()%></h1>

					<p class="brand">
						Brand: <%=product.getBrand()%>
					</p>

					<p class="description">
						<%=product.getDescription()%>
					</p>

					<form action="<%=request.getContextPath()%>/add-to-cart"
						method="post">

						<h3>Select Variant</h3>

						<div class="variant-list">

							<%
							for(ProductVariant variant : variants){
							%>

							<label class="variant-option">

								<input type="radio"
									name="variantId"
									value="<%=variant.getVariantId()%>"
									required>

								<span>
									Size:
									<strong><%=variant.getSize()%></strong>

									|

									Color:
									<strong><%=variant.getColor()%></strong>

									|

									Price:
									<strong>₹<%=variant.getPrice()%></strong>

									|

									Stock:
									<strong><%=variant.getStockQuantity()%></strong>

								</span>

							</label>

							<%
							}
							%>

						</div>

						<div class="quantity-box">

							<label>Quantity:</label>

							<input type="number"
								name="quantity"
								value="1"
								min="1"
								required>

						</div>

						<button type="submit"
							class="add-cart-btn">

							Add To Cart

						</button>

					</form>

				</div>

			</div>

		</div>

	</section>

	<jsp:include page="partials/footer.jsp" />

</body>
</html>