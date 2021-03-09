<%@page import="in.co.rays.util.DataValidator"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="in.co.rays.controller.MarksheetCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ include file="Header.jsp"%>
<html>

<head>

<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />

</head>

<body style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg');">



	<jsp:useBean id="dto" class="in.co.rays.dto.MarksheetDTO"
		scope="request"></jsp:useBean>

	<%
	
		@SuppressWarnings("rawtypes")
		List l = (List) request.getAttribute("studentList");
	
	%>


	<div class="section section-signup page-header" style="height: 120vh;">
		<div class="container">
			<div class="row" style="margin-left: 70vh">

				<div class="col-lg-4 col-md-6 ml-auto mr-auto">

					<div class="card card-login">

						<form class="form" method="post" action="<%=ORSView.MARKSHEET_CTL%>">
						
							<div class="card-header card-header-primary text-center">
							
								<span><i class="fa fa-plus-square" aria-hidden="true"></i>
								</span>
								<%
									if (dto != null && dto.getId() > 0) {
								%>Update<%
									} else {
								%>

								Add<%
									}
								%>

								Marksheet

								</h4>
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

							</div>

							<div class="card-body">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-id-card-o" aria-hidden="true"
											style="font-size: 1.5em"></i>

										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Your RollNo..." name="rollNo" maxlength="5"
										value="<%=DataUtility.getStringData(dto.getRollNo())%>"
										<%=(dto.getId() > 0) ? "readonly" : ""%>>

									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("rollNo", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("rollNo", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>

								</div>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="fa fa-users"
											aria-hidden="true" style="font-size: 1.5em;"></i>

										</span>
									</div>

									<%=HTMLUtility.getList("studentId", String.valueOf(dto.getStudentId()), l)%>

									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("studentId", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("studentId", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>

								</div>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-calculator" aria-hidden="true"
											style="font-size: 1.5em"></i>

										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Your Physics Marks..." name="physics"
										maxlength="3"
										value="<%=DataUtility.getStringData(dto.getPhysics())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("physics", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("physics", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-calculator" aria-hidden="true"
											style="font-size: 1.5em"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Your Chemistry Marks..." name="chemistry"
										maxlength="3"
										value="<%=DataUtility.getStringData(dto.getChemistry())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("chemistry", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrorMessage("chemistry", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>

									</span>
									<%
										}
									%>
								</div>

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-calculator" aria-hidden="true"
											style="font-size: 1.5em"></i>
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Enter Your Maths Marks..." name="maths"
										maxlength="3"
										value="<%=DataUtility.getStringData(dto.getMaths())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrorMessage("maths", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="<%=ServletUtility.getErrorMessage("maths", request)%>">

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
								<div class="col-md-12 ml-auto d-flex justify-content-center"
									style="margin-top: 6%;">

									<button class="btn btn-primary" name="operation" type="submit"
										value="<%=MarksheetCtl.OP_UPDATE%>">Update</button>

									<button class="btn btn-primary" style="margin-left: 6%"name="operation" type="submit"
										value="<%=MarksheetCtl.OP_CANCEL%>">Cancel</button>

								</div>
								<%
									} else {
								%>

								<div class="col-md-12 ml-auto d-flex justify-content-center"
									style="margin-top: 6%">

									<button class="btn btn-primary" name="operation" type="submit"
										value="<%=MarksheetCtl.OP_SAVE%>">
										Save <span><i class="fa fa-floppy-o" aria-hidden="true"
											style="font-size: 1em;"></i> </span>
									</button>

									<button class="btn btn-primary " style="margin-left: 5%" name="operation" type="submit"
										value="<%=MarksheetCtl.OP_RESET%>">

										Reset <span><i class="fa fa-refresh" aria-hidden="true"
											style="font-size: 1em;"></i> </span>

									</button>

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