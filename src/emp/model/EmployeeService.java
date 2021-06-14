package emp.model;
import java.util.List;

import com.dao.*;

public class EmployeeService {

	private EmployeeDao dao;
	
	
	
	public EmployeeService() {
		
	 this.dao=new EmployeeDaoJDBCImpl();
	
	}
	
	
	
	public List<Employee> addThenFindAll(Employee e){
		dao.add(e);
		List<Employee> emps=dao.getAllEmployee();
		
		return emps;
		
	}
	



}
