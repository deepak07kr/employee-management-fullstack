package com.deepak.employee_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepak.employee_management.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
}
