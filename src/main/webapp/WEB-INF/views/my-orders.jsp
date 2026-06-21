<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.vibein.model.Order"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Orders</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/my-orders.css">

</head>
<body>

<jsp:include page="partials/navbar.jsp"/>

<%
List<Order> orders =
	(List<Order>)request.getAttribute("orders");
%>

<section class="orders-section">

	<div class="container">

		<h2>My Orders</h2>

		<%
		if(orders == null || orders.isEmpty()){
		%>

		<div class="empty-orders">

			<h3>No Orders Found</h3>

			<a href="<%=request.getContextPath()%>/products"
				class="shop-btn">

				Start Shopping

			</a>

		</div>

		<%
		}
		else{
		%>

		<table class="orders-table">

			<tr>
				<th>Order ID</th>
				<th>Total Amount</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
			</tr>

			<%
			for(Order order : orders){
			%>

			<tr>

				<td>
					#<%=order.getOrderId()%>
				</td>

				<td>
					₹<%=order.getTotalAmount()%>
				</td>

				<td>
					<%=order.getPaymentMethod()%>
				</td>

				<td>
					<%=order.getOrderStatus()%>
				</td>

				<td>
					<%=order.getOrderDate()%>
				</td>

			</tr>

			<%
			}
			%>

		</table>

		<%
		}
		%>

	</div>

</section>

<jsp:include page="partials/footer.jsp"/>

</body>
</html>