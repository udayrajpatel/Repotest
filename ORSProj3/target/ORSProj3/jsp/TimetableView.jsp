<%@page import="in.co.rays.util.DataValidator"%>
<%@page import="in.co.rays.dto.SubjectDTO"%>
<%@page import="in.co.rays.dto.CourseDTO"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="in.co.rays.controller.UserCtl"%>
<%@page import="in.co.rays.dto.TimetableDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.controller.TimetableCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>


<html>
<head>
<title>Add Timetable</title>

<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
	
</head>

<body style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg');">
<%@ include file="Header.jsp"%>
	

	<jsp:useBean id="dto" class="in.co.rays.dto.TimetableDTO"
		scope="request"></jsp:useBean>


	<%
		List<CourseDTO> courseList = (List<CourseDTO>) request.getAttribute("courseList");

		List<SubjectDTO> subjectList = (List<SubjectDTO>) request.getAttribute("subjectList");
	%>

	<div class="section section-signup page-header"
		style="background-image: url('./assets/img/bg5.jpg'); height: 120vh;">

		<div class="container">
			<div class="row" style="margin-left: 70vh">
				<div class="col-lg-4 col-md-6 ml-auto mr-auto">
				
					<div class="card card-login" style="margin-top: 200px">
					
						<form class="form" method="post" action="<%=ORSView.TIMETABLE_CTL%>">
						
							<div class="card-header card-header-primary text-center">
								<h4 class="card-title">
									<%
										if (dto != null && dto.getId() > 0) {
									%>Update<%
										} else {
									%>Add<%
										}
									%>
									Timetable
								</h4>
							</div>
							<%
							
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
									
							%>
							<div class="alert alert-success" align="left">
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
							<div class="alert alert-danger" align="center">
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
							<%
								String uri = (String) request.getAttribute("uri");
							%>
							<div class="card-body">
								<div class="input-group col-xs-10">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-graduation-cap" aria-hidden="true"></i>
										</span>
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

								<div class="input-group col-xs-10">
									<div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-graduation-cap" aria-hidden="true"></i>

										</span>
									</div>

									<%=HTMLUtility.getList("subjectId", String.valueOf(dto.getCourseId()), subjectList)%>
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("subjectId", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("subjectId", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>

								<div class="input-group col-xs-10">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-envelope" aria-hidden="true"></i>

										</span>
									</div>

									<%
										HashMap<String, String> map = new HashMap<String, String>();

										map.put("1", "1");
										map.put("2", "2");
										map.put("3", "3");
										map.put("4", "4");
										map.put("5", "5");
										map.put("6", "6");
										map.put("7", "7");
										map.put("8", "8");
										map.put("9", "9");
										map.put("10", "10");

										
										String htmlList = HTMLUtility.getList("semester", dto.getSemester(), map);
										
									%>

									<%=htmlList%>

									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("semester", request))) {
									%>

									<span class="text-danger pt-3" data-toggle="tooltip" 	data-placement="left"
										title="<%=ServletUtility.getErrorMessage("semester", request)%>">
									
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>

									</span>
									<%
										}
									%>
								</div>
								<div class="input-group col-xs-110">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-calendar-plus-o" aria-hidden="true"
											style="font-size: 1.1em;"></i>
										</span>
									</div>

									<input type="text" class="form-control datetimepicker"
										placeholder=" Enter Exam Date" name="examDate"
										value="<%=DataUtility.getStringData(dto.getExamDate())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("examDate", request))) {
									%>

									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="<%=ServletUtility.getErrorMessage("examDate", request)%>">

										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>

									</span>
									<%
										}
									%>
								</div>

								<div class="input-group col-xs-10">

									<div class="input-group-prepend">

										<span class="input-group-text"> <i
											class="fa fa-calendar-plus-o" aria-hidden="true"
											style="font-size: 1.1em;"></i>

										</span>
									</div>

									<%
										LinkedHashMap<String, String> map1 = new LinkedHashMap<String, String>();

										map1.put("08:00 AM to 11:00 AM", "08:00 AM to 11:00 AM");

										map1.put("12:00 PM to 03:00 PM", "12:00 PM to 03:00 PM");

										map1.put("04:00 PM to 07:00 PM", "04:00 PM to 07:00 PM");

										String htmlList1 = HTMLUtility.getList("examTime", dto.getExamTime(), map1);
									%>
									<%=htmlList1%>
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("examTime", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("examTime", request)%>">

										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>

									</span>
									<%
										}
									%>

								</div>

								<div class="input-group col-sm-110">

									<div class="input-group-prepend">

										<span class="input-group-text"> <i
											class="fa fa-pencil-square" aria-hidden="true"> </i>

										</span>
									</div>

									<textarea type="text" class="form-control"
										style="min-width: 110%"
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
								<div class="col-md-12 ml-auto d-flex justify-content-center" style="margin-top: 10%">

									<button class="btn btn-primary" name="operation" type="submit"
										value="<%=TimetableCtl.OP_UPDATE%>">Update</button>
									
									<button class="btn btn-primary"style="margin-left: 6%" name="operation" type="submit"
										value="<%=TimetableCtl.OP_CANCEL%>">Cancel</button>
								</div>
								<%
									} else {
								%>
								<div class="col-md-12 ml-auto d-flex justify-content-center"
									style="margin-top: 10%">

									<button class="btn btn-primary" name="operation" type="submit"
										value="<%=TimetableCtl.OP_SAVE%>">Save</button>

									<button class="btn btn-primary" style="margin-left: 8%"
										name="operation" type="submit"
										value="<%=TimetableCtl.OP_RESET%>">Reset</button>
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