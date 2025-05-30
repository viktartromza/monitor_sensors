package by.agsr.monitorsensors.aop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LogAspect {

    private final HttpServletRequest request;

    @Pointcut("execution(public * *(..))")
    private void anyPublicOperation() {
    }

    @Pointcut("execution(* by.agsr.monitorsensors.service..*.*(..))")
    private void anyOperation() {
    }

    @Pointcut("within(by.agsr.monitorsensors.controller..*)")
    private void inController() {
    }

    @Pointcut("within(by.agsr.monitorsensors.service..*)")
    private void inService() {
    }

    @Around(value = "anyPublicOperation() && inService() && target(service)")
    private Object getLogAfterServiceMethod(ProceedingJoinPoint joinPoint, Object service) throws Throwable {
        log.info("Method {} in {} started!", methodName(joinPoint), classNameService(service));
        Object retVal = joinPoint.proceed();
        log.info("Method {} in {} finished!", methodName(joinPoint), classNameService(service));
        return retVal;
    }

    @Around(value = "inController() && target(controller)")
    private Object getLogAroundController(@NotNull ProceedingJoinPoint joinPoint, Object controller) throws Throwable {
        log.info("Starting request {} in {}, method {}, parameters [{}]  [{}]", request.getMethod(), classNameController(controller), methodName(joinPoint),
                parameters(request), request.getRequestURL());
        Object retVal = joinPoint.proceed();
        log.info("Returns response {}", retVal);
        return retVal;
    }

   @AfterThrowing(value = "anyOperation()", throwing = "ex")
    void getLogAfterThrowing(JoinPoint joinPoint, Exception ex) {
        log.error("Method {} resulted into exception {}, message {}", methodName(joinPoint), ex.getClass(), ex.getMessage());
    }

    private String classNameController(Object pointcut) {
        return pointcut.toString().split("\\.")[4].split("@")[0];
    }

    private String classNameService(Object pointcut) {
        return pointcut.toString().split("\\.")[5].split("@")[0];
    }

    private String methodName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

    private Map<String, String> parameters(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>();
        for (Map.Entry<String, String[]> es : request.getParameterMap().entrySet()) {
            parameters.put(es.getKey(), Arrays.toString(es.getValue()));
        }
        return parameters;
    }
}