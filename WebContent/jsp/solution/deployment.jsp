<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="inputFieldsList" value='${inputFieldsListMap.get("4")}'/>
<c:set var="savedInputFieldsList" value='${solInputFieldsListMap.get("4")}'/>
<c:set var="pIndex" value="-1"  scope="page"/>

<table width="90%" border="0" >
<tr>
	<td class="tdLabelLeft" width="35%">Total No. of deployment Man Hours</td>
	<c:set var="pIndex" value="${pIndex+1}"  scope="page"/>
	<td class="tdInputBox">
			<c:set var="index" value="${index+1}"   scope="request"/>
			<input type="text" name="solInputFieldsList[${index}].inputFieldsValue"  class="textBoxSmall" value="${savedInputFieldsList[pIndex].inputFieldsValue}"/>
			<input type="hidden" name="solInputFieldsList[${index}].inputFieldsDTO.inputFieldsId"  value="${inputFieldsList[pIndex].inputFieldsId}" />
			<input type="hidden" name="solInputFieldsList[${index}].solutionInputDefinitionId"   value="${savedInputFieldsList[pIndex].solutionInputDefinitionId}" />
		</td>
</tr>
</table>