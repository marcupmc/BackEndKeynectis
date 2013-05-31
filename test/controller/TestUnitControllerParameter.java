package controller;

import static org.junit.Assert.*;

import model.AuthorityParameters;

import org.junit.Test;

public class TestUnitControllerParameter {

	//TODO: Faire un before avec l'ajout des fichiers de test
	//TODO : Faire un after qui supprime les fichiers de test
	
	
	@Test
	public void TestValidateParameter_Null_Null(){
		String arg1=null;
		String arg2=null;
		String arg3=null;
		
		AuthorityParameters returnParam = ControllerParameter.getInstance().validateParameters(arg1, arg2, arg3);
		assertTrue(returnParam==null);
	}
	
	
	@Test
	public void TestValidateParameter_Vide_Null(){
		String arg1 = "";
		String arg2= "";
		String arg3="";
		
		AuthorityParameters returnParam = ControllerParameter.getInstance().validateParameters(arg1, arg2, arg3);
		assertTrue(returnParam==null);
	}
	
	

}
