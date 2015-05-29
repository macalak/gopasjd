package ite.librarymaster.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitorInterceptor {
	final Logger logger = LoggerFactory.getLogger(MonitorInterceptor.class);
	
	@AroundInvoke
	public Object traceMethod(InvocationContext ctx) throws Exception{
		try {
			String methodName = ctx.getMethod().getName();
			String className = ctx.getTarget().getClass().getName();
			// Retrieve message from previous interceptor
			logger.info("MONITOR: Message received {}",ctx.getContextData().get("MESSAGE"));
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
