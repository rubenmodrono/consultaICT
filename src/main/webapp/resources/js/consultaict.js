

function initMap(){
	var latlng = new google.maps.LatLng(40.4036081564,-3.74110783551);
	
	var settings = {
		zoom: 6,
		center: latlng,
		mapTypeControl: true,
		mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
		navigationControl: true,
		navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
		mapTypeId: google.maps.MapTypeId.ROADMAP
		};
	
		return new google.maps.Map(document.getElementById("map_canvas"), settings);
}


  var infowindow;

 




function loadKML(){
	
	var value =  $('#combobox').val();
	
	$.getJSON("getProvince.do", { province: value}, function() {
    });

	if (value == 0){
		initMap();
		return;
	}
	
	if (value!=28 && value!=33 && value!=06){
		$( "#dialog" ).dialog( "open" );
		initMap();
		return;
	} 
		
	var map = initMap();

	var myParser = new geoXML3.parser({map: map, singleInfoWindow:true});
	
	var kmlUrl='';
	
	if (value ==28){
		kmlUrl ='resources/documents/madrid.kml';	
	} else if (value == 33){
		kmlUrl='resources/documents/asturias.kml';
	} else if (value == 06){
		kmlUrl='resources/documents/badajoz.kml';
	}

	myParser.parse(kmlUrl);
}