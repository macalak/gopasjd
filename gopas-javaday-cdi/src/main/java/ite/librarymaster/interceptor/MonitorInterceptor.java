package ite.librarymaster.interceptor;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;

//@Priority(Interceptor.Priority.APPLICATION+10)
@Interceptor
@Monitor
public class MonitorInterceptor {
	
	@Inject
	private Logger logger;
	
	@AroundInvoke
	public Object traceMethod(InvocationContext ctx) throws Exception{
		try {
			String methodName = ctx.getMethod().getName();
			String className = ctx.getTarget().getClass().getName();
			long startTime = System.currentTimeMillis();
			Object result = ctx.proceed();
			logger.info("MONITOR: {}.{} executed in {} ms.",className,methodName,(System.currentTimeMillis()-startTime));
			return result;
		} catch (Exception e) {
			logger.error("Exception in MonitorInterceptor: {}", e.getMessage());
			throw e;
		}
	}
}
