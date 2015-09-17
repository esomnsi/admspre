<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<tr class="prodlever" rowid="$('#ss').prop('selectedIndex')" rowindex="${prodLeverPosition}">
					
	<td width="135" height="30" align="center" bgcolor="#fafafa" class="">
		<input type="textbox" class="calendar prodectivLeverInputText" name="prodLeverList[${prodLeverPosition}].monthYear" 
			value="${steadyStateStartDate}"></input>
	</td>
	<td align="center" bgcolor="#fafafa" class="">
		<input type="text" name="prodLeverList[${prodLeverPosition}].orgStructure" value="0" readonly class="prodectivLeverInputText">
	</td>
	<td align="center" bgcolor="#fafafa" class="">
		<input type="text" name="prodLeverList[${prodLeverPosition}].process" value="0" readonly class="prodectivLeverInputText">
	</td>
	<td align="center" bgcolor="#fafafa" class="">
		<input type="text" name="prodLeverList[${prodLeverPosition}].serviceManagement" value="0" readonly class="prodectivLeverInputText">
	</td>
	<td align="center" bgcolor="#fafafa" class="">
		<input type="text" name="prodLeverList[${prodLeverPosition}].teamSourcing" value="0" readonly class="prodectivLeverInputText">
	</td>
	<td align="center" bgcolor="#fafafa" class="">
		<input type="text" name="prodLeverList[${prodLeverPosition}].portfolioOptimization" value="0" readonly class="prodectivLeverInputText">
	</td>
	<td align="center" bgcolor="#fafafa" class="">
		<input type="text" name="prodLeverList[${prodLeverPosition}].computedProductivity" value="0" readonly class="prodectivLeverInputText">
	</td>
	<td align="center" bgcolor="#fafafa" class="">
		<input type="text" name="prodLeverList[${prodLeverPosition}].calculatedFte" value="0" readonly class="prodectivLeverInputText">
	</td>		
</tr>		
<c:forEach begin="1" end="5" varStatus="i">					
	<tr class="prodlever" rowid="$('#ss').prop('selectedIndex')">					
		<td height="30" align="center" bgcolor="#fafafa" class="">
			<input type="textbox" class="calendar prodectivLeverInputText" name="prodLeverList[${prodLeverPosition}+${i.index}].monthYear" 
				value=""></input>
		</td>
		<td align="center" bgcolor="#fafafa" class="">
			<input type="text" name="prodLeverList[${prodLeverPosition}+${i.index}].orgStructure" value="" class="prodectivLeverInputText">
		</td>
		<td align="center" bgcolor="#fafafa" class="">
			<input type="text" name="prodLeverList[${prodLeverPosition}+${i.index}].process" value="" class="prodectivLeverInputText">
		</td>
		<td align="center" bgcolor="#fafafa" class="">
			<input type="text" name="prodLeverList[${prodLeverPosition}+${i.index}].serviceManagement" value="" class="prodectivLeverInputText">
		</td>
		<td align="center" bgcolor="#fafafa" class="">
			<input type="text" name="prodLeverList[${prodLeverPosition}+${i.index}].teamSourcing" value="" class="prodectivLeverInputText">
		</td>
		<td align="center" bgcolor="#fafafa" class="">
			<input type="text" name="prodLeverList[${prodLeverPosition}+${i.index}].portfolioOptimization" value="" class="prodectivLeverInputText">
		</td>
		<td align="center" bgcolor="#fafafa" class="">
			<input type="text" name="prodLeverList[${prodLeverPosition}+${i.index}].computedProductivity" value="" class="prodectivLeverInputText">
		</td>
		<td align="center" bgcolor="#fafafa" class="">
			<input type="text" name="prodLeverList[${prodLeverPosition}+${i.index}].calculatedFte" value="" class="prodectivLeverInputText">
		</td>		
	</tr>
</c:forEach>