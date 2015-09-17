<script type="text/javascript">
        $(document).ready(function() {

             $("#AddRow").click(function() {
            	var row = $('#dataTable1 tbody>tr:last').clone(true);
               	$("td input:text", row).val("");
               	$("td input:checkbox", row).attr('checked',false);
               	row.insertAfter('#dataTable1 tbody>tr:last');
              	return false;
            });
        });
    </script>

<div id="bodyDiv1" class="mainBodyDivwithoutLine">
<table id="dataTable1" border="1" style="width: 100%;">
	<tr style="width: 100%;">
		<td style="width: 5%;">Sl No.</td>
		<td style="width: 35%;">Role (As Per Rate Card)</td>
		<td>FTE</td>
		<td>Remarks</td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>

</table>
<table>
	<tr>
		<td class="button" id="AddRow">Add Row</td>
	</tr>
</table>
</div>