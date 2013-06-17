
function loadKML(){
	
	var value = $("#province").val();

	if (value == 0){
		return;
	}
	
	if (value!=28){
		$( "#dialog" ).dialog( "open" );
		return;
	} 
	
	var latlng = new google.maps.LatLng(40.4036081564,-3.74110783551);
	
	var settings = {
		zoom: 10,
		center: latlng,
		mapTypeControl: true,
		mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
		navigationControl: true,
		navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
		mapTypeId: google.maps.MapTypeId.ROADMAP
		};
	
	var map = new google.maps.Map(document.getElementById("map_canvas"), settings);

	var myParser = new geoXML3.parser({map: map});
	myParser.parse('resources/documents/doc.kml');
	
}
		
	
	
	
	