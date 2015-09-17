<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"  %>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.5.3/jquery-ui.min.js"></script>
<script type="text/javascript">
var pageIndex = 0;
$(document).ready(function(){
$("#from").datepicker({ showOn: 'button', buttonImageOnly: true, buttonImage: '../images/icon_cal.png' });
$("#to").datepicker({ showOn: 'button', buttonImageOnly: true, buttonImage: '../images/icon_cal.png' });
$("#from1").datepicker({ showOn: 'button', buttonImageOnly: true, buttonImage: '../images/icon_cal.png' });
$("#to1").datepicker({ showOn: 'button', buttonImageOnly: true, buttonImage: '../images/icon_cal.png' });
$("#from2").datepicker({ showOn: 'button', buttonImageOnly: true, buttonImage: '../images/icon_cal.png' });
$("#to2").datepicker({ showOn: 'button', buttonImageOnly: true, buttonImage: '../images/icon_cal.png' });
$("#from3").datepicker({ showOn: 'button', buttonImageOnly: true, buttonImage: '../images/icon_cal.png' });
$("#to3").datepicker({ showOn: 'button', buttonImageOnly: true, buttonImage: '../images/icon_cal.png' });
});
</script>


     <script type="text/javascript">
     var rowIndex=1;
        $(document).ready(function() {

         /* $("#Add").live('click', function() {
             var $tr    = $(this).closest('.tr_clone');
             var $clone = $tr.clone();
             $clone.find(':text').val('');
             $tr.after($clone);
         }); */
             $("#Add").click(function() {
            	 alert( 1);
            	// $('#pageIndex').val(pageIndex++);
            	//alert("pageIndex "+ $('#pageIndex').val()); 
              /* $('#dataTable tbody>tr:last').clone(true).insertAfter('#dataTable tbody>tr:last'); */              
               /* $('#dataTable tbody>tr #txtBox').val(''); */

               /* var row = $('#dataTable tbody>tr:last').clone(false);
               $("td input:text", row).val("");
               $("td input:checkbox", row).attr('checked',false);
               row.insertAfter('#dataTable tbody>tr:last'); */
				
               var str =addRow();
               // var row = $('#dataTable tbody>tr:last').clone(false);
             /*   $("td input:text", row).val("");
               $("td input:checkbox", row).attr('checked',false);*/ 
               alert(str);
               $(str).insertAfter('#dataTable tbody>tr:last'); 
             
              return false;
            });
    /*         $("#Delete_FadeOut").click(function() {
             $(this).parent().parent().css("background-color","#FF3700");
                $(this).parent().parent().fadeOut(600, function(){
                    $(this).remove();
                });
         });
            $("#Delete").click(function() { 
                    $(this).parent().parent().remove();
            });
            $("#Checked_Delete").click(function() {
             $('#dataTable input:checkbox').each(function(){
              if(this.checked){
               $(this).parent().parent().remove();
              }
             });
             return false;
         });

       $("#Reset_Table").click(function() {
                $("#dataTable").find('input:text').val('');
                $("#dataTable").find('input:checkbox').attr('checked',false);
         });
            $("#Reset_Form").click(function() { 
                $("#myForm").find('input').val('');
         }); */
        }); 
        
        function saveitSpend() {
    		document.forms[0].action = "/ADM_PRE/solution/SaveitSpend";
    		document.forms[0].submit();
    	}
        
        
        function addRow(){
        	
        	var row = '<tr>'+
					  '<td class="tdInputBox">PR001</td>'+	
				'<td class="tdInputBox" ><input path="itemDesc" name="itSpendDto['+ rowIndex++ +'].itemDesc" id="itemDesc" value="" class="textBoxMedium"></input></td></tr>';
			
				return row;
        }
    </script>



