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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Title title1 = (Title) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(employeeNo, title1.employeeNo)
                .append(title, title1.title)
                .append(fromDate, title1.fromDate)
                .append(toDate, title1.toDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(employeeNo)
                .append(title)
                .append(fromDate)
                .append(toDate)
                .toHashCode();
    }
}


