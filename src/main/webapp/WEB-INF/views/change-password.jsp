<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/change-password.css">

</head>
<body>

	<jsp:include page="partials/navbar.jsp"/>

	<%
	String error =
			(String) request.getAttribute(
					"error");
	%>

	<section class="change-password-section">

		<div class="change-password-card">

			<h2>Change Password</h2>

			<%
			if(error != null){
			%>

			<p class="error-message">

				<%=error%>

			</p>

			<%
			}
			%>

			<form action="<%=request.getContextPath()%>/change-password"
					method="post">

				<div class="form-group">

					<label>Current Password</label>

					<input type="password"
							name="currentPassword"
							required>

				</div>

				<div class="form-group">

					<label>New Password</label>

					<input type="password"
							name="newPassword"
							required>

				</div>

				<div class="form-group">

					<label>Confirm New Password</label>

					<input type="password"
							name="confirmPassword"
							required>

				</div>

				<button type="submit"
						class="change-btn">

					Update Password

				</button>

			</form>

		</div>

	</section>

	<jsp:include page="partials/footer.jsp"/>

</body>
</html>