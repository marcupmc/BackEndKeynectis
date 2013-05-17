/**
 * 
 */
function nextTab()
{
	$("#nextTab").click(
		function()
		{
			var sel = $('#authority').val();
			switch (sel)
			{
				case "1":
					$("#constInner").load("paramConstKEY.jsp");
					break;
				case "2":
					$("#constInner").load("paramConstDICTAO.jsp");
					break;
			}
		});
}