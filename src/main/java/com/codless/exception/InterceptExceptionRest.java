package com.codless.exception;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;

/**
 * 
 * Interceptor of all requests, here observer the method executed and 
 * case the method executed call a new exception, this interceptor send a new {@link Response}
 * 
 * */
@Interceptor @ExceptionRest @Priority(Interceptor.Priority.APPLICATION)
public class InterceptExceptionRest {
	
	@AroundInvoke
	public Object aditar(InvocationContext context){
		
		try {
			
			return context.proceed(); 
		} catch (Exception e) {

			return Response.status(500).entity(new ExceptionDefault(e.getMessage())).build();
		}
	}
}

