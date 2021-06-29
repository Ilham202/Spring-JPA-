package com.cognizant.orm_learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognizant.orm_learn.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(value = "SELECT e FROM Employee e WHERE e.permanent = 1")
	List<Employee> getAllPermanentEmployees();
	
	@Query(value = "SELECT AVG(e.salary) FROM Employee e")
	double getAverageSalary();
	
	@Query(value = "SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :id")
	double getAverageSalary(@Param("id")int id);
	
	@Query(value="SELECT * FROM employee", nativeQuery = true)
	List<Employee> getAllEmployeesNative();
	
}
