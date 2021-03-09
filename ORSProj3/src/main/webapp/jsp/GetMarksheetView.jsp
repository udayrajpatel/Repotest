<%@page import="java.text.DecimalFormat"%>
<%@page import="in.co.rays.controller.GetMarksheetCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ include file="Header.jsp"%>

<html>


<head>

<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />

</head>

<body
	style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg'); height: 100vh;">

	<jsp:useBean id="dto" class="in.co.rays.dto.MarksheetDTO"
		scope="request"></jsp:useBean>

	<div class="section section-signup ">

		<div class="container-fluid">

			<div class="row" style="margin-left: 70vh">

				<div class="col-lg-4 col-md-6 ml-auto mr-auto">

					<div class="card card-login">

						<form  class="form"
							method="post" action="<%=ORSView.GET_MARKSHEET_CTL%>">

							<div class="card-header card-header-primary text-center">

								<h4 class="card-title">Get Marksheet</h4>
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
							
								if (ServletUtility.getErrorMessage(request) != null && ServletUtility.getErrorMessage(request).length() > 0) {
									
									
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

							<div class="card-body">
								<div class="input-group col-md-12">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="material-icons">RollNO</i>
										</span>
									</div>
									<input type="text" class="form-control" name="rollNo"
										placeholder="Enter Your RollNo..."
										value="<%=ServletUtility.getParameter("rollNo", request)%>">
								</div>
								<p class="description text-center">
								
									<font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%></font>
									
								</p>
							</div>
							<div class="row">
								<div class="col-md-12 d-flex justify-content-center">

									<button class="btn btn-primary col-md-offset-3"
										name="operation" type="submit"
										value="<%=GetMarksheetCtl.OP_GO%>">Go</button>
								</div>
								
							</div>

							<br>
							<%
							
								int physics = DataUtility.getInt(DataUtility.getStringData(dto.getPhysics()));
							
								int chemistry = DataUtility.getInt(DataUtility.getStringData(dto.getChemistry()));
								
								int maths = DataUtility.getInt(DataUtility.getStringData(dto.getMaths()));

								int total = physics + chemistry + maths;

								float percentage = (float) total / 3;
								
								percentage = Float.parseFloat(new DecimalFormat("##.##").format(percentage));

								if (dto.getRollNo() != null && dto.getRollNo().trim().length() > 0) {
									
							%>

							<table>

								<tr>
									<td align="center" style="background-color: infotext;  color: red;">

										<h2>Rays Technologies Indore</h2>
									</td>

								</tr>

							</table>

							<table border="1" style="border: groove; ">
								<tr>
									<td align="center" style="width: 15%">Name</td>
									
									<th align="center" style="width: 35%; text-transform: capitalize;"><%=DataUtility.getStringData(dto.getName())%></th>

									<td align="center" style="width: 15%">Roll No</td>

									<th align="center"
										style="width: 25%; text-transform: uppercase;"><%=DataUtility.getStringData(dto.getRollNo())%></th>

								</tr>

								<tr>
									<td align="center" style="width: 15%">Status</td>
									<th align="center" style="width: 35%">Regular</th>

									<td align="center" style="width: 15%">Course</td>
									<th align="center" style="width: 25%">BE</th>

								</tr>
							</table>

							<table border="1" style="border: groove;">
								<tr style="background-color: #e6e6e485;">
								
									<th align="center" style="width: 25%">Subject</th>
									<th align="center" style="width: 25%">Earned Credits</th>
									<th align="center" style="width: 25%">Total Credits</th>
									<th align="center" style="width: 25%">Grade</th>
									
								</tr>
								<tr>
									<td align="center">Physics</td>
									<td align="center"><%=physics%> <%
 	if (physics < 33) {
 %><span style="color: red">*</span> <%
 	}
 %></td>
									<td align="center">100</td>
									<td align="center">
										<%
											if (physics > 90 && physics <= 100) {
										%>A+ <%
											} else if (physics > 80 && physics <= 90) {
										%>A <%
											} else if (physics > 70 && physics <= 80) {
										%>B+ <%
											} else if (physics > 70 && physics <= 80) {
										%>B <%
											} else if (physics > 60 && physics <= 70) {
										%>C+ <%
											} else if (physics > 50 && physics <= 60) {
										%>C <%
											} else if (physics >= 33 && physics <= 50) {
										%>D <%
											} else if (physics >= 0 && physics < 33) {
										%><span style="color: red;">F</span> <%
 	}
 %>
									</td>

								</tr>
								<tr>
									<td align="center">Chemistry</td>
									<td align="center"><%=chemistry%> <%
 	if (chemistry < 33) {
 %><span style="color: red">*</span> <%
 	}
 %></td>
									<td align="center">100</td>
									<td align="center">
										<%
											if (chemistry > 90 && chemistry <= 100) {
										%>A+ <%
											} else if (chemistry > 80 && chemistry <= 90) {
										%>A <%
											} else if (chemistry > 70 && chemistry <= 80) {
										%>B+ <%
											} else if (chemistry > 70 && chemistry <= 80) {
										%>B <%
											} else if (chemistry > 60 && chemistry <= 70) {
										%>C+ <%
											} else if (chemistry > 50 && chemistry <= 60) {
										%>C <%
											} else if (chemistry >= 33 && chemistry <= 50) {
										%>D <%
											} else if (chemistry >= 0 && chemistry < 33) {
										%><span style="color: red;">F</span> <%
 	}
 %>
									</td>

								</tr>
								<tr>
									<td align="center">Maths</td>
									<td align="center"><%=maths%> <%
 	if (maths < 33) {
 %><span style="color: red">*</span> <%
 	}
 %></td>
									<td align="center">100</td>
									<td align="center">
										<%
											if (maths > 90 && maths <= 100) {
										%>A+ <%
											} else if (maths > 80 && maths <= 90) {
										%>A <%
											} else if (maths > 70 && maths <= 80) {
										%>B+ <%
											} else if (maths > 70 && maths <= 80) {
										%>B <%
											} else if (maths > 60 && maths <= 70) {
										%>C+ <%
											} else if (maths > 50 && maths <= 60) {
										%>C <%
											} else if (maths >= 33 && maths <= 50) {
										%>D <%
											} else if (maths >= 0 && maths < 33) {
										%><span style="color: red;">F</span> <%
 	}
 %>
									</td>

								</tr>
							</table>

							<table border="1" style="border: groove; ">
							
								<tr style="background-color: #e6e6e485;">
								
									<th align="center" style="width: 25%">Total Marks</th>
									<th align="center" style="width: 25%">Percentage (%)</th>
									<th align="center" style="width: 25%">Division</th>
									<th align="center" style="width: 25%">Result</th>

								</tr>
								<tr>
									<th align="center"><%=total%> <%
 	if (total < 99 || physics < 33 || chemistry < 33 || maths < 33) {
 %><span style="color: red;">*</span> <%
 	}
 %></th>
									<th align="center"><%=percentage%> %</th>
									<th align="center">
										<%
											if (percentage >= 60 && percentage <= 100) {
										%>1<sup>st</sup> <%
 	} else if (percentage >= 40 && percentage < 60) {
 %>2<sup>nd</sup> <%
 	} else if (percentage >= 0 && percentage < 40) {
 %>3<sup>rd</sup> <%
 	}
 %>
									</th>

									<th align="center">
										<%
											if (physics >= 33 && chemistry >= 33 && maths >= 33) {
										%><span style="color: forestgreen;">Pass</span> <%
 	} else {
 %><span style="color: red;">Fail</span> <%
 	}
 %>
									</th>
								</tr>

							</table>
							<%
								}
							%>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<div style="margin-top: 100%"></div>
</html>