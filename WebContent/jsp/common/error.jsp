
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  java scripts -->
<script type="text/javascript" src="../js/it_mssp.js">
	
</script>
<script type="text/javascript" src="../js/jquery-1.8.2.js">
	
</script>
<script type="text/javascript" src="../js/jquery.collapsiblepanel.js">
	
</script>
<script type="text/javascript" src="../js//jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../js/validation.js"></script>
<script type="text/javascript" src="../js/jquery_tab.js"></script>

<!-- css files -->
<link rel="stylesheet" type="text/css" href="../css/it_mssp.css" />
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css"
	href="../css/diQuery-collapsiblePanel.css" />
<link rel="stylesheet" type="text/css" href="../css/ui.all.css" />
<link rel="stylesheet" type="text/css" href="../css/bannerStyle.css" />
<link rel="stylesheet" type="text/css"
	href="../css/landingPageStyle.css" />
</head>
<div class="mainBodyDivwithoutLine">
	<jsp:include page="/jsp/common/header.jsp"></jsp:include>
	<%-- <jsp:include page="/jsp/common/menu.jsp"></jsp:include> --%>

	<div id="bodyDiv" class="mainBodyDiv">

		<table align="center" border=0 width=100%>

			<tr>
				<td style="width: 30%;"><img alt=""
					src="<%=request.getContextPath()%>/images/error.jpg"></td>
				<td style="width: 70%;"><h3 class="style9">Oops... You are experiencing
						background unavoidable circumstances !!!</h3></td>
			</tr>
			<tr>
				<td style="width: 30%;"></td>
				<td style="width: 70%;text-align: right;">
					<a style="text-decoration: none;" href="../search/openSolution">Go back to Open Opportunity/Solution</a>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="/jsp/common/footer.jsp"></jsp:include>
</div>