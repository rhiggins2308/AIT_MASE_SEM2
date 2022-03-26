package mase.ericsson.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	User testUser;
	@BeforeEach
	public void setUp()
	{
		testUser = new User();
	}

	@Test
	void testUserMake() {
		testUser = new User(1,"John","Doe","Password");
		assertEquals(testUser.getFirstName(), "John");
		assertEquals(testUser.getLastName(), "Doe");
		assertEquals(testUser.getPassword(), "Password");
		assertEquals(testUser.getUserType(), 1);
	}
	
	@Test
	void testSetFirstName() {
		String name = "test";
		testUser.setFirstName(name);
		assertEquals(testUser.getFirstName(), name);
	}
	@Test
	void testSetLastName() {
		String name = "test";
		testUser.setLastName(name);
		assertEquals(testUser.getLastName(), name);
	}
	@Test
	void testSetPassword() {
		String pass = "test";
		testUser.setPassword(pass);
		assertEquals(testUser.getPassword(), pass);
	}
	@Test
	void testSetUserType() {
		int type = 2;
		testUser.setUserType(type);
		assertEquals(testUser.getUserType(), type);
	}
	@Test
	void testUserId() {
		assertEquals(testUser.getUserId(), 0);
	}

	

}
