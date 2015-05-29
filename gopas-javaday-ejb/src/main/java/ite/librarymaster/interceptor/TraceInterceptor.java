package ite.librarymaster.interceptor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TraceInterceptor {
	final Logger logger = LoggerFactory.getLogger(TraceInterceptor.class);
	
	@AroundInvoke
	public Object traceMethod(InvocationContext ctx) throws Exception{
		try {
			String methodName = ctx.getMethod().getName();
			String className = ctx.getTarget().getClass().getName();
			// Pass some message to following interceptors in the chain
			ctx.getContextData().put("MESSAGE", "Hallo from TRACER!");
			logger.info("TRACER: Entering  {}.{}.",className,methodName);
			Object result = ctx.proceed();
			logger.info("TRACER: Exiting  {}.{}.",className,methodName);
			return result;
		} catch (Exception e) {
			logger.error("Exception in TraceInterceptor: {}", e.getMessage());
			throw e;
		}
	}
	
	@AroundConstruct
	public void traceConstructor(InvocationContext ctx) {
		try {
			ctx.proceed();
			String className = ctx.getTarget().getClass().getName();
			logger.info("TRACER: [CONSTRUCTOR] Service {} created.",className);
		} catch (Exception e) {
			logger.error("Exception in TraceInterceptor: {}", e.getMessage());
		}
	}
	
	@PostConstruct
	public void tracePostConstruct(InvocationContext ctx) {
		try {
			// Try to comment next line and see what happens (PostConstruct will not be called at all)
			ctx.proceed();
			String className = ctx.getTarget().getClass().getName();
			logger.info("TRACER: [POSTCONSTRUCT] Service {} initialized.",className);
		} catch (Exception e) {
			logger.error("Exception in TraceInterceptor: {}", e.getMessage());
		}
	}
	
	@PreDestroy
	public void tracePreDestroy(InvocationContext ctx) {
		try {
			String className = ctx.getTarget().getClass().getName();
			logger.info("TRACER: [PREDESTROY] : Service {} about to destroy.",className);
			ctx.proceed();
		} catch (Exception e) {
			logger.error("Exception in TraceInterceptor: {}", e.getMessage());
		}
	}
}
