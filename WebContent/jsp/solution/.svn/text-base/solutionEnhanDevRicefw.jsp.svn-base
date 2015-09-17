<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script type="text/javascript">




$(document).ready (function () { 
	  $('#FixedCapacityPanel').hide();
 $('input[name=type1]:radio').change(function() {
       var selValue = $('input[name=type1]:checked').val(); 
       if(selValue == 1){
    	
    	   $('#RICEFWPanel').show();
    	   $('#FixedCapacityPanel').hide();
    	   
    	   
       }else  if(selValue ==2){
    	 
    	   $('#RICEFWPanel').hide();
    	   $('#FixedCapacityPanel').show();
    	   
    	 
       }else{
    	   $('#FixedCapacityPanel').show();
    	   $('#RICEFWPanel').show();
    	 
       }
    });
 
 

});

</script>


<table width="1080" border="0" cellspacing="0" cellpadding="0" class="tdLabelLeft">
  <tr>
    <td width="19%">Select Estimation Model</td>
    <td width="18%"><strong>
      <input type="radio" name="type1" id="type" value="1" checked/>
    RICEFW </strong></td>
    <td width="20%"><strong>
      <input type="radio" name="type1" id="type" value="2"/>
      Fixed Capacity </strong></td>
    <td width="20%"><strong>
      <input type="radio" name="type1" id="type" value="3" />
      Both</strong></td>
     <td width="22%">  </td>
  </tr>
  <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
</table>


<div style="overflow:scroll; height:400px;"	>
<div id="RICEFWPanel" style="display:block;"  width="100%">
 <jsp:include page="solutionRICEFW.jsp"></jsp:include>

</div>
<div id="FixedCapacityPanel" width="100%">
 <jsp:include page="solutionFixedCapacity.jsp"></jsp:include>
</div>

</div>