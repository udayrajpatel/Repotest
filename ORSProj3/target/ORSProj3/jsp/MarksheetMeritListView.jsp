<%@page import="java.text.DecimalFormat"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.controller.MarksheetMeritListCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.dto.MarksheetDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@include file="Header.jsp"%>

<html>

<head>

<title>Marksheet Merit List</title>

<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>

<body
	style="background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.jpg');">
	
	<div class="container-fluid" style="margin-top: 125px;">
	
	
		<jsp:useBean id="Dto" class="in.co.rays.dto.MarksheetDTO"
			scope="request"></jsp:useBean>
			
		<form action="<%=ORSView.USER_LIST_CTL%>" method="post">
			<div class="row d-flex justify-content-center">
				<div class="col-12 text-center">
					<h3>
						<strong style="color: #fff">Marksheet Merrit List</strong>
					</h3>
				</div>
				<%
				
					int pageNo = ServletUtility.getPageNo(request);
				
					int pageSize = ServletUtility.getPageSize(request);
					
					int index = ((pageNo - 1) * pageSize) + 1;

					@SuppressWarnings("unchecked")
				
					List<MarksheetDTO> list = (List<MarksheetDTO>) ServletUtility.getList(request);
					
					Iterator<MarksheetDTO> it = list.iterator();

					if (list.size() != 0) {
						
				%>

				<input type="hidden" name="pageNo" value="<%=pageNo%>"> 
				<input
					type="hidden" name="pageSize" value="<%=pageSize%>">
			
				<div class="col-12 d-flex justify-content-center">
				
					<a class="btn btn-primary  "  href="<%=ORSView.JASPER_CTL%>"
					
						target="blank"> Print</a>
						
				</div>

				<div class="col-12 tabel-responsive">
					<table class="table table-striped table-hover bg-light">
					
						<thead class="bg-primary text-white">
						
							<tr>
								<th scope="col">S.No.</th>
								<th scope="col">RollNo</th>
								<th scope="col">Name</th>
								<th scope="col">Physics</th>
								<th scope="col">Chemistry</th>
								<th scope="col">Maths</th>
								<th scope="col">Total</th>
								<th scope="col">Percentage (%)</th>
								<th scope="col">Edit</th>

							</tr>
						</thead>

						<%
							while (it.hasNext()) {

									Dto = it.next();

									int physics = Dto.getPhysics();
									int chemistry = Dto.getChemistry();
									int maths = Dto.getMaths();
									
									int total = physics + chemistry + maths;
									
									float percentage = (float) total / 3;
									percentage = Float.parseFloat(new DecimalFormat("##.##").format(percentage));
									
						%>
						<tbody>
							<tr>
								<td><%=index++%></td>
								<td><%=Dto.getRollNo()%></td>
								<td><%=Dto.getName()%></td>
								<td><%=Dto.getPhysics()%></td>
								<td><%=Dto.getChemistry()%></td>
								<td><%=Dto.getMaths()%></td>
								<td><%=total%></td>
								<td><%=percentage%> %</td>
								<td><a href="MarksheetCtl?id=<%=Dto.getId()%>"><i
										class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<%
						}
					%>


					<div class="row">

						<div class="col-md-7 ml-auto">

							<button class="btn btn-primary" type="submit" name="operation"
								value="<%=MarksheetMeritListCtl.OP_BACK%>">Back</button>

						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>