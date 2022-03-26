package mase.ericsson.entities;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(MccMncID.class)
@Table(name = "MccMnc")
public class MccMnc {

    @Id @NotNull
    private Integer mcc;
    
    @Id @NotNull
    private Integer mnc;
   
    @NotNull
    private String country;
    @NotNull
    private String operator;
    
    public MccMnc() {
	}
    
	public MccMnc(Integer mcc, Integer mnc, String country, String operator) {
		this.mcc = mcc;
		this.mnc = mnc;
		this.country = country;
		this.operator = operator;
	}
	
	public Integer getMcc() {
		return mcc;
	}
	
	public Integer getMnc() {
		return mnc;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public void setMcc(Integer mcc) {
		this.mcc = mcc;
	}
	
	public void setMnc(Integer mnc) {
		this.mnc = mnc;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setOperator(String operator) {
		this.operator = operator;
	}    
}