package com.getLunch.test.unit;

import static org.junit.Assert.*;

import javax.naming.NamingException;

import org.junit.Test;

import com.getLunch.backend.utils.ClientUtility;

public class ClientUtilityTest {

	@Test
	public void testGetInitialContextForClient() {
		
		try {
			
			assertNotNull(ClientUtility.getInitialContextForClient());
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			fail("Ocorreu erro durante o teste");
			e.printStackTrace();
		}
		
	}

}
