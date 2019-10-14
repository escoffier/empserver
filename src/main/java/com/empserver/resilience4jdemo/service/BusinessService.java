package com.empserver.resilience4jdemo.service;

import java.util.concurrent.CompletableFuture;

public interface BusinessService {
    String failure();

    String success();

    String ignore();

    CompletableFuture<String> futureSuccess();

    CompletableFuture<String> futureFailure();

}
