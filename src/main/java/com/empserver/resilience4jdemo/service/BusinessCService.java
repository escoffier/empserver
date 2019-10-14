package com.empserver.resilience4jdemo.service;

import com.empserver.resilience4jdemo.connector.Connector;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service("businessCService")
public class BusinessCService implements BusinessService {

    private final Connector connectorC;

    public BusinessCService(@Qualifier("backendCConnector") Connector backendAConnector) {
        this.connectorC =  backendAConnector;
    }
    @Override
    public String failure() {
        return connectorC.failure();
    }

    @Override
    public String success() {
        return connectorC.success();
    }

    @Override
    public String ignore() {
        return connectorC.ignoreException();
    }

    @Override
    public CompletableFuture<String> futureSuccess() {
        return connectorC.futureSuccess();
    }

    @Override
    public CompletableFuture<String> futureFailure() {
        return connectorC.futureFailure();
    }
}
