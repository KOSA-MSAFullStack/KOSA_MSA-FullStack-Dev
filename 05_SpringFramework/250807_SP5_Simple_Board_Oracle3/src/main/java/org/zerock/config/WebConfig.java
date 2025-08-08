// WebConfig.java

package org.zerock.config;
//import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.support.
AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{
	

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class}; 
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfig.class};
	}
	@Override
	@NonNull
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
}
