package mase.ericsson.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FailureClass")
public class FailureClass {

    @Id @NotNull
    private Integer failureClass;
    @NotNull
    private String description;
    
    public FailureClass() {
	}
    
	public FailureClass(Integer failureClass, String description) {
		this.failureClass = failureClass;
		this.description = description;
	}
	
	public Integer getFailureClass() {
		return failureClass;
	}
	public String getDescription() {
		return description;
	}
	public void setFailureClass(Integer failureClass) {
		this.failureClass = failureClass;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}