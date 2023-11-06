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
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBOKKfj_MwehUJhm-t7jPbO1ydNODLgLOM&callback=initMap"></script>
</head>
<body class="border border-light">
	<h1>${ rest.restName }</h1>
	<pre style="font-size: 18px;">
	
	✔用餐時間為120分鐘，餐廳保留座位十分鐘，逾時餐位將自動取消。

	✔網路訂位恕不指定座位。
	
	✔訂位系統目前不開放當天與30天後訂位，如需訂其他時段，歡迎電話洽詢。
	
	✔由於桌型限制，網路訂位可接受2-8位訂位，1人或9人以上的餐位，請於營業時間致電，將由訂餐專員為您安排。
	
	✔本店線上與電話訂位系統分開，若線上訂餐已無座位，也歡迎您致電詢問。
	
	✔如需臨時更改人數，請提早撥打專線由專人確認位子是否可以調整。
	</pre>
	<div id="map" style="height: 300px; width: 300px;"></div>
	
	
	
	<script>
	
		function initMap() {
	    	var geocoder = new google.maps.Geocoder();
	        var address = "${ rest.restAddress }";
	        geocoder.geocode({ 'address': address }, function (results, status) {
	            if (status === 'OK') {
	            	// 取出地址經緯度
	                var addresslat = results[0].geometry.location.lat();
	                var addresslng = results[0].geometry.location.lng();
	                
	             	// 顯示地圖位置
			        var mapOptions = {
			            zoom: 16,
			            center: { lat: addresslat, lng: addresslng } 
			        };
			        var map = new google.maps.Map(document.getElementById('map'), mapOptions);
	            	
			        // 在地圖上標記地址
	                var marker = new google.maps.Marker({
	                    map: map,
	                    position: results[0].geometry.location,
	                });
	                var placeId = results[0].place_id;
	                console.log(placeId);
	                
	                
	            } else {
	                return "error";
	            }
	        });
	        
	    }
		
// 		fetch("http://maps.googleapis.com/maps/api/place/details/json?place_id=ChIJPxnPnrCrQjQRmEwUNrIViu0&language=zh-TW&key=AIzaSyBOKKfj_MwehUJhm-t7jPbO1ydNODLgLOM")
// 	        .then(response => response.json())
// 	        .then(data => {
// 	            console.log(data.results[0].current_opening_hours.weekday_text);
// 	        })
// 	        .catch(error => console.error('Place Details request failed: ', error));
    
		        
	</script>
</body>
</html>

