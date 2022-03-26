package mase.ericsson.entities;

import java.io.Serializable;

public class EventCauseID implements Serializable {
	
    private static final long serialVersionUID = 442649781743479037L;
	
    public Integer causeCode;
    public Integer eventId;

    public EventCauseID() {}
    
    public EventCauseID(Integer causeCode, Integer eventId) {
    	this.causeCode = causeCode;
    	this.eventId = eventId;
    }

    
    public Integer getCauseCode() {
		return causeCode;
	}

	public Integer getEventId() {
		return eventId;
	}

	@Override
    public boolean equals(Object obj) {    	
    	
    	if (this == obj) return true;
    	
    	if (obj == null || obj.getClass() != this.getClass()) return false;
    	
    	EventCauseID checkPK = (EventCauseID) obj;
    	return (checkPK.causeCode.intValue() == this.causeCode.intValue() && checkPK.eventId.intValue() == this.eventId.intValue());
    }
    
    @Override
    public int hashCode() {
    	return (37 * (37 * (causeCode^causeCode)) + (eventId ^ eventId))%(2^31-1);
    }
    
}
