package dao;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.EventType;
import domain.TypeLog;

public class TestUnitDAOLog {

	@Test
	public void TestAddLog_Null_False() {
		TypeLog arg1 = null;
		String arg2 = null;
		String arg3 = null;
		EventType arg4= null;
		String arg5 = null;
		String arg6 = null;
		
		boolean returnValue = DAOLog.getInstance().addLog(arg1, arg2, arg3, arg4, arg5, arg6);
		assertFalse(returnValue);
		//6
	}

}
