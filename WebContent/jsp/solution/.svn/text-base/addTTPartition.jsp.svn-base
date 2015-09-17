 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <table width="100%"  cellpadding="0" cellspacing="1">
  <tr class="ComontableTextTd"> <td width="210" align="left" bgcolor="#f1f1f1">
 Partition Name</td><td  align="left" bgcolor="#f1f1f1">
 FTE</td></tr>
 <c:forEach var="i" begin="0" end="${numOfPartition-1}">
 <tr class="ComontableTextTd">
    <td align="center" bgcolor="#f1f1f1"><span class="smfVal">
    	<input type="test" class="textBoxSmalldevlopment" id="partitionNameList[${i}].ttpartitionName"
			name="partitionNameList[${i}].ttpartitionName" value=""> 
      </span></td>
     <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
     <input type="test" class="textBoxSmallNumric" id="partitionNameList[${i}].ttpartitionFte"
			name="partitionNameList[${i}].ttpartitionFte" value=""> 
    </span></td>
   </tr>
   </c:forEach>
  
   <tr class="ComontableTextTd">
        <td  height="20" bgcolor="#f1f1f1"><strong>Total FTE Count</strong></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal" >
          <input type="text" class="textBoxCalculateResults" value="${totalFTE}"/>
        </span></td>
   </tr> 
   </table>
   