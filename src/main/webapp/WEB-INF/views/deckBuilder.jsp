<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Deck Builder</title>  
	
	<!-- Include Header -->
    <%@ include file="header/header.jsp" %>
  
  	<!-- Include Banniere Information div -->
    <%@ include file="banniere/ban.jsp" %>
  
    <!-- Include menu -->
    <%@ include file="navigation/nav.jsp" %>
    
    <!-- Include deck builder form -->
    <%@ include file="decks/addDeck.jsp" %> 
      
    <!-- Include List Cards -->
    <%@ include file="cards/list.jsp" %>
    
    <!-- Include Footer -->      
    <%@ include file="footer/footer.jsp" %>
</html>