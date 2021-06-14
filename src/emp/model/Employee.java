package emp.model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "employe")
public class Employee implements Serializable {

	
@Id
@Column(name = "id")
private int id;

private String name;
private int gender;
private String email;





public Employee() {
	super();
	// TODO Auto-generated constructor stub
}





public Employee(String name, int gender, String email) {
	super();
	this.name = name;
	this.gender = gender;
	this.email = email;
}





public int getId() {
	return id;
}





public void setId(int id) {
	this.id = id;
}





public String getName() {
	return name;
}





public void setName(String name) {
	this.name = name;
}





public int getGender() {
	return gender;
}





public void setGender(int gender) {
	this.gender = gender;
}





public String getEmail() {
	return email;
}





public void setEmail(String email) {
	this.email = email;
}





@Override
public String toString() {
	return "Employee id=" + id + ", name=" + name + ", gender=" + gender + ", email=" + email;
}
















}
