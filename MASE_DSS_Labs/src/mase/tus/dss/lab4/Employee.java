package mase.tus.dss.lab4;

public class Employee implements java.io.Serializable {
	private static final long serialVersionUID = -2688512736864162480L;
	
	private String name;
	private String address;
	private String telNo;
	
	public Employee(String name, String address, String telNo) {
		super();
		this.name = name;
		this.address = address;
		this.telNo = telNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
}
