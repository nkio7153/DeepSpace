
          
          $(document).ready(function () {
        	  //獲取alldays的文字資料
          	let alldaysText = $("#tripDuration").text();
        		
        	// 使用政則表達式提取數字部分\d來匹配數字
    		let alldaysMatch = alldaysText.match(/\d+/);
        		if (alldaysMatch) {
        	        // 如果找到匹配的數字部分，就轉型變成數字
        	        let alldays = parseInt(alldaysMatch[0]);
        	        console.log("alldays=" + alldays)
        	      
	        	for(var i=1;i<=alldays;i++) {
	        		var newTourDay = document.createElement("div");
	    			newTourDay.classList.add("container");
	    			newTourDay.innerHTML = `
	    	   			 	<div class="row">
	    	   			 		<br><br>
	    		   			  		<span class="oneDay">第 ${i} 天</span>
	    							<input type="hidden" name="days" value=${i}>
	    		   			  	<div class="col-md-12 d-flex justify-content-center">
	    		   			    	<label for="attractionTime">時間:</label>
	    		   			    	<input type="time" name="attractionTime" style="margin-right: 10px;" required>
	    		   			    	<label style="margin-left: 10px;">你要去哪兒?</label>
								        <select name="attractions" id="attractions"  style="margin-left: 10px; padding: 5px; border: 1px solid #ccc; border-radius: 5px; background-color: #f5f5f5; font-family: Arial; font-size: 14px;">
								            <c:forEach var="attraction" items="${attrvo}">
								                <option value="${attraction.attractionsId}">${attraction.attractionsName}</option>
								            </c:forEach>
								        </select>
	    	   			   			<br>
	    	   			   		</div>
	    	   			  	</div>
	    	   			
	    	   			 <div class="container offset-5" name="afterSelector">
	    	   			 	<div class="col-md-5 mt-2"></div>
	    	   			   		<div class="col-md-4 d-flex align-items-center">
	    	   			    	<!-- 使用 d-flex 和 align-items-center 使內容垂直居中 -->
	    	   			    	<label for="tourAttr" class="form-label mt-3">新增景點</label>
	    	   			    		<div class="circle d-flex align-items-center justify-content-center ml-2 mt-2" name="circle">
	    	   			     		<span class="plus" >+</span>
	    	   			    		</div>
	    	   			   		</div>
	    	   			   			<div class="col-md-3 mt-2">
	    	   			   		</div>
	    	   				 </div>
	    	   			</div>`;
    			tourdays.appendChild(newTourDay);
        	}
        		}
          })
          
          //輸入景點的焦點文字框
			function clearPlaceholder(input) {
				if (input.value === "輸入景點") {
					input.value = "";
				}
			}
			
			function restorePlaceholder(input) {
				if (input.value === "") {
					input.value = "輸入景點";
				}
			}
          
          //增加行程選項(圓形)
		let circle = $("[name='circle']");
		let circle2 = $("#circle2");
		let select_html = `
		        	<div class="row">
						<div class="col-md-12 justify-content-center">
					 		
					 		<div class="new">
					 		<label for="attractionTime">時間:</label>
					 		<input type="time" name="attractionTime" id="newTime" style="margin-right: 10px;" required>
							<input type="text" class="tourInput" id="newAttraction" name="attraction" required placeholder="輸入景點" 
					  			onfocus="clearPlaceholder(this)" onblur="restorePlaceholder(this)">
							</div>
						</div>
						<div class="circle2 d-flex align-items-center justify-content-center ms-auto ml-2" id="dash">
							<span class="dash ">-</span>
				    	</div>
				    </div>`;
		
		$('body').on('mouseover', '[name="circle"]', function() {
			console.log("mouseover觸發了");
			$(this).css({
				"background-color": "blue",
				"width": "25px",
				"height": "25px"
			});
		});
		
		$('body').on('mouseout', '[name="circle"]', function() {
			$(this).css({
				"background-color": "lightskyblue",
				"width": "21px",
				"height": "21px"
			});
		});
		
		$('body').on('click', '[name="circle"]', function() {
			console.log("click事件觸發了")
			$(this).closest("[name='afterSelector']").before(select_html);
		
			//		    $(this).closest(".row").find("[name='afterSelector']").before(select_html);
		});
		
		
		
		// 圓形-
		// 委派事件到動態生成的 .circle2 元素
		$('body').on('mouseover', '.circle2', function() {
			$(this).css({
				"background-color": "red",
				"width": "25px",
				"height": "25px"
			});
		});
		
		$('body').on('mouseout', '.circle2', function() {
			$(this).css({
				"background-color": "lightcoral",
				"width": "21px",
				"height": "21px"
			});
		});
		
		$('body').on('click', '.circle2', function() {
			$(this).closest(".row").remove();
		});
		
		$("#city").on("change" , function(){
//			先抓取該鎮縣市的景點
		})
		
		
		
//		change需測試
		$(document).on("change" , "select.attraction" , function(){
//			
		})
		
		
		