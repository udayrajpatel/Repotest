<%@page import="in.co.rays.controller.ORSView"%>
<%@page import="in.co.rays.dto.UserDTO"%>
<%@page import="in.co.rays.dto.RoleDTO"%>
<%@ include file="Header.jsp"%>

<html>
<head>
<title>Welcome to ORS</title>

<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>

<body>

	<div class="page-header header-filter clear-filter purple-filter"
		data-parallax="true" style="background-image: url('./img/'); height: 100vh;">
		<div class="container">

			<div class="row d-flex justify-content-center">

				<div class="col-12 text-center text-white">
                    
					<h1 class="text-danger">Welcome to ORS</h1>

				</div>

				<%
					UserDTO dtoUserDTO = (UserDTO) session.getAttribute("user");
					if (dtoUserDTO != null) {

						if (dtoUserDTO.getRoleId() == RoleDTO.STUDENT) {
				%>
				<div class="col-6 text-center">
					<hr class="border border-secondary">
					<h2>
						<a class="text-light" href="<%=ORSView.GET_MARKSHEET_CTL%>">
						Click here to see your Marksheet</a>

					</h2>
					<hr class="border border-secondary">
				</div>
				<%
					}
					}
				%>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>