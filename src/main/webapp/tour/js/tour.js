
	// 函数用于更新時間选择框
	function updateAttractionTime(input) {
	    var attractionTimeInput = document.querySelector("input[name='attractionTime']");
	    var selectedTime = input.value;
	    attractionTimeInput.value = selectedTime;
	}

	// 在輸入景點的文字框之前的時間選擇框上添加事件監聽器
//	document.querySelector("input[name='attractionTime']").addEventListener("change", function () {
//	    updateAttractionTime(this);
//	});

	// 更新日期并計算天數
	function updateDurationSelect() {
	    var startDateInput = document.querySelector("input[name='startDate']");
	    var endDateInput = document.querySelector("input[name='endDate']");
	    var tripDuration = document.getElementById("tripDuration");
	    var selectedDays = document.getElementById("selectedDays");
	    var tourdays = document.getElementById("tourdays");

	    if (startDateInput.value && endDateInput.value) {
	        var startDate = new Date(startDateInput.value);
	        var endDate = new Date(endDateInput.value);

	        var timeDiff = Math.abs(endDate - startDate);
	        var daysDifference = Math.ceil(timeDiff / (1000 * 3600 * 24) + 1 );

	        tripDuration.innerText = "總天數: 共 " + daysDifference + " 天";

	       
	        
	        // 清除先前的 tourdays 内容
	        tourdays.innerHTML = "";
			//let count = 0;
	        // 創建与天数相对应的内容
	        for (var i = 1; i <= daysDifference; i++) {
	        	//count++;
	            var newTourDay = document.createElement("div");
	            newTourDay.classList.add("container");
				
	            newTourDay.innerHTML = `
	   			 	<!--  1031  -->
	   			 	<div class="row">
	   			 		<br><br>

		   			  		<span>第 ${i} 天</span>

		   			  	<div class="col-md-12 d-flex justify-content-center">
		   			    	<label for="attractionTime" >時間:</label>
		   			    	<input type="time" name="attractionTime" style="margin-right: 10px;" required>
		   			    	<input type="text" name="attraction" required placeholder="輸入景點" 
	   			     			onfocus="clearPlaceholder(this)" onblur="restorePlaceholder(this)">
	   			   			<br>
	   			   		</div>
	   			  	</div>

	   			
	   			<!-- 1031 -->
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
	    } else {
	        tripDuration.innerText = "總天數:";
//	        selectedDays.innerHTML = "";
	        tourdays.innerHTML = ""; // 清除 tourdays 内容
	    }
	}
	

	// 添加事件监听器以响应日期选择的变化
//	document.getElementById("selectedDays").addEventListener("change", function() {
//	    var selectedDay = parseInt(this.value);
//	    generateAttractionInputs(selectedDay);
	 // 重置景点输入框的值
//	    for (var i = 1; i <= selectedDay; i++) {
//	        var attractionInput = document.querySelector(`input[name='attraction']`);
//	        if (attractionInput) {
//	            attractionInput.value = ""; // 清空输入框的值
//	        }
//	    }

//	});

	// 添加日期選擇框的事件監聽器
	document.querySelector("input[name='startDate']").addEventListener("change", updateDurationSelect);
	document.querySelector("input[name='endDate']").addEventListener("change", updateDurationSelect);

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
    let circle=$("[name='circle']");
    let circle2=$("#circle2");
    let select_html=`

        	<div class="row">
				<div class="col-md-12 mt-2 d-flex justify-content-center">
			 		<label for="attractionTime">時間:</label>
			 		<div class="new">
			 		<input type="time" name="attractionTime" id="newTime" style="margin-right: 10px;" required>
					<input type="text" id="newAttraction" name="attraction2[]" required placeholder="輸入景點" 
			  			onfocus="clearPlaceholder(this)" onblur="restorePlaceholder(this)">
					</div>
				</div>
				<div class="circle2 d-flex align-items-center justify-content-center ms-auto ml-2" id="dash">
					<span class="dash ">-</span>
		    	</div>
		    </div>
`;
		 
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







//    $(document).ready(function(){
//		 $('body').on('mouseover', '[name="circle"]', function() {
//			 $(this).css({
//	            "background-color": "blue",
//	            "width":"25px",
//	            "height":"25px"
//	     });
//
//    });
//		 
////	    circle.on("mouseover", function () {
////	        $(this).css({
////	            "background-color": "blue",
////	            "width":"25px",
////	            "height":"25px"
////	        });
////	    })
//	    circle.on("mouseout", function () {
//	        $(this).css({
//	            "background-color": "lightskyblue",
//	            "width":"21px",
//	            "height":"21px"
//	        });
//	    })
//	   
//	    $(document).on("click",".circle",  function () {
//	    	$(this).closest(".row").find("[name='afterSelector']").before(select_html);
//    	});
//	    
//    });
    
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
		



