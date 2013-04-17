<%@ page import="com.dictao.keynectis.quicksign.transid.*"%>
<%@ page import="java.io.*"%>
<%
	String transNumInSession = (String)session.getAttribute("transNum");
	String pdfOutPath = this.getServletContext().getRealPath((String)session.getAttribute("OUT")+"/test.pdf");//.concat(transNumInSession+".pdf");

	FileOutputStream fos = new FileOutputStream(pdfOutPath);
	String blob = request.getParameter("blob");
	ResponseTransId rti = new ResponseTransId();
	rti.setB64Blob(blob);
	rti.setCipherCertFilePath(this.getServletContext().getRealPath((String)session.getAttribute("CERT"))+ "/demoqs_c.p12", "DemoQS");
	//rti.setExtractDir("/home/quicksign/simulclient/demoqs/tmp");
	rti.setOutputStream(fos);
	String transNum = rti.getTransNum();
	int status = rti.getStatus();
	fos.close();

	String subDirectory = request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/"));
	String url = "../index.jsp?pageDemo="+subDirectory+"/demo6p5.jsp&transNum="+transNum+"&status="+status+"&pdfOutPath="+((String)session.getAttribute("OUT")).concat("/"+transNumInSession+".pdf");
	
	response.sendRedirect(url);
%>
