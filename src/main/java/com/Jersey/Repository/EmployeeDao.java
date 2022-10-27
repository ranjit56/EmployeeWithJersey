package com.Jersey.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Jersey.Entity.Employee;


public interface EmployeeDao extends JpaRepository<Employee, Integer>{

	Employee findById(Long id);

    Boolean deleteById(Long id);
	

}
