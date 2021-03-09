<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.controller.TimetableListCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.dto.TimetableDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@include file="Header.jsp"%>

<html>
<head>

<title>Timetable List</title>


<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />

<link href="<%=ORSView.APP_CONTEXT%>/assets/css/style-select.css"
	rel="stylesheet">

<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>

<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>../assets/js/CheckBox.js">
	
</script>
</head>
<body
	style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg');">
	<div class="container-fluid" style="margin-top: 125px;">

		<jsp:useBean id="dto" class="in.co.rays.dto.TimetableDTO"
			scope="request"></jsp:useBean>

		<form action="<%=ORSView.TIMETABLE_LIST_CTL%>" method="post">
			<div class="row d-flex justify-content-center">
				<div class="col-4 text-center">
					<h3>
						<strong style="color: #fff;">Timetable List</strong>
					</h3>
				</div>
				<div class="col-12 d-flex justify-content-center"
					style="min-height: 70px;">
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
				</div>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

					@SuppressWarnings("unchecked")

					List<TimetableDTO> courseList = (List<TimetableDTO>) request.getAttribute("courseList");

					@SuppressWarnings("unchecked")

					List<TimetableDTO> subjectList = (List<TimetableDTO>) request.getAttribute("subjectList");

					@SuppressWarnings("unchecked")

					List<TimetableDTO> list = (List<TimetableDTO>) ServletUtility.getList(request);
					Iterator<TimetableDTO> it = list.iterator();

					if (list.size() != 0) {
				%>

				<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
					type="hidden" name="pageSize" value="<%=pageSize%>">

				<div class="col-12">
					<div class="row d-flex justify-content-center">
						<div class="col-lg-3 col-sm-4" style="margin-top: 22px">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-search-plus" aria-hidden="true"
											style="color: #fff"></i>

										</span>
									</div>

									<%=HTMLUtility.getList("courseId", String.valueOf(dto.getCourseId()), courseList)%>

								</div>
							</div>
						</div>
						<div class="col-lg-3 col-sm-4" style="margin-top: 22px">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-search-plus" aria-hidden="true"
											style="color: #fff"></i>
										</span>
									</div>

									<%=HTMLUtility.getList("subjectId", String.valueOf(dto.getSubjectId()), subjectList)%>

								</div>

							</div>

						</div>

						<div class="col-lg-3 col-sm-4" style="margin-top: 1.5%">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend">

										<span class="input-group-text"> 
										
										<i class="fa fa-search-plus" aria-hidden="true"
											style="color: #fff"> </i>
											
									</div>

									<input type="text"
										class="form-control datetimepicker text-grey"
										placeholder="Select Exam Date" name="examDate"
										<%=DataUtility.getDateString(dto.getExamDate())%>>

								</div>

							</div>
						</div>

						<div class="col-lg-3 col-sm-4  col-md-4" style="margin-top: 3%">
							<div class="row d-inline-flex align-item-cenr">
								<div class="col-12">

									<button class="btn  btn-info "
										style="border-radius: 10px; margin-left: 10%;"
										name="operation" type="submit"
										value="<%=TimetableListCtl.OP_SEARCH%>">

										<i class="fa fa-search-plus" aria-hidden="true"></i> <b
											style="font-size: 15px;">Search</b>

									</button>

									<button class="btn btn-info "
										style="border-radius: 10px; margin-left: 16%;"
										name="operation" type="submit"
										value="<%=TimetableListCtl.OP_RESET%>">

										<i class="fa fa-refresh" aria-hidden="true"></i>&emsp;<b
											style="font-size: 15px;">Reset</b>

									</button>

								</div>
							</div>
						</div>
					</div>

				</div>
				<div class="col-12 table-responsive">
					<table class="table table-striped table-hover bg-light">
						<thead class="bg-primary text-white">
							<tr>

								<th scope="col"><input type="checkbox" id="selectall" /></th>
								<th scope="col" class="text-center">S.No.</th>
								<th scope="col" class="text-center">Course Name</th>
								<th scope="col" class="text-center">Subject Name</th>
								<th scope="col" class="text-center">Semester</th>
								<th scope="col" class="text-center">Exam Date</th>
								<th scope="col" class="text-center">Exam Time</th>
								<th scope="col" class="text-center">Description</th>
								<th scope="col" class="d-flex justify-content-center">Delete</th>
								<th scope="col" class="text-center">Edit</th>

							</tr>
						</thead>

						<%
							while (it.hasNext()) {

									dto = it.next();
						%>

						<tbody>
							<tr>
								<td><input type="checkbox" class="case" name="ids"
									value="<%=dto.getId()%>"></td>
								<td class="text-center"><%=index++%></td>
								<td class="text-center"><%=dto.getCourseName()%></td>
								<td class="text-center"><%=dto.getSubjectName()%></td>
								<td class="text-center"><%=dto.getSemester()%></td>
								<%
									SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
											String date = sdf.format(dto.getExamDate());
								%>

								<td class="text-center"><%=date%></td>
								<td class="text-center"><%=dto.getExamTime()%></td>
								<td class="text-center"><%=dto.getDescription()%></td>
								<td class="d-flex justify-content-center">
									<button class="btn btn-link text-primary " type="submit"
										name="operation" value="<%=TimetableListCtl.OP_DELETE%>">
										<i class="fa fa-trash" aria-hidden="true"></i>
									</button>

								</td>
								<td class="text-center"><a
									href="TimetableCtl?id=<%=dto.getId()%>"> <i
										class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
				<div class="col-12">
					<div class="row">
						<div class="col-md-12 d-flex justify-content-center">

							<button class="btn btn-primary col-lg-1 col-sm-2" type="submit"
								name="operation" value="<%=TimetableListCtl.OP_PREVIOUS%>"
								<%=pageNo > 1 ? "" : "disabled"%>>
								<i class="fa fa-arrow-circle-left" aria-hidden="true"></i>&emsp;Prev
							</button>
							<button class="btn btn-rose col-lg-1 col-sm-6 col-md-offset-5"
								type="submit" name="operation"
								value="<%=TimetableListCtl.OP_NEW%>">
								<i class="fa fa-plus-circle" aria-hidden="true"></i>&emsp;Add
							</button>

							<button class="btn btn-primary col-lg-1 col-sm-2 pull-right"
								type="submit" name="operation"
								value="<%=TimetableListCtl.OP_NEXT%>"
								<%=(nextPageSize != 0) ? "" : "disabled"%>>
								Next&emsp;<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
							</button>
						</div>
					</div>
				</div>
				<%
					}
					if (list.size() == 0) {
				%>
				<div class="row">
					<div class="col-md-6 d-flex justify-content-center">

						<button class="btn btn-primary" type="submit" name="operation"
							value="<%=TimetableListCtl.OP_BACK%>">Back</button>
					</div>
				</div>
				<%
					}
				%>

			</div>


		</form>
	</div>
	<div style="margin-top: 100%">

		<%@include file="Footer.jsp"%>

	</div>

</body>
</html>