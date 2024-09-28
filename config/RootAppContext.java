package kr.co.softsoldesk.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.context.annotation.SessionScope;

import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.mapper.AdminMapper;
import kr.co.softsoldesk.mapper.BoardMapper;
import kr.co.softsoldesk.mapper.ContestMapper;
import kr.co.softsoldesk.mapper.EventMapper;
import kr.co.softsoldesk.mapper.ImageMapper;
import kr.co.softsoldesk.mapper.MyPageMapper;
import kr.co.softsoldesk.mapper.UserMapper;
import kr.co.softsoldesk.mapper.VolunteerMapper;


@Configuration
@ComponentScan(basePackages = {"kr.co.softsoldesk.service", "kr.co.softsoldesk.dao", "kr.co.softsoldesk.Validator"})
@PropertySource("/WEB-INF/properties/db.properties")
public class RootAppContext {
	//프로젝트 작업 시 사용할 bean을 관리하는 클래스
	//@PropertySource("file:C:/Users/soldesk/Downloads/SpoLinkProject/src/main/webapp/WEB-INF/properties/option.properties")
	@Value("${db.classname}")
	private String db_classname;
	
	@Value("${db.url}")
	private String db_url;
	
	@Value("${db.username}")
	private String db_username;
	
	@Value("${db.password}")
	private String db_password;
	
	//로그인한 회원의 정보 주입
		@Bean("loginUserBean") //Bean 등록
		@SessionScope //객체 생성을 Session 영역에 생성
		@Lazy //객체의 생성시점을 loginUserBean을 호출하는 시점에 생성
		public UserBean loginUserBean() {
			
			return new UserBean();
		}
	
	//데이터베이스 접속정보 관리 Bean
	@Bean 
	public BasicDataSource dataSource() {
		
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);
		
		return source;
		
	}
	//쿼리문과 접속정보를 관리 Bean
	@Bean
	public SqlSessionFactory factory(BasicDataSource souce) throws Exception {
		
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(souce);
		SqlSessionFactory factory = factoryBean.getObject();
		
		return factory;
	}
	
	//#1 userMapper 등록
	@Bean
	public MapperFactoryBean<UserMapper> getTopMenuMapper(SqlSessionFactory factory) {
		
		MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<UserMapper>(UserMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		
		return factoryBean;
	}
	//성현
	//#2 boarMapper 등록
		@Bean
		public MapperFactoryBean<BoardMapper> getBoardMapper(SqlSessionFactory factory) {
			
			MapperFactoryBean<BoardMapper> factoryBean = new MapperFactoryBean<BoardMapper>(BoardMapper.class);
			factoryBean.setSqlSessionFactory(factory);
			
			return factoryBean;
		}
		//#3 imageMapper 등록
		@Bean
		public MapperFactoryBean<ImageMapper> getImageMapper(SqlSessionFactory factory) {
			
			MapperFactoryBean<ImageMapper> factoryBean = new MapperFactoryBean<ImageMapper>(ImageMapper.class);
			factoryBean.setSqlSessionFactory(factory);
			
			return factoryBean;
		}
	
		//#4 ContentMapper 등록
		@Bean
		public MapperFactoryBean<ContestMapper> getContestMapper(SqlSessionFactory factory) {
			
			MapperFactoryBean<ContestMapper> factoryBean = new MapperFactoryBean<ContestMapper>(ContestMapper.class);
			factoryBean.setSqlSessionFactory(factory);
			
			return factoryBean;
		}
		//#5 EventMapper 등록
		@Bean
		public MapperFactoryBean<EventMapper> getEventMapper(SqlSessionFactory factory) {
			
			MapperFactoryBean<EventMapper> factoryBean = new MapperFactoryBean<EventMapper>(EventMapper.class);
			factoryBean.setSqlSessionFactory(factory);
			
			return factoryBean;
		}
		//#6 volunteerMapper 등록
		@Bean
		public MapperFactoryBean<VolunteerMapper> getVolunteerMapper(SqlSessionFactory factory) {
			
			MapperFactoryBean<VolunteerMapper> factoryBean = new MapperFactoryBean<VolunteerMapper>(VolunteerMapper.class);
			factoryBean.setSqlSessionFactory(factory);
			
			return factoryBean;
		}
		
		
		 // 프로퍼티 파일에서 값을 로딩하기 위한 Bean 설정
	    @Bean
	    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	        return new PropertySourcesPlaceholderConfigurer();
	    }
	    // 트랜잭션 관리를 위한 Bean
	    @Bean
	    public DataSourceTransactionManager transactionManager(BasicDataSource dataSource) {
	        return new DataSourceTransactionManager(dataSource);
	    }

	    // SqlSessionTemplate Bean 설정 (SqlSessionFactory를 주입받음)
	    @Bean
	    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
	        return new SqlSessionTemplate(sqlSessionFactory);
	    }
	
		
	//#7 mypageMapper 등록
	@Bean
	public MapperFactoryBean<MyPageMapper> getMyPageuMapper(SqlSessionFactory factory) {
		
		MapperFactoryBean<MyPageMapper> factoryBean = new MapperFactoryBean<MyPageMapper>(MyPageMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		
		return factoryBean;
	}
	
	//#8 adminMapper 등록
		@Bean
		public MapperFactoryBean<AdminMapper> getAdMinuMapper(SqlSessionFactory factory) {
			
			MapperFactoryBean<AdminMapper> factoryBean = new MapperFactoryBean<AdminMapper>(AdminMapper.class);
			factoryBean.setSqlSessionFactory(factory);
			
			return factoryBean;
		}
	
}
