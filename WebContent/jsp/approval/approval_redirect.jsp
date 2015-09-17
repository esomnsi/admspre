<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="../js/jquery-1.js"></script>
<script type="text/javascript" src="../js/jquery_003.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script>
$(document).ready(function() {
	setTimeout("location.href='../approval/home'", 10000);
});
</script>
</head>
<div class="commonHeader">Message</div>
<div id="bodyDiv" class="subminApprovalTabDiv">      

<!-- <div id="m" class="message"><div id="messageinfo">${message}</div> You will be redirected to home page in 10 seconds
</div> -->
<c:if test="${not empty successMessage}">
    <div class="actionPerformedMessage" align="left">${successMessage} You will be redirected to home page in 10 seconds</div>
</c:if>

<c:if test="${not empty errorMessage}">
<div class="errorMessageDisp" align="left">${errorMessage} You will be redirected to home page in 10 seconds</div>
</c:if> 	
</div>




