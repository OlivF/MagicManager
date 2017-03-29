<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Card Manager</title>  
    
    <!-- Include Header -->
    <%@ include file="header/header.jsp" %>
    
    <!-- Include menu -->
    <%@ include file="navigation/nav.jsp" %>
    <div class="generic-container">
    	<div class="alert alert-info" role="alert"><h1>${deck.name}</h1> <deck-color color="'${deck.color}'" url="'<c:url value='/static/img/'/>'"></deck-color></div>
    </div>
    <list-cards deckid="${deck.id}" deckname="'${deck.name}'" url="'<c:url value='/static/img/'/>'"></list-cards>
    
    <!-- Include Footer -->      
    <%@ include file="footer/footer.jsp" %>
</html>