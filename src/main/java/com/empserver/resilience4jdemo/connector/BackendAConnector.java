package com.empserver.resilience4jdemo.connector;

import com.empserver.exceptions.BusinessException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Component("backendAConnector")
@CircuitBreaker(name = "backendA")
@Retry(name = "backendA")
public class BackendAConnector implements Connector {
    @Override
    public String failure() {
         throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,  "This is a remote exception");
    }

    @Override
    public String success() {
        return "Hello World from backend A";
    }

    @Override
    public String ignoreException() {
        throw new BusinessException("This exception is ignored by the CircuitBreaker of backend A");
    }

    @Override
    @Bulkhead(name = "backendA", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> futureSuccess() {
        return CompletableFuture.completedFuture("Hello World from backend A");
    }

    @Override
    @Bulkhead(name = "backendA", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> futureFailure() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.completeExceptionally(new IOException("BAM!"));
        return future;
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
