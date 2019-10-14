package com.empserver.model;

public class Order {
    String id;
    String status;
    Integer total;
    String customer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(id, order.id)
                .append(status, order.status)
                .append(total, order.total)
                .append(customer, order.customer)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(id)
                .append(status)
                .append(total)
                .append(customer)
                .toHashCode();
    }
}
