<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

			<!-- 右側內容 -->
				<div class="d-flex justify-content-between align-items-center mb-3">
					<div >
					<c:choose>
						<c:when test="${not empty searchCount}">
							<h3 class="mb-0">搜尋結果 ${searchCount} 項票券</h3>
						</c:when>
						<c:otherwise>
							<h3 class="mb-0">共有 ${totalTickets} 項票券體驗</h3>
						</c:otherwise>
					</c:choose>
					</div>
				<!-- 排序 -->				    
				<label for="sortOption"></label>
				<div class="form-group">
				    <select class="form-control" id="sortOption" onchange="updateTicketList(1)">
				 <option value="ticketId_asc" ${param.sortField == 'ticketId' && param.sortOrder == 'asc' ? 'selected' : ''}>依上市日(新→舊)</option>
				<option value="ticketId_desc" ${param.sortField == 'ticketId' && param.sortOrder == 'desc' ? 'selected' : ''}>依上市日(舊→新)</option>
				    </select>
				</div>
				</div>
				<!-- 票券列表 -->
				<div class="ticket-lists" id="ticketright">
					<c:forEach items="${resultSet}" var="ticket">
						<a
							href="${pageContext.request.contextPath}/ticketproduct/item?ticketId=${ticket.ticketId}"
							class="no-underline"> <!-- 整張卡片點擊 -->
							<div class="card mb-3 clickable-card">
								<div class="row no-gutters">
									<div class="col-md-4">
										<img
											src="<%=request.getContextPath()%>/ticketmainimage?ticketId=${ticket.ticketId}"
											alt="Main Ticket Image" class="ticket-img">
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-title">${ticket.ticketName}</h5>
											<p class="card-title">${ticket.ticketType.typeName}&ensp;|&ensp;
												${ticket.city.cityName}</p>
											<p class="card-title">
												<c:choose>
													<c:when test="${fn:length(ticket.description) > 30}">
								${fn:substring(ticket.description,0,30)}...
								</c:when>
													<c:otherwise>
								${ticket.description}
								</c:otherwise>
												</c:choose>
											</p>
											<p class="card-text">
											<c:choose>
											    <c:when test="${totalRatingCountMap[ticket.ticketId] == 0 || totalRatingCountMap[ticket.ticketId] == null}">
											        <p>暫無評價</p>
											    </c:when>
											    <c:otherwise>
											        <p class="card-text">
											            <small class="text-muted">
											                ${averageStarsMap[ticket.ticketId]}
											                <c:forEach begin="1" end="${averageStarsMap[ticket.ticketId]}" var="i">
											                    <i class="fas fa-star gold-star"></i>
											                </c:forEach>
											                <c:if test="${averageStarsMap[ticket.ticketId] % 1 != 0}">
											                    <i class="fas fa-star-half-alt gold-star"></i>
											                    <c:set var="emptyStarsStart" value="${Math.floor(averageStarsMap[ticket.ticketId]) + 2}" />
											                </c:if>
											                <c:if test="${averageStarsMap[ticket.ticketId] % 1 == 0}">
											                    <c:set var="emptyStarsStart" value="${averageStarsMap[ticket.ticketId] + 1}" />
											                </c:if>
											                <c:forEach begin="${emptyStarsStart}" end="5" var="j">
											                    <i class="far fa-star gold-star"></i>
											                </c:forEach>
											                (${totalRatingCountMap[ticket.ticketId]})
											            </small>
											        </p>
											    </c:otherwise>
											</c:choose>
											<p class="card-text">NT$ ${ticket.price}</p>
										</div>
									</div>
								</div>
							</div>
						</a>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
<!-- 分頁 -->
<!-- <ul class="pagination justify-content-center"> -->
<%--     <c:forEach var="i" begin="1" end="${ticketPageQty}" step="1"> --%>
<%--         <li class="page-item ${i == currentPage ? 'active' : ''}"> --%>
<%--             <a class="page-link" href="#" onclick="updateTicketList(${i});">${i}</a> --%>
<!--         </li> -->
<%--     </c:forEach> --%>
<!-- </ul> -->