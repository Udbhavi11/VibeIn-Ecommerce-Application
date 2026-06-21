<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Placed</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/order-success.css">

</head>
<body>

	<jsp:include page="partials/navbar.jsp"/>

	<%
	Integer orderId =
			(Integer) request.getAttribute("orderId");
	%>

	<section class="success-section">

		<div class="success-card">

			<h1>🎉 Order Placed Successfully!</h1>

			<p>
				Thank you for shopping with VibeIn.
			</p>

			<p>
				Order ID :
				<strong>#<%=orderId%></strong>
			</p>

			<div class="btn-group">

				<a href="<%=request.getContextPath()%>/products"
					class="continue-btn">

					Continue Shopping

				</a>

				<a href="<%=request.getContextPath()%>/my-orders"
					class="orders-btn">

					My Orders

				</a>

			</div>

		</div>

	</section>

	<jsp:include page="partials/footer.jsp"/>

</body>
</html>