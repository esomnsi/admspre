<tr class="prodlever" rowid="${osid}" rowindex="${prodLeverNumber}">
	<td height="30" align="center" bgcolor="#fafafa" class="">
		<input type="textbox" readonly class="calendar textBoxSNumric"  name="prodLeverList[${prodLeverNumber}].monthYear" 
			onchange="validateNextMonthYear(this,'prodLeverList','${prodLeverNumber}')" ></input>
	</td>
	<td align="center" bgcolor="#fafafa" class="">
		<input type="text" maxlength="5" onkeypress="return isDecimal(event);"  
			id="prodLeverList[${prodLeverNumber}].orgStructure" name="prodLeverList[${prodLeverNumber}].orgStructure" value="" class="orgStructure textBoxSNumric">
	</td>
	<td align="center" bgcolor="#fafafa" class="">
		<input type="text" maxlength="5" onkeypress="return isDecimal(event);"  
			id="prodLeverList[${prodLeverNumber}].process" name="prodLeverList[${prodLeverNumber}].process" value="" class="process textBoxSNumric">
	</td>
	<td align="center" bgcolor="#fafafa" class="">
		<input type="text" maxlength="5" onkeypress="return isDecimal(event);"  
			id="prodLeverList[${prodLeverNumber}].serviceManagement" name="prodLeverList[${prodLeverNumber}].serviceManagement" value="" class="serviceManagement textBoxSNumric">
	</td>
	<td align="center" bgcolor="#fafafa" class="">
		<input type="text" maxlength="5" onkeypress="return isDecimal(event);"  
			id="prodLeverList[${prodLeverNumber}].teamSourcing" name="prodLeverList[${prodLeverNumber}].teamSourcing" value="" class="teamSourcing textBoxSNumric">
	</td>
	<td align="center" bgcolor="#fafafa" class="">
		<input type="text" maxlength="5" onkeypress="return isDecimal(event);"  
			id="prodLeverList[${prodLeverNumber}].portfolioOptimization" name="prodLeverList[${prodLeverNumber}].portfolioOptimization" value="" class="portfolioOptimization textBoxSNumric">
	</td>
	<td align="center" bgcolor="#fafafa" class="">
		<input type="text" maxlength="5" id="prodLeverList[${prodLeverNumber}].computedProductivity" name="prodLeverList[${prodLeverNumber}].computedProductivity" value="" readonly class="productivity textBoxSCalculateResults">
	</td>
	<td align="center" bgcolor="#fafafa" class="">
		<input type="text" maxlength="5" id="prodLeverList[${prodLeverNumber}].calculatedFte" name="prodLeverList[${prodLeverNumber}].calculatedFte" value="" readonly class="fteval textBoxSCalculateResults">
		<input name="prodLeverList[${prodLeverNumber}].opportunityScope.opportunityScopeId" 
										type="hidden" class="textBoxMedium" value="${osid}">
	</td>			
</tr>