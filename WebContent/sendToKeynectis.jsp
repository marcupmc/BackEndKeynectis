<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function go() {
		
		document.getElementById("myForm").submit();
	}
</script>
</head>
<body>
	<%
		String blob = (String) request.getAttribute("blob");
	%>
	<script type="text/javascript">alert('Blob \n'+<%=blob%>);</script>
	
	<form id="myForm" method="POST"
		action="https://keynectis.kwebsign.net/QS/Page1V2">
		<input type="hidden" name="appid" value="ZZDEMAV1"> <input
			type="hidden" name="blob" value="<%=blob%>">
	</form>

	<script>
		go();
	</script>
</body>
</html>