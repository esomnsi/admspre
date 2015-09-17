<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- <link type="text/css" href="../css/menuStyle.css" rel="stylesheet">
<link rel="shortcut icon" href="../images/favicon.ico" /> -->

<link rel="stylesheet" type="text/css" href="../css/ddsmoothmenu.css" />
<link rel="stylesheet" type="text/css" href="../css/ddsmoothmenu-v.css" />
<!-- <script type="text/javascript" src="../js/jquery.min.js"></script> -->
<script type="text/javascript" src="../js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="../js/drop-down.js"></script>

<script type="text/javascript">
$(document).ready(function() {
$("ul#topnav li").hover(function() {
//Hover over event on list item
	$(this).find("span").show(); //Show the subnav
} , function() { //on hover out...
	$(this).css({ 'background' : 'none'}); //Ditch the background
	$(this).find("span").show(); //Hide the subnav
});
});
</script>


<%-- <div class="container">	
	<ul id="topnav">
    	<li>
        	<a href="../opportunity/landingPage" class="imghover">
        		<img src="../images/homeIcon.png" width="21" height="21" />
        	</a>
            <span style="display: none;"></span>
        </li>
        <sec:authorize access="hasAnyRole('ROLE_SUPER_USER','ROLE_SOLUTION_MANAGER','ROLE_APPROVER')">
        <li class="">
            <a href="../action/initiateApproval" class="rightborder">My Action</a>
            <span style="display: none;"></span>
        </li>
        
        <li>
            <a href="../opportunity/displayOpportunity">Create NewSolution</a>
            <span style="display: block;">
                <a href="../opportunity/displayOpportunity"><b>1.</b>Opportunity</a> |
                <a href="../opportunity/defineScope"><b>2.</b>Define Scope</a> |
                <a href="../solution/inputBaseline"><b>3.</b>Input Baseline</a> |
                <a href="../solution/complexityAdjuster"><b>4.</b>Adjust Complexity</a> | 
                <a href="../solution/productivityLevers"><b>5.</b>Apply Solution Levers</a> |
                <a href="../solution/reviewFTE"><b>6.</b>Review FTE</a> |
                <a href="../solution/ttDetail"><b>7.</b>T&T Design</a> |
                <a href="../solution/createStaffingPlan"><b>8.</b>Staffing Plan</a> |
                <a href="../solution/labourCost"><b>9.</b>Labor Cost</a> |
                <a href="../solution/loadNLC"><b>10.</b>Non Labor Cost</a> |
                <a href="../solution/manageADRGrid"><b>11.</b>Include A-D-R</a> |
                <a href="../report/ViewReports"><b>12.</b>Reports</a>
            </span>
        </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_SUPER_USER','ROLE_SOLUTION_MANAGER','ROLE_APPROVER','ROLE_GUEST')">
        <li>
            <a href="../search/openSolution">Open Opportunity</a>
            <span style="display: none;">
               
            </span>
        </li>
     
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_SUPER_USER','ROLE_APPROVER')">
        <!-- <li>
            <a href="../approval/approvalDetails">Approval</a>
            <span style="display: none;"></span>
            <!--  <a href="#">Approval</a>
            <span style="display: none;">
                <a href="../common/undercons">Opportunity Summary</a> |
                <a href="../common/undercons">Steady State Solution</a> |
                <a href="../common/undercons">Skills Summary</a> |
                <a href="../common/undercons">Transformation Summary</a> |
                <a href="../common/undercons">Top 3 ADRs</a> |
                <a href="../common/undercons">Financial Summary</a>

            </span>
         </li> -->
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_SUPER_USER')">
         <li>
            <a href="#">Admin</a>
            <span style="display: none;">
               <a href="../admin/manageUserAccess"><b>1.</b>User Management</a> |
                <a href="../admin/solutionManagement"><b>2.</b>Solution Management</a> |
               <a href="../admin/initApprovalChange"><b>3.</b>Approver Management</a> |
               <a href="../admin/rateManagement"><b>4.</b>Financial Management</a> |
               <a href="../admin/jobRoleManagement"><b>5.</b>JobRole Management</a>
               <a href="../admin/serviceElementManagement"><b>6.</b>Service Element Management</a>
               </span>
        </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_SUPER_USER','ROLE_SOLUTION_MANAGER','ROLE_APPROVER','ROLE_GUEST')">
        <li class="rightborder">
            <a href="#">Dashboard</a>
            <span style="display: none;">
                <!-- <a href="../solution/inputBaseline">List Solutions</a> | -->
               	<a href="../common/undercons">List Solutions</a> |
               <!--  <a href="dashboardStat.jsp">Deal Statistics</a> | -->
               	<a href="../common/undercons">Deal Statistics</a> | 
                <!-- <a href="dashboardStat.jsp">Signing Potential Report</a> | -->
                <a href="../common/undercons">Signing Potential Report</a> |
                <a href="../common/undercons">Scope Analysis Report</a> |
               <!--  <a href="dashStaffingPyramidReport.jsp">Staff Pyramid Store</a>| -->
               	<a href="../common/undercons">Staff Pyramid Store</a>|
                <a href="../common/undercons">Scope Analysis</a>|
                <a href="../common/undercons">Daily Rate Analysis</a>
               
            </span>
        </li>
     </sec:authorize>
        
        
    </ul>
