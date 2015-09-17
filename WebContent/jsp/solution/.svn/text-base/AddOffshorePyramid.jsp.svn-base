<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tr class="offshorePyramid" >
	<td height="30" align="center" bgcolor="#fafafa" class="">
		<input type="textbox" readonly class="calendar onPyrCal textBoxSNumric"
		name="monthYearOffshore"  value="" onchange="validateNextMonthYearForPyramid(this,'monthYearOffshore','${onPyrNumber}'); setCalendarValue4Pyramid(this);"></input>
	</td>
		
	<c:forEach var="jr" items="${allRoles}" varStatus="j">
		<td align="center" bgcolor="#fafafa" class="" colid="${j.index}">
			<input type="hidden" class="onPyrHiddenCal"
			id="offLocPyramidList[${onPyrNumber+j.index}].monthYear"
			name="offLocPyramidList[${onPyrNumber+j.index}].monthYear"
			value=""> 
			<input type="text"
			onkeypress="return isDecimal(event);"
			id="offLocPyramidList[${onPyrNumber+j.index}].distributionPc"
			name="offLocPyramidList[${onPyrNumber+j.index}].distributionPc"
			value="" class="textBoxSNumric" maxlength="5"> 
			<input type="hidden"
			name="offLocPyramidList[${onPyrNumber+j.index}].jobRole.jobRoleId"
			value="${jr.jobRoleId}"> 
			<input type="hidden"
			name="offLocPyramidList[${onPyrNumber+j.index}].location"
			value="Offshore">
			 <input type="hidden" class="onPyrFTE"
			 id="offLocPyramidList[${onPyrNumber+j.index}].ftecount"
			name="offLocPyramidList[${onPyrNumber+j.index}].ftecount"
			value="">			
		</td>
	</c:forEach>
</tr>


</tr>