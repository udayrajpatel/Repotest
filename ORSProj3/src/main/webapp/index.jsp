<%@page import="in.co.rays.controller.ORSView" %>
<%@page import="in.co.rays.dto.UserDTO" %>
<%@page import="in.co.rays.util.ServletUtility" %>
<html>
<head>
<title>ORS</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
	
</head>
<body>
<%
UserDTO userDTO=(UserDTO) session.getAttribute("user");

boolean userLogedIn=userDTO !=null;

if(userLogedIn){
	ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);
}

%>
<div align="center">

<img src="<%=ORSView.APP_CONTEXT %>/img/logo.png" style="width:400px;" >

<div style="padding: 50px"> <a href="<%=ORSView.WELCOME_CTL%>">

<h1>

<font color="cornflowerblue">ONLINE RESULT SYSTEM</font>

</h1>
</a>

</div>
</body>
</html>
