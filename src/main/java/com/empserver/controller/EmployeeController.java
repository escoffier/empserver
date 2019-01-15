package com.empserver.controller;

import com.empserver.mapper.EmployeeMapper;
import com.empserver.model.Employee;
import com.empserver.model.EmployeeDetail;
import com.empserver.model.Title;
import com.empserver.service.ServiceImpl;
import com.empserver.util.ExtLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    ServiceImpl service;

    @GetMapping("/employees/{id}")
    Employee getEmployee(@PathVariable("id") Long id) {
        return service.getEmployee(id);
    }

    @GetMapping("/employees")
    List<Employee> getEmployee(@RequestParam("page") Integer page, @RequestParam("size") Integer pageSize) {
        logger.info("page: " + page + "  size: " + pageSize);
        ExtLimit extLimit = new ExtLimit();
        extLimit.setLimit(pageSize);
        extLimit.setStart(page);
        extLimit.setSort("first_name");
        extLimit.setDir("ASC");
        Employee employee = new Employee();
        employee.setFirstName("Aamer");
        //employee.setEmployeeNo(10009);
        return employeeMapper.selectByLimit(employee, extLimit);
    }

    @GetMapping("/employeesdetail/{id}")
    EmployeeDetail employeeDetail(@PathVariable("id") Long id) {
        return service.employeeDetail(id);
//        EmployeeDetail employeeDetail = employeeDetailMapper.selectById(id);
//        return employeeDetail;
    }

    @GetMapping("/title/{id}")
    Title getTitle(@PathVariable("id") Long id)
    {
        return service.getTitle(id);
    }
}
