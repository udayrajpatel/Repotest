<%@page import="in.co.rays.controller.MyProfileCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ include file="Header.jsp"%>

<html>

<head>

<title>My Profile</title>

<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />

</head>

<body
	style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg'); min-height: 170vh">

	<jsp:useBean id="dto" class="in.co.rays.dto.UserDTO" scope="request">

	</jsp:useBean>

	<div class="section section-signup ">

		<div class="container">

			<div class="row" style="margin-left: 70vh">

				<div class="col-lg-4 col-md-6 ml-auto mr-auto">

					<div class="card card-login">

						<form class="form" method="post" action="<%=ORSView.USER_CTL%>">

							<div class="card-header card-header-primary text-center">

								<h4 class="card-title">My Profile</h4>

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
										<span class="input-group-text"> <i
											class="material-icons">mail</i>
										</span>
									</div>

									<input type="email" class="form-control"
										placeholder="Enter Your LoginId..." name="login"
										value="<%=DataUtility.getStringData(dto.getLogin())%>">
								</div>
								<p class="description text-right">

									<font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
								</p>

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="material-icons">face</i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Your First Name..." name="firstName"
										value="<%=DataUtility.getStringData(dto.getFirstName())%>">
								</div>
								<p class="description text-right">
									<font color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font>
								</p>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="material-icons">face</i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Your Last Name..." name="lastName"
										value="<%=DataUtility.getStringData(dto.getLastName())%>">
								</div>
								<p class="description text-right">
									<font color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font>
								</p>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="material-icons">face</i>
										</span>
									</div>
									<input type="text" class="form-control datetimepicker"
										placeholder="Enter Your DOB..." name="dob"
										value="<%=DataUtility.getStringData(dto.getDob())%>">
								</div>
								<p class="description text-right">
									<font color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font>
								</p>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="material-icons">face</i>
										</span>
									</div>

									<%
										HashMap<String, String> map = new HashMap<String, String>();
										map.put("Male", "Male");
										map.put("Female", "Female");

										String htmlList = HTMLUtility.getList("gender", dto.getGender(), map);
									%>
									<%=htmlList%>
								</div>
								<p class="description text-right">
									<font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font>
								</p>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="material-icons">face</i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Your Mobile No..." name="mobileNo"
										value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
								</div>
								<p class="description text-right">
									<font color="red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></font>
								</p>
							</div>

							<div class="row d-flex justify-content-center"	style="margin-left: 3%">
								<button class="btn btn-primary btn-round" name="operation"
									type="submit" value="<%=MyProfileCtl.OP_SAVE%>">Save</button>

								<button class="btn btn-primary btn-round" name="operation"
									type="submit" value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>">Update</button>

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