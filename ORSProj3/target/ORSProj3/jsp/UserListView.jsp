<%@page import="in.co.rays.model.ModelFactory"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.model.RoleModelInt"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.dto.BaseDTO"%>
<%@page import="in.co.rays.controller.UserListCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@include file="Header.jsp"%>

<html>

<head>

<title>User List</title>

<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />

<link href="<%=ORSView.APP_CONTEXT%>/assets/css/style-select.css"
	rel="stylesheet">

</head>

<body class="img-responsive"
	style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg');">

	<div class="container-fluid" style="margin-top: 100px;">

		<jsp:useBean id="dto" class="in.co.rays.dto.UserDTO" scope="request"></jsp:useBean>

		<form action="<%=ORSView.USER_LIST_CTL%>" method="post">

			<div class="row d-flex justify-content-center">
				<div class="col-4 text-center text-white">
					<h3>
						<strong>User List</strong>
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

					List<RoleDTO> roleList = (List<RoleDTO>) request.getAttribute("roleList");

					@SuppressWarnings("unchecked")
					List<UserDTO> list = (List<UserDTO>) ServletUtility.getList(request);

					Iterator<UserDTO> it = list.iterator();

					if (list.size() != 0) {
				%>


				<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
					type="hidden" name="pageSize" value="<%=pageSize%>">
				<div class="col-12">

					<div class="row d-flex justify-content-center">
						<div class="col-lg-3 col-sm-4">

							<div class="form-group bmd-form-group">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-search-plus" aria-hidden="true"
											style="color: #fff"></i>
										</span>

									</div>

									<%=HTMLUtility.getList("roleId", String.valueOf(dto.getRoleId()), roleList)%>

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

									<input type="text" class="form-control text-grey"
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
											style="color: #fff"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Email Id" name="login"
										value="<%=ServletUtility.getParameter("login", request)%>">
								</div>
							</div>
						</div>

						<div class="col-lg-3 col-sm-4 mt-4">
							<div class="row d-inline-flex align-item-cenr">
								<div class="col-6">
									<button class="btn  btn-info "
										style="border-radius: 1rem; margin-top: 14px" name="operation"
										type="submit" value="<%=UserListCtl.OP_SEARCH%>">
										<i class="fa fa-search-plus" aria-hidden="true"></i><b
											style="font-size: 15px;">Search</b>
									</button>

									<button class="btn f btn-info"
										style="border-radius: 1rem; margin-top: 14px" name="operation"
										type="submit" value="<%=UserListCtl.OP_RESET%>">
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

								<th scope="col" class="text-center"><input type="checkbox"
									id="selectall" /></th>

								<th scope="col" class="text-center">S.No.</th>
								<th scope="col" class="text-center">First Name</th>
								<th scope="col" class="text-center">Last Name</th>
								<th scope="col" class="text-center">Login Id</th>
								<th scope="col" class="text-center">Mobile No</th>
								<th scope="col" class="text-center">Gender</th>
								<th scope="col" class="text-center">DOB</th>
								<th scope="col" class="text-center">Role</th>
								<th scope="col" class="d-flex justify-content-center">Delete</th>
								<th scope="col" class="text-center">Edit</th>

							</tr>
						</thead>

						<%
							while (it.hasNext()) {

									dto = (UserDTO) it.next();
									RoleModelInt model = ModelFactory.getInstance().getRoleModel();
									RoleDTO roleDTO = new RoleDTO();
									roleDTO = model.findByPK(dto.getRoleId());
						%>
						<tbody>
							<tr>
								<td><input type="checkbox" class="case" name="ids"
									value="<%=dto.getId()%>"
									<%if (userDto.getId() == dto.getId() || dto.getRoleId() == RoleDTO.ADMIN) {%>
									<%="disabled"%> <%}%>></td>

								<td class="text-center"><%=index++%></td>
								<td class="text-center"><%=dto.getFirstName()%></td>
								<td class="text-center"><%=dto.getLastName()%></td>
								<td class="text-center"><%=dto.getLogin()%></td>
								<td class="text-center"><%=dto.getMobileNo()%></td>
								<td class="text-center"><%=dto.getGender()%></td>
								<%
									SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

											String date = sdf.format(dto.getDob());
								%>
								<td class="text-center"><%=date%></td>
								<td class="text-center"><%=roleDTO.getName()%></td>
								<td class="d-flex justify-content-center">
									<button class="btn btn-link text-primary " type="submit"
										name="operation" value="<%=UserListCtl.OP_DELETE%>"
										<%if (userDto.getId() == dto.getId() || dto.getRoleId() == RoleDTO.ADMIN) {%>
										<%="disabled"%> <%}%>>

										<i class="fa fa-trash" aria-hidden="true"></i>

									</button>
								</td>
								<td class="text-center"><a
									href="UserCtl?id=<%=dto.getId()%>"
									<%if (userDto.getId() == dto.getId() || dto.getRoleId() == RoleDTO.ADMIN) {%>
									onclick="return false;" <%}%>> <i
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
								name="operation" value="<%=UserListCtl.OP_PREVIOUS%>"
								<%=pageNo > 1 ? "" : "disabled"%>>
								<i class="fa fa-arrow-circle-left" aria-hidden="true"></i>&emsp;Prev

							</button>
							<button class="btn btn-rose col-lg-1 col-sm-6 col-md-offset-5 "
								type="submit" name="operation" value="<%=UserListCtl.OP_NEW%>">
								<i class="fa fa-plus-circle" aria-hidden="true"></i>&emsp;Add
							</button>

							<button class="btn btn-primary col-lg-1 col-sm-2 pull-right"
								type="submit" name="operation" value="<%=UserListCtl.OP_NEXT%>"
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
				<div class="row d-flex justify-content-center">

					<div class="col-md-6 d-flex justify-content-center"
						style="margin: 6.3125rem 1px;">

						<button class="btn btn-primary" type="submit" name="operation"
							value="<%=UserListCtl.OP_BACK%>">Back</button>

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