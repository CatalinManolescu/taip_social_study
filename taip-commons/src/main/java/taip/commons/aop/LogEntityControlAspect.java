package taip.commons.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Laura Asoltanei <laura.asoltanei@gmail.com>
 */
@Aspect
public class LogEntityControlAspect {
    private static Logger logger = LoggerFactory.getLogger(LogEntityControlAspect.class);
    
    @Around("execution(* taip.commons.control.EntityControl+.create(..))")
    public void logCreate(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.debug("<<: " + joinPoint.getSignature().getName());
        joinPoint.proceed();
        logger.debug(">>: " + joinPoint.getSignature().getName());
    }

    @Around("execution(* taip.commons.control.EntityControl+.update(..))")
    public void logUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.debug("<<: " + joinPoint.getSignature().getName());
        joinPoint.proceed();
        logger.debug(">>: " + joinPoint.getSignature().getName());
    }

    @Around("execution(* taip.commons.control.EntityControl+.remove(..))")
    public void logRemove(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.debug("<<: " + joinPoint.getSignature().getName());
        joinPoint.proceed();
        logger.debug(">>: " + joinPoint.getSignature().getName());
    }
}
