function drawGanttChart(arr_planning, arr_learn, arr_assist, arr_perform, arr_deliver,noOfLoop)
{
	  var loopCount=0;var pid=1;parentid=0;	
	  
		g.setShowRes(1); // Show/Hide Responsible (0/1)
		g.setShowDur(1); // Show/Hide Duration (0/1)
		g.setShowComp(1); // Show/Hide % Complete(0/1)
		g.setDateInputFormat('mm/dd/yyyy');
	   g.setCaptionType('Resource');  // Set to Show Caption (None,Caption,Resource,Duration,Complete)
	  if( g ) {
	    // Parameters             (pID, pName,                  pStart,      pEnd,        pColor,   pLink,          pMile, pRes,  pComp, pGroup, pParent, pOpen, pDepend, pCaption)
		
		// You can also use the XML file parser JSGantt.parseXML('project.xml',g)
		while(loopCount<noOfLoop) 
		{
			parentid=pid++;
			g.AddTaskItem(new JSGantt.TaskItem(pid, ''+(((arr_fte[loopCount][0])*eftArray[loopCount])/100), arr_planning[loopCount][0], arr_planning[loopCount][1], arr_color[0], '', 0, ''+arr_partitions[loopCount]+'-Planning',100, 0, 0, 1));
			parentid=pid++;
			g.AddTaskItem(new JSGantt.TaskItem(pid, ''+(((arr_fte[loopCount][1])*eftArray[loopCount])/100), arr_learn[loopCount][0],arr_learn[loopCount][1], arr_color[1], '', 0, ''+arr_partitions[loopCount]+'-Learning',100, 0, 0, 1,parentid));
			parentid=pid++;
			g.AddTaskItem(new JSGantt.TaskItem(pid, ''+(((arr_fte[loopCount][2])*eftArray[loopCount])/100), arr_assist[loopCount][0], arr_assist[loopCount][1], arr_color[2], '', 0, ''+arr_partitions[loopCount]+'-Assist',100, 0, 0, 1,parentid));
			parentid=pid++;
			g.AddTaskItem(new JSGantt.TaskItem(pid, ''+(((arr_fte[loopCount][3])*eftArray[loopCount])/100), arr_perform[loopCount][0], arr_perform[loopCount][1], arr_color[3], '', 0, ''+arr_partitions[loopCount]+'-Perform',100, 0, 0, 1,parentid));
			parentid=pid++;
			g.AddTaskItem(new JSGantt.TaskItem(pid, ''+(((arr_fte[loopCount][4])*eftArray[loopCount])/100), arr_deliver[loopCount][0], arr_deliver[loopCount][1], arr_color[4], '', 0, ''+arr_partitions[loopCount]+'-Deliver',100, 0, 0, 1,parentid));
			loopCount++;
		}
		document.getElementById('GanttChartDIV').style.display = "block";
	    g.Draw();	
	    g.DrawDependencies();
	  }
	  else
	  {
	    alert("not defined");
	  }
	  document.getElementById('saveChartDiv').style.display='block';
}