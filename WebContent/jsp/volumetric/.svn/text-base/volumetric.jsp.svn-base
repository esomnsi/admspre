<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript">

function populate(index){
	var selectCriteria=null;
	if(index ==1 ){
		selectCriteria = 'APP_DEV';
		document.getElementById("admSup").action="./volumetricAppDev";
		document.getElementById("admSup").submit();
	}
	else if(index == 2){
		selectCriteria = 'APP_TEST';
		document.getElementById("admSup").action="./volumetricApplicationTesting";
		document.getElementById("admSup").submit();
	}
	else if(index == 3){
		selectCriteria = 'APP_MAIN';
		document.getElementById("admSup").action="./applicationMaintenanceNew";
		document.getElementById("admSup").submit();
	}
	else if(index ==4 ){
		selectCriteria = 'ADM_SUPP';
		document.getElementById("admSup").action="./volumetricAdmSupp";
		document.getElementById("admSup").submit();
		
	}
	else if(index ==5 ){
		selectCriteria = 'Product';
		document.getElementById("admSup").action="./productEstimation";
		document.getElementById("admSup").submit();
		
	}
}

function setTab(index){
	
		currentSelectedTab = index;
	
		document.getElementById('tt1').className = 'usual';
		document.getElementById('tt2').className = 'usual';
		document.getElementById('tt3').className = 'usual';
		document.getElementById('tt4').className = 'usual';
		document.getElementById('tt5').className = 'usual';

		
		document.getElementById('tab1').style.display = "none";
		document.getElementById('tab2').style.display = "none";
		document.getElementById('tab3').style.display = "none";
		document.getElementById('tab4').style.display = "none";
		document.getElementById('tab5').style.display = "none";
		
		document.getElementById('tt' + (currentSelectedTab)).className = 'selected';
		document.getElementById('tab' + (currentSelectedTab)).style.display = "block";
}

window.onload = loadTab;

function loadTab(){
	var index=1;
	var tab = "${selectedTab}";
	if('APP_DEV' == tab){
		index = 1;
	}else if('APP_TEST' == tab){
		index = 2;
	}else if('APP_MAIN' == tab){
		index = 3;
	}
	else if('ADM_SUPP' == tab){
		index = 4;
	}
	else if('Product' == tab){
		index = 5;
	}
	setTab(index);
	
}

function refreshServiceBucket(){
	 $.ajax({
			type : "POST",
			url : "./refreshServiceBucket",
			datatype : "text",
			success : function(html){
				
				 $("#serviceBucket").html(html);
			
			},
			error : function(e) {
				alert("Operation failed to load service bucket.");
			}
		});} 

</script>


		
<form:form id="admSup" >
	
 	<div id="serviceBucket" style="margin-bottom:5px;" >
			<table width="730" align="center">
			<tr>
				<td align="center" colspan="6">
				<jsp:include page="../solution/serviceBucket.jsp"/>
				</td>
			</tr>
			</table>
	</div>
	
	
	  <div class="tdHeaderLabel" style="width:1096px;margin-bottom:4px;margin-left:1px;">Input Baseline</div> 
	 
	<div id="usual1" class="usual">
            <ul>
             <!--  <li><a class="" href="#tab1" id="tt1" onclick="populate(1);"><span></span>ADM Support Process</a></li>
              <li><a class="" href="#tab2" id="tt2" onclick="populate(2);"><span></span>Application Development</a></li>
              <li><a class="" href="#tab3" id="tt3" onclick="populate(3);"><span></span>Application Maintenance</a></li>
              <li><a class="" href="#tab4" id="tt4" onclick="populate(4);"><span></span>Application Testing</a></li> -->
              <li><a class="" href="#tab1" id="tt1" onclick="populate(1);"><span></span>Application Development</a></li>
              <li><a class="" href="#tab2" id="tt2" onclick="populate(2);"><span></span>Application Testing</a></li>
              <li><a class="" href="#tab3" id="tt3" onclick="populate(3);"><span></span>Application Maintenance</a></li>
              <li><a class="" href="#tab4" id="tt4" onclick="populate(4);"><span></span>ADM Support Process</a></li>
              <li><a class="" href="#tab5" id="tt5" onclick="populate(5);"><span></span>Ericsson Product</a></li>
            </ul>
         </div>    
<!---------------------------------------Tab 1 (Oppurtunity Details DIV Start from Here)--------------------------------------------->


		
 </form:form>
 

  <div style="display: none;" id="tab1" class="tabBodyArea">
            <c:if test="${(selectedTab =='APP_DEV')}">
            <jsp:include page="ApplicationDevelopment.jsp"></jsp:include>            
            </c:if>
            </div>
 
           

 <!---------------------------------------Tab 1 (Oppurtunity Details DIV END from Here)--------------------------------------------->
           
 <!---------------------------------------Tab 2 (Location Profile DIV Start from Here)--------------------------------------------->           

            <div style="display: none;" id="tab2" class="tabBodyArea">
            <c:if test="${(selectedTab =='APP_TEST')}">
            <jsp:include page="applicationTesting.jsp"></jsp:include>
           </c:if>
            </div>
  <!---------------------------------------Tab 2 (Location Profile DIV END from Here)--------------------------------------------->    
 
  <!---------------------------------------Tab 3 (Customer and Competitive Information DIV Start from Here)--------------------------------------------->             
 
            <div style="display: none;" id="tab3" class="tabBodyArea">
            <c:if test="${(selectedTab =='APP_MAIN')}">
            <jsp:include page="applicationMaintenanceNew.jsp"></jsp:include>
           </c:if>
            </div>
       
<!---------------------------------------Tab 3 (Customer and Competitive Information DIV End from Here)------------------------------------------->              


 <!---------------------------------------Tab 4 (Application Testing DIV Start from Here)--------------------------------------------->             
 
  <div style="display: none;" id="tab4" class="tabBodyArea" > 
            <c:if test="${(selectedTab =='ADM_SUPP')}">
            	<jsp:include page="AdmSupportProcess.jsp"></jsp:include>
            </c:if>
            	
            </div>
 
           
       
<!---------------------------------------Tab 4 (Application Testing DIV End from Here)------------------------------------------->              


<!---------------------------------------Tab 5 (CBIO DIV Start from Here)--------------------------------------------->
<div style="display: none;" id="tab5" class="tabBodyArea">
	<c:if test="${(selectedTab =='Product')}">
		<jsp:include page="productInputBaseline.jsp"></jsp:include>
	</c:if>
</div>
<!---------------------------------------Tab 5 (CBIO DIV End from Here)------------------------------------------->



