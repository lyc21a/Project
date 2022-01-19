package com.example.demo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class JavaConfig {
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8&serverTimezone=UTC");
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2);   //최초로 생성할 connection 수
		ds.setMaxActive(10);	//최대로 생성할 connection 수
		ds.setTestWhileIdle(true);	//연결 유효성 검사
		ds.setMinEvictableIdleTimeMillis(60000 * 3);	//최대 유휴 상태 시간
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);	// 검사주기
		return ds;
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
	@Bean
	public BookDao bookDao() {
		return new BookDao(dataSource());
	}
	
	@Bean
	public BorrowerDao borrowerDao() {
		return new BorrowerDao(dataSource());
	}
	
	@Bean
	public BookreserveDao reserveDao() {
		return new BookreserveDao(dataSource());
	}
}