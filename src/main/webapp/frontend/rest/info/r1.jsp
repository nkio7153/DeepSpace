<%@page import="java.awt.PrintGraphics"%>
<%@ page import="com.depthspace.restaurant.model.restaurant.RestVO"%>
<%@ page import="com.depthspace.restaurant.service.RestService"%>
<%@ page import="com.depthspace.restaurant.service.RestServiecImpl"%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
	String restId = request.getParameter("restId");
	RestService restService = new RestServiecImpl();
	RestVO restList = restService.getRestByRestId(Integer.valueOf(restId));
	request.setAttribute("rest", restList);

%>

<h1>${ rest.restName }</h1>
