# WebFlux


### 1. 의존성
- java 11
- Spring Boot DevTools
- LomBok
- Spring Reactive Web
- MySQL Driver
- Spring R2DBC

### 2. DB
```sql
create user 'pos'@'%' identified by '1234';
GRANT ALL PRIVILEGES ON *.* TO 'pos'@'%';
create database pos;
```

### 3. CommandLineRunner 이해하기
```
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Test implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		System.out.println("안뇽~~~~~~~~~~~~~~~~~~~~~~");	
	}
}
```