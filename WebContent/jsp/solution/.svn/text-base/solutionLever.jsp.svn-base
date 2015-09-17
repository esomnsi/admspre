<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="stylesheet" type="text/css" href="../css/ui.all.css" />

<link rel="stylesheet" type="text/css" 	href="../css/diQuery-collapsiblePanel.css" />

<script type="text/javascript">

function populateTab(index){

	var selectCriteria=null;
	
	if(index ==1 ){
		
		selectCriteria = 'TAB1';
		document.getElementById("solLeverForm").action="./solutionLever";
		document.getElementById("solLeverForm").submit();
	}
	else if(index == 2){
		selectCriteria = 'TAB2';
		document.getElementById("solLeverForm").action="./deliveryPyramid";
		document.getElementById("solLeverForm").submit();
		
	}
	else if(index == 3){
		selectCriteria = 'TAB3';
		document.getElementById("solLeverForm").action="./productivityModelling";
		document.getElementById("solLeverForm").submit();
	}
	

}

function setTab(index){
	
	currentSelectedTab = index;

	document.getElementById('tt1').className = 'usual';
	document.getElementById('tt2').className = 'usual';
	document.getElementById('tt3').className = 'usual';
	
	
	document.getElementById('tab1').style.display = "none";
	document.getElementById('tab2').style.display = "none";
	document.getElementById('tab3').style.display = "none";
		
	document.getElementById('tt' + (currentSelectedTab)).className = 'selected';
	document.getElementById('tab' + (currentSelectedTab)).style.display = "block";
}

window.onload = loadTab;

function loadTab(){
	var index=1;
	var tab = "${selectedTab}";
	if('TAB1' == tab){
		index = 1;
	 	var msg= '${message}';
		if (msg=="success"){
		      document.getElementById("messageDiv1").style.display='block';
		      document.getElementById("messageDiv1").innerHTML ="Data saved sucessfully !! ";
			
		}
		if (msg=="exception"){
		      document.getElementById("errorDiv1").style.display='block';
		      document.getElementById("errorDiv1").innerHTML ="Exception while saving the data !! ";
			
		} 
		
	}else if('TAB2' == tab){
		index = 2;
	}else if('TAB3' == tab){
		index = 3;
	}

	setTab(index);
	
}


function saveSolutionUtil(){
	if(checkMandatory()){
	document.getElementById("solutionUtilForm").action = "./saveSolutionUtil";
	document.getElementById("solutionUtilForm").submit();
	
	/* var msg= '${message}';
	if (msg=="success"){
		document.getElementById("errorDiv1").style.display='none';
	      document.getElementById("messageDiv1").style.display='block';
	      document.getElementById("messageDiv1").innerHTML ="<ul><li>Data saved sucessfully !! </li></ul>";
		
	}
	if (msg=="exception"){
	      document.getElementById("errorDiv1").style.display='block';
	      document.getElementById("errorDiv1").innerHTML ="<ul><li>Exception while saving the data !! </li></ul>";
		
	} */
	}
}

   /*  window.document.onkeydown = function (e)
    {
      if (!e){
        e = event;
      }
      if (e.keyCode == 27){
        lightbox_close();
      }
    } */
    function lightbox_open(){
      window.moveTo(0,0);
      document.getElementById('lightPopup').style.display='block';
      document.getElementById('fade').style.display='block';  
    }
    function lightbox_close(){
		document.getElementById('lightPopup').style.display='none';
      document.getElementById('fade').style.display='none';
    }
    
    
   function next1() {
    	document.getElementById("solLeverForm").action="./deliveryPyramid";
		document.getElementById("solLeverForm").submit();
	  };
    
	  function prev_baseLine(){
		document.getElementById("solLeverForm").action="./productEstimation";
		document.getElementById("solLeverForm").submit();
	  }
	  
	  function checkMandatory(){
			
			var errorMsg = "<ul>";
			var flag = true;
			onshoreLocal = $("#onshoreLocal").val();
			if(onshoreLocal==null || onshoreLocal == ""){
				errorMsg += "<ul>Utilization effort for onshore local is mandatory !</ul>";
				flag = false;
			}
			onshoreGSC = $("#onshoreGSC").val();
			if(onshoreGSC==null || onshoreGSC == ""){
				errorMsg += "<ul>Utilization effort for onshore landed GSC is mandatory !</ul>";
				flag = false;
			}
			nearShore = $("#nearShore").val();
			if(nearShore==null || nearShore == ""){
				errorMsg += "<ul>Utilization effort for nearshore is mandatory !</ul>";
				flag = false;
			}
			onshore3PP = $("#onshore3PP").val();
			if(onshore3PP==null || onshore3PP == ""){
				errorMsg += "<ul>Utilization effort for 3PP is mandatory !</ul>";
				flag = false;
			}
			offShore = $("#offShore").val();
			if(offShore==null || offShore == ""){
				errorMsg += "<ul>Utilization effort for offshore is mandatory !</ul>";
				flag = false;
			}
			 
			if(!(errorMsg == "<ul>")){
				document.getElementById("messageDiv1").style.display='none';
				document.getElementById("errorDiv1").style.display='block';
				document.getElementById("errorDiv1").innerHTML = errorMsg+"</ul>";
			}
			return flag;
		};
	  
  </script>


