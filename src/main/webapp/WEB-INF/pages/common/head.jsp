<%@ page language="java" pageEncoding="ISO-8859-1" %>

<head>
	<title>Consulta ICT</title>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	
	<link href="resources/css/consultaict.css" rel="stylesheet"  type="text/css" />
	<!-- Bootstrap CSS Toolkit styles -->
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<!-- Generic page styles -->
	<link rel="stylesheet" href="resources/css/style.css">
	<!-- Bootstrap styles for responsive website layout, supporting different screen sizes -->
	<link rel="stylesheet" href="resources/css/bootstrap-responsive.min.css">
	<!-- blueimp Gallery styles -->
	<link rel="stylesheet" href="resources/css/blueimp-gallery.min.css">
	<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
	<link rel="stylesheet" href="resources/css/jquery.fileupload-ui.css">
	<!-- CSS adjustments for browsers with JavaScript disabled -->
	<noscript><link rel="stylesheet" href="resources/css/jquery.fileupload-ui-noscript.css"></noscript>
	<!-- Bootstrap CSS fixes for IE6 -->
	<!--[if lt IE 7]>
	<link rel="stylesheet" href="resources/css/bootstrap-ie6.min.css">
	<![endif]-->	

	<script src="resources/js/consultaict.js"></script>

	<script src="resources/js/jquery-1.9.1.min.js"></script>
	<script src="resources/js/jquery-ui-1.10.3.custom.min.js"></script>

	<script src="resources/js/geoxml3.js"></script>
	<script src="resources/js/ProjectedOverlay.js"></script>


	<script src="resources/js/jquery.iframe-transport.js"></script>
	<script src="resources/js/jquery.fileupload.js"></script>
	
	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
	
	
	<script type="text/javascript">
	$(function() {
		
		$( "#dialog" ).dialog({autoOpen: false});
		initMap();
		
	});
	</script>
</head>