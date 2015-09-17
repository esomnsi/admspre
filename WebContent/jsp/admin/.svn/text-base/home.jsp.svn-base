<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="shortcut icon" href="../images/favicon.ico" />

<script type="text/javascript">

	$(document).ready(function() {
		
		$('#submit').click(function() {
			 
			 $.ajax({
					type : "POST",
					url : "/ADM_PRE/login/setRole",
					data : {"role": $('#assignedRole').val()},
					datatype : "text",
					success: function(response) {
							$('#login').submit(); 
				        },
				        error: function (response){
				           return false;
				        }
				});
	
		   });
		
		$('input').keypress(function(eventObj) {
			var keycode;
			if (eventObj.keyCode) // For IE
				keycode = eventObj.keyCode;
			else if (eventObj.which)
				keycode = eventObj.which; // For FireFox
			else
				keycode = eventObj.charCode; // Other Browser
				
				
				
			 if(keycode == 13){
				
			 $.ajax({
					type : "POST",
					url : "/ADM_PRE/login/setRole",
					data : {"role": $('#assignedRole').val()},
					datatype : "text",
					success: function(response) {
							$('#login').submit(); 
				        },
				        error: function (response){
				           return false;
				        }
				});
			 }
		   });
		  
		$(document.forms[0]).validate({
		
			errorContainer : "#errorDiv",
			errorLabelContainer : "#errorDiv ",
			/* wrapper : "li", */
			focusInvalid : true,
			rules : {
				j_username : {
					required:true,
					maxlength:7
				},
				j_password : {
					required : true,
					maxlength : 20
				}
			},
			messages : {
				j_username : {
					required : "<font size=2>Enter your Signum Id<br>",
					maxlength:"<font size=2> User name should not be more than 7 characters<br>"
				},
				j_password : {
					required : "<font size=2>Enter your LAN password",
					maxlength : "<font size=2>Password should not exceed 20 characters"
				}
			}
		});
	});
		
	function showPopUp() {
		var DivPopUp = document.getElementById("loginDiv");
		if (DivPopUp != null) {
			document.forms[0].j_username.value = "";
			document.forms[0].j_password.value = "";
			DivPopUp.style.display = "block";
			document.getElementById("uName").focus();
		}

	}

	function setFocus()
	{
			document.getElementById("submit").focus();
	}
	
	function hidePopUp() {
		var DivPopUp = document.getElementById("loginDiv");
		if (DivPopUp != null) {
			document.getElementById("errorDiv").innerHTML = "" ;
			DivPopUp.style.display = "none";
		}
	}

	
</script>


<c:if test="${not empty message}">
		<div  id="e1" class="errorMessageLogin" style="font-family: arial;font-size: 12px;">${message }</div>
		</c:if>
<c:if test="${not empty authMessage}">
	<div id="e2" class="errorMessageLogin" style="font-family: arial;font-size: 12px;">${authMessage}</div>
</c:if>

<div  class="errorMessageLogin" style="font-family: arial;font-size: 12px;">
			${sessionMsg}
	</div>
<div id="errorDiv" class="errorMessageLogin" style="font-family: arial;font-size: 12px;">
				
			
</div>

