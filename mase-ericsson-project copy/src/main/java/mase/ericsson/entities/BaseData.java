package mase.ericsson.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;

@Entity
@Table(name = "BaseData")
public class BaseData {

	@Id @GeneratedValue(strategy=GenerationType.AUTO) //@NotNull
	private int id;
	
	//@NotNull
    private Integer eventId;
	
	//@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="eventDate")
    private Date eventDate;
    
	//@NotNull
	private Integer failureClass;
    
	//@NotNull
	private Integer ueType;
    
	//@NotNull
	private Integer market;
    
	//@NotNull
	private Integer operator;
	
	
	private Integer cellId;
    private Integer duration;
    private Integer causeCode;
    private String neVersion;
    private Long imsi;
    private Long hier3Id;
    private Long hier32Id;
    private Long hier321Id;

    public BaseData() {
    	
	}
    
    public BaseData(Integer eventId, Date eventDate, Integer failureClass, Integer ueType, Integer market, Integer operator,
    		Integer cellId, Integer duration, Integer causeCode, String neVersion, Long imsi, Long hier3Id, Long hier32Id,
    		Long hier321Id) {
		this.eventId = eventId;
		this.eventDate = eventDate;
		this.failureClass = failureClass;
		this.ueType = ueType;
		this.market = market;
		this.operator = operator;
		this.cellId = cellId;
		this.duration = duration;
		this.causeCode = causeCode;
		this.neVersion = neVersion;
		this.imsi = imsi;
		this.hier3Id = hier3Id;
		this.hier32Id = hier32Id;
		this.hier321Id = hier321Id;
	}

    public int getId() {
    	return this.id;
    }
    
    public int getEventId() {
        return eventId;
    }
    
	public Date getEventDate() {
		return eventDate;
	}
	
	public Integer getFailureClass() {
        return failureClass;
    }

	public Integer getUeType() {
        return ueType;
    }
	
	public Integer getMarket() {
        return market;
    }
	
	public Integer getOperator() {
        return operator;
    }
	
	public Integer getCellId() {
        return cellId;
    }
	
	public Integer getDuration() {
        return duration;
    }
	
	public Integer getCauseCode() {
        return causeCode;
    }
	
	public String getNeVersion() {
        return neVersion;
    }
	
	public Long getImsi() {
        return imsi;
    }
	
	public Long getHier3Id() {
        return hier3Id;
    }
	
	public Long getHier32Id() {
        return hier32Id;
    }
	
	public Long getHier321Id() {
        return hier321Id;
    }

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public void setFailureClass(Integer failureClass) {
		this.failureClass = failureClass;
	}

	public void setUeType(Integer ueType) {
		this.ueType = ueType;
	}

	public void setMarket(Integer market) {
		this.market = market;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public void setCellId(Integer cellId) {
		this.cellId = cellId;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public void setCauseCode(Integer causeCode) {
		this.causeCode = causeCode;
	}

	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	public void setHier3Id(Long hier3Id) {
		this.hier3Id = hier3Id;
	}

	public void setHier32Id(Long hier32Id) {
		this.hier32Id = hier32Id;
	}

	public void setHier321Id(Long hier321Id) {
		this.hier321Id = hier321Id;
	}
}
