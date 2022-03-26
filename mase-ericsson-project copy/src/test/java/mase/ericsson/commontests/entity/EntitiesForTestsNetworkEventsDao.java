package mase.ericsson.commontests.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mase.ericsson.entities.BaseData;
import mase.ericsson.entities.EventCause;
import mase.ericsson.entities.FailureClass;
import mase.ericsson.entities.MccMnc;
import mase.ericsson.entities.User;
import mase.ericsson.entities.UserEquipment;
import mase.ericsson.entities.EventCauseID;
import mase.ericsson.entities.MccMncID;

public class EntitiesForTestsNetworkEventsDao {

	public static EventCause eventCause() {
		return new EventCause( 1, 1, "TestEventCause");
	}
	
	public static EventCause invalidEventCause() {
		return new EventCause( 1, 1, null);
	}
	
	public static List<EventCause> eventCauseList() {
		List<EventCause> eventCauseList = new ArrayList<>();
		eventCauseList.add(new EventCause( 1, 1, "TestEventCause"));
		return eventCauseList;
	}
	
	public static FailureClass failureClass() {
		return new FailureClass(6, "TestFailureClass");
	}
	
	public static FailureClass invalidFailureClass() {
		return new FailureClass(6, null);
	}
	
	public static MccMnc mccMnc() {
		return new MccMnc(1, 1, "Ireland", "Three Ireland");
	}
	
	public static MccMnc invalidMccMnc() {
		return new MccMnc(1, 1, null, "Three Ireland");
	}
	
	public static UserEquipment userEquipment() {
		return new UserEquipment(1, "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test");
	}
	
	public static UserEquipment invalidUserEquipment() {
		return new UserEquipment(1, "Test", "Test", "Test", null, "Test", "Test", null, "Test");
	}
	
	public static BaseData baseData() {
		return new BaseData(new Integer(4125), new Date("11/01/2020  17:15:00"), new Integer(1), new Integer(100500), new Integer(238), new Integer(3), new Integer(1), new Integer(1), new Integer(0), "Test", new Long(11), new Long(300), new Long(470300), new Long(1479940));
	}
	
	public static BaseData invalidBaseData() {
		return new BaseData(null, new Date("11/01/2020  17:15:00"), new Integer(1), null, new Integer(238), new Integer(3), new Integer(1), new Integer(1), new Integer(0), "Test", new Long(11), new Long(300), new Long(470300), new Long(1479940));
	}
	
	public static User user() {
		return new User(1, "Test", "Test", "testpass");
	}

	public static EventCauseID eventCauseID() {
		return new EventCauseID(1, 1);
	}

	public static MccMncID mccMncID() {
		return new MccMncID(1, 1);
	}
}