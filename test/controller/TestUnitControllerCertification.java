package controller;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import domain.DocumentPDF;

public class TestUnitControllerCertification {

	@Test
	public void TestCertificationPDFFromXML_Null_Null(){
		String arg1 =null;
		String arg2 = null;
		String arg3 = null;
		String arg4=null;
		HashMap<String, String> mapFromTest = ControllerCertification.getInstance().certificationPDFFromXml(arg1, arg2,arg3,arg4);
		assertTrue(mapFromTest==null);
	}

	@Test
	public void TestCertificationPDFFromXML_Empty_Null(){
		String arg1 ="";
		String arg2 = "";
		String arg3 = "";
		String arg4="";
		HashMap<String, String> mapFromTest = ControllerCertification.getInstance().certificationPDFFromXml(arg1, arg2,arg3,arg4);
		assertTrue(mapFromTest==null);
	}
	
	//TODO : IMPORTANT ecrire le test !!
//	@Test 
//	public void TestCertificationPDFFromXML_Ok_HashMap(){
//		String arg1 ="";
//		String arg2 = "";
//		String arg3 = "";
//		String arg4="";
//		HashMap<String, String> mapFromTest = ControllerCertification.getInstance().certificationPDFFromXml(arg1, arg2,arg3,arg4);
//		assertFalse(mapFromTest==null);
//	}
	
}
