package com.cognizant.orm_learn;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.orm_learn.model.Department;
import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.model.Skill;
import com.cognizant.orm_learn.service.DepartmentService;
import com.cognizant.orm_learn.service.EmployeeService;
import com.cognizant.orm_learn.service.SkillService;

@SpringBootApplication
public class OrmLearnApplication {

	@Autowired
	private static EmployeeService employeeService;

	@Autowired
	private static SkillService skillService;

	@Autowired
	private static DepartmentService departmentService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

		employeeService = context.getBean(EmployeeService.class);
		skillService = context.getBean(SkillService.class);
		departmentService = context.getBean(DepartmentService.class);

		// testGetEmployee();
		// testAddEmployee();
		// testUpdateEmployee();
		// testGetDepartment();
		// testGetAllPermanentEmployees();
		// testGetAverageSalary();
		// testGetAverageSalaryOfDepartment();
		testGetAllEmployeesNative();
		
	}

	private static void testGetEmployee() {

		LOGGER.info("Start");
		Employee employee = employeeService.get(1);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.debug("Department:{}", employee.getDepartment());
		LOGGER.debug("Skills:{}", employee.getSkillList());
		LOGGER.info("End");

	}

	private static void testAddEmployee() {

		LOGGER.info("Start");
		Employee employee = new Employee();
		employee.setName("Tarun");
		employee.setPermanent(true);
		employee.setSalary(2000000);
		employee.setDateOfBirth(new Date(2000, 12, 3));
		Department department = departmentService.get(1);
		employee.setDepartment(department);
		employeeService.save(employee);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.info("End");

	}

	private static void testUpdateEmployee() {

		LOGGER.info("Start");
		Employee employee = employeeService.get(5);
		Department department = departmentService.get(4);
		employee.setDepartment(department);
		employee.setName("Tarun Tavva");
		employeeService.save(employee);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.info("End");

	}

	private static void testGetDepartment() {

		LOGGER.info("Start");
		Department department = departmentService.get(3);
		Set<Employee> employees = department.getEmployeeList();
		LOGGER.debug("Department:{}", department);
		LOGGER.debug("Employees:{}", employees);
		LOGGER.info("End");

	}

	private static void testAddSkillToEmployee() {

		LOGGER.info("Start");
		Employee employee = employeeService.get(1);
		Skill skill = skillService.get(3);
		Set<Skill> skillList = employee.getSkillList();
		skillList.add(skill);
		employee.setSkillList(skillList);
		employeeService.save(employee);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.debug("Skills:{}", employee.getSkillList());
		LOGGER.info("End");

	}

	public static void testGetAllPermanentEmployees() {

		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllPermanent();
		LOGGER.debug("Permanent Employees:{}", employees);
		employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
		LOGGER.info("End");

	}
	
	public static void testGetAverageSalary() {
		
		LOGGER.info("Start");
		double salary = employeeService.getAverageSalary();
		LOGGER.debug("Average Salary:{}", salary);
		LOGGER.info("End");
		
	}
	
	public static void testGetAverageSalaryOfDepartment() {
		
		LOGGER.info("Start");
		double salary = employeeService.getAverageSalaryOfDepartment(1);
		LOGGER.debug("Average Salary:{}", salary);
		LOGGER.info("End");
		
	}
	
	public static void testGetAllEmployeesNative() {
		
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllEmployeesNative();
		LOGGER.debug("Employees:{}", employees);
		LOGGER.info("End");
		
	}
	
}
