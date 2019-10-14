package com.empserver.resilience4jdemo.connector;


import com.empserver.exceptions.BusinessException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Component
//@RateLimiter(name = "backendB")
public class BackendBConnector implements Connector {

    @Override
    @Bulkhead(name = "backendB")
    public String failure() {
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,  "This is a remote exception");
    }

    @Override
    @Bulkhead(name = "backendB")
    public String success() {
        return "Hello World from backend B";
    }

    @Override
    @Bulkhead(name = "backendB")
    public String ignoreException() {
        throw new BusinessException("This exception is ignored by the CircuitBreaker of backend B");
    }

    @Override
    public CompletableFuture<String> futureSuccess() {
        return null;
    }

    @Override
    public CompletableFuture<String> futureFailure() {
        return null;
    }

    @Override
    public Mono<String> MonoSuccess() {
        return null;
    }

    @Override
    public Mono<String> monoFailure() {
        return null;
    }
}
