<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.vibein.model.Cart"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/checkout.css">

</head>
<body>

	<jsp:include page="partials/navbar.jsp"/>

	<%
	Cart cart =
		(Cart) request.getAttribute("cart");
	%>

	<section class="checkout-section">

		<div class="container">

			<h2>Checkout</h2>

			<div class="checkout-card">

				<h3>Delivery Details</h3>

				<form action="<%=request.getContextPath()%>/place-order"
					method="post">

					<div class="form-group">

						<label>Full Name</label>

						<input type="text"
							name="fullName"
							required>

					</div>

					<div class="form-group">

						<label>Phone Number</label>

						<input type="text"
							name="phone"
							required>

					</div>

					<div class="form-group">

						<label>Delivery Address</label>

						<textarea name="address"
							rows="4"
							required></textarea>

					</div>

					<button type="submit"
						class="place-order-btn">

						Place Order

					</button>

				</form>

			</div>

		</div>

	</section>

	<jsp:include page="partials/footer.jsp"/>

</body>
</html>