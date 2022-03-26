package mase.ericsson.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import mase.ericsson.entities.User;


public class UserDaoTest {

	 private static final String TEST_DB_INSTANCE = "test-db";

	  @Mock
	  private EntityManager mockedEntityManager;
	  @Mock
	  private Query mockedQuery;

	  @InjectMocks 
	  private UserDao userDao;
	  private User testUser;
//	  private User mockUser;

	  @BeforeEach
	  public void initMocks() {
	      MockitoAnnotations.initMocks(this);
	      // Old init
	      userDao= new UserDao();
	      userDao.em = mockedEntityManager;
	      testUser = new User(1,"test","test","test");

	      
//	      mockUser = Mockito.mock(User.class);
	  }
	//  
	  @AfterEach
	  public void closeEntityManager() {
	      mockedEntityManager.close();
	      mockedEntityManager.close();
	  }

	@Test
    public void getUserTest() {
        String firstName = "Admin";
        String lastName = "John";
        String password = "password";
        Integer userId = 4;
        Integer userType = 4;

    	List<User> userListTotal = new ArrayList<>();
    	Mockito.when(mockedEntityManager.createQuery(Matchers.anyString())).thenReturn(mockedQuery);
    	Mockito.when(mockedQuery.getResultList()).thenReturn(userListTotal);
    	User user =userDao.findbyuserid(2);
    	
    	userListTotal = userDao.getAllUsers();
    	firstName = userListTotal.get(1).getFirstName();
    	lastName = userListTotal.get(1).getLastName();
    	password = userListTotal.get(1).getPassword();
    	userType = userListTotal.get(1).getUserType();
    	userId = userListTotal.get(1).getUserId();
		assertTrue(userListTotal.size() > 0);
		assertEquals(firstName, user.getFirstName());
		assertEquals(lastName, user.getLastName());
		assertEquals(password, user.getPassword());
        assertEquals(userId, user.getUserId());
        assertEquals(userType, user.getUserType());
        assertEquals(userDao.registerUser(testUser),testUser);

}
}