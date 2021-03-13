<%@page import="in.co.rays.controller.ORSView"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.controller.ChangePasswordCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>

<html>
<head>
<title>Change Password</title>

<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body
	style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg'); ">
	<%@ include file="Header.jsp"%>

	<jsp:useBean id="dto" class="in.co.rays.dto.UserDTO" scope="request"></jsp:useBean>

	<div class="section section-signup">

		<div class="container">

			<div class="row" style="margin-left: 70vh">
				<div class="col-lg-4 col-md-6 ml-auto mr-auto">
					<div class="card card-login">
						<form class="form" method="post"
							action="<%=ORSView.CHANGE_PASSWORD_CTL%>">
							<div class="card-header card-header-primary text-center">
								<h4 class="card-title">Change Password</h4>
							</div>
							<%
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
							%>

							<div class="alert alert-success">
								<div class="container-fluid" style="text-align: left;">
									<div class="alert-icon">
										<i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
									</div>

									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">

										<span aria-hidden="true"><i class="material-icons">clear</i></span>

									</button>

									<b><%=ServletUtility.getSuccessMessage(request)%></b>
								</div>
							</div>
							<%
								}
							%>

							<%
								if (ServletUtility.getErrorMessage(request) != null
										&& ServletUtility.getErrorMessage(request).length() > 0) {
							%>

							<div class="alert alert-danger">
								<div class="container-fluid" style="text-align: left;">
									<div class="alert-icon">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</div>

									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true"><i class="material-icons">clear</i></span>
									</button>

									<b><%=ServletUtility.getErrorMessage(request)%></b>

								</div>

							</div>

							<%
								}
							%>
							<input type="hidden" name="id" value="<%=dto.getId()%>">

							<input type="hidden" name="createdBy"
								value="<%=dto.getCreatedBy()%>"> <input type="hidden"
								name="modifiedBy" value="<%=dto.getModifiedBy()%>"> <input
								type="hidden" name="createdDatetime"
								value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">

							<input type="hidden" name="modifiedDatetime"
								value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">

							<div class="card-body">

								<div class="input-group">

									<div class="input-group-prepend">

										<span class="input-group-text"> <i class="fa fa-lock"
											aria-hidden="true"></i>

										</span>

									</div>
									<input type="text" class="form-control"
										placeholder="Enter Old Password..." name="oldPassword"
										value="<%=DataUtility.getString(request.getParameter("oldPassword") == null ? ""
					: DataUtility.getString(request.getParameter("oldPassword")))%>">
								</div>
								<p class="description text-right">
									<font color="red"><%=ServletUtility.getErrorMessage("oldPassword", request)%></font>
								</p>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="fa fa-lock"
											aria-hidden="true"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter New Password..." name="newPassword"
										value="<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
					: DataUtility.getString(request.getParameter("newPassword")))%>">
								</div>
								<p class="description text-right">
									<font color="red"><%=ServletUtility.getErrorMessage("newPassword", request)%></font>
								</p>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="fa fa-lock"
											aria-hidden="true"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Confirm Password..." name="confirmPassword"
										value="<%=DataUtility.getString(request.getParameter("confirmPassword") == null ? ""
					: DataUtility.getString(request.getParameter("confirmPassword")))%>">
								</div>
								<p class="description text-right">
									<font color="red"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
								</p>
							</div>
							<div class="row">
								<div class="col-md-12 d-flex justify-content-center">
									<button class="btn btn-primary btn-round" name="operation"
										type="submit" value="<%=ChangePasswordCtl.OP_SAVE%>">Save</button>
										
									<button class="btn btn-primary btn-round" name="operation"
										type="submit" value="<%=ChangePasswordCtl.OP_RESET%>">Reset</button>
										
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>

<%@ include file="Footer.jsp"%>
</html>