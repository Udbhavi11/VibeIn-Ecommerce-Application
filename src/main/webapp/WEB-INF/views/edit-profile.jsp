<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.vibein.model.User"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Profile</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/edit-profile.css">

</head>
<body>

	<jsp:include page="partials/navbar.jsp"/>

	<%
	User user =
			(User) request.getAttribute(
					"user");
	%>

	<section class="edit-profile-section">

		<div class="edit-profile-card">

			<h2>Edit Profile</h2>

			<form action="<%=request.getContextPath()%>/edit-profile"
					method="post">

				<div class="form-group">

					<label>Full Name</label>

					<input type="text"
							name="fullName"
							value="<%=user.getFullName()%>"
							required>

				</div>

				<div class="form-group">

					<label>Email</label>

					<input type="email"
							name="email"
							value="<%=user.getEmail()%>"
							required>

				</div>

				<div class="form-group">

					<label>Phone</label>

					<input type="text"
							name="phone"
							value="<%=user.getPhone()%>"
							required>

				</div>

				<div class="form-group">

					<label>Address</label>

					<textarea name="address"
							rows="4"
							required><%=user.getAddress()%></textarea>

				</div>

				<button type="submit"
						class="save-btn">

					Save Changes

				</button>

			</form>

		</div>

	</section>

	<jsp:include page="partials/footer.jsp"/>

</body>
</html>