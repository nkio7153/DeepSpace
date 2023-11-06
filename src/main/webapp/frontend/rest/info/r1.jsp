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
	營業時間：
	周日~周四 - 11:00-22:00/最後入場：20:00
	周五、周六 - 11:00-22:30/最後入場：20:30
	門市地址：桃園市中壢區中園路二段501號B1
	電話：+886 3-468-0161
	消費方案：https://www.facebook.com/newmalahotpot/menu
	
	注意事項：
	*歡迎自帶飲料、酒類，免收開瓶費
	*用餐時間2小時(自第一人進場起計時)
	*須同桌同消費方式，10％清潔費另計
	*限2成人開爐，1成人開爐酌加收100元
	*浪費食材每人加收300元食材費
	*超過13人以上預約，請來電洽詢門市
	*小一 - 小六 半價 (基於安全考量，限1大配1小)
	*小童 4 - 6 歲 $100 (基於安全考量，限1大配1小)
	*幼童 0 - 3 歲 免費 (基於安全考量，限1大配1小)
	*提供現金及刷卡服務
	*附設電梯
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

