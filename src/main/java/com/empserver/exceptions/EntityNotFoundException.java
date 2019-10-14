package com.empserver.exceptions;

import java.util.function.Supplier;

public class EntityNotFoundException extends RuntimeException  {

    private String entityName;

    public EntityNotFoundException(String entityName){
        super(entityName + " was not found");
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }
}
