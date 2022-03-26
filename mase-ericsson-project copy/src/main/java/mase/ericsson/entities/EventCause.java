package mase.ericsson.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(EventCauseID.class)
@Table(name = "EventCause")
public class EventCause {


    @Id
    @Column(name = "causeCode", nullable=false)
    private Integer causeCode;

    @Id
    @Column(name = "eventId", nullable=false)
    private Integer eventId;
    
    @NotNull
    private String description;
    
    public EventCause() {
    }   
    
	public EventCause(Integer causeCode, Integer eventId, String description) {
		this.causeCode = causeCode;
		this.eventId = eventId;
		this.description = description;
	}

	public Integer getCauseCode() {
		return causeCode;
	}
	public Integer getEventId() {
		return eventId;
	}
	public String getDescription() {
		return description;
	}
	public void setCauseCode(Integer causeCode) {
		this.causeCode = causeCode;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public void setDescription(String description) {
		this.description = description;
	}    
}