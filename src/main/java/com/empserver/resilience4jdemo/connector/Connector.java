package com.empserver.resilience4jdemo.connector;

import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public interface Connector {
    String failure();

    String success();

    String ignoreException();

    public CompletableFuture<String> futureSuccess();

    public CompletableFuture<String> futureFailure();

    public Mono<String> MonoSuccess();

    public Mono<String> monoFailure();

}
