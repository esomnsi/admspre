
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
  <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=9" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" >
        <!--  java scripts -->
     	
     	 <script type="text/javascript" src="../js/jquery-1.8.2.js"> </script>
     	 <script type="text/javascript" src="../js/jquery.collapsiblepanel.js"> </script>
     	 <script type="text/javascript" src="../js/jquery-ui.min.js"></script>
     	 <script type="text/javascript" src="../js/jquery.validate.js"></script>
		 <script type="text/javascript" src="../js/jquery.form.js"></script>
		 <script type="text/javascript" src="../js/validation.js"></script>
		 <script type="text/javascript" src="../js/jquery_tab.js"></script>
		  <script type="text/javascript" src="../js/accordion.js"></script>
		 <script type="text/javascript" src="../js/jquery.blockUI.js" ></script>
		  <script type="text/javascript" src="../js/it_mssp.js"> </script>
     	  
     	 <!-- css files -->
		 <link rel="stylesheet" type="text/css" href="../css/it_mssp.css"/>
		  <link rel="stylesheet" type="text/css" href="../css/main.css"/>
		 <link rel="stylesheet" type="text/css" href="../css/diQuery-collapsiblePanel.css" />
		 <link rel="stylesheet" type="text/css" href="../css/ui.all.css" />
		 <link rel="stylesheet" type="text/css" href="../css/bannerStyle.css" />
		 <link rel="stylesheet" type="text/css" href="../css/landingPageStyle.css" />
		
        <script type="text/javascript">

        function DisableBackButton() {
        	window.history.forward();
        	}
        	DisableBackButton();
        	window.onload = DisableBackButton;
        	window.onpageshow = function(evt) { if (evt.persisted) DisableBackButton(); };
        	window.onunload = function() { void (0);};
        
        function showProgress(msg){
        	var msgStr = 'Please wait.....Request is in progress...';
        	if(msg != undefined){
        		msgStr = msg;
        	}
        	$.blockUI({ message: '<img src="../images/ajax-loader.gif" /> '+msgStr+' <font size="2px;"></font>' ,css: { 
                border: '1px solid #00477D', 
                padding: '2px', 
                backgroundColor: 'white', 
                '-webkit-border-radius': '5px', 
                '-moz-border-radius': '5px', 
                opacity: .4, 
                width:'500px',
                height:'60px',
                color: 'black' 
            } }); 
        	
        	 var tr = $('#dataTable').find('tr');
             tr.bind('click', function(event) {
            	 var values = '';
                 tr.removeClass('highlight');
                 var tds = $(this).addClass('highlight').find('td');
             });		
        }

        function hideProgress(){
        	$.unblockUI();
        }


        </script>
        
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
    </head>
    <body>
  
   		<div class="mainBodyDivwithoutLine">
	    	<tiles:insertAttribute name="header" />
	    	<tiles:insertAttribute name="menu" />
	    	<tiles:insertAttribute name="body"/>
	      	<tiles:insertAttribute name="footer" />
    	</div> 
   
    </body>
  
</html>
