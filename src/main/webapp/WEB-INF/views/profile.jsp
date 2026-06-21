<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.vibein.model.User"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Profile</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/profile.css">

</head>
<body>

	<jsp:include page="partials/navbar.jsp"/>

	<%
	User user =
			(User) request.getAttribute("user");
	%>

	<section class="profile-section">

		<div class="profile-card">

			<div class="profile-icon">

				👤

			</div>

			<h2>My Profile</h2>

			<div class="profile-info">

				<p>

					<strong>Name :</strong>

					<%=user.getFullName()%>

				</p>

				<p>

					<strong>Email :</strong>

					<%=user.getEmail()%>

				</p>

				<p>

					<strong>Phone :</strong>

					<%=user.getPhone()%>

				</p>

				<p>

					<strong>Address :</strong>

					<%=user.getAddress()%>

				</p>

			</div>

			<div class="profile-buttons">

				<a href="<%=request.getContextPath()%>/edit-profile"
					class="edit-btn">

					Edit Profile

				</a>

				<a href="<%=request.getContextPath()%>/change-password"
					class="password-btn">

					Change Password

				</a>

			</div>

		</div>

	</section>

	<jsp:include page="partials/footer.jsp"/>

</body>
</html>