package tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class TestUnitCryptoTool {

	@Test
	public void TestGetEncodedPassword_Null_Void() {
		String arg1=null;
		
		String res="";
		try {
			res=CryptoTool.getEncodedPassword(arg1);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
		assertEquals("", res);
		
	}
	
	@Test
	public void TestGetEncodedPassword_Void_Void() {
		String arg1="";
		
		String res="";
		try {
			res=CryptoTool.getEncodedPassword(arg1);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
		assertEquals("", res);
		
	}
	
	@Test
	public void TestGetEncodedPassword_password_OK(){
		String arg1 = "password";
		String arg2 = "password";
		String arg3 = "password2";
		
		String res1="";
		String res2="";
		String res3="";
		
		try {
			 res1 = CryptoTool.getEncodedPassword(arg1);
			 res2= CryptoTool.getEncodedPassword(arg2);
			 res3= CryptoTool.getEncodedPassword(arg3);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
		
		assertEquals(res1, res2);
		assertFalse(res1.equals(res3));
	}

}
