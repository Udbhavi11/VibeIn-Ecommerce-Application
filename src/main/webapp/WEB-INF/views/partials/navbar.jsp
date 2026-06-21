<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<header class="header">

	<div class="container navbar-container">

		<div class="logo">

			<a href="<%=request.getContextPath()%>/home">

				VibeIn

			</a>

		</div>

		<form class="search-bar"
				action="<%=request.getContextPath()%>/products"
				method="get">

			<input type="text"
					name="query"
					placeholder="Search for fashion products...">

			<button type="submit">

				Search

			</button>

		</form>

		<nav class="nav-links">

			<a href="<%=request.getContextPath()%>/home">

				Home

			</a>

			<a href="<%=request.getContextPath()%>/products">

				Products

			</a>

			<a href="<%=request.getContextPath()%>/cart">

				Cart

			</a>

			<a href="<%=request.getContextPath()%>/profile">

				Profile

			</a>

			<a href="<%=request.getContextPath()%>/my-orders">

				My Orders

			</a>

			<a href="<%=request.getContextPath()%>/logout">

				Logout

			</a>

		</nav>

	</div>

</header>