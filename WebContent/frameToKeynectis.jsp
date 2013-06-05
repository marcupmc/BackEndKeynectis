<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>



</head>
<body>

<%-- 	<% --%>

<%-- 	%> --%>
<!-- 	<p>hey</p> -->
	<p>
		<%-- 	<%=blob %> --%>
	</p>

	<form id="myForm" method="POST"
		action="https://keynectis.kwebsign.net/QS/Page1V2">
		<!-- 		action="https://keynectis.kwebsign.net/QS/Page1V2?page=%3Cscript%3Ewindow.document.forms%5B0%5D.submit%28%29%3B%3C%2Fscript%3E"> -->
		<input type="hidden" name="appid" value="ZZDEMAV1"> <input
			type="hidden" id="blob" name="blob" value="">
	</form>

	<div id="result"></div>

	<script>
	function go() {

		document.getElementById("myForm").submit();
	}

// 	/* attach a submit handler to the form */
// 	function toto() {
	 
// 	  /* stop form from submitting normally */
// 	//  event.preventDefault();
	 
// 	  /* get some values from elements on the page: */
// 	  var $form = $( this ),
// 	      term = $form.find( 'input[name="blob"]' ).val(),
// 	      url = $form.attr( 'action' );
	 
// 	  /* Send the data using post */
// 	  var posting = $.post( url, { s: term } );
	 
// 	  /* Put the results in a div */
// 	  posting.done(function( data ) {
// 	    var content = $( data ).find( '#content' );
// 	    $( "#result" ).empty().append( content );
	    
// 	  });
// 	}
	
	document.getElementById("blob").value = parent.test();
	go();
// 	toto();
</script>
</body>
</html>