<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.vibein.model.Product"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Products</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/products.css">

</head>
<body>

	<jsp:include page="partials/navbar.jsp"/>

	<section class="products-section">

		<div class="container">

			<h1>All Products</h1>

			<div class="product-grid">

				<%
				List<Product> products =
					(List<Product>) request.getAttribute("products");

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

					<a
						href="<%=request.getContextPath()%>/product-details?id=<%=product.getProductId()%>"
						class="view-btn">

						View Details

					</a>

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