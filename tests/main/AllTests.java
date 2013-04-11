package main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tools.TestCryptoTool;

import controller.TestControllerAjoutClient;
import controller.TestControllerAuthentification;

@RunWith(Suite.class)
@SuiteClasses({ TestControllerAjoutClient.class,
		TestControllerAuthentification.class,TestCryptoTool.class })
public class AllTests {

}
