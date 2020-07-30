package com.empserver.service;

import com.empserver.mapper.EmployeeDetailMapper;
import com.empserver.mapper.EmployeeMapper;
import com.empserver.mapper.TitleMapper;
import com.empserver.model.Employee;
import com.empserver.model.EmployeeDetail;
import com.empserver.model.Title;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.Optional;

//@Component
@Service
//@Transactional
public class ServiceImpl implements EmployeeService {

    @Autowired
    public ServiceImpl(EmployeeDetailMapper employeeDetailMapper, EmployeeMapper employeeMapper, TitleMapper titleMapper) {
        this.employeeDetailMapper = employeeDetailMapper;
        this.employeeMapper = employeeMapper;
        this.titleMapper = titleMapper;
    }

    //@Autowired
    EmployeeDetailMapper employeeDetailMapper;

    //@Autowired
    EmployeeMapper employeeMapper;

    //@Autowired
    TitleMapper titleMapper;

    @Transactional
    public Employee getEmployee(Long id) {
         return employeeMapper.selectById(id);
    }

    public Optional<Employee> getEmployee1(Long id) {
        BigDecimal bigDecimal;
        Optional<Employee> employee =  Optional.ofNullable(employeeMapper.selectById(id));
        return  employee;
    }

    public Title getTitle(Long id){
        return titleMapper.selectById(id);
    }

    @Override
    public EmployeeDetail getEmployeeDetail(Long id) {
        EmployeeDetail employeeDetail = employeeDetailMapper.selectById(id);
        return employeeDetail;
    }
}
