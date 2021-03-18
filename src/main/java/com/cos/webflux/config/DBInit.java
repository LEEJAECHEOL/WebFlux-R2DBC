package com.cos.webflux.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import com.cos.webflux.domain.Customer;
import com.cos.webflux.domain.CustomerRepository;

import io.r2dbc.spi.ConnectionFactory;

@Configuration
public class DBInit {
	
	// 디비를 초기화 할 수 있음
	@Bean
	public ConnectionFactoryInitializer dbInit(ConnectionFactory connectionFactory) {
		ConnectionFactoryInitializer init = new ConnectionFactoryInitializer();
		init.setConnectionFactory(connectionFactory);
		init.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
		return init;
	}
	
	//  IoC에 등록이 되면 스프링부트에서 CommandLineRunner는 리턴되는 값을 실행시킴
	@Bean
	public CommandLineRunner dataInit(CustomerRepository customerRepository) { 
		// run 함수
		return (args) -> {
			// 데이터 초기화하기
			customerRepository.saveAll(Arrays.asList(
					new Customer("Jack", "Bauer"),
					new Customer("Choi", "Bauer"),
					new Customer("Hong", "Bauer"),
					new Customer("LEE", "Bauer"),
					new Customer("Joo", "Bauer")
					)
				).blockLast(); // blockLast()를 걸어줘야 데이터가 삽입됨. 비동기에서 끝을 알려 주어야 함!!! 종료시키는 것이다.
		};
	}
}
