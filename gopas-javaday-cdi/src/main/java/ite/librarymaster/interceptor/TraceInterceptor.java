package ite.librarymaster.interceptor;

import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Interceptor
@Tracer
public class TraceInterceptor {
	final Logger logger = LoggerFactory.getLogger(TraceInterceptor.class);
	
	@AroundInvoke
	public Object traceMethod(InvocationContext ctx) throws Exception{
		try {
			String methodName = ctx.getMethod().getName();
			String className = ctx.getTarget().getClass().getName();
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
}
