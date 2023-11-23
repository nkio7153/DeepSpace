let path = window.document.location.pathname;
let project = path.substring( 0 , path.substring(1).indexOf("/")+1);
 $("#city").change(function () {
            var cityId = $(this).val();
            console.log(cityId)
            
            let url = `${project}/attractionsEnd/getArea`;
		        fetch(url,{
					method : "POST",
					headers : {
						"Content-Type" : "application/x-www-form-urlencoded"
					},
					body : `city=${$(this).val()}`
				})
		                .then(function(response){
		                  return response.json();
		                })
		                .then(function(data){
							console.log(data);            	
		               
		               		let html = "";
		               		let arr= new Array();
		               		
		               		arr.push(`<option value="">請選區域</option>`);
		               		data.forEach(item=>{
							   arr.push(`<option value="${item.areaId}">${item.areaName}</option>`);
							});
							html = arr.join("");
							$("#area").html(html);
		               	
		                })
		                
		                .catch(function(error){
		                  console.log(error);
		                })
        });