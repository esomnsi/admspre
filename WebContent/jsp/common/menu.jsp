<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link type="text/css" href="../css/menuStyle.css" rel="stylesheet">
<link rel="shortcut icon" href="../images/favicon.ico" />

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


<div class="container">	
	<ul id="topnav">
	<sec:authorize access="hasAnyRole('ROLE_SUPER_USER','ROLE_SOLUTION_MANAGER')">
    		<li>
        		<a href="../opportunity/landingPage" class="imghover">
        			<img src="../images/homeIcon.png" width="21" height="21" />
        		</a>
            	<span style="display: none;"></span>
        	</li>
	</sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_SUPER_USER','ROLE_SOLUTION_MANAGER','ROLE_APPROVER')">
        <li class="">
            <a href="../action/initiateApproval" class="rightborder">My Action</a>
        
        	
            <sec:authorize access="hasAnyRole('ROLE_SUPER_USER','ROLE_SOLUTION_MANAGER')">
            <span style="display: none;">
               
            </span>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_APPROVER')">
            <span style="display: block;">
               
            </span>
            </sec:authorize>
           
            
        </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_SUPER_USER','ROLE_SOLUTION_MANAGER')">
        <li>
            <a href="../opportunity/displayOpportunity">Create NewSolution</a>
            <span style="display: block;">
                <a href="../opportunity/displayOpportunity">Opportunity</a>
                <a href="../opportunity/defineScope">Define Scope</a>
                <a href="../solution/productEstimation">Input Baseline</a>
                <!-- <a href="../solution/complexityAdjuster">Adjust Complexity</a> --> 
                <a href="../solution/solutionLever">Apply Solution Levers</a>
                <a href="../solution/reviewUpdatedFTE">Review FTE</a>
                <a href="../solution/ttDetail">T&T Design</a>
                <a href="../solution/createStaffingPlan">Staffing Plan</a>
                <a href="../solution/labourCost">Labor Cost</a>
                <a href="../solution/loadNLC">Non Labor Cost</a>
                <a href="../solution/manageADRGrid">Include A-D-R</a>
                <a href="../report/ViewReports">Reports</a>
            </span>
        </li>
	</sec:authorize>
       <%--  </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_SUPER_USER','ROLE_SOLUTION_MANAGER','ROLE_APPROVER','ROLE_GUEST')"> --%>
        <sec:authorize access="hasAnyRole('ROLE_SUPER_USER','ROLE_SOLUTION_MANAGER','ROLE_GUEST')">
        <li>
            <a href="../search/openSolution">Open Opportunity</a>
            <sec:authorize access="hasAnyRole('ROLE_SUPER_USER','ROLE_SOLUTION_MANAGER')">
            <span style="display: none;">
               
            </span>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_GUEST')">
            <span style="display: block;">
               
            </span>
            </sec:authorize>
        </li>
     </sec:authorize>
     
        <%-- </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_SUPER_USER','ROLE_APPROVER')"> --%>
        <!-- <li>
            <a href="../approval/approvalDetails">Approval</a>
            <span style="display: none;"></span>
            <!--  <a href="#">Approval</a>
            <span style="display: none;">
                <a href="../common/undercons">Opportunity Summary</a>
                <a href="../common/undercons">Steady State Solution</a>
                <a href="../common/undercons">Skills Summary</a>
                <a href="../common/undercons">Transformation Summary</a>
                <a href="../common/undercons">Top 3 ADRs</a>
                <a href="../common/undercons">Financial Summary</a>

            </span>
         </li> -->
       <%--  </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_SUPER_USER')"> --%>
	<sec:authorize access="hasAnyRole('ROLE_SUPER_USER')">
         <li>
            <a href="#">Admin</a>
            <span style="display: none;">
               <a href="../admin/manageUserAccess">User Management</a>
                <a href="../admin/solutionManagement">Solution Management</a>
               <a href="../admin/initApprovalChange">Approver Management</a>
               <a href="../admin/rateManagement">Financial Management</a>
             <a href="../admin/serviceJobRoleManagement">Job Role & Service Management</a> 

               </span>
        </li>
</sec:authorize>
        <%-- </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_SUPER_USER','ROLE_SOLUTION_MANAGER','ROLE_APPROVER','ROLE_GUEST')"> --%>
        <!-- <li class="rightborder">
            <a href="#">Dashboard</a>
            <span style="display: none;">
                <a href="../solution/inputBaseline">List Solutions</a>
               	<a href="../common/undercons">List Solutions</a>
                <a href="dashboardStat.jsp">Deal Statistics</a>
               	<a href="../common/undercons">Deal Statistics</a> 
                <a href="dashboardStat.jsp">Signing Potential Report</a>
                <a href="../common/undercons">Signing Potential Report</a>
                <a href="../common/undercons">Scope Analysis Report</a>
                <a href="dashStaffingPyramidReport.jsp">Staff Pyramid Store</a>
               	<a href="../common/undercons">Staff Pyramid Store</a>
                <a href="../common/undercons">Scope Analysis</a>
                <a href="../common/undercons">Daily Rate Analysis</a>
               
            </span>
        </li> -->
    <%--  </sec:authorize> --%>
        
        
    </ul>
</div>
