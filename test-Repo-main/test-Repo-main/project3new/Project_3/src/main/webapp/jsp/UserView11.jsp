<%-- <%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.controller.UserCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="<%=ORSView.USER_CTL%>" method="post">
<div>	<%@ include file="Header.jsp"%>
<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="dto" class="in.co.rays.dto.UserDTO"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("list");
		
		%>
	</div>
<br>
<br>
<br>
<br>
<div class="container-fluid">
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-6">
<div class="card">

<div class="card-header">

<%
				if (dto.getId() > 0) {
			%>
			<h1 align="center">Update User</h1>
			<%
				} else {
			%>

			<h1 align="center">Add User</h1>
			<%
				}
			%>
</div>
<div>

			
			<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=dto.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
</div>
<div class="card-body">
<%
						if (!ServletUtility.getErrorMessage(request).equals(""))
						{
							%>
						
							 <div class="alert alert-danger alert-dismissible">
							    <button type="button" class="close" data-dismiss="alert">&times;</button>
							    <strong>Danger!</strong> <%=ServletUtility.getErrorMessage(request)%>
							  </div>
<% 
						}			
						
						%>
						
						<%
						if (!ServletUtility.getSuccessMessage(request).equals(""))
						{
							%>
						
							 <div class="alert alert-success alert-dismissible">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success!</strong> <%=ServletUtility.getSuccessMessage(request)%>
  </div>
<% 
						}			
						
						%>
First Name<span style="color: red">*</span><input type="text" name="firstName" class="form-control" placeholder="Enter First Name" value="<%=DataUtility.getStringData(dto.getFirstName())%>"><td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td><br> 
Last Name<span style="color: red">*</span><input type="text" name="lastName" class="form-control" placeholder="Enter First Name" value="<%=DataUtility.getStringData(dto.getLastName())%>"><td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td><br> 
LoginId<span style="color: red">*</span><input type="text" name="login" class="form-control" placeholder="Must be Email ID" value="<%=DataUtility.getStringData(dto.getLogin())%>"><td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td><br> 
Password<span style="color: red">*</span><input type="password" name="password" class="form-control" placeholder="Enter Password" value="<%=DataUtility.getStringData(dto.getPassword())%>"><td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td><br> 
Confirm Password<span style="color: red">*</span><input type="password" name="confirmPassword" class="form-control" placeholder="Enter Confirm Password" value="<%=DataUtility.getStringData(dto.getPassword())%>"><td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td><br> 
Gender<span style="color: red">*</span><%
							HashMap map = new HashMap();
							map.put("M", "Male");
							map.put("F", "Female");

							String htmlList = HTMLUtility.getList("gender", dto.getGender(), map);
						%> <%=htmlList%><td style="position: fixed;">
						<font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font>
					</td><br> 
Role<span style="color: red">*</span><%=HTMLUtility.getList("roleId", String.valueOf(dto.getRoleId()), l)%><td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("roleId", request)%></font>
					</td><br> 
Date Of Birth<span style="color: red">*</span><input type="text" name="dob" id="date" readonly  placeholder="Enter DOB" class="form-control"
						value="<%=DataUtility.getDateString(dto.getDob())%>"><td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>

<br>
<div align="center">
<%
							if (dto.getId() > 0) {
						%> &emsp;<input type="submit" name="operation" class="btn btn-success" 
						value="<%=UserCtl.OP_UPDATE%>"> &emsp; <input
						type="submit" name="operation" class="btn btn-info" value=<%=UserCtl.OP_CANCEL%>>
						<%
							} else {
								%>
<input type="submit" class="btn btn-success"name ="operation"  value="<%=UserCtl.OP_SAVE%>">
<input type="submit" class="btn btn-info" name ="operation"  value="<%=UserCtl.OP_RESET%>">
<%
 	}
 %>
</div>
</div>

</div>
</div>
</div>
</div>
	
	
    
   
    
	
</form>
<br>
<br>
<br>
<%@include file="Footer.jsp"%>
</body>
</html> --%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>

<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.controller.UserCtl"%>
<%@page import="in.co.rays.controller.ORSView"%>

<html>
<head lang="en">

<title>User View</title>
<!--    favicon-->
 <link rel="shortcut icon"
	href="../theam_wel/image/fav-icon.png" type="image/x-icon"> 
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">


