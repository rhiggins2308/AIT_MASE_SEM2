package mockitoSpyStub;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id @GeneratedValue
	private long id;
	private String name;
	private String address;
	
	public Customer() {
		
	}
	
	public Customer(long id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
