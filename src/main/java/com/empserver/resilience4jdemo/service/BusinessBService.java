package com.empserver.resilience4jdemo.service;

import com.empserver.resilience4jdemo.connector.Connector;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service("businessBService")
public class BusinessBService implements BusinessService {

    private final Connector connectorB;

    public BusinessBService(@Qualifier("backendBConnector") Connector backendBConnector) {
        this.connectorB =  backendBConnector;
    }
    @Override
    public String failure() {
        return connectorB.failure();
    }

    @Override
    public String success() {
        return connectorB.success();
    }

    @Override
    public String ignore() {
        return connectorB.ignoreException();
    }

    @Override
    public CompletableFuture<String> futureSuccess() {
        return null;
    }

    @Override
    public CompletableFuture<String> futureFailure() {
        return null;
    }
}
