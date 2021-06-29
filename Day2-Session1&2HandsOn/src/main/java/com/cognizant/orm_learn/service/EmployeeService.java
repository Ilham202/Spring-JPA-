package com.cognizant.orm_learn.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	@Transactional
	public Employee get(int id) {
		LOGGER.info("Start");
		return employeeRepository.findById(id).get();
	}

	@Transactional
	public void save(Employee employee) {
		LOGGER.info("Start");
		employeeRepository.save(employee);
		LOGGER.info("End");
	}
	
	@Transactional
	public List<Employee> getAllPermanent() {
		LOGGER.info("Start");
		return employeeRepository.getAllPermanentEmployees();
	}
	
	@Transactional
	public double getAverageSalary() {
		LOGGER.info("Start");
		return employeeRepository.getAverageSalary();
	}
	
	@Transactional
	public double getAverageSalaryOfDepartment(int id) {
		LOGGER.info("Start");
		return employeeRepository.getAverageSalary(id);
	}
	
	@Transactional
	public List<Employee> getAllEmployeesNative() {
		LOGGER.info("Start");
		return employeeRepository.getAllEmployeesNative();
	}

}
