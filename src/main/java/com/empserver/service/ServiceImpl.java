package com.empserver.service;

import com.empserver.mapper.EmployeeDetailMapper;
import com.empserver.mapper.EmployeeMapper;
import com.empserver.mapper.TitleMapper;
import com.empserver.model.Employee;
import com.empserver.model.EmployeeDetail;
import com.empserver.model.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Component
@org.springframework.stereotype.Service
@Transactional
public class ServiceImpl implements Service {

    @Autowired
    EmployeeDetailMapper employeeDetailMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    TitleMapper titleMapper;

    public Employee getEmployee(Long id) {
        //Optional<Employee> employee = new Optional<>(employeeMapper.selectById(id));
        return employeeMapper.selectById(id);
    }

    public Optional<Employee> getEmployee1(Long id) {
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
