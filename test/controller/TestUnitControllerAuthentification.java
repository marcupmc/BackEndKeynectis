package controller;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DAOUtilisateur;
import domain.DocumentPDF;
import domain.Utilisateur;

public class TestUnitControllerAuthentification {


	/**
	 * Add a user with the identifiant : login and password : password
	 */
	@Before
	public void addUser_login_password(){
		Set<DocumentPDF> docs = new HashSet<DocumentPDF>();
		DAOUtilisateur.getInstance().addUser("login", "login", "login", "login@toto", "password", "000000", docs);
	}
	
	/**
	 * Delete the user with the login : login
	 */
	@After
	public void deleteUser_login(){
		Utilisateur user = DAOUtilisateur.getInstance().getUserByIdentifiant("login");
		DAOUtilisateur.getInstance().deleteUser(user.getId());
	}
	
	
	@Test
	public void isAuthentified_Null_Null_False() {
		assertFalse(ControllerAuthentification.getInstance().isAuthentified(null, null));
	}
	
	@Test
	public void TestIsAuthentified_VoidVoid_False() {
		String arg1 ="";
		String arg2="";
		
		boolean result = ControllerAuthentification.getInstance().isAuthentified(arg1,arg2);
		assertFalse(result);
	}
	
	@Test
	public void TestIsAuthentified_VoidNull_False() {
		String arg1 ="";
		String arg2=null;
		
		boolean result = ControllerAuthentification.getInstance().isAuthentified(arg1,arg2);
		assertFalse(result);
	}
	
	@Test
	public void TestIsAuthentified_NullVoid_False() {
		String arg1 =null;
		String arg2="";
		
		boolean result = ControllerAuthentification.getInstance().isAuthentified(arg1,arg2);
		assertFalse(result);
	}
	
	@Test
	public void TestIsAuthentified_login2password2_False() {
		String arg1 ="login2";
		String arg2="password2";
		
		boolean result = ControllerAuthentification.getInstance().isAuthentified(arg1,arg2);
		assertFalse(result);
	}
	
	@Test
	public void TestIsAuthentified_loginpassword2_False() {
		String arg1 ="login";
		String arg2="password2";
		
		boolean result = ControllerAuthentification.getInstance().isAuthentified(arg1,arg2);
		assertFalse(result);
	}
	
	@Test
	public void TestIsAuthentified_login2password_False() {
		String arg1 ="login2";
		String arg2="password";
		
		boolean result = ControllerAuthentification.getInstance().isAuthentified(arg1,arg2);
		assertFalse(result);
	}
	
	@Test
	public void TestIsAuthentified_loginpassword_True() {
		String arg1 ="login";
		String arg2="password";
		
		boolean result = ControllerAuthentification.getInstance().isAuthentified(arg1,arg2);
		assertTrue(result);
	}

}
