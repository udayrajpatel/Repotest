<%-- <%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.dto.FacultyDTO"%>
<%@page import="in.co.rays.controller.FacultyListCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Faculty List</title>
<!-- Select All Library -->
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>	

<!-- font-awesome library -->
<style type="text/css">
@import url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css);
@import url(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.3/css/mdb.min.css);

.hm-gradient {
background-image: url("<%=ORSView.APP_CONTEXT%>/img/white.png");}
.darken-grey-text {
    color: #2E2E2E;
}
.danger-text {
    color: #ff3547; }
.default-text {
    color: #2BBBAD; 
}
.info-text {
    color: #33b5e5; 
}
.paddingclass{
padding-top: 10px;
}

</style>


<style type="text/css">
.setForm{
padding-top: 5%;
padding-left: 25%;
width: 130%
}
.button{
border-radius:5px;width:100px; padding:5px;color:white;font-size:20px;background-color:#00cc88
}
.fonta{
font-size: 20px;
}
</style>
</head>
<body class="hm-gradient">
<jsp:useBean id="dto" scope="request" class="in.co.rays.dto.FacultyDTO" />
<div>
<%@include file="Header.jsp" %>
</div>
<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post">
<div class="container-fluid">
	<% List list = ServletUtility.getList(request);
       List collegeList=(List)request.getAttribute("collegeList");
       List courseList=(List)request.getAttribute("courseList");
	
	System.out.println(list);
        if (list.size()==0){ %>
			<div class="text-center">
			<td><input class="button" type="submit"  name="operation" value="<%=FacultyListCtl.OP_BACK%>"></td>
			</div>
			<%}else{ %>
	 <h3 class="text-center default-text py-3"> Faculty List:</h3>
	  <br>
	  <div class="row">
	  <div class="col-sm-1"></div>
	  <div class="col-sm-2 fonta default-text"><input placeholder="First Name" type="text" id="defaultForm-email"  name="firstName" class="border"  value="<%=ServletUtility.getParameter("firstName", request)%>"></div>
	  <div class="col-sm-2 fonta default-text"><input placeholder="Email" type="text" id="defaultForm-email" name="email" class="border"  value="<%=ServletUtility.getParameter("email", request)%>"></div>
	  <div class="col-sm-2"><%=HTMLUtility.getList("college",String.valueOf(dto.getCollegeId()),collegeList) %></div>
	   <div class="col-sm-2"><%=HTMLUtility.getList("course",String.valueOf(dto.getCollegeId()),courseList) %></div>
	  
	  <div class="col-sm-2">
	  <input class="button" type="submit"  name="operation" value="<%=FacultyListCtl.OP_SEARCH%>">
	  <input class="button" type="submit"  name="operation" value="<%=FacultyListCtl.OP_RESET%>">
	  </div> 
	  <div class="col-sm-1"></div>
	  </div>
	  
	  <br>
	  <br>
      
      <div class="table-responsive" >
      <table class="table table-striped" width="100%">
      <thead>
      <tr class="table-active">
      <th class="text-center"><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th>S.NO</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Email Id</th>
					<th>Qualification</th>
					<th>Mobile No.</th>
					<th>Joining Date</th>
					<th>College Name</th>
					<th>Course Name</th>
					<th>Edit</th>
      </tr>
      </thead>
      <%
	                int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;
                    int nextPageSize = DataUtility.getInt
                   		 (request.getAttribute("nextListSize").toString());
                   
                    Iterator<FacultyDTO> it = list.iterator();
                    while (it.hasNext()) {
                    	
                        dto = it.next();
               %>
               <tbody>
				<tr class="<%=((index)%2==0)?"table-info":"table-danger"%>">
					<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
					<td class=""><%=index++%></td>
					<td><%=dto.getFirstName()%></td>
					<td><%=dto.getLastName()%></td>
					<td><%=dto.getEmailId()%></td>
					<td><%=dto.getQualification()%></td>
					<td><%=dto.getMobileNo()%></td>
					<td><%=dto.getJoiningDob()%></td>
					<td><%=dto.getCollegeName()%></td>
					<td><%=dto.getCourseName()%></td>
					
					<td><a href="FacultyCtl?id=<%=dto.getId()%>">Edit</a></td>
				</tr>
				</tbody>
				<%
                    }
                %>
			</table>
			</div>
			
			
			<table width="100%">
			<tr>
			<td><input class="button" type="submit"  name="operation" value="<%=FacultyListCtl.OP_PREVIOUS%>"  <%=pageNo > 1 ? "" : "disabled"%>></td>
			<td><input class="button" type="submit"  name="operation" value="<%=FacultyListCtl.OP_NEW%>"></td>
			<td><input class="button" type="submit"  name="operation" value="<%=FacultyListCtl.OP_DELETE%>"></td>
			
			<td align="right"><input class="button" type="submit"  name="operation" value="<%=FacultyListCtl.OP_NEXT%>" <%=(nextPageSize != 0) ? "" : "disabled"%>  >
			</td></tr>
			
			
			</table>
			
			
			<%} %>
			
			</div>
</form>
</body>
<%@include file="Footer.jsp" %>
</html> --%>


<%@page import="in.co.rays.dto.FacultyDTO"%>
<%@page import="in.co.rays.controller.FacultyListCtl"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<style  >
.divider-text {
    position: relative;
    text-align: center;
    margin-top: 15px;
    margin-bottom: 15px;
}
.divider-text span {
    padding: 7px;
    font-size: 12px;
    position: relative;   
    z-index: 2;
}
.divider-text:after {
    content: "";
    position: absolute;
    width: 100%;
    border-bottom: 1px solid #ddd;
    top: 55%;
    left: 0;
    z-index: 1;
}

.btn-facebook {
    background-color: #405D9D;
    color: #fff;
}
.btn-twitter {
    background-color: #42AEEC;
    color: #fff;
}
</style>

</head>
<body background="/Project_3/jsp/p3.png">
<jsp:useBean id="dto" scope="request" class="in.co.rays.dto.FacultyDTO" />
<div>
<%@include file="Header.jsp" %>
</div>

<div class="container-fluid">


<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post">

   <%if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
    <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success!</strong><font color="green"> <%=ServletUtility.getSuccessMessage(request) %></font>
    </div>
    <%} %>                            
	
	<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
  <center><div class="alert alert-danger alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error!</strong><font color="red"><%=ServletUtility.getErrorMessage(request) %></font>
  </div></center>

