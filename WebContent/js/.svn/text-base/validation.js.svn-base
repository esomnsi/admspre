/*
 * trim all the unwanted blank space before and after the value of all fields within the page.
 */
function trimAll()
{
	$('[type=text]').filter(function(){
		$(this).val($.trim($(this).val()));
	});
}
/*
 * validate value of specified field
 * isBlank=1 ---> field can't be blank
 * isNumeric=1---> field must have a non negative value
 */
function validate(id,isBlank,isNumeric)
{
		var elementVal = $('#'+id).val();
		if(isBlank==1)
			if(elementVal == "")
			    return false;
		if(isNumeric==1 && elementVal != "")
		{	
			if(isNaN(elementVal) || Number(elementVal)<0)
			    return false;
		}	
		else if(isNumeric==1 && elementVal == "")
		{	
			$('#'+id).val('0.0');
		}	
		return true;
}