</div> --%>
<div id="smoothmenu1" class="ddsmoothmenu">
<ul>
<li><a href="../opportunity/landingPage">Home</a></li>
<li><a href="../action/initiateApproval">My Action</a></li>
<li><a href="#">Create New Solution</a>
  <ul>
                <li><a href="../opportunity/displayOpportunity"><b>1.</b>Opportunity</a></li>
                <li><a href="../opportunity/defineScope"><b>2.</b>Define Scope</a></li>
                <li><a href="../solution/inputBaseline"><b>3.</b>Input Baseline</a></li>
                <li><a href="../solution/complexityAdjuster"><b>4.</b>Adjust Complexity</a></li>
                <li><a href="../solution/productivityLevers"><b>5.</b>Apply Solution Levers</a></li>
                <li><a href="../solution/reviewFTE"><b>6.</b>Review FTE</a></li>
                <li><a href="../solution/ttDetail"><b>7.</b>T&T Design</a></li>
                <li><a href="../solution/createStaffingPlan"><b>8.</b>Staffing Plan</a></li>
                <li><a href="../solution/labourCost"><b>9.</b>Labor Cost</a></li>
                <li><a href="../solution/loadNLC"><b>10.</b>Non Labor Cost</a></li>
                <li><a href="../solution/manageADRGrid"><b>11.</b>Include A-D-R</a></li>
                <li><a href="../report/ViewReports"><b>12.</b>Reports</a></li>
  
  </ul>
</li>
<li><a href="#">Admin</a>
  <ul>
               <li><a href="../admin/manageUserAccess"><b>1.</b>User Management</a></li>
               <li><a href="../admin/solutionManagement"><b>2.</b>Solution Management</a></li>
               <li><a href="../admin/initApprovalChange"><b>3.</b>Approver Management</a></li>
               <li><a href="../admin/jobRoleManagement"><b>5.</b>JobRole Management</a></li>
               <li><a href="../admin/serviceElementManagement"><b>6.</b>Service Element Management</a></li>
  </ul>
</li>
<li><a href="../search/openSolution">Open Solution</a></li>
<!--<li><a href="#">Approval</a>
  <ul>
  <li><a href="../common/undercons">Opportunity Summary</a></li>
  <li><a href="../common/undercons">Steady State Solution</a></li>
              <li>  <a href="../common/undercons">Skills Summary</a></li>
             <li>   <a href="../common/undercons">Transformation Summary</a></li>
             <li>   <a href="../common/undercons">Top 3 ADRs</a></li>
             <li>   <a href="../common/undercons">Financial Summary</a></li>
  
  </ul>
</li>-->
<li><a href="http://www.dynamicdrive.com/style/">Dashboard</a>
  <ul>
                <li><a href="../solution/inputBaseline">List Solutions</a></li>
                <li><a href="dashboardStat.jsp">Deal Statistics</a></li>
                <li><a href="dashboardStat.jsp">Signing Potential Report</a></li>
                <li><a href="../common/undercons">Scope Analysis Report</a></li>
                <li><a href="dashStaffingPyramidReport.jsp">Staff Pyramid Store</a></li>
               <li> <a href="../common/undercons">Scope Analysis</a></li>
                <li><a href="../common/undercons">Daily Rate Analysis</a></li>
  
  </ul>


</li>



</ul>
<br style="clear: left" />
</div>
