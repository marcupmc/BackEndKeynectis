<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@ page import="com.dictao.keynectis.quicksign.transid.*"%>
<%@ page import="java.io.*"%>

<%
	System.out.println("Je suis arrive");

	String transNumInSession = (String)request.getAttribute("transNum");
	String pdfOutPath = (String)request.getAttribute("OUT")+"/+transNumInSession"+".pdf";

	FileOutputStream fos = new FileOutputStream(pdfOutPath);
	String blob = (String)request.getAttribute("blob");
	ResponseTransId rti = new ResponseTransId();
	rti.setB64Blob(blob);
	rti.setCipherCertFilePath((String)request.getAttribute("CERT")+ "/demoqs_c.p12", "DemoQS");
	//rti.setExtractDir("/home/quicksign/simulclient/demoqs/tmp");
	rti.setOutputStream(fos);
	String transNum = rti.getTransNum();
	int status = rti.getStatus();
	fos.close();

	String subDirectory = request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/"));
	String url = "../index.jsp?pageDemo="+subDirectory+"/demo6p5.jsp&transNum="+transNum+"&status="+status+"&pdfOutPath="+((String)request.getAttribute("OUT")).concat("/"+transNumInSession+".pdf");
	
	response.sendRedirect(url);
%>
