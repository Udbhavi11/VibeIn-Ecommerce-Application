<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.vibein.model.Category"%>
<%@ page import="com.vibein.model.Product"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VibeIn - Home</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/home.css">

</head>
<body>

	<jsp:include page="partials/navbar.jsp"/>

	<!-- Hero Section -->

	<section class="hero">

		<div class="container">

			<h1>Discover Your Style</h1>

			<p>

				Explore trending fashion, footwear and accessories.

			</p>

			<a href="<%=request.getContextPath()%>/products"
				class="hero-btn">

				Shop Now

			</a>

		</div>

	</section>

	<!-- Categories Section -->

	<section class="categories">

		<div class="container">

			<h2>Shop By Category</h2>

			<div class="category-grid">

				<%
				List<Category> categories =
						(List<Category>) request.getAttribute(
								"categories");

				if(categories != null){

					for(Category category : categories){
				%>

				<a href="<%=request.getContextPath()%>/products?categoryId=<%=category.getCategoryId()%>"
					class="category-card">

					<h3>

						<%=category.getCategoryName()%>

					</h3>

				</a>

				<%
					}
				}
				%>

			</div>

		</div>

	</section>

	<!-- Featured Products -->

	<section class="featured-products">

		<div class="container">

			<h2>Featured Products</h2>

			<div class="product-grid">

				<%
				List<Product> products =
						(List<Product>) request.getAttribute(
								"products");

				if(products != null){

					for(Product product : products){
				%>

				<div class="product-card">

					<img
						src="<%=request.getContextPath()%>/<%=product.getImageUrl()%>"
						alt="<%=product.getProductName()%>">

					<h3>

						<%=product.getProductName()%>

					</h3>

					<p>

						<%=product.getBrand()%>

					</p>

				</div>

				<%
					}
				}
				%>

			</div>

		</div>

	</section>

	<jsp:include page="partials/footer.jsp"/>

</body>
</html>