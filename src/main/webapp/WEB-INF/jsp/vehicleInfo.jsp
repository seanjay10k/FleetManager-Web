<!DOCTYPE html>
<html>
  <head>
    <style>
#map {
        width: 100%;
        height: 800px;
     }
    </style>
  </head>
  <body>
	<h1>Vehicle :  ${model.vehicle.name}</h1>
	
	<p> ${model.position.timestamp}</p>


    <div id="map"></div>
    <script>
    
      function initMap() {
        var myLatLng = {lat: ${model.position.lat}, lng: ${model.position.longitude}};
        var map = new google.maps.Map(document.getElementById('map'), {           
            zoom: 13,
            center: myLatLng
        });
        
          var marker = new google.maps.Marker({
    		position: myLatLng,
    		map: map,
    		title: '${model.position.timestamp}'
  		  });               
      }
      
   
    </script>
    <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8oKvmEUDJ5SBLMaDjIXzf-5LaMSLiYE8&callback=initMap">
    </script>
  </body>
</html>