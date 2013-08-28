

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


function loadKML(){
	
	var map = initMap();
	var myParser = new geoXML3.parser({map: map, singleInfoWindow:true});
	var kmlUrl='resources/documents/test.kml';
	
	var value =  $('#combobox').val();
	
	if (value !=0){
		//se hace una llamada sincrona 
		//para que actualice el documento a leer.
		var result = $.ajax({
			  type: "GET",
			  dataType: "json",
			  url: "getProvince.do",
			  data: {province:value},
			  async: false,
			  success: function(string){
				  return string;
			  }
			});
		
		if (result.responseText=="ict"){
			myParser.parse(kmlUrl);
		}
	}

}