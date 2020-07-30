package com.empserver.service;

import com.empserver.model.Employee;
import com.empserver.model.EmployeeDetail;
import com.empserver.model.Title;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface EmployeeService {
//    @Transactional
    public Employee getEmployee(Long id);
    EmployeeDetail getEmployeeDetail(Long id);

    public Title getTitle(Long id);

    public Optional<Employee> getEmployee1(Long id);
}
