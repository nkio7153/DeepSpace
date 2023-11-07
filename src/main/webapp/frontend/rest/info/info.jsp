<%@page import="java.awt.PrintGraphics"%>
<%@ page import="com.depthspace.restaurant.model.restaurant.RestVO"%>
<%@ page import="com.depthspace.restaurant.service.RestService"%>
<%@ page import="com.depthspace.restaurant.service.RestServiecImpl"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String restId = request.getParameter("restId");
	RestService restService = new RestServiecImpl();
	RestVO restList = restService.getRestByRestId(Integer.valueOf(restId));
	request.setAttribute("rest", restList);
%>
<!DOCTYPE html>
<html>
<head>
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBOKKfj_MwehUJhm-t7jPbO1ydNODLgLOM&libraries=places&callback=initMap"></script>
</head>
<body class="border border-light">

	<h1 id="r_name">${ rest.restName }</h1>
	
	
	<div id="map" style="height: 300px; width: 300px;"></div>
	
	<script>
		// https://developers.google.com/maps/documentation/javascript/places?hl=zh-tw#find_place_from_query
		// GoogleMap API 使用餐廳名稱當地點透過findPlaceFromQuery()抓出place_id與經緯度 再使用getDetails()抓出地點的詳細資料
		function initMap() {
		  	var infowindow = new google.maps.InfoWindow();
		  	
		  	// findPlaceFromQuery()參數 用餐廳名稱當地名取place_id與經緯度在geometry
		    var request = {
					query: "${ rest.restName }",
		     		fields: ['name', 'place_id', 'geometry']
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
		     		fields: ['name', 'rating', 'photo', 'type', 'opening_hours']
				};
		        
		        service = new google.maps.places.PlacesService(map);
		        service.getDetails(req, callback);
		      }
		    });
		}
		
			function callback(place, status) {
			if (status == google.maps.places.PlacesServiceStatus.OK) {
	// 			console.log(place);
	
				// 評分
				$("#r_name").append("<h5 id='rating'>"+place.rating+"</h5>")
				
				// 營業時間
				var openTime = place.opening_hours.weekday_text;
				if (openTime != null){
					var ul = $("<ul id='openTime'></ul>");
					openTime.forEach(function(daytime, index){
						ul.append("<li>"+daytime+"</li>");
					})
					$('#rating').append(ul);
					
				}
				// 圖片 photos為陣列
				var photos = place.photos;
				if (photos != null){
					photos.forEach(function(photo, index) {
						let photoUrl = photo.getUrl({maxWidth: 100, maxHeight: 100});
						$("ul#openTime").append("<img src="+photoUrl+">");
					});
				}
			  
			}
		}
    
		        
	</script>
</body>
</html>

