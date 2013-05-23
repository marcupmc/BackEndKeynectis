/**
 * 
 */

$(function() {
	$("#formButton").click(function() {

		$("#types").attr("class", "tab-pane active");
		$("#tabTypes").show();		
		$("#tabTypes").attr("class", "active");
		$("#tabConst").attr("class", "");

	});
});
