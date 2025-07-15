package UserAuthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class UserAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationApplication.class, args);
		System.out.println(" --- S T A R T --- ");
	}

}
