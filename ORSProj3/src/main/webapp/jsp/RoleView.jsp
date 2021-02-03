<%@page import="in.co.rays.util.DataValidator"%>
<%@page import="in.co.rays.controller.RoleCtl"%>
<%@page import="in.co.rays.controller.BaseCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<html>
<head>
<title>Add Role</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body
	style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg');">
	<%@ include file="Header.jsp"%>

	<jsp:useBean id="Dto" class="in.co.rays.dto.RoleDTO"
		scope="request"></jsp:useBean>

	<div class="section section-signup page-header"
		style="background-image: url('/assets/img/bg2.jpg'); min-height: 120vh">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-6 ml-auto mr-auto">
					<div class="card card-login">
						<form class="form" method="post" action="<%=ORSView.ROLE_CTL%>">
							<div class="card-header card-header-primary text-center">
								<h4 class="card-title">
									<%
										if (Dto != null && Dto.getId() > 0) {
									%>Update<%
										} else {
									%>Add<%
										}
									%>
									Role
								</h4>

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
							<input type="hidden" name="id" value="<%=Dto.getId()%>">
							<input type="hidden" name="createdBy"
								value="<%=Dto.getCreatedBy()%>"> <input type="hidden"
								name="modifiedBy" value="<%=Dto.getModifiedBy()%>"> <input
								type="hidden" name="createdDatetime"
								value="<%=DataUtility.getTimestamp(Dto.getCreatedDatetime())%>">
							<input type="hidden" name="modifiedDatetime"
								value="<%=DataUtility.getTimestamp(Dto.getModifiedDatetime())%>">

							<div class="card-body">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-user-circle" aria-hidden="true"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Role Name..." name="name"
										value="<%=DataUtility.getStringData(Dto.getName())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("name", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("name", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-pencil-square-o" aria-hidden="true"></i>
										</span>
									</div>
									<textarea type="text" class="form-control"
										placeholder="Write Short Description..." name="description"
										value="<%=DataUtility.getStringData(Dto.getDescription())%>"></textarea>
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("description", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("description", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>
							</div>


							<div class="row">
								<%
									if (Dto != null && Dto.getId() > 0) {
								%>
								<div class="col-md-12 ml-auto d-flex justify-content-center">

									<button class="btn btn-primary" name="operation" type="submit"
										value="<%=RoleCtl.OP_UPDATE%>">Update</button>
									<button class="btn" name="operation" type="submit"
										value="<%=RoleCtl.OP_CANCEL%>">Cancel</button>
								</div>
								<%
									} else {
								%>
								<div class="col-md-12 ml-auto d-flex justify-content-center">

									<button class="btn btn-primary" name="operation" type="submit"
										value="<%=RoleCtl.OP_SAVE%>">Save</button>
									<button class="btn" name="operation" type="submit"
										value="<%=RoleCtl.OP_RESET%>">Reset</button>
								</div>
								<%
									}
								%>
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