<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/login.css">

</head>
<body>

	<jsp:include page="partials/navbar.jsp" />

	<section class="login-section">

		<div class="login-container">

			<h2>Login</h2>

			<%
			String error =
					(String) request.getAttribute("error");

			if(error != null){
			%>

			<p class="error-message">

				<%=error%>

			</p>

			<%
			}
			%>

			<form action="<%=request.getContextPath()%>/login"
					method="post">

				<div class="form-group">

					<label>Email</label>

					<input type="email"
							name="email"
							required>

				</div>

				<div class="form-group">

					<label>Password</label>

					<input type="password"
							name="password"
							required>

				</div>

				<div class="login-links">

					<a href="javascript:void(0)">

						Forgot Password? (Coming Soon)

					</a>

				</div>

				<button type="submit"
						class="login-btn">

					Login

				</button>

			</form>

			<div class="register-link">

				<p>

					Don't have an account?

					<a href="<%=request.getContextPath()%>/register">

						Register Here

					</a>

				</p>

			</div>

		</div>

	</section>

	<jsp:include page="partials/footer.jsp" />

</body>
</html>