<input type="hidden" name="pageIndex" id="pageIndex" value="" />
<div id="mainBody" class="bodyCss">


	<div id="bodyDiv1" class="mainBodyDivwithoutLine">

		<div id="bodyDiv1" class="mainBodyDiv1" style="width: 100%; mafloat: left; margin-bottom: 5px;">

			<h1 class="tdHeaderLabel" style="margin-top: 0px;margin-bottom: 0px;"> IT Spend</h1>
		</div>
	
	<form:form id="spendForm" method="POST" commandName="itSpendForm" action="SaveitSpend">
	<div id="bodyDiv1" class="mainBodyDiv1" style="float: left; width: 100%;overflow-y:auto;overflow-x:auto;">
		<table id="dataTable" width="100%" border="0">
			<tr>
				<td class="tdTableHead" colspan="7">CAPEX Spend Analysis</td>
				<td class="tdTableHead" colspan="2">Man Day Rate</td>
				<td class="tdTableHead" >Effort</td>
				<td class="tdTableHead" colspan="2">Period</td>
				<td class="tdTableHead" ></td>
			</tr>
			<tr>
				<td class="tdSubTableHead"><B>Budget Code No.</B></td>	
				<td class="tdSubTableHead" ><B>Project/Item Description</B></td>
				<td class="tdSubTableHead" ><B>In Scope</B></td>
				<td class="tdSubTableHead" ><B>Category</B></td>
				
				<td class="tdSubTableHead"><B>Budget-Local Currency</B></td>	
				<td class="tdSubTableHead" ><B>Budget (USD)</B></td>
				<td class="tdSubTableHead" ><B>Spend/Forecast</B></td>
				<td class="tdSubTableHead" ><B>Local Currency</B></td>
				
				<td class="tdSubTableHead"><B>USD</B></td>	
				<td class="tdSubTableHead" ><B>Man-Days</B></td>
				<td class="tdSubTableHead" ><B>From</B></td>
				<td class="tdSubTableHead" ><B>To</B></td>
				
				<td class="tdSubTableHead" ><B>Remarks</B></td>
			</tr>
			
			
			
	   <tr>
				<td class="tdInputBox">PR001</td>	
				<td class="tdInputBox" ><input path="itemDesc" name="itSpendDto.itemDesc" id="itemDesc" value="" class="textBoxMedium"></input></td>
				<!-- <td class="tdInputBox"><select name="itSpendDto[0].capInScope"  class="listBoxS">
					<option value="">Select</option>
					<option value="yes">Yes</option>
					<option value="no">No</option>
					<select>
				</td>
				<td class="tdInputBox" ><select name="itSpendDto[0].category" class="textBoxM">
					<option value="">Select</option>
					<option value="Development">Development</option>
					<option value="Licence">Licence</option>
					<option value="Enhancement">Enhancement</option>
					<option value="Others">Others</option>
					<select>
				</td>
				<td class="tdInputBox" ><input name="itSpendDto[0].capBudgetLocalCurr" type="text" value="" class="textBoxM" /></td>	
				<td class="tdInputBox" ><input name="itSpendDto[0].capBudgetUSD"  type="text" value="" class="textBoxM" /></td>
				<td class="tdInputBox" ><select name="itSpendDto[0].capSpendForecast" class="textBoxM">
					<option value="">Select</option>
					<option value="Planned Spend">Planned Spend</option>
					<option value="Actual Spend">Actual Spend</option>
					<option value="Planned Forecast">Planned Forecast</option>
					<option value="Committed Forecast">Committed Forecast</option>
					<select></td>
				<td class="tdInputBox" ><input name="itSpendDto[0].capLocalCurrency" type="text" value="" class="textBoxM" /></td>
				
				<td class="tdInputBox">123456</td>	
				<td class="tdInputBox" ><input name="itSpendDto[0].capManDays" value="" type="text" class="textBoxSmall" /></td>
				
				<td class="tdInputBox" ><input name="itSpendDto[0].capFrom" id="capFrom" type="text" class="textBoxDate"></input></td>
				<td class="tdInputBox" ><input name="itSpendDto[0].capTo" id="capTo" type="text" class="textBoxDate"></input></td>
				
				<td class="tdInputBox" ><textarea name="itSpendDto[0].capRemark" rows="1" cols="2" class="textBoxLarge"></textarea></td> -->
			</tr>
			
		</table>
		
			<table width="100%">

					<tr class="tdOddRow">
						<td align="right" colspan="15" class="tdButtonRow">
						<a href="#" id="Add" class="button" target="">Add Row</a></td>
					</tr>
					<tr class="tdOddRow">
						<td class="" colspan="15" width="9%">&nbsp;</td>
					</tr>

				</table>
		<%-- 	<table>
			<tr>
				<td class="tdInputBox" colspan="4" style="text-align: right;"><b>TOTAL CAPEX</b></td>
				<td class="tdInputBox"><form:input path="itSpendDto[${status.index}].totalCaps" name="" value="NGR 23,123,456" class="textBoxM" /></td>
				<td class="tdInputBox"><form:input path="itSpendDto[${status.index}]." name="" value="$12,356" class="textBoxM" /></td>
			</tr>
			
		</table> --%>
	</div>



	<%-- <div id="bodyDiv1" class="mainBodyDiv1" style="float: left; width: 100%;overflow-y:auto;overflow-x:auto;">
		<table width="100%" border="0">
			<tr>
				<td class="tdTableHead" colspan="8">OPEX Spend Analysis</td>
				<td class="tdTableHead" colspan="2">Man Day Rate</td>
				<td class="tdTableHead" >Effort</td>
				<td class="tdTableHead"></td>
				<td class="tdTableHead" colspan="2">Period</td>
				<td class="tdTableHead" ></td>
			</tr>
			<tr>
				<td class="tdSubTableHead"><B>Serial No.</B></td>	
				<td class="tdSubTableHead" ><B>Business Service</B></td>
				<td class="tdSubTableHead" ><B>Product Name</B></td>
				<td class="tdSubTableHead" ><B>Vendor</B></td>
				
				<td class="tdSubTableHead"><B>In Scope</B></td>	
				<td class="tdSubTableHead" ><B>Budget (USD)</B></td>
				<td class="tdSubTableHead" ><B>Cost (Local Currency)</B></td>
				<td class="tdSubTableHead" ><B>Cost (UsD)</B></td>
				
				<td class="tdSubTableHead"><B>Local Currency</B></td>	
				<td class="tdSubTableHead" ><B>USD</B></td>
				<td class="tdSubTableHead" ><B>Man Days</B></td>
				<td class="tdSubTableHead" ><B>Spend/Forecast</B></td>
				
				<td class="tdSubTableHead" ><B>From</B></td>
				<td class="tdSubTableHead" ><B>To</B></td>
				<td class="tdSubTableHead" ><B>Remark</B></td>
			</tr>
			
			
			<tr>
				<td class="tdInputBox">001</td>	
				<td class="tdInputBox" ><form:input path="itSpendDto[${status.index}].businesService" name="" value="" class="textBoxMedium"></td>
				<td class="tdInputBox"><form:input path="itSpendDto[${status.index}].productName" name="" value="" class="textBoxM"></td>
				<td class="tdInputBox" ><form:input path="itSpendDto[${status.index}].vendor" name="" value="" class="textBoxM"></td>
				<td class="tdInputBox" ><form:select path="itSpendDto[${status.index}].opexInScope" name="" class="listBoxS">
					<option>Select</option>
					<option>Yes</option>
					<option>No</option>
					</select>
				</td>	
				<td class="tdInputBox" ><form:input path="itSpendDto[${status.index}].opexUSDBudget" name="" value="" class="textBoxM"></td>
				<td class="tdInputBox" ><form:input path="itSpendDto[${status.index}].opexCostLocalCurr" name="" value="" class="textBoxM"></td>
				<td class="tdInputBox" ><form:input path="itSpendDto[${status.index}].opexUSDCost" name="" value="" class="textBoxM"></td>
				
				<td class="tdInputBox"><form:input path="itSpendDto[${status.index}].opexLocalCurr" name="" value="" class="textBoxM"></td>	
				<td class="tdInputBox" >123456</td>
				<td class="tdInputBox"><form:input path="itSpendDto[${status.index}].opexManDays" name="" value="" class="textBoxSmall"></td>	
				<td class="tdInputBox" ><form:input path="itSpendDto[${status.index}].opexSpendForcast" name="" value="" class="textBoxM"></td>
				
				<td class="tdInputBox" ><form:input path="itSpendDto[${status.index}].opexFrom" id="opexFrom" class="textBoxDate"></input></td>
				<td class="tdInputBox" ><form:input path="itSpendDto[${status.index}].opexTo" id="opexTo" class="textBoxDate"></input></td>
				
				<td class="tdInputBox" ><form:textarea path="itSpendDto[${status.index}]." rows="1" cols="2" class="textBoxLarge"></textarea></td>
			</tr>
			
			
			<!-- <tr>
				<td class="tdInputBox">002</td>	
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxMedium"></td>
				<td class="tdInputBox"><input type="text" name="" value="" class="textBoxM"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxM"></td>
				<td class="tdInputBox" ><select name="" class="listBoxS">
					<option>Select</option>
					<option>Yes</option>
					<option>No</option>
					</select>
				</td>	
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxM"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxM"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxM"></td>
				
				<td class="tdInputBox"><input type="text" name="" value="" class="textBoxM"></td>	
				<td class="tdInputBox" >987654</td>
				<td class="tdInputBox"><input type="text" name="" value="" class="textBoxSmall"></td>	
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxM"></td>
				
				<td class="tdInputBox" ><input id="from3" type="textbox" class="textBoxDate"></input></td>
				<td class="tdInputBox" ><input id="to3" type="textbox" class="textBoxDate"></input></td>
				
				<td class="tdInputBox" ><textarea rows="1" cols="2" class="textBoxLarge"></textarea></td>
			</tr> -->
			</table>
			<table width="100%">

					<tr class="tdOddRow">
						<td align="right" colspan="15" class="tdButtonRow">
						<a href="#" id="Add" class="button" target="_blank">Add Row</a></td>
					</tr>
					<tr class="tdOddRow">
						<td class="" colspan="15" width="9%">&nbsp;</td>
					</tr>
				</table>
	</div> --%>



