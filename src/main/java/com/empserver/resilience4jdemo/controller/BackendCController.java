package com.empserver.resilience4jdemo.controller;

import com.empserver.resilience4jdemo.service.BusinessService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/backendC")
public class BackendCController {

    private final BusinessService businessCService;

    public BackendCController(@Qualifier("businessCService") BusinessService businessAService){
        this.businessCService = businessAService;
    }

    @GetMapping("failure")
    public String failure(){
        return businessCService.failure();
    }

    @GetMapping("success")
    public String success(){
        return businessCService.success();
    }

    @GetMapping("ignore")
    public String ignore(){
        return businessCService.ignore();
    }

    @GetMapping("futureSuccess")
    public CompletableFuture<String> futureSuccess() {
        return businessCService.futureSuccess();
    }

    @GetMapping("futureFailure")
    public CompletableFuture<String > futureFailure() {
        return businessCService.futureFailure();
    }

}
