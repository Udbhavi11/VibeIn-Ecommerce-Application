<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="com.vibein.model.CartViewItem"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/cart.css">

</head>
<body>

	<jsp:include page="partials/navbar.jsp"/>

	<%
	List<CartViewItem> cartItems =
			(List<CartViewItem>) request.getAttribute("cartItems");

	BigDecimal grandTotal =
			(BigDecimal) request.getAttribute("grandTotal");
	%>

	<section class="cart-section">

		<div class="container">

			<h2>My Shopping Cart</h2>

			<%
			if(cartItems == null || cartItems.isEmpty()){
			%>

			<div class="empty-cart">

				<h3>Your cart is empty</h3>

				<a href="<%=request.getContextPath()%>/products"
					class="shop-btn">

					Continue Shopping

				</a>

			</div>

			<%
			}
			else{
			%>

			<div class="cart-container">

				<%
				for(CartViewItem item : cartItems){
				%>

				<div class="cart-item">

					<div class="cart-image">

						<img
							src="<%=request.getContextPath()%>/<%=item.getImageUrl()%>"
							alt="<%=item.getProductName()%>">

					</div>

					<div class="cart-details">

						<h3>
							<%=item.getProductName()%>
						</h3>

						<p>
							Brand:
							<%=item.getBrand()%>
						</p>

						<p>
							Size:
							<%=item.getSize()%>
						</p>

						<p>
							Color:
							<%=item.getColor()%>
						</p>

						<p>
							Price:
							₹<%=item.getPrice()%>
						</p>

						<p>
							Subtotal:
							₹<%=item.getSubtotal()%>
						</p>

						<form action="<%=request.getContextPath()%>/update-cart"
							method="post">

							<input type="hidden"
								name="cartItemId"
								value="<%=item.getCartItemId()%>">

							<label>
								Quantity:
							</label>

							<input type="number"
								name="quantity"
								value="<%=item.getQuantity()%>"
								min="1">

							<button type="submit"
								class="update-btn">

								Update

							</button>

						</form>

						<a href="<%=request.getContextPath()%>/remove-cart-item?cartItemId=<%=item.getCartItemId()%>"
							class="remove-btn">

							Remove

						</a>

					</div>

				</div>

				<%
				}
				%>

				<div class="cart-total">

					<h2>

						Total :
						₹<%=grandTotal%>

					</h2>

					<br>

					<a href="<%=request.getContextPath()%>/products"
						class="shop-btn">

						Continue Shopping

					</a>

					&nbsp;&nbsp;

					<a href="<%=request.getContextPath()%>/checkout"
						class="checkout-btn">

						Proceed To Checkout

					</a>

				</div>

			</div>

			<%
			}
			%>

		</div>

	</section>

	<jsp:include page="partials/footer.jsp"/>

</body>
</html>