<%} %>                            
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">



<% List list = ServletUtility.getList(request);
List collegeList=(List)request.getAttribute("collegeList");
List courseList=(List)request.getAttribute("courseList");

System.out.println(list);
 if (list.size()==0){ %>
	
        <div class="text-center">
			<td><input class="button" type="submit"  name="operation" value="<%=FacultyListCtl.OP_BACK%>"></td>
			</div>
			<%}else{ %>
	 <h3 class="text-center default-text text-dark py-3"> Faculty List:</h3>
	 
	  <br>
	 
	  <div class="row">
	  <div class="col-sm-2"></div>
	  <label class="form-check-label" for="check2" style = "width:50px">
     Name:
      </label>
	  <div class="col-sm-2"><input placeholder="First Name" type="text" id="defaultForm-email"  name="firstName" class="border"  value="<%=ServletUtility.getParameter("firstName", request)%>">
</div>

<label class="form-check-label" for="check2" style = "width:50px">
      EmailID:
      </label>
	  <div class="col-sm-2"> <input placeholder="Email" type="text" id="defaultForm-email" name="email" class="border"  value="<%=ServletUtility.getParameter("email", request)%>">
</div>
	 <label class="form-check-label" for="check2" style = "width:50px">
       Course:
      </label>
	  <div class="col-sm-2"> <%=HTMLUtility.getList("course",String.valueOf(dto.getCourseId()),courseList) %></div>
	 
	   
	 
	  <div class="col-sm-2">
	  <input   class="bg-success text-white"style="font-size: 17px" type="submit"  name="operation" value="<%=FacultyListCtl.OP_SEARCH%>">
	  <input  class="bg-warning text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=FacultyListCtl.OP_RESET%>">
	  </div> 
	  <div class="col-sm-2"></div>
	  </div>
	  
	  <br>
	  <br>
	  
	  
      <div class="table-responsive " >
      <table class="table table-striped" width="100%">
      <thead class="thead-dark">
      <tr >
      <th class="text-center"><input type="checkbox" id="select_all" name="select">Select All</th>
                   <th>S.NO</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Email Id</th>
					<th>Qualification</th>
					<th>Mobile No.</th>
					<th>Joining Date</th>
					<th>College Name</th>
					<th>Course Name</th>
					<th>Edit</th>
      </tr>
      </thead>
     <%
	                int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;
                    int nextPageSize = DataUtility.getInt
                   		 (request.getAttribute("nextListSize").toString());
                   
                    Iterator<FacultyDTO> it = list.iterator();
                    while (it.hasNext()) {
                    	
                        dto = it.next();
               %>
               <tbody>
				<tr   class="<%=((index)%2==0)?"table-info":"table-danger"%>">
					<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
					<td class=""><%=index++%></td>
					<td><%=dto.getFirstName()%></td>
					<td><%=dto.getLastName()%></td>
					<td><%=dto.getEmailId()%></td>
					<td><%=dto.getQualification()%></td>
					<td><%=dto.getMobileNo()%></td>
					<td><%=DataUtility.getDateString(dto.getJoiningDate())%></td>
					<td><%=dto.getCollegeName()%></td>
					<td><%=dto.getCourseName()%></td>
					
					<td><a class="text-dark" href="FacultyCtl?id=<%=dto.getId()%>">Edit</a></td>
				</tr>
				</tbody>
				<%
                    }
                %>
			</table>
			
			</div>
			<div class="table-responsive " >
			<table width="100%">
			<tr>
			<td><input  class="bg-primary text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=FacultyListCtl.OP_PREVIOUS%>"  <%=pageNo > 1 ? "" : "disabled"%>></td>
			<td><input  class="bg-success text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=FacultyListCtl.OP_NEW%>"></td>
			<td><input  class="bg-danger text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=FacultyListCtl.OP_DELETE%>"></td>
			
			<td align="right"><input   class="bg-primary text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=FacultyListCtl.OP_NEXT%>" <%=(nextPageSize != 0) ? "" : "disabled"%>  >
			</td>
			
			</tr>
						
			</table>
			</div>
					
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> 
			<input type="hidden" name="pageSize" value="<%=pageSize%>">
						
			<%} %>

 <br><br>     <br><br> 
</form>
</div>
</body>
<%@include file="Footer.jsp" %>
</html>
