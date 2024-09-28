package kr.co.softsoldesk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
	
	//스프링 MVC의 CORS(Cross-Origin Resource Sharing) 설정을 담당하는 클래스
		@Configuration
		public class WebConfig implements WebMvcConfigurer {
		    @Override
		    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		        registry.addResourceHandler("/upload/**")
		                .addResourceLocations("file:/SpoLinkProject/src/main/webapp/WEB-INF/resource/upload/"); //파일경로
		        
		        registry.addResourceHandler("/images/**")
	            .addResourceLocations("classpath:/static/images/")
	            .addResourceLocations("/WEB-INF/resource/images/");
		        registry.addResourceHandler("/resource/**")	
        		.addResourceLocations("/WEB-INF/resource/");
	    }
	}

