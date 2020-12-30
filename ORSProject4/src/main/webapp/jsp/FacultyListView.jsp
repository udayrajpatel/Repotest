<%@page import="in.co.rays.proj4.ctl.FacultyListCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="in.co.rays.proj4.util.*"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.bean.FacultyBean"%>
<%@page import="in.co.rays.proj4.model.FacultyModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@include file="Header.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Faculty List</title>
</head>

<body>

	<div align="center"  style="height: 590px;">

		<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post" name="frm">

			<jsp:useBean id="bean" class="in.co.rays.proj4.bean.FacultyBean"
				scope="request"></jsp:useBean>
			<jsp:useBean id="model" class="in.co.rays.proj4.model.FacultyModel"
				scope="request"></jsp:useBean>

			<h1>Faculty List</h1>

			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table width="100%">
				<tr>
					<td align="center"><label>First Name :</label> <input
						type="text" name="firstName" placeholder="Enter First Name"
						value="<%=ServletUtility.getParameter("firstName", request)%>">
						&emsp; <label>Last Name:</label> <input type="text"
						name="lastName" placeholder="Enter Last Name"
						value="<%=ServletUtility.getParameter("lastName", request)%>">
						&emsp; <label>Login Id:</label> <input type="text" name="loginId"
						placeholder="Must be a Login_Id"
						value="<%=ServletUtility.getParameter("loginId", request)%>">
						&emsp; <input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_SEARCH%>"> &emsp; <input
						type="submit" name="operation"
						value="<%=FacultyListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			
			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
			</H2>
			<br>

			<%
				List list = ServletUtility.getList(request);
			System.out.println(list);
				if (list.size() == 0) {
			%>
			
			<table>
				<tr>
					<td><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<%
				} else {
			%>
			<table class="tbl">
				<tr bgcolor="#BFC9CA">
					<th><input type="checkbox" id="mainbox"
						onchange="selectAll(this)">Select All</th>
					<th>S.No</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Login Id</th>
					<th>Mobile No</th>
					<th>Date of Joining</th>
					<th>College Name</th>
					<th>Subject Name</th>
					<th>Edit</th>
				</tr>
				<%
					}
				%>
				
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					Iterator<FacultyBean> it = list.iterator();
					while (it.hasNext()) {
						bean = it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids" id="mainbox"
						onchange="selectone(this)" value="<%=bean.getId()%>"
						<%=((userBean.getRoleId() == RoleBean.ADMIN)) ? "" : "disabled"%>></td>
					<td><%=index++%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getLoginId()%></td>
					<td><%=bean.getMobileNo()%></td>
					<td><%=bean.getDoj()%></td>
					<td><%=bean.getCollegeName()%></td>
					<td><%=bean.getSubjectName()%>
					<%
						if (userBean.getRoleId() == 1) {
					%>
						<td><a href="FacultyCtl?id=<%=bean.getId()%>">Edit</a></td>
					<%
						} else {
					%>
						<td><a>----</a></td>
					<%
						}
					%>
				</tr>
				<%
					}
				%>
			</table>
			<br>
			<%
				if (list.size() != 0) {
			%>

			<table style="width: 100%">

				<colgroup>
					<col style="width: 30%">
					<col style="width: 35%">
					<col style="width: 10%">
					<col style="width: 25%">
				</colgroup>

				<tr>
					<td align="left"><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
					<%
						if (userBean.getRoleId() == RoleBean.ADMIN || userBean.getRoleId() == RoleBean.FACULTY) {
					%>

					<td><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_NEW%>"></td>

					<td><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_DELETE%>"></td>
					<%
						}
					%>

					<td align="right"><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_NEXT%>"
						<%=((list.size() < pageSize) || model.nextPk() - 1 == bean.getId()) ? "disabled" : ""%>></td>
				</tr>
			</table>
			<%
				}
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
	</div>
</body>
</html>
<%@include file="Footer.jsp"%>