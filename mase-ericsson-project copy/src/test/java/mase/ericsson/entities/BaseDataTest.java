package mase.ericsson.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import static org.junit.Assert.*;
//import junit.framework.Assert;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;

public class BaseDataTest {
	
	private BaseData basedata;
		
	@BeforeEach
	public void setUp() {
		basedata = new BaseData(new Integer(123), null, new Integer(77), new Integer(77), new Integer(77), new Integer(77),
				new Integer(77), new Integer(77), new Integer(77), "abc", new Long(77), new Long(77), new Long(77), new Long(77)); 
	}
	
	@Test
	public void testConstructor() {
		Integer eventId = 123;
	    Date eventDate = null;
	    Integer failureClass = 77;
	    Integer ueType = 77;
	    Integer market = 77;
	    Integer operator = 77;
	    Integer cellId = 77;
	    Integer duration = 77;
	    Integer causeCode = 77;
	    String neVersion = "abc";
	    Long imsi = (long) 77;
	    Long hier3Id = (long) 77;
	    Long hier32Id = (long) 77;
	    Long hier321Id = (long) 77;
	    
	    assertEquals(123,basedata.getEventId());
		assertEquals(null,basedata.getEventDate());
		assertEquals(77,basedata.getFailureClass());
		assertEquals(77,basedata.getUeType());
		assertEquals(77,basedata.getMarket());
		assertEquals(77,basedata.getCellId());
		assertEquals(77,basedata.getDuration());
		assertEquals(77,basedata.getCauseCode());
		assertEquals(77,basedata.getOperator());
		assertEquals("abc",basedata.getNeVersion());
		assertEquals(77,basedata.getImsi());
		assertEquals(77,basedata.getHier321Id());
		assertEquals(77,basedata.getHier32Id());
		assertEquals(77,basedata.getHier3Id());
	    
		assertEquals(eventId,basedata.getEventId());
		//assertEquals(null,basedata.getEventDate());
		assertEquals(failureClass,basedata.getFailureClass());
		assertEquals(ueType,basedata.getUeType());
		assertEquals(market,basedata.getMarket());
		assertEquals(operator,basedata.getCellId());
		assertEquals(cellId,basedata.getDuration());
		assertEquals(duration,basedata.getCauseCode());
		assertEquals(causeCode,basedata.getOperator());
		//assertEquals(neVersion, basedata.getNeVersion());
		assertEquals(imsi,basedata.getImsi());
		assertEquals(hier321Id,basedata.getHier321Id());
		assertEquals(hier32Id,basedata.getHier32Id());
		assertEquals(hier3Id,basedata.getHier3Id());
	}	
	
    @Test
	public void testFailureClass() {
		basedata.setFailureClass(2);
		assertEquals(2,basedata.getFailureClass());

    }
    
    @Test
	public void testEventID() {
		basedata.setEventId(1);
		assertEquals(1,basedata.getEventId());
    }
    
    @Test
	public void testMarket() {
		basedata.setMarket(12);;
		assertEquals(12,basedata.getMarket());
    }
    
    @Test
	public void testOperator() {
		basedata.setOperator(12);;
		assertEquals(12,basedata.getOperator());
    }
    
    @Test
	public void testCellId() {
		basedata.setCellId(12);;
		assertEquals(12,basedata.getCellId());
    }
    
    @Test
	public void testDuration() {
		basedata.setDuration(20);
		assertEquals(20,basedata.getDuration());
    }
    
    @Test
	public void testCauseCode() {
		basedata.setCauseCode(20);
		assertEquals(20,basedata.getCauseCode());
    }
    
    @Test
   	public void testsetHier321IdCode() {
  		basedata.setHier321Id((long) 777777);
   		assertEquals((long) 777777,basedata.getHier321Id());
    }
    
    @Test
   	public void testsetHier32IdCode() {
   		basedata.setHier32Id((long) 777777);
   		assertEquals((long) 777777,basedata.getHier32Id());
    }    
    
    @Test
   	public void testsetHier3IdCode() {
   		basedata.setHier3Id((long) 777777);
   		assertEquals((long) 777777,basedata.getHier3Id());
    }
    
    @Test
   	public void testNEversionCode() {
   		basedata.setNeVersion("abc123");
   		assertEquals("abc123",basedata.getNeVersion());
   		//assertEquals("abc123",basedata.getNeVersion());
    }      
    
    @Test
   	public void testIMsID() {
   		basedata.setImsi((long) 7777777);
   		assertEquals(7777777,basedata.getImsi());
    }
    
    @Test
   	public void testEventData() {
   		basedata.setEventDate(null);
   		assertEquals(null,basedata.getEventDate());
    }    
}