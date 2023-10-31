$(function() {

	fetch('http://localhost:8080/DepthSpace/RestApi/getRestAll')
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(data => {
			console.log(data);
			data.forEach(function(rest){
				let html = `
					<td><img src="../../static/images/rest/r_${rest.restId}.jpg" onerror="this.src='../../static/images/rest/404.jpg'"></td>
					<td>${rest.restName}</td>
					<td>${rest.restType}</td>
					<td>${rest.restOpen}</td>
					`;
			let tr = document.createElement("tr");
			tr.innerHTML = html;
//			document.getElementById("Restdata").appendChild(tr);
			$("table#Restdata").append(tr);
			})
		})
		.catch(error => {
			console.error('There has been a problem with your fetch operation:', error);
		});






})

