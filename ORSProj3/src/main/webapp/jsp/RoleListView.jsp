<%@page import="javax.management.relation.Role"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.controller.RoleListCtl"%>
<%@page import="in.co.rays.controller.BaseCtl"%>
<%@page import="in.co.rays.dto.RoleDTO"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<title>Role List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
<link
	href="<%=ORSView.APP_CONTEXT%>/assets/css/style-select.css"
	rel="stylesheet">
</head>
<body
	style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg');">
	<div class="container-fluid" style="margin-top: 125px;">
		<%@include file="Header.jsp"%>
		<jsp:useBean id="Dto" class="in.co.rays.dto.RoleDTO"
			scope="request"></jsp:useBean>
		<form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">
			<div class="row d-flex justify-content-center">
				<div class="col-4 text-center">
					<h3>
						<strong style="color: #fff;">Role List</strong>
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
					List<RoleDTO> roleList = (List<RoleDTO>) request.getAttribute("roleList");

					@SuppressWarnings("unchecked")
					List<RoleDTO> list = (List<RoleDTO>) ServletUtility.getList(request);
					Iterator<RoleDTO> it = list.iterator();

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
									<%=HTMLUtility.getList("roleId", String.valueOf(Dto.getId()), roleList)%>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-sm-4 mt-4">
							<div class="row d-inline-flex align-item-cenr">
								<div class="col-6">
									<button class="btn  btn-info " style="border-radius: 10px;"
										name="operation" type="submit"
										value="<%=RoleListCtl.OP_SEARCH%>">
										<i class="fa fa-search-plus" aria-hidden="true"></i><b
											style="font-size: 15px;">Search</b>
									</button>
								</div>
								<div class="col-6">
									<button class="btn btn-default " style="border-radius: 10px;"
										name="operation" type="submit"
										value="<%=RoleListCtl.OP_RESET%>">
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
								<th scope="col">S.No.</th>
								<th scope="col">Role</th>
								<th scope="col">Description</th>
								<th scope="col" class="d-flex justify-content-center">Delete</th>
								<th scope="col">Edit</th>

							</tr>
						</thead>


						<%
							while (it.hasNext()) {
									Dto = (RoleDTO) it.next();
						%>
						<tbody>
							<tr>
								<td><input type="checkbox" class="case" name="ids"
									value="<%=Dto.getId()%>"></td>
								<td><%=index++%></td>
								<td><%=Dto.getName()%></td>
								<td><%=Dto.getDescription()%></td>
								<td class="d-flex justify-content-center"> <button class="btn btn-link text-primary " type="submit" name="operation" value="<%=RoleListCtl.OP_DELETE%>">
										<i class="fa fa-trash" aria-hidden="true"></i></button></td>
								<td><a href="RoleCtl?id=<%=Dto.getId()%>"><i
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
								name="operation" value="<%=RoleListCtl.OP_PREVIOUS%>"
								<%=pageNo > 1 ? "" : "disabled"%>>
								<i class="fa fa-arrow-circle-left" aria-hidden="true"></i>&emsp;Prev
							</button>
							<button class="btn btn-rose col-lg-1 col-sm-6" type="submit"
								name="operation" value="<%=RoleListCtl.OP_NEW%>">
								<i class="fa fa-plus-circle" aria-hidden="true"></i>&emsp;Add
							</button>
							<button class="btn btn-primary col-lg-1 col-sm-2" type="submit"
								name="operation" value="<%=RoleListCtl.OP_NEXT%>"
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
							value="<%=RoleListCtl.OP_BACK%>">Back</button>
					</div>
				</div>
				<%
					}
				%>

			</div>


		</form>
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>