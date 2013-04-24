package controller;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DAOUtilisateur;
import domain.DocumentPDF;
import domain.Utilisateur;

public class TestControllerAjoutClient {

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
	public void TestAddClientInDB_Null_False() {
		String arg1=null;
		String arg2=null;
		String arg3=null;
		String arg4=null;
		String arg5=null;
		String arg6=null;
		
		boolean result = ControllerAjoutClient.getInstance().addClientInDB(arg1, arg2, arg3, arg4, arg5, arg6);
		assertFalse(result);
	}
	
	@Test
	public void TestAddClientInDB_Void_False() {
		String arg1="";
		String arg2="";
		String arg3="";
		String arg4="";
		String arg5="";
		String arg6="";
		
		boolean result = ControllerAjoutClient.getInstance().addClientInDB(arg1, arg2, arg3, arg4, arg5, arg6);
		assertFalse(result);
	}

	@Test
	public void TestAddClientInDB_loginExist_False() {
		String arg1="login";
		String arg2="login";
		String arg3="login";
		String arg4="login";
		String arg5="2131";
		String arg6="pass";
		
		boolean result = ControllerAjoutClient.getInstance().addClientInDB(arg1, arg2, arg3, arg4, arg5, arg6);
		assertFalse(result);
	}
	
	@Test
	public void TestAddClientInDB_login2NotExist_True() {
		String arg1="login2";
		String arg2="login";
		String arg3="login";
		String arg4="login";
		String arg5="2131";
		String arg6="pass";
		
		boolean result = ControllerAjoutClient.getInstance().addClientInDB(arg1, arg2, arg3, arg4, arg5, arg6);
		
		Utilisateur user = DAOUtilisateur.getInstance().getUserByIdentifiant(arg1);
		DAOUtilisateur.getInstance().deleteUser(user.getId());
		assertTrue(result);
	}
	
	
}
