package mase.ericsson.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "UserEquipment")
public class UserEquipment {

    @Id @NotNull
    private Integer tac;
    
    @NotNull
    private String marketingName;

    @NotNull
    private String manufacturer;

    @NotNull
    private String accessCapability;

    @NotNull
    private String model;

    @NotNull
    private String vendorName;

    @NotNull
    private String ueType;

    @NotNull
    private String os;

    @NotNull
    private String inputMode;
	
    public UserEquipment() {
	}
    
    public UserEquipment(Integer tac, String marketingName, String manufacturer, String accessCapability, String model,
			String vendorName, String ueType, String os, String inputMode) {
		super();
		this.tac = tac;
		this.marketingName = marketingName;
		this.manufacturer = manufacturer;
		this.accessCapability = accessCapability;
		this.model = model;
		this.vendorName = vendorName;
		this.ueType = ueType;
		this.os = os;
		this.inputMode = inputMode;
	}

	public Integer getTac() {
		return tac;
	}

	public void setTac(Integer tac) {
		this.tac = tac;
	}

	public String getMarketingName() {
		return marketingName;
	}

	public void setMarketingName(String marketingName) {
		this.marketingName = marketingName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getAccessCapability() {
		return accessCapability;
	}

	public void setAccessCapability(String accessCapability) {
		this.accessCapability = accessCapability;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getUeType() {
		return ueType;
	}

	public void setUeType(String ueType) {
		this.ueType = ueType;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getInputMode() {
		return inputMode;
	}

	public void setInputMode(String inputMode) {
		this.inputMode = inputMode;
	}
}