</head><body>
<form:form id="solLeverForm">
			<div id="serviceBucket" style="margin-bottom:0px;" >
					<table width="730" align="center">
					<tr>
						<td align="center" colspan="6">
						<jsp:include page="../solution/serviceBucket.jsp"/>
						</td>
					</tr>
					</table>
			</div>


          <div id="usual1" class="solutionLeverTab">
            <ul id="sollever" >
              <li><a class=""  id="tt1" onclick="populateTab(1);"><span></span>Solution Approach </a></li>          
              <li><a class=""  id="tt2" onclick="populateTab(2);"><span></span>Pyramid modeling</a></li>
               <li><a class=""  id="tt3" onclick="populateTab(3);"><span></span>Resource modeling</a></li>
            </ul>
     </form:form>       
<!---------------------------------------Tab 1 (Oppurtunity Details DIV Start from Here)--------------------------------------------->
            <form:form id="solutionUtilForm" modelAttribute="solutionUtilPerYearDTO" method="post" name="solutionUtilForm">
            <div style="display: block;" id="tab1" class="tabBodyArea">
            <div id="errorDiv1"  class="errorMessageDisp" style=" width:1080px;display:none ;margin-left:10px;text-align:left;"></div>

			<div id="messageDiv1" class="message" style="width:1080px;display:none ;margin-left:10px;"></div>
               
              <c:if test="${(selectedTab =='TAB1')}">  
            <div class="headerBulepanel">Utilization Effort</div>
           
             <form:hidden path="solutionUtilPerYearID"/>
              <table border="0" cellspacing="0" cellpadding="0" class="solutionLeverTable" >
               
                <tr>
                  <td>Utilization effort for onshore local<sup style="color: red" >*</sup> :</td>
                  <td><label for="textfield"></label>
                  <form:input type="text"  path="onshoreLocal" maxlength="7" onkeypress="return isNumericWithDecimal(event,this);" class="textBoxSNumric"/></td>
                  <td>Hrs / Month</td>
                  <td width="100">&nbsp;</td>
                  <td>Utilization effort for onshore landed GSC<sup style="color: red" >*</sup> :<br /></td>
                  <td><label for="textfield"></label>
                    <form:input type="text"  path= "onshoreGSC" maxlength="7" onkeypress="return isNumericWithDecimal(event,this);" class="textBoxSNumric"/></td>
                  <td>Hrs / Month</td>
                </tr>
                
                
                <tr>
                  <td>Utilization effort for nearshore<sup style="color: red" >*</sup> :</td>
                  <td><form:input type="text"  path="nearShore" maxlength="7" onkeypress="return isNumericWithDecimal(event,this);" class="textBoxSNumric"/></td>
                  <td>Hrs / Month</td>
                  <td>&nbsp;</td>
                  <td>Utilization effort for 3PP<sup style="color: red" >*</sup> :<br /></td>
                  <td><form:input type="text"  path="onshore3PP" maxlength="7" onkeypress="return isNumericWithDecimal(event,this);" class="textBoxSNumric"/></td>
                  <td>Hrs / Month</td>
                </tr>
                <tr>
                  <td>Utilization effort for offshore<sup style="color: red" >*</sup> :</td>
                  <td><form:input type="text"  path="offShore" maxlength="7" onkeypress="return isNumericWithDecimal(event,this);" class="textBoxSNumric"/></td>
                  <td>Hrs / Month</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                <tr id="buttonRow">
                  <td colspan="4">&nbsp;</td>
                  <td colspan="2" >
                  <a id="next1" class="portfolioButtons" onClick="next1()" >Next</a>
                   <c:choose>
                	 <c:when test="${hasEditSolAccess!='false'}">
                  <a id="Save" class="buttoncomonStyle" onclick="saveSolutionUtil()">Save</a>
                  </c:when>
                  </c:choose>
                  <a id="prev_baseLine" class="portfolioButtons" onClick="prev_baseLine()" >Previous</a>
                <!--   <a id="prev1" class="portfolioButtons" >Previous</a> -->
                  </td>
                </tr>
              </table>
              
              
              </c:if>
            </div>
               </form:form>
            
            <div style="display: none;" id="tab2" class="tabBodyArea">
            <c:if test="${(selectedTab =='TAB2')}">  
            <jsp:include page="deliveryAndPyramidModelling.jsp"/>
            </c:if>
            </div>
  
 
             <div style="display: none;" id="tab3" class="tabBodyArea">
             <c:if test="${(selectedTab =='TAB3')}">  
         	   <jsp:include page="productivityModelling.jsp"/>
         	   </c:if>
            </div>
            
           </div>
             
            
      
            
  
  
            

<script type="text/javascript">
	$("#usual1 ul").idTabs();
</script>

<!-- <script type="text/javascript">
var layersSelector = document.getElementById("layersSelector");
layersSelector.addEventListener("change",function(e){
	closeAllSelect();
	try{
	var targetDiv = document.getElementById("layersSelector_"+layersSelector.value);
	targetDiv.style.display = "block";
	}catch(e){}
	
});

function closeAllSelect(){
	var td = document.getElementById("layersSelector_selectContainer");
	var divs = (td.getElementsByTagName('div'));
	
	for(var i=0;i<divs.length;i++){
		divs[i].style.display='none';
	}
}
</script> -->

</body>
</html>