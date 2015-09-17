<!DOCTYPE html>
<html><head>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
  	<meta http-equiv="refresh" content="1150" />
		
     	<%--  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.2.js"> </script> --%>
     		  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
     	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
		 <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
		<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js//jquery-ui.min.js"></script>
      --%>
     	  
     	   <script type="text/javascript" src="<%=request.getContextPath()%>/js/BannerWidth.js"></script>
     	 <!-- css files -->
		 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/it_mssp.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ui.all.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/loginstyle.css" />
         <link rel="Stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/carousel.css">
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
        <script type="text/javascript">
        function DisableBackButton() {
        	window.history.forward();
        	}
        	DisableBackButton();
        	window.onload = DisableBackButton;
        	window.onpageshow = function(evt) { if (evt.persisted) DisableBackButton(); };
        	window.onunload = function() { void (0);};
        	</script>
        
    </head>
    <body>
    <center>
   		<div class="mainBodyDivwithoutLine">
	    	<tiles:insertAttribute name="header" />
	    	<tiles:insertAttribute name="body"/>
	      	<tiles:insertAttribute name="footer" />
    	</div> 
    </center>
    </body>
  
</html>
