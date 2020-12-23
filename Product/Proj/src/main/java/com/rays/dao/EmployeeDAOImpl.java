package com.rays.dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.ray.entity.Employee;
import com.rays.util.MyCassandraTemplate;


public class EmployeeDAOImpl implements EmployeeDAO {
    
    @Autowired
    private MyCassandraTemplate myCassandraTemplate;

	@Override
	public Employee createEmployee(Employee employee) {
		
		   return myCassandraTemplate.create(employee, Employee.class);
	}

	/*
	 * @Override public Employee getEmployee(int id) {
	 * 
	 * return myCassandraTemplate.findById(id, Employee.class); }
	 */

	@Override
	public Employee updateEmployee(Employee employee) {
		
		return myCassandraTemplate.update(employee, Employee.class);
	}

	/*
	 * @Override public void deleteEmployee(int id) {
	 * 
	 * myCassandraTemplate.deleteById(id, Employee.class);
	 * 
	 * }
	 */

	@Override
	public List<Employee> getAllEmployees() {
		
		return myCassandraTemplate.findAll(Employee.class);
		
	}

	@Override
	public Employee getEmployee(int id) {
		
		return null;
	}

	@Override
	public void deleteEmployee(int id) {
		
		
	}

	
    
   

	
}