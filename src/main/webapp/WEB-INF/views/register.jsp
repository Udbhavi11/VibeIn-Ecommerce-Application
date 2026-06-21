<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/register.css">

</head>
<body>

	<jsp:include page="partials/navbar.jsp" />

	<section class="register-section">

		<div class="register-container">

			<h2>Create Account</h2>

			<%
			String error = (String) request.getAttribute("error");

			if (error != null) {
			%>

			<p class="error-message">
				<%=error%>
			</p>

			<%
			}
			%>

			<form action="<%=request.getContextPath()%>/register"
				method="post">

				<div class="form-group">

					<label>Full Name</label>

					<input type="text"
						name="fullName"
						required>

				</div>

				<div class="form-group">

					<label>Email</label>

					<input type="email"
						name="email"
						required>

				</div>

				<div class="form-group">

					<label>Phone</label>

					<input type="text"
						name="phone"
						required>

				</div>

				<div class="form-group">

					<label>Password</label>

					<input type="password"
						name="password"
						required>

				</div>

				<div class="form-group">

					<label>Address</label>

					<textarea name="address"
						rows="4"
						required></textarea>

				</div>

				<button type="submit"
					class="register-btn">

					Register

				</button>

			</form>

		</div>

	</section>

	<jsp:include page="partials/footer.jsp" />

</body>
</html>