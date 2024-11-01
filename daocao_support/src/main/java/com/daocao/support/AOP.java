package com.daocao.support;

import com.daocao.common.domain.dto.LoginDto;
import com.daocao.common.response.DaocaoResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/*
* 统一日志管理
* */
@Component
@Aspect
public class AOP {
    /**
     * 请求开始时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 设置操作日志切入点，记录操作日志，在注解(@OperationLogger)的位置切入代码
     */
    @Pointcut("@annotation(com.daocao.support.OperationLog)")
    public void optLogPointCut() {
    }
    /**
     * 前置通知，在切入点(optLogPointCut方法)之前执行，用于记录请求开始时间。
     */
    @Before("optLogPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        // 记录请求开始时间

    }
    /**
     *  AfterReturning通知方法，当带有@OptLogger注解的方法成功执行后，拦截并记录用户操作的细节。
     *
     * @param joinPoint 切面方法的信息
     * @param result    返回结果
     */
    @AfterReturning(value = "optLogPointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        // 获取操作
        String name = joinPoint.getSignature().getName();
        if(name.equals("sysLogin")){
            Object[] args = joinPoint.getArgs();
            LoginDto arg = (LoginDto)args[0];
            DaocaoResult res = (DaocaoResult) result;
            StringBuilder str= new StringBuilder("AOP日志记录：---------用户[" + arg.getAccount() + "]正在调用方法-" + name + "-尝试登录--->[登录");
            str.append(res.isSuccess() ? "成功" : "失败").append("]");
            logger.info(String.valueOf(str));
        }



    }
    @AfterThrowing(value = "optLogPointCut()",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception) {
        //目标方法名
        Object[] args = joinPoint.getArgs();
        LoginDto arg = (LoginDto)args[0];
        if (exception instanceof  AuthenticationServiceException ) {
            logger.error("AOP日志记录：---------用户["+arg.getAccount()+"]登录失败，账号或密码错误");
        }

    }

    /**
     * 环绕通知：
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
//    @Around(value = "optLogPointCut()")
//    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
//        logger.info("环绕通知的目标方法名："+proceedingJoinPoint.getSignature().getName());
//        try {
//            Object obj = proceedingJoinPoint.proceed();
//            return obj;
//        } catch (Throwable throwable) {
//
//        }
//        return null;
//    }

}
