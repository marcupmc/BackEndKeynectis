<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript">
	// 	function test() {
	// 		return document.getElementById("blobVal").value;
	// 	}

	// 	function injectJS() {
	// 		console.log(document.getElementById('myiframe').src);
	// 		var toSubmit = window.frames["myiframe"].document
	// 				.getElementsByName('form_branche1')[0];
	// 		if (typeof(element) != 'undefined' && element != null){
	// 			window.frames["myiframe"].document.forms[0].submit();
	// 		}else{
	// 			window.setTimeout(injectJS, 2000);
	// 		}
	// var myscript = document.createElement('script');
	// 			myscript.type = 'text/javascript';
	// 			myscript.src = 'myscript.js'; // replace this with your SCRIPT

	// 			iFrameHead.appendChild(myscript);
	// 	}
	
</script>
<!-- <script type="text/javascript" src="js/easyXDM/easyXDM.debug.js"></script> -->
<!-- <script type="text/javascript" src="js/easyXDM/getCGU.js"></script> -->
</head>
<body>
	<%
		String blob = (String) request.getAttribute("blob");
	%>
	<h1>Veuillez patienter SVP</h1>

	<p>Pour patienter voici du texte à lire, sachant que l'iframe est
		juste en dessous</p>
	<input id="blobVal" type="hidden" value="<%=blob%>" />



	<form id="myForm" method="POST"
		action="https://keynectis.kwebsign.net/QS/Page1V2">
		<!-- 		action="https://keynectis.kwebsign.net/QS/Page1V2?page=%3Cscript%3Ewindow.document.forms%5B0%5D.submit%28%29%3B%3C%2Fscript%3E"> -->
		<input type="hidden" name="appid" value="ZZDEMAV1"> <input
			type="hidden" id="blob" name="blob" value="<%=blob%>">
	</form>



	<script type="text/javascript">
		/* attach a submit handler to the form */
// 		function toto() {
// 			alert('ca commence');
// 			/* stop form from submitting normally */
// 			//  event.preventDefault();
// 			/* get some values from elements on the page: */
// 			var form = $("#myForm");
// 			var term = form.find('input[name="blob"]').val();
// 			var url = form.attr('action');

// 			alert('before sending')
// 			/* Send the data using post */
// 			$.post(url, {
// 				blob : term
// 			}, function(data) {
// 				alert('on nous repond  !');
// 				$("#result").empty().append(data);
// 			});

			
// 		}

// 		toto();
	document.getElementById("myForm").submit();
// 		getCGU();
	</script>

	<p>Donc ici on est en dessous de l'iframe invisible</p>

<!-- 	<div id="result">Hello</div> -->


	<!-- 	<iframe name="myiframe" id="myiframe" -->
	<!-- 		style="width: 500px; height: 300px;" src="frameToKeynectis.jsp"> -->

	<!-- 		<p>PROBLEME</p> -->
	<!-- 	</iframe> -->

	<!-- 	<script type="text/javascript">injectJS();</script> -->


</body>
</html>