<%@ page language="java" pageEncoding="ISO-8859-1" %>

<head>
	<title>Consulta ICT</title>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	
	<link href="resources/css/start/jquery-ui-1.10.3.custom.min.css" rel="stylesheet"  type="text/css" />	
	<link href="resources/css/consultaict.css" rel="stylesheet"  type="text/css" />		
	<script type="text/javascript" src="resources/js/consultaict.js"><!--  //--></script>
	<script type="text/javascript" src="resources/js/geoxml3.js"><!--  //--></script>
	<script type="text/javascript" src="resources/js/ProjectedOverlay.js"></script>
	<script type="text/javascript" src="resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
	<script type="text/javascript">
	
	$(function() {

		$( "#dialog" ).dialog({autoOpen: false});
		
		initMap();	
			

    });
	</script>
</head>