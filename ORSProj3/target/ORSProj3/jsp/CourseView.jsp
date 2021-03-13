<%@page import="in.co.rays.util.DataValidator"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.rays.controller.CourseCtl"%>
<%@page import="in.co.rays.controller.BaseCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>

<html>
<head>
<title>Add Course</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body
	style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg');">
	<%@ include file="Header.jsp"%>

	<jsp:useBean id="dto" class="in.co.rays.dto.CourseDTO" scope="request"></jsp:useBean>

	<div class="section section-signup page-header"
		style="background-image: url('/assets/img/bg2.jpg'); min-height: 120vh;">
		<div class="container">
			<div class="row" style="margin-left: 70vh">
				<div class="col-lg-4 col-md-6 ml-auto mr-auto">
					<div class="card card-login ">
						<form class="form" method="post" action="<%=ORSView.COURSE_CTL%>">
							<div class="card-header card-header-primary text-center">
								<input type="hidden" name="id" value="<%=dto.getId()%>"><span
									style="color: red;"></span> <input type="hidden"
									name="createdBy" value="<%=dto.getCreatedBy()%>"> <input
									type="hidden" name="modifiedBy"
									value="<%=dto.getModifiedBy()%>"> <input type="hidden"
									name="createdDatetime"
									value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
								<input type="hidden" name="modifiedDatetime"
									value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
								<h4 class="card-title">
									<%
										if (dto != null && dto.getId() > 0) {
									%>Update<%
										} else {
									%>Add<%
										}
									%>
									Course
								</h4>

							</div>
							<%
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
							%>

							<div class="alert alert-success"
								>
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
										<i class="fa fa-exclamation-circle " aria-hidden="true"></i>
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
							<div class="card-body">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-user-circle" aria-hidden="true"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Your CourseName" name="name"
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
											class="fa fa-clock-o" aria-hidden="true"></i>

										</span>
									</div>

									<%
										LinkedHashMap<String, String> map1 = new LinkedHashMap<String, String>();
										map1.put("1 Year", "1 Year");
										map1.put("2 Years", "2 Years");
										map1.put("3 Years", "3 Years");
										map1.put("4 Years", "4 Years");
										map1.put("5 Years", "5 Years");
										map1.put("6 Years", "6 Years");
										map1.put("7 Years", "7 Years");

										String htmlList1 = HTMLUtility.getList("duration", dto.getDuration(), map1);
									%>
									<%=htmlList1%>
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("duration", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("duration", request)%>">
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
									<textarea type="text" class="form-control" style="min-width: 110%"
										placeholder="Write Short Description..." name="description"
										value="<%=DataUtility.getStringData(dto.getDescription())%>"></textarea>
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
									if (dto != null && dto.getId() > 0) {
								%>
								<div class="col-md-12 ml-auto d-flex justify-content-center" style="margin-top: 10">

									<button class="btn btn-primary" name="operation" type="submit"
										value="<%=CourseCtl.OP_UPDATE%>">Update</button>
                                     
									<button class="btn btn-primary" style="margin-left: 10" name="operation" type="submit"
										value="<%=CourseCtl.OP_CANCEL%>">Cancel</button>
								</div>
								<%
									} else {
								%>
								<div class="col-md-12 ml-auto d-flex justify-content-center " style="margin-top: 10">

									<button class="btn btn-primary" name="operation" type="submit"
										value="<%=CourseCtl.OP_SAVE%>">Save</button>
										
									<button class="btn btn-primary" name="operation" type="submit" style="margin-left: 10"
										value="<%=CourseCtl.OP_RESET%>">Reset</button>
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