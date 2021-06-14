package com.dao;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import emp.model.Employee;

public class EmployeeDaoJDBCImpl implements EmployeeDao{

/**
 *  ���[   �ϥ�JNDI ���o�s�u��
 *  
 *  1. JNDI �O JAVA ���o�~���귽���@�ؤ覡�C�� connection pool �O�䤤�@�ءA
 *  	�ǥ� java �w�]��(JNDI)APIs�Ѯe�����o [DataSource ����] ����@����
 *  
 *  2. JNDI �귽 �Ѧҩx���� : https://docs.oracle.com/cd/E19823-01/819-1554/jndi.html#wp666691
 *  3. DataSource�����ݩ� javax.sql�M��
 *  4. �ϥ�JNDI ���n�B�O���Φۤv���g connection pool ����
 *  5. �{���X �i�H�ಾ�ܲŦX JAVA EE �W�檺�e��
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  �b Tomcat �e���̳]�w DataSouce�C
 *   �ݳ]�w context.xml
 *   �ݳ]�w server.xml
 *  
 *  ���Ѥ@�Ө��o�Ӹ귽�� �W�٪A��(naming service) �A�� �W�٪A�� �i�H�ǥ�JNDI API�Ӭd��
 *  ��e����������{�� �A �ݭn��Ʈw�s�u��:
 *  �ϥ�JNDI ��X (lookup) �W�٪A�ȭI�᪺ DataSouce
 *  DataSouce �|�ϥ�JDBC API �M��Ʈw��P�@�~
 *    
 * 
 * 
 * 
 * */

	
	
	
	private DataSource dataSource;
	String tbName="employe";
	
	
	
	public EmployeeDaoJDBCImpl() {
	dataSource=this.getDataSource();
		
	}
	
	
	private DataSource getDataSource() {
		 DataSource ds=null;
		try {
			Context ctx=new InitialContext();
	
			if(ctx==null) {
				throw new RuntimeException("JNDI CONTEXT COULD not be found.");
			}
			
			//java:comp/env �i�H�Ѧҩx���� :  https://docs.oracle.com/cd/E19823-01/819-1554/jndi.html
			// jdbc/mssqlBD �]�N�OJNDI�W�� �A�M�ӡA�ǥѳo�ӦW�٧�X �ӪA�ȭI�᪺DataSouce
			
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/mssqlBD");
			
			if(ds==null) {
				throw new RuntimeException("datasource could not be found.");
			}
			
			
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
		return ds;
	}
	
	
	

	@Override
	public void add(Employee e) {
	String sql="insert into "+tbName+"(name,gender,email)values(?,?,?)";
	
	
	try(Connection con=dataSource.getConnection();
		PreparedStatement preparedStatement=con.prepareStatement(sql);
		) {
		preparedStatement.setString(1, e.getName());
		preparedStatement.setInt(2, e.getGender());
		preparedStatement.setString(3, e.getEmail());
		preparedStatement.executeUpdate();
	} catch (Exception e2) {
		e2.printStackTrace();
	}
		
		
	}

	@Override
	public void update(Employee e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Employee findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Employee> getAllEmployee() {
		ArrayList<Employee> e=new ArrayList();
		String sql="select * from "+tbName+" order by id desc";
		
		
		try(Connection con=dataSource.getConnection();
			Statement statement=con.createStatement();
			ResultSet resultSet=statement.executeQuery(sql);
		   ) {
			while(resultSet.next()) {
			Employee employee=new Employee();
			employee.setId(resultSet.getInt("id"));
			employee.setName(resultSet.getString("name"));
			employee.setGender(resultSet.getInt("gender"));
			employee.setEmail(resultSet.getString("email"));
			e.add(employee);
			}
			
			
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		
		
		return e;
	}

}
