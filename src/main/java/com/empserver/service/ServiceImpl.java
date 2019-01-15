package com.empserver.service;

import com.empserver.mapper.EmployeeDetailMapper;
import com.empserver.mapper.EmployeeMapper;
import com.empserver.model.Employee;
import com.empserver.model.EmployeeDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@Transactional
public class ServiceImpl implements Service {

    @Autowired
    EmployeeDetailMapper employeeDetailMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    public Employee getEmployee(Long id) {
        return employeeMapper.selectById(id);
    }

    @Override
    public EmployeeDetail employeeDetail(Long id) {
        EmployeeDetail employeeDetail = employeeDetailMapper.selectById(id);
        return employeeDetail;
    }
}
