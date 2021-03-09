<%@page import="java.text.SimpleDateFormat"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.controller.StudentListCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.dto.StudentDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@include file="Header.jsp"%>

<html>
<head>
<title>Student List</title>

<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
<link
	href="<%=ORSView.APP_CONTEXT%>/assets/css/style-select.css"
	rel="stylesheet">
	
</head>
<body

	style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg');">
	<div class="container-fluid" style="margin-top: 125px;">
	
		<jsp:useBean id="dto" class="in.co.rays.dto.StudentDTO"
			scope="request"></jsp:useBean>
		<form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post">

			<div class="row d-flex justify-content-center">
				<div class="col-4 text-center">
					<h3>
						<strong style="color: #fff;">Student List</strong>
					</h3>
				</div>
				<div class="col-12 d-flex justify-content-center">
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
						<div class="container" style="text-align: left;">
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
					List<StudentDTO> list = (List<StudentDTO>) ServletUtility.getList(request);
					Iterator<StudentDTO> it = list.iterator();

					if (list.size() != 0) {
				%>

				<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
					type="hidden" name="pageSize" value="<%=pageSize%>">
				<div class="col-11">
					<div class="row d-flex justify-content-center">
						<div class="col-lg-3 col-sm-4">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-search-plus" aria-hidden="true"
											style="color: #fff"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter First Name" name="firstName"
										value="<%=ServletUtility.getParameter("firstName", request)%>">
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-sm-4">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-search-plus" aria-hidden="true"
											style="color: #fff"></i>>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Last Name" name="lastName"
										value="<%=ServletUtility.getParameter("lastName", request)%>">
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-sm-4">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-search-plus" aria-hidden="true"
											style="color: #fff"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Email Id" name="email"
										value="<%=ServletUtility.getParameter("email", request)%>">
								</div>
							</div>
						</div>

						<div class="col-lg-3 col-sm-4 col-md-4" style="margin-top: 1%;">
							<div class="row d-inline-flex align-item-cenr">
								<div class="col-12">
									<button class="btn btn-info" style="border-radius: 1rem;"
										name="operation" type="submit"
										value="<%=StudentListCtl.OP_SEARCH%>">
										<i class="fa fa-search-plus" aria-hidden="true"></i><b
											style="font-size: 15px;">Search</b>
								
									</button>
								
									<button class="btn btn-info " style="border-radius: 1rem"
										name="operation" type="submit"
										value="<%=StudentListCtl.OP_RESET%>">
										<i class="fa fa-refresh" aria-hidden="true"></i>&emsp;<b
											style="font-size: 15px;">Reset</b>
									</button>
								</div>
							</div>
						</div>
					</div>

				</div>
				<div class="col-12" >
					<table class="table table-striped table-hover bg-light">
						<thead class="bg-primary text-white">
							<tr>
								<th scope="col" class="text-center"><input type="checkbox" id="selectall" /></th>
								<th scope="col" class="text-center">S.No.</th>
								<th scope="col" class="text-center">First Name</th>
								<th scope="col" class="text-center">Last Name</th>
								<th scope="col" class="text-center">Email Id</th>
								<th scope="col" class="text-center">College Name</th>
								<th scope="col" class="text-center">Gender</th>
								<th scope="col" class="text-center">Mobile No</th>
								<th scope="col" class="text-center">DOB</th>
								<th scope="col" class="text-center">Delete</th>
								<th scope="col"class="text-center">Edit</th>

							</tr>
						</thead>

						<%
							while (it.hasNext()) {
									dto = it.next();
						%>
						<tbody>
							<tr>
								<td style="text-align: center;"><input type="checkbox"
									class="case" name="ids" value="<%=dto.getId()%>"></td>
								<td><%=index++%></td>
								<td><%=dto.getFirstName()%></td>
								<td><%=dto.getLastName()%></td>
								<td><%=dto.getEmail()%></td>
								<td><%=dto.getCollegeName()%></td>
								<td><%=dto.getGender()%></td>
								<td><%=dto.getMobileNo()%></td>
								<%
									SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
											String date = sdf.format(dto.getDob());
								%>
								<td style="text-align: center;"><%=date%></td>
								<td class="d-flex justify-content-center"> <button class="btn btn-link text-primary " type="submit" name="operation" value="<%=StudentListCtl.OP_DELETE%>">
										<i class="fa fa-trash" aria-hidden="true"></i></button></td>
								<td style="text-align: center;"><a
									href="StudentCtl?id=<%=dto.getId()%>"><i
										class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<div class="col-12">
						<div class="row">
							<div class="col-md-12 d-flex justify-content-center">

								<button class="btn btn-primary col-lg-1 col-sm-2" type="submit"
									name="operation" value="<%=StudentListCtl.OP_PREVIOUS%>"
									<%=pageNo > 1 ? "" : "disabled"%>>
									<i class="fa fa-arrow-circle-left" aria-hidden="true"></i>&emsp;Prev
								</button>
								<button class="btn btn-rose col-lg-1 col-sm-6 col-md-offset-5" type="submit"
									name="operation" value="<%=StudentListCtl.OP_NEW%>">
									<i class="fa fa-plus-circle" aria-hidden="true"></i>&emsp;Add
								</button>
								<button class="btn btn-primary col-lg-1 col-sm-2 pull-right" type="submit"
									name="operation" value="<%=StudentListCtl.OP_NEXT%>"
									<%=(nextPageSize != 0) ? "" : "disabled"%>>
									Next&emsp;<i class="fa fa-arrow-circle-right"
										aria-hidden="true"></i>
								</button>
							</div>
						</div>
					</div>
					<%
						}
						if (list.size() == 0) {
					%>
					<div class="row">
						<div class="col-md-6 ml-auto" >

							<button class="btn btn-primary" type="submit" name="operation"
								value="<%=StudentListCtl.OP_BACK%>">Back</button>
						</div>
						
					</div>
					<%
						}
					%>

				</div>
				</div>
		</form>
		</div>
</body>
	<div style="margin-top: 100%">
	<%@include file="Footer.jsp"%>
	</div>
</html>