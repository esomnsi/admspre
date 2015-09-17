	var selectedLink;
	var comeFromSubmenuClick = false;
	var submenuActivated = false;
	var subAnchorClicked = false;
	var menuAlignment = 'left';	// Align menu to the left or right?		
	var topMenuSpacer = 0; // Horizontal space(pixels) between the main menu items	
	var activateSubOnClick = false; // if true-> Show sub menu items on click, if false, show submenu items onmouseover
	var leftAlignSubItems = false; 	// left align sub items t
	
	var lastClickedItem = false;
	var lastSubClicked = false;
	var clickCount = 0;
	
	var activeMenuItem = false;	// Don't change this option. It should initially be false
	var activeTabIndex = 0;	// Index of initial active tab	(0 = first tab) - If the value below is set to true, it will override this one.
	var rememberActiveTabByCookie = true;	// Set it to true if you want to be able to save active tab as cookie
	
	var MSIE = navigator.userAgent.indexOf('MSIE')>=0?true:false;
	var Opera = navigator.userAgent.indexOf('Opera')>=0?true:false;
	var navigatorVersion = navigator.appVersion.replace(/.*?MSIE ([0-9]\.[0-9]).*/g,'$1')/1;
		

	function Get_Cookie(name) { 
	   var start = document.cookie.indexOf(name+"="); 
	   var len = start+name.length+1; 
	   if ((!start) && (name != document.cookie.substring(0,name.length))) return null; 
	   if (start == -1) return null; 
	   var end = document.cookie.indexOf(";",len); 
	   if (end == -1) end = document.cookie.length; 
	   return unescape(document.cookie.substring(len,end)); 
	} 
	// This function has been slightly modified
	function Set_Cookie(name,value,expires,path,domain,secure) { 
		expires = expires * 60*60*24*1000;
		var today = new Date();
		var expires_date = new Date( today.getTime() + (expires) );
	    var cookieString = name + "=" +escape(value) + 
	       ( (expires) ? ";expires=" + expires_date.toGMTString() : "") + 
	       ( (path) ? ";path=" + path : "") + 
	       ( (domain) ? ";domain=" + domain : "") + 
	       ( (secure) ? ";secure" : ""); 
	    document.cookie = cookieString; 
	}	
	
	function wait(msecs)
	{
		var start = new Date().getTime();
		var cur = start
		while(cur - start < msecs)
		{
			cur = new Date().getTime();
		}
	}
	
	function callFunc(){
		
		if((this.id) == (lastClickedItem.id)){
			document.getElementById(this.id).className = 'activeMenuItem';
			var theId_0 = this.id.replace(/[^0-9]/g,'');
			document.getElementById('submenu_'+theId_0).style.display='block';
		}else{			
			
			document.getElementById(lastClickedItem.id).className = 'activeMenuItem';
			document.getElementById(this.id).className = 'inactiveMenuItem';
			var theId = this.id.replace(/[^0-9]/g,'');
			var theId_1 = lastClickedItem.id.replace(/[^0-9]/g,'');
			document.getElementById('submenu').onmouseout = function(){
				document.getElementById('submenu_'+theId).onmouseover = function(){
					document.getElementById('submenu_'+theId_1).style.display='none';
					document.getElementById('submenu_'+theId).style.display='block';	
				}
				
				document.getElementById('submenu_'+theId_1).style.display='block';
				document.getElementById('submenu_'+theId).style.display='none';				
			}		
			
			if(!document.getElementById('submenu').onmouseover){
				
				document.getElementById('submenu_'+theId).onmouseover = function(){
					document.getElementById('submenu_'+theId_1).style.display='none';
					document.getElementById('submenu_'+theId).style.display='block';	
				}
				document.getElementById('submenu_'+theId_1).style.display='block';
				document.getElementById('submenu_'+theId).style.display='none';	
			}
			
		}		
	}
	
	
	
	function showHide()
	{
		var theId_1 = lastClickedItem.id.replace(/[^0-9]/g,'');
		document.getElementById('submenu_'+theId_1).style.display='none';
		if(activeMenuItem){		
			activeMenuItem.className = 'inactiveMenuItem'; 	
			var theId = activeMenuItem.id.replace(/[^0-9]/g,'');
			document.getElementById('submenu_'+theId).style.display='none';
			var img = activeMenuItem.getElementsByTagName('IMG');
			if(img.length>0)img[0].style.display='none';			
		}
		
		var img = this.getElementsByTagName('IMG');
		if(img.length>0)img[0].style.display='inline';
		activeMenuItem = this;	
		this.className = 'activeMenuItem';
		var theId = this.id.replace(/[^0-9]/g,'');
		document.getElementById('submenu_'+theId).style.display='block';
		if(rememberActiveTabByCookie){
			document.getElementById('submenu').onclick = function(){
				Set_Cookie('dhtmlgoodies_tab_menu_tabIndex','index: ' + ((theId)-1),100);
				return;
			}
			
		}	
		
		document.getElementById(this.id).onmouseout = callFunc;
	}
	
	
	function initMenu()
	{
		var mainMenuObj = document.getElementById('mainMenu');
		var menuItems = mainMenuObj.getElementsByTagName('A');

		 var sfEls = document.getElementById("navbar").getElementsByTagName("li");
   		for (var i=0; i<sfEls.length; i++) {
   			   sfEls[i].onmouseover=function() {
    			   this.className+="hover";    			   
     		  	}
   			   
   			  		   
    		    sfEls[i].onclick=function() {
    		       this.className+="current";
    			   lastSubClicked = sfEls[i];       			   
    			 }
    		    
    			sfEls[i].onmouseout=function() {    			
    				  this.className=this.className.replace(new RegExp("hover"), "");      				 
       			}      			
       			
  		 }
		if(document.all){
			mainMenuObj.style.visibility = 'hidden';
			document.getElementById('submenu').style.visibility='hidden';
		}		
		if(rememberActiveTabByCookie){
			var cookieValue = Get_Cookie('dhtmlgoodies_tab_menu_tabIndex') + '';
			cookieValue = cookieValue.replace(/[^0-9]/g,'');
			
			var cookieValue1 = Get_Cookie('dhtmlgoodies_tab_menu_click_tabIndex') + '';
			cookieValue1 = cookieValue1.replace(/[^0-9]/g,'');
			if(cookieValue == cookieValue1){
				if(cookieValue.length>0 && cookieValue<menuItems.length){
					activeTabIndex = cookieValue/1;					
				}	
			}else{
				if(cookieValue1.length>0){
					activeTabIndex = cookieValue1/1;
				}
			}
		}
		
		var currentLeftPos = 15;
		
		for(var no=0;no<menuItems.length;no++){			
			if(activateSubOnClick){
				menuItems[no].onclick = showHide;
			}else {
				menuItems[no].onmouseover = showHide;
			}
		
						
			menuItems[no].id = 'mainMenuItem' + (no+1);
			if(menuAlignment=='left')
				menuItems[no].style.left = currentLeftPos + 'px';
			else
				menuItems[no].style.right = currentLeftPos + 'px';
			currentLeftPos = currentLeftPos + menuItems[no].offsetWidth + topMenuSpacer; 
			
			var img = menuItems[no].getElementsByTagName('IMG');
			if(img.length>0){
				img[0].style.display='none';
				if(MSIE && !Opera && navigatorVersion<7){
					img[0].style.bottom = '-1px';
					img[0].style.right = '-1px';
				}
			}
			
			if(no==activeTabIndex){
				menuItems[no].className='activeMenuItem';
				activeMenuItem = menuItems[no];
				lastClickedItem = activeMenuItem;
				var img = activeMenuItem.getElementsByTagName('IMG');
				if(img.length>0)img[0].style.display='inline';	
							
			}else menuItems[no].className='inactiveMenuItem';
			if(!document.all)menuItems[no].style.bottom = '-1px';
			if(MSIE && navigatorVersion < 6)menuItems[no].style.bottom = '-2px';
		}		
		
		var mainMenuLinks = mainMenuObj.getElementsByTagName('A');		
		var subCounter = 1;
		var parentWidth = mainMenuObj.offsetWidth;
		while(document.getElementById('submenu_' + subCounter)){

			var subItem = document.getElementById('submenu_' + subCounter);
			if(leftAlignSubItems){
				// No action
			}else{				
				var leftPos = mainMenuLinks[subCounter-1].offsetLeft;
				//document.getElementById('submenu_'+subCounter).style.paddingLeft =  leftPos + 'px';
				subItem.style.position ='absolute';
				if(subItem.offsetWidth > parentWidth){
					leftPos = leftPos - Math.max(0,subItem.offsetWidth-parentWidth); 	
				}
				//subItem.style.paddingLeft =  leftPos + 'px';
				subItem.style.paddingLeft = "0px";
				subItem.style.position ='absolute';
			}
			if(subCounter==(activeTabIndex+1)){
				subItem.style.display='block';
			}else{
				subItem.style.display='none';
			}
			
			subCounter++;
		}
		if(document.all){
			mainMenuObj.style.visibility = 'visible';
			document.getElementById('submenu').style.visibility='visible';
		}		
		document.getElementById('submenu').style.display='block';	
		
		var cookieValue = Get_Cookie('dhtmlgoodies_tab_menu_anchor_tabIndex') + '';
		cookieValue = cookieValue.replace("index: ",'');
		if(cookieValue!=null){
			document.getElementById(cookieValue).style.color = "#00477D";
			document.getElementById(cookieValue).style.textDecoration = "underline";
		}
		
		var menuFlag = Get_Cookie('dhtmlgoodies_tab_menu_flag') + '';
		menuFlag = menuFlag.replace("index: ",'');
				
	}
	window.onload = initMenu;
	


 function highlight(e){
	 comeFromSubmenuClick = true;
	 Set_Cookie('dhtmlgoodies_tab_menu_anchor_tabIndex','index: ' + e,100);
	 var submenu = e.substring(0,9);	 
	 var cookieValue = submenu.replace(/[^0-9]/g,'');
	 var mainMenu = "mainMenuItem" + (parseInt(cookieValue)) ;
	 var c = (parseInt(cookieValue))-1;
	 Set_Cookie('dhtmlgoodies_main_menu_tabIndex','index: ' + mainMenu,100);
	 Set_Cookie('dhtmlgoodies_sub_menu_tabIndex','index: ' + submenu,100);
	 Set_Cookie('dhtmlgoodies_tab_menu_flag','index: ' + comeFromSubmenuClick,100);
	 Set_Cookie('dhtmlgoodies_tab_menu_click_tabIndex','index: ' + c,100);
	}
 
 
 function showSelectionColor(ele,anchor){
	 
	// alert(ele);
	 document.getElementById(anchor.id).style.backgroundColor = "#E0E0E0";
	 var sfEls = document.getElementById(ele).getElementsByTagName("ul");
	 for(var i=0;i<sfEls.length;i++){
		 var a = document.getElementById(ele).getElementsByTagName("li");
		 for(var j=0;j<a.length;j++){
			// alert(a[j].id);
			var k = document.getElementById(a[j].id).getElementsByTagName("a");
			for(var t=0;t<k.length;t++){
				//alert(k[t].id);
				document.getElementById(k[t].id).style.backgroundColor = "#E0E0E0";
			}
		 }
	 }
	 
 }
 
 
 function resetColor(e){
	 document.getElementById(e).style.backgroundColor = "#A4A4A4";
 }
 
 function keepSelected(id){
	 document.getElementById(id).style.backgroundColor = "#E0E0E0";
 }