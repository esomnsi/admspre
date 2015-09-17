<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="inputFieldsList" value='${inputFieldsListMap.get("9")}'/>
<c:set var="savedInputFieldsList" value='${solInputFieldsListMap.get("9")}'/>
<c:set var="pIndex" value="-1"  scope="page"/>

<table width="90%" border="0" align="center">
	
    <tr>	
		<td class="tdTableHead" width="70%">Testing hours required per L3 problem resolution
		 </td>
        <td class="tdTableHead" >Default</td>
        <td class="tdTableHead" >Overridden</td>
    </tr> 
   	 <tr>
          <td class="tdLabelLeft" > Functional Testing Effort in % [ of Level3 Support Work ]
		</td>
          <td class="tdLabelLeft" >
			<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
			${inputFieldsList[pIndex].defaultValues}
			</td>
			<td class="tdInputBox" colspan="5">
				<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
     </tr>  
    <tr>
          <td class="tdLabelLeft" > Regression Testing Effort in % [ of Level3 Support Work ]
		</td>
          <td class="tdLabelLeft" >
			<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
			${inputFieldsList[pIndex].defaultValues}
			</td>
			<td class="tdInputBox" colspan="5">
				<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
     </tr>  
     
     
      <tr>	
		<td class="tdTableHead" width="70%">Testing hours required per Level3 Enhancement Work
		 </td>
        <td class="tdTableHead" ></td>
        <td class="tdTableHead" ></td>
    </tr> 
   	 <tr>
          <td class="tdLabelLeft" > Functional Testing Effort in % [ of Level3 Support Work ]
		</td>
          <td class="tdLabelLeft" >
			<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
			${inputFieldsList[pIndex].defaultValues}
			</td>
			<td class="tdInputBox" colspan="5">
				<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
     </tr>  
    <tr>
          <td class="tdLabelLeft" > Regression Testing Effort in % [ of Level3 Support Work ]
		</td>
          <td class="tdLabelLeft" >
			<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
			${inputFieldsList[pIndex].defaultValues}
			</td>
			<td class="tdInputBox" colspan="5">
				<c:set var="index" value="${index+1}"   scope="request"/>
				<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmallNumric" onkeypress="return isNumeric(event);" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
				<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
				<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
			</td>
     </tr>  
   
</table>


