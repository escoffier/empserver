package com.empserver.resilience4jdemo.controller;

import com.empserver.resilience4jdemo.service.BusinessService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/backendA")
public class BackendAController {

    private final BusinessService businessAService;

    public BackendAController(@Qualifier("businessAService") BusinessService businessAService){
        this.businessAService = businessAService;
    }

    @GetMapping("failure")
    public String failure(){
        return businessAService.failure();
    }

    @GetMapping("success")
    public String success(){
        return businessAService.success();
    }

    @GetMapping("ignore")
    public String ignore(){
        return businessAService.ignore();
    }

    @GetMapping("futureSuccess")
    public CompletableFuture<String> futureSuccess() {
        return businessAService.futureSuccess();
    }

    @GetMapping("futureFailure")
    public CompletableFuture<String > futureFailure() {
        return businessAService.futureFailure();
    }

}
