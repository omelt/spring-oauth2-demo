package com.example.logservice.aspect;

import com.alibaba.fastjson.JSON;
import com.example.logservice.annotation.SysLogger;
import com.example.logservice.service.LoggerService;
import com.example.logservice.util.tools.UserUtils;
import com.sun.deploy.net.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class SysLoggerAspect {

    @Autowired
    private LoggerService loggerService;

    @Pointcut("@annotation(com.example.logservice.annotation.SysLogger)")
    public void loggerPointCut(){}

    @Before("loggerPointCut()")
    public void saveSysLog(JoinPoint joinPoint){
        MethodSignature signature= (MethodSignature) joinPoint.getSignature();

        Method method=signature.getMethod();

        String msg="";

        SysLogger sysLogger=method.getAnnotation(SysLogger.class);

        if (sysLogger !=null){
            msg+=sysLogger.value();
        }

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        msg+="\n"+className+'.'+methodName+"()";

        Object[] args = joinPoint.getArgs();

        String parms="";

        for (Object o : args) {
            parms+= JSON.toJSONString(o);
        }
        if(StringUtils.isNotBlank(parms)){
            msg+="\n"+parms;
        }
        msg+="\nip";

        String username= UserUtils.getCurrentPrinciple();

        msg+="\n"+username;

        msg+="\n"+new Date().toString();


        loggerService.log(msg);

    }
}
























