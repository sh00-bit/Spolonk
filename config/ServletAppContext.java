package kr.co.softsoldesk.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.interceptor.TopMenuInterceptor;

@Configuration
@EnableWebMvc // 컨트롤러라고 명시한 걸 끌고옴 (컨트롤러로 쓰겠다는 걸 알아보기 위함)
@ComponentScan("kr.co.softsoldesk.controller")
public class ServletAppContext implements WebMvcConfigurer {
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
//		Spring MVC 프로젝트 설정 인터페이스(없어도 굴러는 감, 입맛대로 맞추기용)
//		Controller의 메서드가 반환하는 JSP 의 이름 앞/뒤에 경로와 확장자를 붙여주는 메서드

		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
//		일단 요청이 들어오면 이 파일 뒤져봐! (WEB-INF 안에 넣는게 보안이 뛰어남)
	}
	
	//정적파일 등록
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/resource/");
	}
	
	//인터셉터 등록
		@Override
		public void addInterceptors(InterceptorRegistry registry) {	
			
			WebMvcConfigurer.super.addInterceptors(registry);
			
			TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(loginUserBean);
			InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
			reg1.addPathPatterns("/**");
		}
	
	//에러 메시지 등록
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		
		new PropertySourcesPlaceholderConfigurer();
		
		return new PropertySourcesPlaceholderConfigurer();
	}//프로퍼티 파일 로딩해주는 객체
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		
		ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
		res.setBasename("/WEB-INF/properties/error_message");
		
		return res;
		
	}
	//파일 업로드 처리 클래스
		@Bean
		public StandardServletMultipartResolver multipartResolver() {
			
			return new StandardServletMultipartResolver();
		}
		
		

}
