<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
<meta charset="UTF-8">
<title>會員訊息通知</title>
<jsp:include page="/indexpage/head.jsp" />

<style>

#listview {
	padding-bottom: 30px;
}

.notification-list {
	max-width: 800px;
	margin: 0 auto;
}

.notification-item {
	border-left: 4px solid #63bfc9;
	margin-bottom: 10px;
	position: relative;
}

.notification-read {
	background-color: #f0f0f0;
}

.notification-title {
	font-weight: bold;
}

.notification-content {
	display: none;
	margin-top: 5px;
}

.notification-date {
	font-size: 0.8rem;
	color: #6c757d;
}
.notification-status {
	font-size: 0.8rem;
	font-weight: bold;
	color: #6c757d;
	position: absolute;
	bottom: 0;
	right: 0;
	margin-right: 15px;
}

h5{
	padding-bottom: 20px;
}
</style>
</head>
<body>

	<jsp:include page="/indexpage/header.jsp" />
	<jsp:include page="/indexpage/headpic.jsp" />

	<div class="container mt-3 mb-5">
		<div class="notification-list">
		<h5>會員訊息通知</h5>
			<c:forEach items="${notifications}" var="notification">
				<div id="listview"
					class="list-group-item list-group-item-action flex-column align-items-start notification-item ${notification.noteRead == 0 ? '' : 'notification-read'}"
					data-note-id="${notification.noteId}">
					<div class="d-flex w-100 justify-content-between">
						<h6 class="mb-1 notification-title">${notification.noteType}</h6>
						<small class="notification-date"> <fmt:formatDate
								value="${notification.noteCreated}" pattern="yyyy-MM-dd HH:mm" />
						</small>
					</div>
					<div class="notification-content">
						<p class="mb-1">${notification.noteContent}</p>
					</div>
					<small
						class="notification-status ${notification.noteRead == 0 ? 'notification-unread' : ''}">${notification.noteRead == 0 ? '未讀' : '已讀'}</small>
				</div>
			</c:forEach>
		</div>
	</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script>
	$('.notification-item').click(function(e) {
	e.stopPropagation();
	var $this = $(this);
	$this.find('.notification-content').slideToggle();
	var noteId = $this.data('note-id');
	var isRead = $this.hasClass('notification-read');

	if (!isRead) {
		$this.addClass('notification-read');
		$this.find('.notification-status').text('已讀'); // 更新未讀已讀
		$.post('${pageContext.request.contextPath}/notifications/toBeRead',
				{noteId : noteId}).done(function(response) {
		            if (response.status === 'success') {
		                console.log("狀態更新為已讀");
		                // 這裡觸發 WebSocket 消息發送
		                ws.send(JSON.stringify({action: "updateUnreadCount"}));
				}
		});
	}
});
</script>

<jsp:include page="/indexpage/footer.jsp" />

</body>
</html>
