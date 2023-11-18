<%@	page import="java.awt.PrintGraphics"%>
<%@ page import="com.depthspace.restaurant.model.restaurant.RestVO"%>
<%@ page import="com.depthspace.restaurant.service.RestService"%>
<%@ page import="com.depthspace.restaurant.service.RestServiecImpl"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBOKKfj_MwehUJhm-t7jPbO1ydNODLgLOM&libraries=places&callback=initMap"></script>
	<style>
/* 		.carousel-inner { */
/* 		  height: 500px; */
/* 		} */
		.img-fluid {
		    max-width: 100%;
		    height: auto;
	    }
	</style>
</head>
<body class="border border-light">

	<h1 id="r_name">${ rest.restName }</h1>
	
	<div id="map" style="height: 300px;" class="w-100 mb-3"></div>
	
	<!-- https://bootstrap5.hexschool.com/docs/5.0/components/carousel/ -->
	<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img src="${pageContext.request.contextPath}/static/images/rest/r_${ rest.restId }.jpg" class="d-block img-fluid">
	    </div>
	  </div>
	  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Previous</span>
	  </button>
	  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Next</span>
	  </button>
	</div>
	
	
	
	
	<script>
		// https://developers.google.com/maps/documentation/javascript/places?hl=zh-tw#find_place_from_query
		// GoogleMap API 使用餐廳名稱當地點透過findPlaceFromQuery()抓出place_id與經緯度 再使用getDetails()抓出地點的詳細資料
		function initMap() {
		  	var infowindow = new google.maps.InfoWindow();
		  	
		  	// findPlaceFromQuery()參數 用餐廳名稱當地名取place_id與經緯度在geometry
		    var request = {
					query: "${ rest.restName }",
		     		fields: ['name', 'place_id', 'geometry', 'formatted_address']
		    	};
		    
		    var map = new google.maps.Map(document.getElementById('map'));
		    var service = new google.maps.places.PlacesService(map);
		    
		    service.findPlaceFromQuery(request, function(results, status) {
		      if (status === google.maps.places.PlacesServiceStatus.OK) {
		        console.log(results[0]);
		        
	         	// 用經緯度定位地圖位置
			    var mapOptions = {
			    	zoom: 16,
			        center: { lat: results[0].geometry.location.lat(), lng: results[0].geometry.location.lng() } 
			    };
	         	// 顯示地圖位置 
			    map = new google.maps.Map(document.getElementById('map'), mapOptions);
	        	
			    // 在地圖上標記地址 location包含經緯度
	            var marker = new google.maps.Marker({
	                map: map,
	                position: results[0].geometry.location,
	            });
		        
			    // 用place_id 查詢map詳細資料 fields取平均評分,照片,類型,營業時間
			    placeId = results[0].place_id;
	           	var req = {
		    		placeId: placeId,
		     		fields: ['name', 'rating', 'photo', 'type', 'opening_hours', 'adr_address','url']
				};
		        
		        service = new google.maps.places.PlacesService(map);
		        service.getDetails(req, callback);
		      }
		    });
		}
		
			function callback(place, status) {
			if (status == google.maps.places.PlacesServiceStatus.OK) {
				console.log(place.url);
	
				// 評分
				$("#r_name").append("<h5 id='rating'>"+place.rating+"</h5>")
				
				$('#rating').append("<h5 id='address'>"+place.adr_address+"</h5>")
				
				// 營業時間
				var openTime = place.opening_hours.weekday_text;
				if (openTime != null){
					var ul = $("<ul id='openTime'></ul>");
					ul.append("<li>營業時間</li>");
					openTime.forEach(function(daytime, index){
						ul.append("<li>"+daytime+"</li>");
					})
					$('#address').append(ul);
					
				}
				// 圖片 photos為陣列
				var photos = place.photos;
				if (photos != null){
					photos.forEach(function(photo, index) {
// 						let photoUrl = photo.getUrl({maxWidth: 100, maxHeight: 100});
						let photoUrl = photo.getUrl();
						let img = 
							"<div class='carousel-item'>"+
						      "<img src="+photoUrl+" class='d-block img-fluid'>"+
						    "</div>"
						$(".carousel-inner").append(img);
					});
				}
			  
			}
		}
    
		        
	</script>
</body>
</html>

