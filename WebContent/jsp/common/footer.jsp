<script language="JavaScript">
	function ShowHide(divId)
	{
		if(document.getElementById(divId).style.display == 'none')
		{
			document.getElementById(divId).style.display='block';
			document.getElementById("expand").setAttribute("class",'ui-icon ui-icon-minus');
		}
		else
		{
			document.getElementById(divId).style.display = 'none';
			document.getElementById("expand").setAttribute("class",'ui-icon ui-icon-plus');
			
		}
	}
</script>
<style>
.ui-icon { display: block; text-indent: -99999px; overflow: hidden; background-repeat: no-repeat; }
.ui-icon { width: 16px; height: 16px; background-image: url(../images/dataTableImages/ui-icons_222222_256x240.png); }
.ui-icon-plus { background-position: -16px -128px; }
.ui-icon-minus { background-position: -48px -128px; }
</style>

<div class="mainBodyDivwithoutLine" >

<div style="margin-left:0px;"><img src="<%=request.getContextPath()%>/images/line.png" width="100%" height="3" " align="center"/>  <span id="expand" class="ui-icon ui-icon-plus" onclick="ShowHide('footerDiv');" style="cursor:pointer;"></span> </div>
<div style="margin-left:0px; display:none;" id="footerDiv" >
<table  width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#eeeeee">
    <tr>
      <td><table width="100%" border="0" style="height: 100px;">
        <!-- <tr>
          <td width="15%">&nbsp;</td>
          <td width="20%">&nbsp;</td>
          <td width="20%">&nbsp;</td>
          <td width="20%">&nbsp;</td>
          <td width="25%">&nbsp;</td>
        </tr> -->
        <tr>
          <td height="100"><img src="<%=request.getContextPath()%>/images/eri_logo_blue.png" height="120" /></td>
          <td valign="top" class="style3"><p align="left">People<br /> 
              <span class="style8"><!-- <br /> -->
              <span class="style10">
				  <a class="hrftxt" href="http://esessmw0608.ss.sw.ericsson.se/PublishedCharts/Geographical/Ericsson.htm" target="_blank">Organization Viewer</a><br />
				  <a class="hrftxt" href="http://gp.internal.ericsson.com/wps/portal/pf/pf_default/!ut/p/b1/04_Sj9CPykssy0xPLMnMz0vMAfGjzOL9DTwNjBxNjDwNXMzNDTyDDExM3cy9jQxCDIEKIoEKDHAARwNC-r3ACvDoB1pgVOTr7JuuH1WQWJKhm5mXlq8fUZCaX5CTqh-uH4XXAhdjqAI8Fvh55Oem6hfkRlT5pAUHpDsqKgIANPAhKw!!/dl4/d5/L2dBISEvZ0FBIS9nQSEh/" target="_blank">PeopleFinder</a> <br />
				  <a class="hrftxt" href="http://internal.ericsson.com/page/hub_inside/sales/sales_marketing/tools_and_ways_of_working/global_sales_tools/index.jsp?unit=BUGS" target="_blank">BUGS Leadership Team</a><br />
				  <a class="hrftxt" href="http://internal.ericsson.com/page/hub_inside/company/organization/regions/index.jsp?unit=BUGS" target="_blank">Region Heads</a><br />
				  <a class="hrftxt" href="http://internal.ericsson.com/page/hub_inside/company/organization/regions/global_customer_units.jsp?unit=BUGS" target="_blank">GCU Heads</a><br />
				  <a class="hrftxt" href="http://internal.ericsson.com/page/hub_inside/sales/sales_marketing/tools_and_ways_of_working/ep_ways_of_working/ep_contacts.jsp?unit=BUGS" target="_blank">EP Heads</a><br />
				  <a class="hrftxt" href="http://internal.egi.ericsson.com/contacts" target="_blank">EGI Leadership Team</a><br />
				  <a class="hrftxt" href="http://internal.egi.ericsson.com/csi-adm-home" target="_blank">CSI-ADM &amp; SD&amp;D Leadership</a>
			  </span></span>
			 <!--  <br />
              <br /> -->
            </p>            </td>
          <td valign="top" class="style3"><p align="left">Process &amp; Framework <!-- <br /> -->
            <br />
            <span class="style10">
				<a class="hrftxt" href="http://internal.ericsson.com/page/hub_globalservices/support/sdp/index.jsp?unit=BUGS" target="_blank">Service Delivery &amp; Operations <br />
				<a class="hrftxt" href="https://ericoll.internal.ericsson.com/sites/Consulting_and_Systems_Integration_-_Headquarters/default.aspx" target="_blank">Global CSI Portal </a> <br />
				<a class="hrftxt" href="http://internal.ericsson.com/page/hub_inside/sales/sales_marketing/index.jsp?unit=BUGS" target="_blank">Sales &amp; Marketing Portal</a> <br />
				<a class="hrftxt" href="http://esealmw040-29.al.sw.ericsson.se/Inside/Projects/Models/propsc/index.html" target="_blank">Props-C Framework</a> <br />
				<a class="hrftxt" href="http://internal.ericsson.com/page/hub_globalservices/projects/ms_excellence/index.jsp?unit=BUGS" target="_blank">GT&amp;T Methodology</a> <br />
				<a class="hrftxt" href="http://internal.ericsson.com/page/hub_inside/company/ebp/index.jsp?unit=BUGS" target="_blank">Ericsson Business Process (EBP) </a> <br />
				<a class="hrftxt" href="https://ericoll.internal.ericsson.com/sites/Quality_CMMi_GSDC_India/default.aspx" target="_blank">GSC-I Quality Site </a> <br />
				<a class="hrftxt" href="https://ericoll.internal.ericsson.com/sites/MSTOP_Definitions_Development_Page/Wikis/Home.aspx" target="_blank">MSTOP Process</a>
			</span> <!-- <br /> -->
          </td>
          <td valign="top" class="style3"><p align="left" style="height: 3px;">Tools</p>
                      	<!-- <p align="left" class="style10"> -->
                      	<span class="style10">
            		<a class="hrftxt" href="http://toolsportal.internal.ericsson.com/" target=_blank>BUGS Tools Portal </a> <br />
              		<a class="hrftxt" href="https://ericoll.internal.ericsson.com/sites/MS_IT/Pages/MSDP%20Tools.aspx" target=_blank>MSDP Tools </a> <br />
              		<a class="hrftxt" href="" target=_blank>Exchange Rate Viewer</a> <br />
              		<a class="hrftxt" href="" target=_blank>Timezone Coverage </a> <br />
            		<a class="hrftxt" href="http://internal.ericsson.com/page/hub_inside/sales/sales_marketing/tools_and_ways_of_working/global_sales_tools/index.jsp?unit=BUGS" target=_blank>Global Sales &amp; Marketing Tools</a><br />
            		<a class="hrftxt" href="http://eripro.egi.ericsson.com/eripro/" target=_blank>ERIPRO</a>
            		</span>
           </td>
          <td valign="top" class="style3"><p align="left"></p>
          Organization
            <br />
            <!-- <p align="left" class="style10"> -->
            <span class="style10">
            <a class="hrftxt" href="http://anon.ericsson.se/eridoc/erl/objectId/09004cff802d6d8c?docno=LME-03:000553Uen&action=approved&format=ppt8" target=_blank>Ericsson Organization</a><br />
            <a class="hrftxt" href="http://internal.ericsson.com/page/hub_inside/navigation/choose_site.jsp?unit=BUGS" target=_blank>Business Units &amp; Regions </a> <br />
            <a class="hrftxt" href="http://internal.ericsson.com/page/hub_globalservices/unit/mgmt_system/organization/organization.jsp?unit=BUGS" target=_blank>BUGS Organization </a> <br />
            <a class="hrftxt" href="http://internal.ericsson.com/page/hub_globalservices/unit/units/gsd/gsdc/index.jsp?unit=BUGS" target=_blank>Global Service Centres</a> <br />
            <a class="hrftxt" href="http://internal.ericsson.com/page/hub_multimedia/unit/index.jsp?unit=BUSS" target=_blank>BUSS Organization</a> <br />
            <a class="hrftxt" href="http://internal.ericsson.com/page/hub_bcam/unit/index.jsp?unit=BCAM" target=_blank>BCAM Organization</a> <br />
            <a class="hrftxt" href="http://internal.ericsson.com/page/hub_net/unit/index.jsp?unit=BNET" target=_blank>BNET Organization</a> <br />
            </span>
           </td>
        </tr>
        
      </table>
	
	</td>
    </tr>
  </table>
</div>
</div>