<!-- 
	<div id="bodyDiv1" class="mainBodyDiv1" style="float: left; width: 100%;overflow-y:auto;overflow-x:auto;">
		<table width="100%" border="0">
			<tr>
				<td class="tdTableHead" colspan="8">OPEX Spend Analysis</td>
				<td class="tdTableHead" colspan="2">Man Day Rate</td>
				<td class="tdTableHead" >Effort</td>
				<td class="tdTableHead"></td>
				<td class="tdTableHead" colspan="2">Period</td>
				<td class="tdTableHead" ></td>
			</tr>
			<tr>
				<td class="tdSubTableHead"></td>
				<td class="tdSubTableHead" colspan="4">COST</td>
				<td class="tdSubTableHead" colspan="4">EFFORT</td>
			</tr>
			<tr>
				<td class="tdSubTableHead"></td>
				<td class="tdSubTableHead" colspan="2">PAST ESTIMATE</td>
				<td class="tdSubTableHead" colspan="2">FUTURE FORECAST</td>
				<td class="tdSubTableHead" colspan="2">PAST ESTIMATE</td>
				<td class="tdSubTableHead" colspan="2">FUTURE FORECAST</td>
			</tr>
			<tr>
				<td class="tdSubTableHead"><B>In Scope Budget Summary</B></td>
				<td class="tdSubTableHead" ><B>Planned</B></td>
				<td class="tdSubTableHead" ><B>Actual</B></td>
				<td class="tdSubTableHead" ><B>Planned</B></td>
				
				<td class="tdSubTableHead"><B>Committed</B></td>	
				<td class="tdSubTableHead" ><B>Planned</B></td>
				<td class="tdSubTableHead" ><B>Actual</B></td>
				<td class="tdSubTableHead" ><B>Planned</B></td>
				
				<td class="tdSubTableHead"><B>Committed</B></td>	
				
			</tr>
			<tr>
				<td class="tdInputBox">CAPEX CATEGORIES</td>
			</tr>
			<tr>
				<td class="tdInputBox">1. AA</td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
			</tr>
			<tr>
				<td class="tdInputBox">2. BB</td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
			</tr>
			<tr>
				<td class="tdSubTableHead" colspan="9">In Scope CAPEX Baseline</td>
			</tr>
			<tr>
				<td class="tdInputBox">OPEX CATEGORIES</td>
			</tr>
			<tr>
				<td class="tdInputBox">1. XX</td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
			</tr>
			<tr>
				<td class="tdInputBox">2. YY</td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
				<td class="tdInputBox" ><input type="text" name="" value="" class="textBoxSmall"></td>
			</tr>
			<tr>
				<td class="tdSubTableHead" colspan="9">In Scope OPEX Baseline</td>
			</tr>
			
		</table>
	</div> -->


	<!-- <div id="bodyDiv1" class="mainBodyDiv1" style="float: left; width: 100%;overflow-y:auto;overflow-x:auto;">
		<table width="100%" border="0">
			<tr>
				<td class="tdTableHead" colspan="7">Allocation</td>
			</tr>
			<tr>
				<td class="tdSubTableHead"><B>In Scope Services</B></td>
				<td class="tdSubTableHead" ><B>Capex Allocation</B></td>
				<td class="tdSubTableHead" ><B>Opex Allocation</B></td>
				<td class="tdSubTableHead" ><B>Total Allocation</B></td>
				
				<td class="tdSubTableHead"><B>GSC India</B></td>	
				<td class="tdSubTableHead" ><B>Other GSC</B></td>
				<td class="tdSubTableHead" ><B>Region</B></td>
								
			</tr>
			<tr>
				<td class="tdInputBox"> 1st level Opps</td>
				<td class="tdInputBox">0%</td>
				<td class="tdInputBox">40%</td>
				<td class="tdInputBox">0% * Capex + 40% * Opex</td>
			</tr>
			<tr>
				<td class="tdInputBox"> 2nd level Opps</td>
				<td class="tdInputBox">0%</td>
				<td class="tdInputBox">40%</td>
			</tr>
			<tr>
				<td class="tdInputBox"> Testing</td>
				<td class="tdInputBox">30%</td>
				<td class="tdInputBox">20%</td>
			</tr>
			<tr>
				<td class="tdInputBox"> Development</td>
				<td class="tdInputBox">70%</td>
				<td class="tdInputBox">0%</td>
			</tr>
			<tr>
				<td class="tdInputBox"> Status Allocated</td>
			</tr>
			<tr>
				<td class="tdInputBox"> Status Remaining</td>
			</tr>
		</table>
	</div> 
 -->
<div class="mainBodyDiv1" style="width: 100%; float: left;margin-left: 0px;">
<table width="100%" border="0" >
<tr>
<td align="center" colspan="4"  class="tdButtonRow">
<a class="button" target="_self">Reset</a>
<a class="button" target="_self" onClick="javascript:saveitSpend();">Save</a>
<a class="button" target="_self" href="defineScope.jsp">Next</a>
</tr>
</table>
</div>
	</form:form>

</div> 
</div>

