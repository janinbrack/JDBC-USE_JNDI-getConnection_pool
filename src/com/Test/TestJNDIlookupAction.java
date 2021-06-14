package com.Test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Employee;
import emp.model.EmployeeService;

/**
 * Servlet implementation class TestJNDIlookupAction
 */
@WebServlet("/TestJNDIlookupAction")
public class TestJNDIlookupAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public TestJNDIlookupAction() {
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	EmployeeService employeeService=new EmployeeService();
	request.setAttribute("emp", employeeService.addThenFindAll(new Employee("Sophia", 0, "s12dd2@gmail.com")));
	RequestDispatcher dispatcher=request.getRequestDispatcher("shopemp.jsp");
	dispatcher.forward(request, response);
	}

}
