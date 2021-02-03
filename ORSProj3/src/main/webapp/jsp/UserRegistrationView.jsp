<%@page import="in.co.rays.util.DataValidator"%>
<%@page import="in.co.rays.controller.UserRegistrationCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>

<html>
<head>
<title>User Registration</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>

<body ng-app="720" ng-controller="TestController as ctrl"
	style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg'); height: 100vh;">
	
	<%@ include file="Header.jsp"%>

	<jsp:useBean id="dto" class="in.co.rays.dto.UserDTO"
		scope="request"></jsp:useBean>
		
	<div class="section section-signup page-header" style="height: 140vh;">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-6 ml-auto mr-auto">
					<div class="card card-login pb-2" style="margin-top: 200px">
						<form class="form" method="POST"
							action="<%=ORSView.USER_REGISTRATION_CTL%>">
							<div class="card-header card-header-primary text-center">
								<h4 class="card-title">User Registration</h4>
							</div>
							<%
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
							%>
							<div class="alert alert-success"
								style="line-height: 10px; margin-left: 20px; margin-right: 20px;">
								<div class="container" style="text-align: center;">
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
							<div class="alert alert-danger"
								style="line-height: 3px; margin-left: 20px; margin-right: 20px;">
								<div class="container" style="text-align: center;">
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
										<span class="input-group-text"> <i
											class="fa fa-user-circle" aria-hidden="true"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter First Name..." name="firstName"
										value="<%=DataUtility.getStringData(dto.getFirstName())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("firstName", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("firstName", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-user-circle" aria-hidden="true"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Last Name..." name="lastName"
										value="<%=DataUtility.getStringData(dto.getLastName())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("lastName", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("lastName", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-envelope" aria-hidden="true"></i>
										</span>
									</div>
									<input type="email" class="form-control"
										placeholder="Enter Email..." name="login"
										value="<%=DataUtility.getStringData(dto.getLogin())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("login", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("login", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="fa fa-lock"
											aria-hidden="true" style="font-size: 1.3em;"></i>
										</span>
									</div>
									<input type="password" class="form-control"
										placeholder="Enter Password..." name="password"
										value="<%=DataUtility.getStringData(dto.getPassword())%>">

									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("password", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("password", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>

								</div>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="fa fa-lock"
											aria-hidden="true" style="font-size: 1.3em;"></i>
										</span>
									</div>
									<input type="password" class="form-control"
										placeholder="Enter Confirm Password..." name="confirmPassword"
										value="<%=DataUtility.getStringData(dto.getConfirmPassword())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("confirmPassword", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("confirmPassword", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-calendar-plus-o" aria-hidden="true"
											style="font-size: 1.1em;"></i>

										</span>
									</div>
									<input type="text" class="form-control datetimepicker"
										name="dob">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("dob", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("dob", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="fa fa-venus-double" aria-hidden="true"
											style="font-size: 1em;"></i> </span>
									</div>

									<%
										HashMap<String, String> map = new HashMap<String, String>();
										map.put("Female", "Female");
										map.put("Male", "Male");

										String htmlList = HTMLUtility.getList("gender", dto.getGender(), map);
									%>
									<%=htmlList%>
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("gender", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("gender", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-phone-square" aria-hidden="true"
											style="font-size: 1.2em;"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Mobile No..." name="mobileNo"
										value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("mobileNo", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("mobileNo", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 ml-auto d-flex justify-content-center">
									<button class="btn btn-primary btn-round" name="operation"
										type="submit" value="<%=UserRegistrationCtl.OP_SIGN_UP%>">SignUp</button>
									<button class="btn btn-primary btn-round" name="operation"
										type="submit" value="<%=UserRegistrationCtl.OP_RESET%>">Reset</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>