package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {
	
	@Around("execution( * org.zerock.service.SampleService*.*(..) )" )
	public Object logTime(ProceedingJoinPoint pjp) {		
		//시작시간
		long start = System.currentTimeMillis();		
		log.info("Target :" + pjp.getTarget());
		log.info("param:" + Arrays.toString(pjp.getArgs()));		
		//invoke method
		Object result = null;		
		try {
			result= pjp.proceed();			
		}catch (Throwable e) {
			e.printStackTrace();
		}//end try		
		//끝나는 시간
		long end = System.currentTimeMillis();		
		log.info("TIme:" + (start - end)); //시간 출력		
		return result;		
	}//end log...	

	
	
	@AfterThrowing(pointcut = "execution(* org.zerock.service.SampleService*.*(..))",
			throwing = "exception")
	public void logException(Exception exception) {
		log.info("Exception....!!!!");
		log.info("Exception" + exception);		
	}//end log....

	
	//Aspect expression
	@Before("execution(* org.zerock.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("=======before=====");
	}//end log....
	
	  @Before("execution(* org.zerock.service.SampleService*.doAdd(String, String))&& args(str1,str2)" ) 
	  public void logBeforeWithParam(String str1, String str2) { 
		  log.info("str1"  + str1); 
		  log.info("str2" + str2);
	  
	  }//end log.... 


}//end class

