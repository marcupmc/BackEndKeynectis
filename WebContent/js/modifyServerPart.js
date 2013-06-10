/**
 * 
 */

$(function() 
		{
	$("#tabAuth").hide();
	$('#serverPart').hide();
	$('input:radio').click(function() 
			{

		if((this.checked) && ($(this).val() == "ftpSave"))
		{
			$('#serverPart').show();
		}
		else
			$('#serverPart').hide();

	});
	
	/*$(':radio').click(function()
			{
		try 
		{
			this.blur();
			this.focus();
		}
		catch (e)
		{
			
		}
			});*/
});
