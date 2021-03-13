<%@page import="in.co.rays.util.DataValidator"%>
<%@page import="in.co.rays.controller.UserCtl"%>
<%@page import="in.co.rays.dto.FacultyDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.controller.FacultyCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<html>
<head>
<title>Add Faculty</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body
	style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg');">
	<%@ include file="Header.jsp"%>

	<jsp:useBean id="dto" class="in.co.rays.dto.FacultyDTO" scope="request"></jsp:useBean>

	<%
	
		@SuppressWarnings("unchecked")
		List<FacultyDTO> collegeList = (List<FacultyDTO>) request.getAttribute("collegeList");
		@SuppressWarnings("unchecked")
		List<FacultyDTO> courseList = (List<FacultyDTO>) request.getAttribute("courseList");
		@SuppressWarnings("unchecked")
		List<FacultyDTO> subjectList = (List<FacultyDTO>) request.getAttribute("subjectList");
		
	%>

	<div class="section section-signup "
		style="background-image: url('./assets/img/bg2.jpg'); height: 150vh;">
		<div class="container">
			<div class="row" style="margin-left: 70vh">
				<div class="col-lg-4 col-md-6 ml-auto mr-auto">
				
					<div class="card card-login" style="margin-top: 200px">
						<form class="form" method="post" action="<%=ORSView.FACULTY_CTL%>">
							<div class="card-header card-header-primary text-center" >
								<h4 class="card-title">
									<%
									
										if (dto != null && dto.getId() > 0) {
											
									%>Update<%
										} else {
									%>Add<%
										}
									%>
									Faculty
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
										<span class="input-group-text"><i
											class="fa fa-user-circle" aria-hidden="true"></i> </span>
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
										placeholder="Enter Email..." name="email"
										value="<%=DataUtility.getStringData(dto.getEmail())%>">

									<%
									
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("email", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("email", request)%>">
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
									<input type="text" class="form-control"
										placeholder="Enter Date Of Birth..." name="dob"
										value="<%=DataUtility.getDateString(dto.getDob())%>">
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
										<span class="input-group-text"> <i
											class="fa fa-th-list" aria-hidden="true"></i>
										</span>
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
										title="<%=ServletUtility.getErrorMessage("gender", request)%>">
										
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
										
									</span>
									
									<%
										}
									%>
								</div>

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-phone-square" aria-hidden="true"></i>
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

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-th-list" aria-hidden="true"></i>
										</span>
									</div>

									<%=HTMLUtility.getList("collegeId", String.valueOf(dto.getCollegeId()), collegeList)%>
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("collegeId", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("collegeId", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i class="fa fa-th-list"
											aria-hidden="true"></i> </span>
									</div>

									<%=HTMLUtility.getList("courseId", String.valueOf(dto.getCourseId()), courseList)%>
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
										<span class="input-group-text"> <i
											class="material-icons">face</i>
										</span>
									</div>

									<%=HTMLUtility.getList("subjectId", String.valueOf(dto.getSubjectId()), subjectList)%>
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("subjectId", request))) {
									%>
									
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="<%=ServletUtility.getErrorMessage("subjectId", request)%>">
										
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
								<div class="col-md-12 ml-auto d-flex justify-content-center">

									<button class="btn btn-primary" style="margin-top: 9" name="operation" type="submit"
										value="<%=FacultyCtl.OP_UPDATE%>">Update</button>
									<button class="btn btn-primary" style="margin-left: 13;margin-top: 9" name="operation" type="submit"
										value="<%=FacultyCtl.OP_CANCEL%>">Cancel</button>
								</div>
								<%
									} else {
								%>
								<div class="col-md-12 ml-auto d-flex justify-content-center" style="margin-top: 10">

									<button class="btn btn-primary" name="operation" type="submit"
										value="<%=FacultyCtl.OP_SAVE%>">Save</button>
										
									<button class="btn btn-primary" name="operation" type="submit" style="margin-left: 13"
										value="<%=FacultyCtl.OP_RESET%>">Reset</button>
										
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