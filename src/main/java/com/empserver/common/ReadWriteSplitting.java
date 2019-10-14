package com.empserver.common;

import com.empserver.config.WriteReadRoutingDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReadWriteSplitting implements Ordered {

    private static final Logger logger = LoggerFactory.getLogger(ReadWriteSplitting.class);

//    @Before("execution(* com.empserver.service.*.get*(..))")
//    public void setReadable() {
//        WriteReadRoutingDataSource.readable();
//    }

    @Around("execution(* com.empserver.service.*.get*(..))")
    public Object setReadable(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            logger.debug("Using readable datasource");
            WriteReadRoutingDataSource.readable();
            return joinPoint.proceed();
        } finally {
            WriteReadRoutingDataSource.clear();
        }
    }

    @Around("execution(* com.empserver.service.*.set*(..))")
    private Object setWritable(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            logger.debug("Using writable datasource");
            WriteReadRoutingDataSource.writable();
            return joinPoint.proceed();
        } finally {
            WriteReadRoutingDataSource.clear();
        }

    }

    //重写 getOrder 保证本切面优先级高于事务切面优先级
    @Override
    public int getOrder() {
        return 0;
    }
}
