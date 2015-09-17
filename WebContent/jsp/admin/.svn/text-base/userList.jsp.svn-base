<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <script type="text/javascript">
  var vTable = $('#dataTable2').dataTable( {			
		 "sPaginationType": "full_numbers",			
		 "bJQueryUI": true,
		 "fnDrawCallback": function () {
			 $('input:radio[id=accessRadio]').click(function() {
				  var val = $('input:radio[id=accessRadio]:checked').val();		
				  prev = val;
			 })	      
	       
		/* 	 $('#dataTable2 tr').click(function(event){
				    $(this).find('input[type=radio]').prop('checked', true);							    
				}) */
			 
			 $("#dataTable2 tbody td").hover(function() {
		         $(this).parents('tr').addClass('highlight');
		       }, function() {
		         $(this).parents('tr').removeClass('highlight');
		       })
		     
			}	
	});
  
  </script>
  
   <div class="adminDivdHeaderr">All Users </div>              
              <table id="dataTable2" border="0" align="center" cellpacing="1" style="width: 1100px;margin-left: 6px;">		
				<thead>		
				<tr>
					<td class="tdSubTableHead">First Name</td>
					<td class="tdSubTableHead">Last Name</td>
					<td class="tdSubTableHead">Signum Id</td>
					<td class="tdSubTableHead">Email Id</td>			
					<td class="tdSubTableHead">Contact No</td>
					<td class="tdSubTableHead">Roles</td>
					<td class="tdSubTableHead">Start Date</td>
					<td class="tdSubTableHead">End Date</td>
				</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${userList}">
					<tr class="tdOddRow">
						<td width="12%">${user.firstName}</td>
						<td width="12%">${user.lastName}</td>
						<td width="5%">${user.signumId}</td>
						<td width="15%">${user.emailId}</td>
						<td width="10%;">${user.contactNumber}</td>
						<td width="20%;">${user.roleName}</td>
						<td width="15%;">${user.startDate}</td>
						<td width="15%;">${user.endDate}</td>
						
					</tr>
					</c:forEach>
				</tbody>	
			</table>