package com.empserver.resilience4jdemo.service;

import com.empserver.resilience4jdemo.connector.Connector;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service("businessAService")
public class BusinessAService implements BusinessService {

    private final Connector connectorA;

    public BusinessAService(@Qualifier("backendAConnector") Connector backendAConnector) {
        this.connectorA =  backendAConnector;
    }
    @Override
    public String failure() {
        return connectorA.failure();
    }

    @Override
    public String success() {
        return connectorA.success();
    }

    @Override
    public String ignore() {
        return connectorA.ignoreException();
    }

    @Override
    public CompletableFuture<String> futureSuccess() {
        return connectorA.futureSuccess();
    }

    @Override
    public CompletableFuture<String> futureFailure() {
        return connectorA.futureFailure();
    }
}