<div class="column12">
  <!-- carousel -->
  <div class="carousel">
    <!-- BEGIN CONTAINER -->
    <div style="width: 87%; height: 330px;" class="slides">
      <!-- BEGIN CAROUSEL -->
      
      <!-- SLIDE ITEM -->
      <div style="width: 204.8px; height: 168.96px; top: 73.2px; right: 772.52px; opacity: 0; z-index: 3; display: none;" class="slideItem">
        <img style="width: 204.8px; height: 153.6px; display: inline-block;" src="<%=request.getContextPath()%>/images/Support_estimation.png">
      </div>
      
      
      <div style="width: 256px; height: 211.2px; top: 54px; right: 639.4px; opacity: 1; z-index: 4; display: block;" class="slideItem"> 
      	<img style="width: 256px; height: 192px; display: inline-block;" src="<%=request.getContextPath()%>/images/Integrated_cost.png">
      </div>
      
      
      <div style="width: 320px; height: 264px; top: 30px; right: 473px; opacity: 1; z-index: 5; display: block;" class="slideItem"> 
      	<img style="width: 320px; height: 240px; display: inline-block;" src="<%=request.getContextPath()%>/images/Historical_analysis.png">
      </div>
      
      
      <div style="width: 400px; height: 330px; top: 0px; right: 265px; opacity: 1; z-index: 6; display: block;" class="slideItem"> 
      	<img style="width: 400px; height: 330px; display: inline-block;" src="<%=request.getContextPath()%>/images/TransformationPlanning.png">
      </div>
      
       <div style="width: 400px; height: 330px; top: 0px; right: 265px; opacity: 1; z-index: 6; display: block;" class="slideItem"> 
      	<img style="width: 400px; height: 330px; display: inline-block;" src="<%=request.getContextPath()%>/images/Approval_process.png">
      </div>
      
      <div style="width: 320px; height: 264px; top: 30px; right: 137px; opacity: 1; z-index: 5; display: block;" class="slideItem"> 
      	<img style="width: 320px; height: 240px; display: inline-block;" src="<%=request.getContextPath()%>/images/Application_portfolio.png">
      </div>
      
      
      <div style="width: 256px; height: 211.2px; top: 54px; right: 34.6px; opacity: 1; z-index: 4; display: block;" class="slideItem"> 
      	<img style="width: 256px; height: 192px; display: inline-block;" src="<%=request.getContextPath()%>/images/TransformationPlanning.png">
      </div>
      
      
      <div style="width: 204.8px; height: 168.96px; top: 73.2px; right: -47.32px; opacity: 0; z-index: 3; display: none;" class="slideItem"> 
      	<img style="width: 204.8px; height: 153.6px; display: inline-block;" src="<%=request.getContextPath()%>/images/Application_portfolio.png">
      </div>
      
      
    </div>
    <!-- END SLIDES -->
    <div class="nextButton" ></div>
    <div class="prevButton" ></div>
  </div>
  <!-- END CAROUSEL -->
</div>

 <form:form id="login" method="POST" action ="../j_spring_security_check">
<div id="loginDiv" style="display: none;">
<div class="login_wrapper">
	<div class="login_directionImg"></div>
    <div class="login_bodyPanel">
    	<div class="login_leftpanelBg"></div>
        <div class="login_midpanelBg">
        	<div class="login_headerPanel">Login</div>
            
        	<div class="login_inputpanel inputTopMagin">
            	<div class="login_inputLeftPanel"></div>
                <div class="login_inputMidPanel">
            		<input id="uName" name="j_username" type="text" value="User Name" class="login_inputText" placeholder="Username"/>
                </div>
            	<div class="login_inputRightPanel"></div>
            </div>
            
            <div class="login_inputpanel inputPaswdMagin">
            	<div class="login_inputLeftPanel"></div>
                <div class="login_inputMidPanel">
            		<input name="j_password" type="password" value="User Name" class="login_inputText" placeholder="Password"/>
                </div>
            	<div class="login_inputRightPanel"></div>
            </div>
            <div id="cancel" class="login_butonPanel cancelMargin" onclick="hidePopUp()">CANCEL</div>
            <div id="submit" class="login_butonPanel submitMargin " >SUBMIT</div>
            <div class="inputlogindetailsPanel">
            	    <select name="assignedRole" id="assignedRole" class="inputlogindetails" onchange="setFocus();" >
                        <c:forEach var="item" items="${rList}">
						<option  value="${item.roleName}"> ${item.displayName}</option>
					</c:forEach>
                    </select>
            </div>
        </div>
        <div class="login_rightpanelBg"></div>
    </div>
</div>
</div>
</form:form>



