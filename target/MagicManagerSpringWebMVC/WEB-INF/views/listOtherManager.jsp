<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>List Other Manager</title>  
	
	<!-- Include Header -->
    <%@ include file="header/header.jsp" %>
  
  	<!-- Include Banniere Information div -->
    <%@ include file="banniere/ban.jsp" %>
  
    <!-- Include menu -->
    <%@ include file="navigation/nav.jsp" %>
    
    <div class="generic-container">
	    <div class="row">
		  	<div class="col-md-8">
		  		<%@ include file="lists/listEdition.jsp" %>
		  	</div>
		  	<div class="col-md-4">
			  	<div class="col-md-12">
			  		<%@ include file="lists/listType.jsp" %>
			  	</div>
			  	<div class="col-md-12">
			  		<%@ include file="lists/listRarity.jsp" %>
			 	</div>
		 	</div>
		</div>
	</div>
    
    <!-- Include Popin Form -->
	<%@ include file="forms/forms.jsp" %>
    
    <!-- Include Footer -->      
    <%@ include file="footer/footer.jsp" %>
</html>