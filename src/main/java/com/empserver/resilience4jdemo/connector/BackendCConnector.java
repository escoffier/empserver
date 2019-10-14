package com.empserver.resilience4jdemo.connector;

import com.empserver.exceptions.BusinessException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Component("backendCConnector")
//@CircuitBreaker(name = "backendC")
//@Retry(name = "backendC")
@RateLimiter(name = "backendC")
public class BackendCConnector implements Connector {

    @Override
    public String failure() {
         throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,  "This is a remote exception");
    }

    @Override

    public String success() {
        return "Hello World from backend C";
    }

    @Override
    public String ignoreException() {
        throw new BusinessException("This exception is ignored by the CircuitBreaker of backend A");
    }

    @Override
    @Bulkhead(name = "backendC", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> futureSuccess() {
        return CompletableFuture.completedFuture("Hello World from backend A");
    }

    @Override
    @Bulkhead(name = "backendC", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> futureFailure() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.completeExceptionally(new IOException("BAM!"));
        return future;
    }

    @Override
    public Mono<String> MonoSuccess() {
        return Mono.just("Hello World from backend C");
    }

    @Override
    public Mono<String> monoFailure() {
        return Mono.error(new IOException("BAM!"));
    }
}
