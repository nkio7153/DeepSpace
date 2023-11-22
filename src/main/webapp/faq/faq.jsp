<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, com.depthspace.faq.service.FaqService, com.depthspace.faq.model.FaqVO"%>

<%
FaqService faqService = new FaqService();
List<String> qTypes = faqService.getAllQTypes(); // 獲取所有問題類型
List<FaqVO> faqs = faqService.getFaqsCompleteInfo(); // 獲取所有問題及其詳細資訊
request.setAttribute("qTypes", qTypes);
request.setAttribute("faqs", faqs);
%>

<!DOCTYPE html>
<html>
<head>
    <title>常見問題列表</title>
    <jsp:include page="/indexpage/head.jsp" />
    <!-- 引入jQuery -->
<!--     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
    <style>
        h2 {
            color: #333; /* 標題顏色 */
        }

        .category {
            cursor: pointer;
            padding: 10px;
            margin: 5px;
            background-color: #007bff; /* 更新類別顏色 */
            color: white;
            display: inline-block;
            border-radius: 5px; /* 圓角邊框 */
            transition: background-color 0.3s; /* 過渡效果 */
        }
        .category:hover {
            background-color: #0056b3; /* 懸停顏色變化 */
        }
        .faq-item {
            display: none;
            margin-top: 10px;
            border: 1px solid #ddd; /* 添加邊框 */
            padding: 15px;
            border-radius: 5px; /* 圓角邊框 */
            background-color: white; /* 背景色 */
            box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* 陰影效果 */
        }
        .faq-question {
            cursor: pointer;
            color: #007bff; /* 問題顏色 */
            margin: 0;
        }
        .faq-answer {
            display: none;
            color: #555; /* 答案顏色 */
            padding-top: 10px;
        }
    </style>
    <script>
        $(document).ready(function(){
            $('.category').click(function(){
                var category = $(this).data('category');
                $('.faq-item').slideUp(); // 使用滑動效果隱藏
                $('.faq-answer').slideUp(); // 使用滑動效果隱藏答案
                $('.faq-item[data-category="' + category + '"]').delay(400).slideDown(); // 延遲後滑動顯示
            });

            // 問題點擊事件
            $(document).on('click', '.faq-question', function(){
                $(this).next('.faq-answer').slideToggle(); // 使用滑動切換效果
            });
        });
    </script>
</head>
<body>

	<jsp:include page="/indexpage/header.jsp" />
	<jsp:include page="/indexpage/headpic.jsp" />

    <h2>常見問題類型</h2>
    <div id="faqCategories">
        <c:forEach items="${qTypes}" var="type">
            <div class="category" data-category="${type}">${type}</div>
        </c:forEach>
    </div>

    <h2>常見問題列表</h2>
    <div id="faqQuestions">
        <c:forEach items="${faqs}" var="faq">
            <div class="faq-item" data-category="${faq.faqType.QTypes}">
                <!-- 問題名稱，點擊後顯示答案 -->
                <p class="faq-question"><strong>問題名稱:</strong> ${faq.faqName}</p>
                <!-- 問題答案，預設隱藏 -->
                <p class="faq-answer"><strong>問題答案:</strong> ${faq.faqAns}</p>
            </div>
        </c:forEach>
    </div>
    
    <jsp:include page="/indexpage/footer.jsp" />
    
</body>
</html>
