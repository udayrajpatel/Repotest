<%@page import="in.co.rays.util.DataValidator"%>
<%@page import="in.co.rays.controller.CollegeCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>


<html>
<head>
<title>Add College</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
	<%@ include file="Header.jsp"%>
</head>
<body
	style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg');">
	

	<jsp:useBean id="dto" class="in.co.rays.dto.CollegeDTO"
		scope="request"></jsp:useBean>
		
	<div class="section section-signup ">
		<div class="container">
			<div class="row" style="margin-left:70vh">
			
				<div class="col-lg-4 col-md-6 ml-auto mr-auto">
					<div class="card card-login">
					
						<form class="form"  method="post"  action="<%=ORSView.COLLEGE_CTL %>">
						
							<div class="card-header card-header-primary text-center">
						
								<h4 class="card-title">
									<%
										if (dto != null && dto.getId() > 0) {
									%>Update<%
										} else {
									%>Add<%
										}
									%>
									College
								</h4>
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
							<div class="alert alert-danger"
								>
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
											class="fa fa-university" aria-hidden="true"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter College Name..." name="name"
										value="<%=DataUtility.getStringData(dto.getName())%>">
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
											class="fa fa-address-book" aria-hidden="true"></i>

										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Address..." name="address"
										value="<%=DataUtility.getStringData(dto.getAddress())%>">
									<%
									
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("address", request))) {
											
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("address", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-map-marker" aria-hidden="true"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter State..." name="state"
										value="<%=DataUtility.getStringData(dto.getState())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("state", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("state", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-map-marker" aria-hidden="true"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter City..." name="city"
										value="<%=DataUtility.getStringData(dto.getCity())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("city", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("city", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>
								<p class="description text-right">
									<font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
								</p>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-phone-square" aria-hidden="true"
											style="font-size: 1.2em;"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Phone No..." name="phoneNo"
										value="<%=DataUtility.getStringData(dto.getPhoneNo())%>"
										maxlength="10">
										<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("phoneNo", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("phoneNo", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>
							</div>
							<div class="row"style="margin-top: 6%">
								<div class="col-md-12 ml-auto d-flex justify-content-center">
									<%
										if (dto != null && dto.getId() > 0) {
									%>
									<button class="btn btn-primary " name="operation" type="submit"
										value="<%=CollegeCtl.OP_UPDATE%>">Update</button>
										
									<button class="btn btn-primary " name="operation" type="submit"
										value="<%=CollegeCtl.OP_CANCEL%>">Cancel</button>
									<%
										} else {
									%>
									
									<button class="btn btn-primary " name="operation" type="submit"
										value="<%=CollegeCtl.OP_SAVE%>">Save</button>&emsp;
										
									<button class="btn btn-primary " name="operation" type="submit"
										value="<%=CollegeCtl.OP_RESET%>">Reset</button>
									<%
										}
									%>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
<div style="margin-top: 100%">
<%@ include file="Footer.jsp"%>
</div>
</html>