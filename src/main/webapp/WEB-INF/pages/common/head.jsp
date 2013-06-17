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

	
	function initialize() {
		var latlng = new google.maps.LatLng(40.4036081564,-3.74110783551);
		
		var settings = {
			zoom: 5,
			center: latlng,
			mapTypeControl: true,
			mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
			navigationControl: true,
			navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
			mapTypeId: google.maps.MapTypeId.ROADMAP
			};
		
			var map = new google.maps.Map(document.getElementById("map_canvas"), settings);

			$( "#dialog" ).dialog({autoOpen: false});
    }
	</script>
</head>