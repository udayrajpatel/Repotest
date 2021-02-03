<%@page import="in.co.rays.controller.ForgetPasswordCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>

<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16"/>
</head>
<body
style="background-image: url('./img/bg1.jpg');height: 100vh;"
>
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="Dto" class="in.co.rays.dto.UserDTO"
			scope="request"></jsp:useBean>

		<div class="section section-signup page-header">
      <div class="container">
        <div class="row">
          <div class="col-lg-4 col-md-6 ml-auto mr-auto">
            <div class="card card-login">
              <form style="min-height: 300px;" class="form" method="<%=ORSView.FORGET_PASSWORD_CTL%>" action="post">
                <div class="card-header card-header-primary text-center">
                  <h4 class="card-title">Forgot Your Password</h4>
                 </div>
               <%if(ServletUtility.getSuccessMessage(request) != null && 
							ServletUtility.getSuccessMessage(request).length() > 0) {%>
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
							<%} %>
							<%if(ServletUtility.getErrorMessage(request) != null && 
							ServletUtility.getErrorMessage(request).length() > 0) {%>
							<div class="alert alert-danger"
							style="line-height: 3px; margin-left: 20px; margin-right: 20px;">
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
							<%} %>
							<input type="hidden" name="id" value="<%=Dto.getId()%>">
							 <div class="alert alert-info">
        <div class="container">
          <div class="alert-icon">
          </div>
          <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true"></span>
          </button>
          <b></b> Submit your email address and we'll send you password.
        </div>
      </div>
     
                <div class="card-body">
                  <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text">
                        <i class="material-icons">mail</i>
                      </span>
                    </div>
                    <input type="email" class="form-control" placeholder="Enter Your Email..."
                    value="<%=ServletUtility.getParameter("login", request)%>">
                  </div>
                  <p class="description text-right">
					<font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
				</p>
                </div>
                <div class="row" >
            <div class="col-md-10 ml-auto">
              <button class="btn btn-primary" name="operation" type="submit" value="<%=ForgetPasswordCtl.OP_GO%>">Go</button>
            </div>
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