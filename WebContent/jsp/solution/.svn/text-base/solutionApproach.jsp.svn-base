<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form id="solutionForm" action="saveSolution" method="post" modelAttribute="defineScopeForm">         
	
        <%-- 
            	<div class="inputRow3rd"  style="margin-top:20px;width: 400px;" >
                	<h3 style="width: 150px;">Engagement Approach :</h3>
                    <span style="">
                    	 <form:select  path="solutionDTO.solutionApproach" class="inputtextTabs" onclick="javascript:enableListBox(this);">
						 	<option value="">Select</option>
						   <form:options items="${solApproachList}" />
						</form:select>
                    </span>
                </div>
                
            	<div class="inputRow3rd" style="margin-top:20px;width: 345px;">
                	<h3>Estimation type :</h3>
                    <span>
                    	 <form:select path="solutionDTO.solutionType" class="inputtextTabs" disabled="true" >
						    <option value="">Select</option>
						   <form:options items="${solTypeList}" />
						  </form:select>
                    </span>
                </div> 
            	
            		<div style="clear:both;"></div> --%>
            		<br>
            		<br>
            	<table style="width:100%; margin-top:10px;"> 
            	
            	<tr>
					<td class="tdLabel" style="width:15%; text-align:right;">Engagement Approach : </td>
					<td class="tdText" style="width:15%; text-align:left;"> <form:select  path="solutionDTO.solutionApproach" class="listBoxSmall" onclick="javascript:enableListBox(this);">
						 	<option value="">Select</option>
						   <form:options items="${solApproachList}" />
						</form:select></td>
				
					<%-- <td class="tdLabel"   >Estimation type :</td><td class="tdText">  <form:select path="solutionDTO.solutionType" class="listBoxSmall">
						    <option value="">Select</option>
						   <form:options items="${solTypeList}" />
						  </form:select></td>
						  <td class="tdLabel" width="25%"  >&nbsp;</td> --%>
						  
				<!-- </tr>
            	<tr style="height:5px;"><td class="tdLabel" colspan="5" style="height:5px;" >&nbsp;</td></tr>	
            	<tr> -->
					<td class="tdLabel" style="width:25%; text-align:right;">Competency  Model :</td>
				
					<td class="tdLabel tooltip" style="width:10%; text-align:left;"><form:radiobutton path="solutionDTO.jobRoleModel" value="CCM" />CCM 
					<p>
					The new Career and Competence Model (CCM) currently rolled out globally, aims to optimize the company's talent by giving 
					managers a holistic overview of all employees, thereby enabling them to better meet customer expectations. At the same time,
					It will incentivize employees by giving them a clearer view of career paths and opportunities within the organization.<br>
					
					CCM architecture is designed globally. Job roles are designed, evaluated and stages are defined based on global benchmarks. 
					As EGI, we are responsible to align our current grade structure and map the current roles to new roles and calibrate.<br>
					
					The new Career & Competence Model will help to create:<br>
					
					- Clear, visible career paths for all employee<br>
					- Transparent, standardized requirements on competence for all roles<br>
					- One common approach to career and competence development<br>

					</p>
					</td>
					<td  class="tdLabel tooltip" style="width:10%; text-align:left;"><form:radiobutton path="solutionDTO.jobRoleModel" value="Non-CCM" />Non-CCM 
					<p>Conventional Ericsson Model</p>
					</td>
					
					<%-- <td class="tdLabel"   >Utilization Per Year(in Hours) :</td><td class=""><form:input path="solutionDTO.utilizationPerYear" class="textBoxSmallNumric"  onkeypress="return isDecimal(event);" ></form:input></td> --%>
					 
					<td class="tdLabel" width="25%"  >&nbsp;</td>
				</tr>
				
				</table>
            	<br>
                
            	<%-- <div class="inputRow3rd">
                    <span>
						 <form:select path="solutionDTO.solutionApproachDimensionDTO.dimensionAttributes" class="defineinputareaTabs" size="4" multiple="true">
							<form:options items="${defineScopeForm.solutionDTO.solutionApproachDimensionDTO.dimensionAttributes}" />
						</form:select>                 
                    </span>
                    <div class="tabsDefineScoprAddButtons">
                        <div class="TabsCommonButtons" onclick="javascript:addNewItem();">Add</div>
                        <div class="TabsCommonButtons" onclick="javascript:deleteItem();">Delete</div>
                 	</div>
                </div>
				<div style="clear:both;"></div>
				
            	
            	<div class="inputRow3rd">
                	<h3>Dimension Name :</h3>
                    <span>
                    <form:select path="solutionDTO.solutionApproachDimensionDTO.dimensionName"  class="inputtextTabs" onclick="getOtherDiv(this);">
								  <option value="">-Select</option>
								  <form:options items="${dimensionList}" />
								</form:select> 
                    </span>
                </div>

            	<div class="inputRow3rd">
                	<h3> Name of attributes :</h3>
                    <span>
                    	<input id="attributeName" value="" type=text name="attributeName" class="inputtextTabs" maxlength="50">
                   </span>
                	</div> --%>
          		
            <input type="hidden" id="formAction"  /> 
	        <form:hidden path="solutionDTO.dimentionAttributesString"  /> 
			<form:hidden path="solutionDTO.deleltedDimAttributesIds"  /> 
			<form:hidden path="solutionDTO.newDimAttributesList"  /> 
			
			
            	<div class="tabsDefineScoprButtons" style="float:right;width:42%;">
            		<div id="prevSol" class="TabsCommonButtons">Previous</div>   
            			 <c:choose>                   
						<c:when test="${hasEditSolAccess!='false'}">
					      <c:if test="${createNew==true}">
	            			<div id="createNew" class="TabsCommonButtons" style="width:120px; ">Create New Solution</div>   
	            		</c:if>
            		       <div id="saveSol" class="TabsCommonButtons">Save</div>
		                    <div id="resetSol" class="TabsCommonButtons">Reset</div>   
		                    <div id="nextSol" class="TabsCommonButtons">Next</div> 
	               		 </c:when>
	               		 <c:otherwise>
	               		 	 <div onclick="location.href='../solution/productEstimation'" class="TabsCommonButtons" >Next</div> 
	               		 </c:otherwise>
	               		 
	               	</c:choose>
            		           
                </div>
            
				
				
       
		
  </form:form>	