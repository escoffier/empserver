package com.empserver.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Title implements Serializable {

    private static final long serialVersionUID = 40002L;

    int employeeNo;
    String title;
    LocalDate fromDate;
    LocalDate toDate;

    public int getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(int employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}