<style type="text/css">
body {
	background-image: url("/Project_3/img/dan.jpg");
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	background-position: center;
}

.table-hover tbody tr:hover td
    {
        background-color: #0064ff36;
    }  

.error_msg{ 
 margin-left: -62px;
}


</style>
<!-- datepicker files -->
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
$(function() {
	$("#datepicker").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : 'mm/dd/yy',
		yearRange:"-34:-18",
		defaultDate : "01/01/2000",
	});
});
</script>
</head>

<body>

<%@ include file="Header.jsp"%>
	<form action="<%=ORSView.USER_CTL%>" method="post">

		<div align="center" style="height: 420px;">

			<jsp:useBean id="dto" class="in.co.rays.dto.UserDTO"
				scope="request"></jsp:useBean>

			<%
				List l = (List) request.getAttribute("roleList");
			%>

			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12 col-md-6 col-sm-12 col-lg-4 col-md-offset-4">
						<div class="panel panel-primary"
							style="margin-top: 10px; background-color: #ffffff91;">
							<div class="panel-heading" style="background-color: #e9967a00;"
								align="center">

								<%
									if (dto.getId() > 0) {
								%>
								<h1><b>Update User</b></h1>
								<%
									} else {
								%>
								<h1><b>Add User</b></h1>
								<%
									}
								%>
							</div>


							<div class="panel-body">
								<div align="center">
								<%if(!ServletUtility.getSuccessMessage(request).equals("")){%>
								<div class="alert alert-success alert-dismissible">
                               <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong> <%=ServletUtility.getSuccessMessage(request)%></strong>
                         </div>
								<%} if(!ServletUtility.getErrorMessage(request).equals("")){%>
				       	<div class="alert alert-danger alert-dismissible">
                          <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                          <strong> <%=ServletUtility.getErrorMessage(request)%></strong>
                          </div>
								<%} %>
									
								</div>
                               <br> <input type="hidden" name="id"
									value="<%=dto.getId()%>"> <input type="hidden"
									name="createdBy" value="<%=dto.getCreatedBy()%>"> <input
									type="hidden" name="modifiedBy"
									value="<%=dto.getModifiedBy()%>"> <input type="hidden"
									name="createdDatetime"
									value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
								<input type="hidden" name="modifiedDatetime"
									value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">


								 <div class="col-xs-12 col-sm-12 col-md-10 col-lg-12">
     <!--  <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8"> -->
									<div class="form-group" style="margin-left: 10%;">
										<label align="left" class="control-label text-info col-md-6">
											First Name<span style="color: red;">*</span>
										</label>

										<div class="col-md-12" style="margin-bottom: 20px;">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-user"></i></span> <input type="text"
													class="form-control" name="firstName"
													placeholder="Enter First Name"
													value="<%=DataUtility.getStringData(dto.getFirstName())%>">
											</div>
											<label class="control-label text-danger  error_msg" for="inputError1">
											<%=ServletUtility.getErrorMessage("firstName", request)%></label>
										</div>
									</div>

						

									<div class="form-group" style="margin-left: 10%;">
										<label align="left" class="control-label col-md-6 text-info">
											Last Name<span style="color: red;">*</span>
										</label>
										<div class="col-md-12" style="margin-bottom: 20px;">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-user"></i></span> <input type="text"
													class="form-control" name="lastName"
													placeholder="Enter Last Name"
													value="<%=DataUtility.getStringData(dto.getLastName())%>">
											</div>
											<label class="control-label text-danger error_msg" for="inputError1">
												<%=ServletUtility.getErrorMessage("lastName", request)%></label>
										</div>
									</div>

								

									<div class="form-group" style="margin-left: 10%;">
										<label align="left" class="control-label col-md-6 text-info">
											Login Id<span style="color: red;">*</span>
										</label>

										<div class="col-md-12" style="margin-bottom: 20px;">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-log-in"></i></span> <input type="text"
													class="form-control" name="login"
													placeholder="Enter Login ID"
													value="<%=DataUtility.getStringData(dto.getLogin())%>"
													<%=(dto.getId() > 0) ? "readonly" : ""%>>
											</div>
											
											<label class="control-label text-danger error_msg" for="inputError1">
												<%=ServletUtility.getErrorMessage("login", request)%></label>
										</div>
										 
									</div>



									<div class="form-group" style="margin-left: 10%;">
										<label align="left" class="control-label col-md-6 text-info">
											Password<span style="color: red;">*</span>
										</label>

										<div class="col-md-12" style="margin-bottom: 20px;">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-lock" aria-hidden="true"></i></span> <input
													type="password" class="form-control" name="password"
													placeholder="Enter Password"
													value="<%=DataUtility.getStringData(dto.getPassword())%>">
											</div>
											<label class="control-label text-danger error_msg" for="inputError1">
												<%=ServletUtility.getErrorMessage("password", request)%></label>
										</div>
									</div>

									<div class="form-group" style="margin-left: 10%;">
										<label align="left" class="control-label col-md-6 text-info">
											Gender<span style="color: red;">*</span>
										</label>

										<div class="col-md-12" style="margin-bottom: 20px;">
											<div class="input-group">
												<span class="input-group-addon"><i class="fa fa-venus-double" aria-hidden="true"></i></span>
												<%
													HashMap map = new HashMap();
													map.put("Male", "Male");
													map.put("Female", "Female");
													String htmlList = HTMLUtility.getList("gender", dto.getGender(), map);
												%>
												<%=htmlList%>
											</div>
											<label class="control-label text-danger error_msg" for="inputError1">
												<%=ServletUtility.getErrorMessage("gender", request)%></label>
										</div>
									</div>

									
									<%-- <div class="form-group" style="margin-left: 10%;">
										<label align="left" class="control-label col-md-6 text-info">
											Role<span style="color: red;">*</span>
										</label>

										<div class="col-md-12" style="margin-bottom: 20px;">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i></span>
												<%=HTMLUtility.getList("roleId", String.valueOf(dto.getRoleId()), l)%>
											</div>
											<label class="control-label text-danger error_msg" for="inputError1">
												<%=ServletUtility.getErrorMessage("roleId", request)%></label>
										</div>
									</div>
 --%>
							

									<div class="form-group" style="margin-left: 10%;">
										<label align="left" class="control-label col-md-6 text-info">
											Date of Birth<span style="color: red;">*</span>
										</label>

									<div class="col-md-12" style="margin-bottom: 20px;">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-calendar"></i></span> <input
													type="text" class="form-control" name="dob" id="datepicker"
													readonly="readonly" placeholder="Enter Date of Birth"
													value="<%=DataUtility.getDateString(dto.getDob())%>">
											</div>
											<label class="control-label text-danger" for="inputError1">
												<%=ServletUtility.getErrorMessage("dob", request)%></label>
										</div>
									</div>

								

									<div class="form-group" style="margin-left: 10%;">
										<label align="left" class="control-label col-md-6 text-info">
											Mobile No.<span style="color: red;">*</span>
										</label>

										<div class="col-md-12" style="margin-bottom: 20px;">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-earphone"></i></span> <input
													type="text" class="form-control" name="mobileNo"
													maxlength="10" placeholder="Enter Mobile Number"
													value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
											</div>
											<label class="control-label text-danger error_msg" for="inputError1">
												<%=ServletUtility.getErrorMessage("mobileNo", request)%></label>
										</div>
									</div>


								</div>


								<div class="form-group" align="center">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<%
											if (dto.getId() > 0) {
										%><br>
										<button type="submit" class="btn btn-success" name="operation"
											value="<%=UserCtl.OP_UPDATE%>">
											<span class="glyphicon glyphicon-check"></span> Update
										</button>
										<button type="submit" class="btn btn-primary" name="operation"
											value="<%=UserCtl.OP_CANCEL%>">
											<span class="glyphicon glyphicon-remove"></span> Cancel
										</button>
										<%
											} else {
										%><br>
										<button type="submit" class="btn btn-primary" name="operation"
											value="<%=UserCtl.OP_SAVE%>">
											<span class="glyphicon glyphicon-check"></span> Save
										</button>
										&emsp;
										<button type="submit" class="btn btn-warning" name="operation"
											value="<%=UserCtl.OP_RESET%>">
											<span class="glyphicon glyphicon-refresh"></span> Reset
										</button>
										<%
											}
										%>


									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<br>
				<br>
			</div>
			</div>
			
	</form>

	<div style="min-height: 200px">
		<%@ include file="Footer.jsp"%>
		</div>
</body>
</html>