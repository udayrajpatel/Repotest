<%@page import="in.co.rays.controller.ORSView"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.util.DataValidator"%>
<%@page import="in.co.rays.controller.ORSView"%>

<style type="text/css">

div.sticky {
	position: -webkit-sticky;
	position: sticky;
	width: 100%;
	text-align: center;
	bottom: 0;
	top: 100%;
}
</style>
<div class="sticky" style="background-color: #e8e6ea;">
	
	<strong>&copy; Rays Technologies 2020</strong>
</div>
<!--   Core JS Files   -->
<script src="<%=ORSView.APP_CONTEXT%>/assets/js/core/jquery.min.js"
	type="text/javascript"></script>
<script src="<%=ORSView.APP_CONTEXT%>/assets/js/core/popper.min.js"
	type="text/javascript"></script>
<script
	src="<%=ORSView.APP_CONTEXT%>/assets/js/core/bootstrap-material-design.min.js"
	type="text/javascript"></script>
<script src="<%=ORSView.APP_CONTEXT%>/assets/js/plugins/moment.min.js"></script>
<!--	Plugin for the Datepicker, full documentation here: https://github.com/Eonasdan/bootstrap-datetimepicker -->
<script
	src="<%=ORSView.APP_CONTEXT%>/assets/js/plugins/bootstrap-datetimepicker.js"
	type="text/javascript"></script>
<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script
	src="<%=ORSView.APP_CONTEXT%>/assets/js/plugins/nouislider.min.js"
	type="text/javascript"></script>
<!--	Plugin for Sharrre btn -->
<script
	src="<%=ORSView.APP_CONTEXT%>/assets/js/plugins/jquery.sharrre.js"
	type="text/javascript"></script>
<!-- Control Center for Material Kit: parallax effects, scripts for the example pages etc -->
<script src="<%=ORSView.APP_CONTEXT%>/assets/js/material-kit.js?v=2.0.4"
	type="text/javascript"></script>
<script src="<%=ORSView.APP_CONTEXT%>/assets/js/login.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/assets/js/CheckBox.js">

<script>
	$(document).ready(function() {
		//init DateTimePickers
		materialKit.initFormExtendedDatetimepickers();
		
	});
	$('.datetimepicker').datetimepicker().val('');
</script>


<%
	if (request.getRequestURI().equals(ORSView.APP_CONTEXT + ORSView.USER_REGISTRATION_VIEW)
			|| request.getRequestURI().equals(ORSView.APP_CONTEXT + ORSView.USER_VIEW)|| request.getRequestURI().equals(ORSView.APP_CONTEXT + ORSView.MY_PROFILE_VIEW)) {
%>
<script>
	$('.datetimepicker').datetimepicker({
		format : 'MM/DD/YYYY',
		maxDate : moment().add(-18, 'years'),
	});
	$('.datetimepicker').datetimepicker().val('');
</script>
<%
	} else if (request.getRequestURI().equals(ORSView.APP_CONTEXT + ORSView.TIMETABLE_VIEW)) {
%>
<script>
	$('.datetimepicker').datetimepicker({
		format : 'MM/DD/YYYY',
		minDate : moment().add(1, 'days'),
		maxDate : moment().add(90, 'days'),
		daysOfWeekDisabled : [ 0, 7 ]
	
	});
	$('.datetimepicker').datetimepicker().val('');
</script>
<%
	} else {
%>
<script>
	$('.datetimepicker').datetimepicker({
		format : 'MM/DD/YYYY',
		maxDate : moment().add(90, 'days'),
		daysOfWeekDisabled : [ 0, 7 ]
	});
	$('.datetimepicker').datetimepicker().val('');
</script>
<%
	}
%>