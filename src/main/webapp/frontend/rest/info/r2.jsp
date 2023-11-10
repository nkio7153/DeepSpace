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
<!-- 	<pre style="font-size: 18px;"> -->
<!-- 	營業時間： -->
<!-- 	周日~周四 - 11:00-22:00/最後入場：20:00 -->
<!-- 	周五、周六 - 11:00-22:30/最後入場：20:30 -->
<!-- 	門市地址：桃園市中壢區中園路二段501號B1 -->
<!-- 	電話：+886 3-468-0161 -->
<!-- 	消費方案：https://www.facebook.com/newmalahotpot/menu -->
	
<!-- 	注意事項： -->
<!-- 	*歡迎自帶飲料、酒類，免收開瓶費 -->
<!-- 	*用餐時間2小時(自第一人進場起計時) -->
<!-- 	*須同桌同消費方式，10％清潔費另計 -->
<!-- 	*限2成人開爐，1成人開爐酌加收100元 -->
<!-- 	*浪費食材每人加收300元食材費 -->
<!-- 	*超過13人以上預約，請來電洽詢門市 -->
<!-- 	*小一 - 小六 半價 (基於安全考量，限1大配1小) -->
<!-- 	*小童 4 - 6 歲 $100 (基於安全考量，限1大配1小) -->
<!-- 	*幼童 0 - 3 歲 免費 (基於安全考量，限1大配1小) -->
<!-- 	*提供現金及刷卡服務 -->
<!-- 	*附設電梯 -->
<!-- 	</pre> -->
	<div id="map" style="height: 300px; width: 300px;"></div>
	
	
	
	<script>
	
		function initMap() {
		  	var infowindow = new google.maps.InfoWindow();
			let restname = "${ rest.restName }";
		    var request = {
					query: restname,
		     		fields: ['name', 'place_id', 'geometry']
		    	};
		    
		    var map = new google.maps.Map(document.getElementById('map'));
		    var service = new google.maps.places.PlacesService(map);
		
		    service.findPlaceFromQuery(request, function(results, status) {
		      if (status === google.maps.places.PlacesServiceStatus.OK) {
		        console.log(results[0]);
		        
	         	// 顯示地圖位置
			    var mapOptions = {
			    	zoom: 16,
			        center: { lat: results[0].geometry.location.lat(), lng: results[0].geometry.location.lng() } 
			    };
			    map = new google.maps.Map(document.getElementById('map'), mapOptions);
	        	
			    // 在地圖上標記地址
	            var marker = new google.maps.Marker({
	                map: map,
	                position: results[0].geometry.location,
	            });
		        
			    // 用place_id 查詢map詳細資料
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
					var ul = $("<ul></ul>");
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
						$("ul").append("<img src="+photoUrl+">");
					});
				}
			  
			}
		}
    
		        
	</script>
</body>
</html>

