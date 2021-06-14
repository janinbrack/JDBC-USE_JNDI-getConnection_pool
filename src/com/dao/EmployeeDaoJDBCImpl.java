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
 *  概觀   使用JNDI 取得連線池
 *  
 *  1. JNDI 是 JAVA 取得外部資源的一種方式。而 connection pool 是其中一種，
 *  	藉由 java 預設的(JNDI)APIs由容器取得 [DataSource 介面] 的實作物件
 *  
 *  2. JNDI 資源 參考官方文件 : https://docs.oracle.com/cd/E19823-01/819-1554/jndi.html#wp666691
 *  3. DataSource介面屬於 javax.sql套件
 *  4. 使用JNDI 的好處是不用自己撰寫 connection pool 機制
 *  5. 程式碼 可以轉移至符合 JAVA EE 規格的容器
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *  在 Tomcat 容器裡設定 DataSouce。
 *   需設定 context.xml
 *   需設定 server.xml
 *  
 *  提供一個取得該資源的 名稱服務(naming service) ，而 名稱服務 可以藉由JNDI API來查詢
 *  當容器內的任何程式 ， 需要資料庫連線時:
 *  使用JNDI 找出 (lookup) 名稱服務背後的 DataSouce
 *  DataSouce 會使用JDBC API 和資料庫協同作業
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
			
			//java:comp/env 可以參考官方文件 :  https://docs.oracle.com/cd/E19823-01/819-1554/jndi.html
			// jdbc/mssqlBD 也就是JNDI名稱 ，然而，藉由這個名稱找出 該服務背後的DataSouce
			
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
