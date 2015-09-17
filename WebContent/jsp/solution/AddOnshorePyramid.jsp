<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<tr class="onshorePyramid" >
	<td height="30" align="center" bgcolor="#fafafa" class="">
		<input type="textbox" readonly class="calendar onPyrCal textBoxSNumric"
		name="monthYearOnshore" value="" onchange="validateNextMonthYearForPyramid(this,'monthYearOnshore','${onPyrNumber}'); setCalendarValue4Pyramid(this,'${onPyrNumber}'); "></input>
	</td>
		2222
	<c:forEach var="jr" items="${allRoles}" varStatus="j">
	${jr.jobRoleStages.jobRoleStagesId}
		<td align="center" bgcolor="#fafafa" class="" colid="${j.index}">
			<input type="hidden" class="onPyrHiddenCal"
			id="${listName}[${onPyrNumber+j.index}].monthYear"
			name="${listName}[${onPyrNumber+j.index}].monthYear"
			value=""> 
			<input type="text"
			onkeypress="return isDecimal(event);"
			id="${listName}[${onPyrNumber+j.index}].distributionPc"
			name="${listName}[${onPyrNumber+j.index}].distributionPc"
			value="" class="textBoxSNumric" maxlength="5"> 
			<input type="hidden"
			name="${listName}[${onPyrNumber+j.index}].jobRoleStages.jobRoleStagesId"
			value="${jr.jobRoleStages.jobRoleStagesId}"> 
			<input type="hidden" name="${listName}[${onPyrNumber+j.index}].location" value="${location}">
			<input type="hidden" name="${listName}[${onPyrNumber+j.index}].subLocation" value="${sublocation}">
			 <input type="hidden" class="onPyrFTE"
			 id="${listName}[${onPyrNumber+j.index}].ftecount"
			name="${listName}[${onPyrNumber+j.index}].ftecount"
			value="">			
		</td>
	</c:forEach>
</tr>

