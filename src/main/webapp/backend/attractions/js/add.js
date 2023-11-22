let path = window.document.location.pathname;
let project = path.substring( 0 , path.substring(1).indexOf("/")+1);
function previewImage(event) {
		var file = event.target.files[0];
		if (file) {
			var reader = new FileReader();
			reader.onload = function(e) {
				var imagesPreview = document
						.getElementById('imagePreview');

				// 創建一個div容器，包含圖片和刪除按鈕
				var container = document.createElement('div');
			

				var img = document.createElement('img');
				img.src = e.target.result;
				img.className = 'previewImg img-fluid';
				container.appendChild(img);

				var deleteButton = document.createElement('div');
				deleteButton.innerText = '';
				deleteButton.className = 'deleteIcon';
				deleteButton.onclick = function() {
					imagesPreview.removeChild(container);
				};
				container.appendChild(deleteButton);

				imagesPreview.appendChild(container);
			}
			reader.readAsDataURL(file);
		}
	}
	
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