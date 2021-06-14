package com.dao;
import java.util.ArrayList;

import emp.model.*; 
public interface EmployeeDao {

	
//CRUD
	
	
public void add(Employee e);

public void update(Employee e);

public int delete(int id);

public Employee findByID(int id);

public ArrayList<Employee> getAllEmployee();
	
}
