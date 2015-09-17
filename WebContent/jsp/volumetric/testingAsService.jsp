<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="inputFieldsList" value='${inputFieldsListMap.get("8")}'/>
<c:set var="savedInputFieldsList" value='${solInputFieldsListMap.get("8")}'/>
<c:set var="pIndex" value="-1"  scope="page"/>

<table width="90%" border="0" align="center">
	
    <tr>	
		<td class="tdTableHead" width="40%">Organisation Standard
		 </td>
        <td class="tdTableHead" ></td>
        <td class="tdTableHead" colspan="3">To be one year productivity</td>
    </tr> 
   	 <tr>
          <td class="tdTableSubHead" >Complexity factor for test cases</td>
          <td class="tdTableSubHead">Ratio</td>
          <td class="tdTableSubHead"> Test Design</td>
           <td class="tdTableSubHead">Test Execution</td>
          <td class="tdTableSubHead"> Automation Scripting</td>
     </tr>  
      <tr>
          <td class="tdLabelLeft" >Simple</td>
          <td class="tdInputBox"> 	
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/> 
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
          <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
           <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
            <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
     </tr>  
       <tr>
          <td class="tdLabelLeft" >Medium</td>
         <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			<td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			<td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			<td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
     </tr>  
     <tr>
          <td class="tdLabelLeft" >Complex</td>
           <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
            <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
            <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
           <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
     </tr>  
      <tr><td colspan="6">&nbsp;</td> </tr>
    <tr>
          <td class="tdLabelLeft" > No of major release/Year</td>
             <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
          <td class="tdInputBox"> </td>
     </tr>  
      <tr>
          <td class="tdLabelLeft" > No of minor release/Year</td>
          <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
          <td class="tdInputBox"> </td>
     </tr>  
   
</table>

<table width="90%" border="0" align="center">
	
    <tr>	
		<td class="tdTableHead" width="40%">
		 </td>
        <td class="tdTableHead" ></td>
        <td class="tdTableHead" colspan="3">Efficiency Levers</td>
          <td class="tdTableHead" >AS IS</td>
    </tr> 
   	 <tr>
          <td class="tdTableSubHead" >For a Major Release</td>
          
          <td class="tdTableSubHead">Avg no of test cases</td>
          <td class="tdTableSubHead"> % of automation design</td>
           <td class="tdTableSubHead">Test design reuse</td>
          <td class="tdTableSubHead"> Test execution cycles</td>
           <td class="tdTableSubHead"> Test execution cycles</td>
     </tr>  
      <tr>
          <td class="tdLabelLeft" >Regression</td>
         <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
         <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
           <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
           <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
           <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
     </tr>  
       <tr>
          <td class="tdLabelLeft" >New functionality</td>
          <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
          <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
           <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
           <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
          <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
     </tr>  
     <tr>
          <td class="tdLabelLeft" >UAT/Prod/Go Live</td>
          <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
         <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>  
			  <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
           <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
          <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
     </tr>  
     
     
      <tr>
          <td class="tdTableSubHead" >For a Minor Release</td>
          
          <td class="tdTableSubHead"></td>
          <td class="tdTableSubHead"></td>
           <td class="tdTableSubHead"></td>
          <td class="tdTableSubHead"> </td>
           <td class="tdTableSubHead"> </td>
     </tr>  
      <tr>
          <td class="tdLabelLeft" >Regression</td>
          <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			 <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			 <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			 <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			 <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
     </tr>  
       <tr>
          <td class="tdLabelLeft" >New functionality</td>
         <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			 <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			 <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			 <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			 <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			
     </tr>  
     <tr>
          <td class="tdLabelLeft" >UAT/Prod/Go Live</td>
         <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			 <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			 <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			 <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
			 <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
     </tr> 
     
      <tr><td colspan="6">&nbsp;</td> </tr>
        <tr>
          <td class="tdTableSubHead" >Efficiency Levers</td>
          
          <td class="tdTableSubHead"> Value</td>
          
     </tr>  
    <tr>
          <td class="tdLabelLeft" > % Regression optimized testcases/release</td>
         <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
          <td class="tdInputBox"> </td>
     </tr>  
      <tr>
          <td class="tdLabelLeft" > % New functionality testcases added to regression test cases/release</td>
          <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
          <td class="tdInputBox"> </td>
     </tr>  
   	 <tr>
          <td class="tdLabelLeft" > % Automated regression scripts of major release, reused in Minor release</td>
           <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
          <td class="tdInputBox"> </td>
     </tr>  
      <tr>
          <td class="tdLabelLeft" > % Productivity Improvement Year-on-Year</td>
         <td class="tdInputBox"> 
          		<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
          		<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
          <td class="tdInputBox"> </td>
</table>
