package mase.ericsson.entities;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User{



	@Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int userId;
 
	private int userType;
    private String firstName;
    private String lastName;
    private String password;

    public User() {
    }

    public User( int userType, String firstName, String lastName, String password) {
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

//    public void setUserId(int id) {
//        this.userId = id;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

}
