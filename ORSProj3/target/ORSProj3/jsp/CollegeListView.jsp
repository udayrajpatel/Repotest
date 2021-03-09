<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.Collections"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.controller.CollegeListCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.dto.CollegeDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@include file="Header.jsp"%>
	
<html>
<head>
<title>College List</title>

<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />

<link href="<%=ORSView.APP_CONTEXT%>/assets/css/style-select.css" rel="stylesheet">

</head>
<body
	style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg');">
	<div class="container-fluid" style="margin-top: 125px;">
	
		<jsp:useBean id="dto" class="in.co.rays.dto.CollegeDTO" scope="request"></jsp:useBean>
			
		<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="post">
			<div class="row d-flex justify-content-center">
			
				<div class="col-4 text-center">
					<h3>
						<strong style="color: #fff;">College List</strong>
					</h3>
				</div>
				<div class="col-12 d-flex justify-content-center">
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
						style="line-height: 10gpx; margin-left: 20px; margin-right: 20px;">
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
				</div>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

					@SuppressWarnings("unchecked")
					List<CollegeDTO> collegeList = (List<CollegeDTO>) request.getAttribute("collegeList");

					@SuppressWarnings("unchecked")
					List<CollegeDTO> list = (List<CollegeDTO>) ServletUtility.getList(request);
					Iterator<CollegeDTO> it = list.iterator();

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
									<%=HTMLUtility.getList("collegeId", String.valueOf(dto.getId()), collegeList)%>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-sm-4" style="margin-top:1.5% ">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-search-plus" aria-hidden="true"
											style="color: #fff"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter City" name="city"
										value="<%=ServletUtility.getParameter("city", request)%>">
								</div>
							</div>
						</div>


						<div class="col-lg-3 col-sm-4 col-md-4" style="margin-top: 2%">
						
							<div class="row d-inline-flex align-item-center">
							
								<div class="col-md-12 ">
								
								
									<button class="btn  btn-info " style="border-radius: 1rem;margin-left: 130%"
										name="operation" type="submit"
										value="<%=CollegeListCtl.OP_SEARCH%>">
										<i class="fa fa-search-plus" aria-hidden="true"></i><b
											style="font-size: 15px;  margin-top: 48px">Search</b>
									</button>
								
									<button class="btn btn-info " style="border-radius: 10px; margin-left: 170%; margin-top: -17%"
										name="operation" type="submit"
										value="<%=CollegeListCtl.OP_RESET%>">
										<i class="fa fa-refresh" aria-hidden="true"></i>&emsp;<b
											style="font-size: 15px; margin-top: 48px">Reset</b>
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
								<th scope="col">S.No.</th>
								<th scope="col">College Name</th>
								<th scope="col">Address</th>
								<th width="15%">State</th>
								<th scope="col">City</th>
								<th scope="col">Phone No</th>
								<th scope="col" class="d-flex justify-content-center">Delete</th>
								<th scope="col">Edit</th>

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
								<td><%=index++%></td>
								<td><%=dto.getName()%></td>
								<td><%=dto.getAddress()%></td>
								<td><%=dto.getState()%></td>
								<td><%=dto.getCity()%></td>
								<td><%=dto.getPhoneNo()%></td>
								<td class="d-flex justify-content-center"> <button class="btn btn-link text-primary " type="submit" name="operation" value="<%=CollegeListCtl.OP_DELETE%>">
										<i class="fa fa-trash" aria-hidden="true"></i></button></td>
								<td><a href="CollegeCtl?id=<%=dto.getId()%>"><i
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
								name="operation" value="<%=CollegeListCtl.OP_PREVIOUS%>"
								<%=pageNo > 1 ? "" : "disabled"%>>
								<i class="fa fa-arrow-circle-left" aria-hidden="true"></i>&emsp;Prev
							</button>
							<button class="btn btn-rose col-lg-1 col-sm-6 col-md-offset-5" type="submit"
								name="operation" value="<%=CollegeListCtl.OP_NEW%>">
								<i class="fa fa-plus-circle" aria-hidden="true"></i>&emsp;Add
							</button>
							<button class="btn btn-primary col-lg-1 col-sm-2 pull-right" type="submit"
								name="operation" value="<%=CollegeListCtl.OP_NEXT%>"
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
							value="<%=CollegeListCtl.OP_BACK%>">Back</button>
					</div>
				</div>
				<%
					}
				%>

			</div>


		</form>
	</div>

</body>
<div style="margin-top: 100%">
	<%@include file="Footer.jsp"%>
	</div>
</html>