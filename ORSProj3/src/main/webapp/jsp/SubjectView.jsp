<%@page import="in.co.rays.util.DataValidator"%>
<%@page import="in.co.rays.controller.UserCtl"%>
<%@page import="in.co.rays.dto.SubjectDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.controller.SubjectCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ include file="Header.jsp"%>

<html>
<head>
<title>Add Subject</title>

<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
	
</head>
<body
	style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg');">
	

	<jsp:useBean id="dto" class="in.co.rays.dto.SubjectDTO" scope="request"></jsp:useBean>

	<div class="section section-signup " style="height: 120vh;">
		<div class="container">
			<div class="row" style="margin-left: 70vh">
				<div class="col-lg-4 col-md-6 ml-auto mr-auto">
					<div class="card card-login">
					
						<form class="form" method="post" action="<%=ORSView.SUBJECT_CTL%>">
						
							<%
								@SuppressWarnings("unchecked")

								List<SubjectDTO> l = (List<SubjectDTO>) request.getAttribute("courseList");
							%>
							<input type="hidden" name="id" value="<%=dto.getId()%>">
							<input type="hidden" name="createdBy"
								value="<%=dto.getCreatedBy()%>"> <input type="hidden"
								name="modifiedBy" value="<%=dto.getModifiedBy()%>"> <input
								type="hidden" name="createdDatetime"
								value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
							<input type="hidden" name="modifiedDatetime"
								value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
							<div class="card-header card-header-primary text-center">
								<h4 class="card-title">
									<%
										if (dto != null && dto.getId() > 0) {
									%>Update<%
										} else {
									%>Add<%
										}
									%>
									Subject
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
								<div class="container-fluid" style="text-align: center;">
								
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
										placeholder="Enter Your Subject Name..." name="name"
										value="<%=DataUtility.getStringData(dto.getName())%>">

									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("name", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="<%=ServletUtility.getErrorMessage("name", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
										
									</span>
									<%
										}
									%>
								</div>

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-th-list" aria-hidden="true"></i>
										</span>
									</div>

									<%=HTMLUtility.getList("courseId", String.valueOf(dto.getCourseId()), l)%>
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("courseId", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("courseId", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text">
										 <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
										</span>
									</div>
									
									<textarea class="form-control"  style="min-width: 110%"
										placeholder="Write Short Description..." name="description"
										value="<%=DataUtility.getStringData(dto.getDescription())%>">  </textarea>
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("description", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip" data-placement="left" title="<%=ServletUtility.getErrorMessage("description", request)%>">
									
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
										
									</span>
									<%
										}
									%>
								</div>
							</div>
							<div class="row" style="margin-top: 10%">
								<%
									if (dto != null && dto.getId() > 0) {
								%>
								<div class="col-md-12 ml-auto d-flex justify-content-center">

									<button class="btn btn-primary" name="operation" type="submit"
										value="<%=SubjectCtl.OP_UPDATE%>">Update</button>
										
									<button class="btn btn-primary" style="margin-left: 6%" name="operation" type="submit"
										value="<%=SubjectCtl.OP_CANCEL%>">Cancel</button>

								</div>
								<%
									} else {
								%>
								<div class="col-md-12 ml-auto d-flex justify-content-center" style="margin-top: 10%">

									<button class="btn btn-primary" name="operation"  type="submit"
										value="<%=SubjectCtl.OP_SAVE%>">Save</button>
								
									<button class="btn btn-primary" name="operation" type="submit" style="margin-left: 6%"
										value="<%=SubjectCtl.OP_RESET%>">Reset</button>
										
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