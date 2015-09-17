$(document)
		.ready(
				function() {
					$(document)
							.ajaxStart(
									function() {
										$
												.blockUI({
													message : '<h1><img src="../images/ajax-loader.gif" /><font size="3px;"> Please wait,it may take few seconds...</font></h1>',
													css : {
														border : '3px solid #00477D',
														padding : '10px',
														backgroundColor : 'white',
														'-webkit-border-radius' : '10px',
														'-moz-border-radius' : '10px',
														opacity : .8,
														width : '300px',
														height : '80px',
														color : 'black'
													}
												});
									});

					$(document).ajaxStop(function() {
						$.unblockUI();
					});
				});

function isNumeric(eventObj) {
	var keycode;

	if (eventObj.keyCode) // For IE
		keycode = eventObj.keyCode;
	else if (eventObj.which)
		keycode = eventObj.which; // For FireFox
	else
		keycode = eventObj.charCode; // Other Browser

	if (keycode != 8 && keycode != 9) // if the key is the backspace key
	{
		if (keycode < 48 || keycode > 57) // if not a number
			return false; // disable key press
		else
			return true; // enable key press
	}
}

function isDecimal(eventObj) {
	var keycode;

	if (eventObj.keyCode) // For IE
		keycode = eventObj.keyCode;
	else if (eventObj.which)
		keycode = eventObj.which; // For FireFox
	else
		keycode = eventObj.charCode; // Other Browser

	if (keycode != 8 && keycode != 9 && keycode != 46) // if the key is the
	// backspace key, dot,
	{
		if (keycode < 48 || keycode > 57) // if not a number
			return false; // disable key press
		else
			return true; // enable key press
	}
}

function isNumericWithDecimal(eventObj, obj) {
	var keycode;
	try {
		if (obj == undefined) {
			return false;
		}

		if (eventObj.keyCode) // For IE
			keycode = eventObj.keyCode;
		else if (eventObj.which)
			keycode = eventObj.which; // For FireFox
		else
			keycode = eventObj.charCode; // Other Browser

		if (keycode != 8 && keycode != 9) // if the key is the backspace key
		{
			if (keycode == 46 && obj.value.indexOf(".") < 0) {
				return true;
			}
			if (keycode < 48 || keycode > 57) // if not a number
				return false; // disable key press
			else
				return true; // enable key press
		}
	} catch (e) {
		// alert(e.message);
	}
}

function MM_preloadImages() { // v3.0
	var d = document;
	if (d.images) {
		if (!d.MM_p)
			d.MM_p = new Array();
		var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
		for (i = 0; i < a.length; i++)
			if (a[i].indexOf("#") != 0) {
				d.MM_p[j] = new Image;
				d.MM_p[j++].src = a[i];
			}
	}
}

function MM_swapImgRestore() { // v3.0
	var i, x, a = document.MM_sr;
	for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
		x.src = x.oSrc;
}

function MM_findObj(n, d) { // v4.01
	var p, i, x;
	if (!d)
		d = document;
	if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
		d = parent.frames[n.substring(p + 1)].document;
		n = n.substring(0, p);
	}
	if (!(x = d[n]) && d.all)
		x = d.all[n];
	for (i = 0; !x && i < d.forms.length; i++)
		x = d.forms[i][n];
	for (i = 0; !x && d.layers && i < d.layers.length; i++)
		x = MM_findObj(n, d.layers[i].document);
	if (!x && d.getElementById)
		x = d.getElementById(n);
	return x;
}

function MM_swapImage() { // v3.0
	var i, j = 0, x, a = MM_swapImage.arguments;
	document.MM_sr = new Array;
	for (i = 0; i < (a.length - 2); i += 3)
		if ((x = MM_findObj(a[i])) != null) {
			document.MM_sr[j++] = x;
			if (!x.oSrc)
				x.oSrc = x.src;
			x.src = a[i + 2];
		}
}

function MM_goToURL() { // v3.0
	var i, args = MM_goToURL.arguments;
	document.MM_returnValue = false;
	for (i = 0; i < (args.length - 1); i += 2)
		eval(args[i] + ".location='" + args[i + 1] + "'");
}

function formatInput(inputVal, id) {
	var tmp = inputVal.value;
	if (isNaN(tmp) || tmp < 0) {
		document.getElementById(id).value = tmp;
	} else if (tmp == "" || tmp == null) {
		document.getElementById(id).value = parseFloat(0).toFixed(2);
	} else {
		tmp = parseFloat(tmp).toFixed(2);
		document.getElementById(id).value = tmp;
	}
};

function loadMessageInDiv(messageText, messageDivName, messageDivClassName) {
	$("#" + messageDivName).show();
	$("#" + messageDivName).addClass(messageDivClassName);
	$("#" + messageDivName).html(messageText);
	$('html, body').animate({
		scrollTop : ($("#" + messageDivName).offset().top - 60)
	}, 500);
	return false;
}