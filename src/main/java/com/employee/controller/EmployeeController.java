package com.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.api.EmployeeApi;
import com.employee.entity.Employee;
import com.employee.model.ResponseMessage;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping(path = "/v1/employee")
public class EmployeeController implements EmployeeApi {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@Override
	public ResponseEntity<List<Employee>> getmployeeS() {
		logger.info("enter into getmployeeS");
		List<Employee> employees = null;
		try {
			employees = employeeService.getmployee();
		} catch (Exception e) {
			logger.error("unable to find all employees", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("exit into getmployeeS");
		return new ResponseEntity<>(employees, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<Employee> getEmployeeByName(String name) {
		logger.info("enter into getmployeeS");
		Employee employees = null;
		try {
			employees = employeeService.getEmployeeByName(name);
		} catch (Exception e) {
			logger.error("unable to find all employees", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("exit into getmployeeS");
		return new ResponseEntity<>(employees, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<Object> updateEmployee(String empid, Employee employee) {
		logger.info("enter into getmployeeS");
		ResponseMessage responseMessage = new ResponseMessage();
		boolean check = false;
		try {
			check = employeeService.updateEmployee(empid, employee);
			if (check) {
				responseMessage.setMessage("employee Updated");
			}else {
				responseMessage.setMessage("employee not Updated");	
				return new ResponseEntity<>(responseMessage, HttpStatus.NOT_ACCEPTABLE);	
			}
		} catch (Exception e) {
			responseMessage.setMessage("exception occur");
			logger.error("unable to find all employees", e.getMessage());
			return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("exit into getmployeeS");
		return new ResponseEntity<>(responseMessage, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<Object> deleteEmployee(String empid) {
		logger.info("enter into getmployeeS");
		ResponseMessage responseMessage = new ResponseMessage();
		try {
			boolean deleteEmployee = employeeService.deleteEmployee(empid);
			if (deleteEmployee) {
				responseMessage.setMessage("employee Deleted");
			}else {
				responseMessage.setMessage("employee not Deleted");	
			}
		} catch (Exception e) {
			responseMessage.setMessage("exception occur");
			logger.error("unable to find all employees", e.getMessage());
			return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("exit into getmployeeS");
		return new ResponseEntity<>(responseMessage, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<Object> addEmployee(Employee employee) {
		logger.info("enter into getmployeeS");
		ResponseMessage responseMessage = new ResponseMessage();
		try {
			boolean addEmployee = employeeService.addEmployee(employee);
			if (addEmployee) {
				responseMessage.setMessage("employee added");
			}else {
				responseMessage.setMessage("employee not added");	
			}
		} catch (Exception e) {
			responseMessage.setMessage("exception occur");
			logger.error("unable to find all employees", e.getMessage());
			return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("exit into getmployeeS");
		return new ResponseEntity<>(responseMessage, HttpStatus.ACCEPTED);
	}


}
