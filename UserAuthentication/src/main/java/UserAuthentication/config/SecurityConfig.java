package UserAuthentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.PUT, "/api/v1/user/update/*").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/register").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/login").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/deactivate/{username}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/activate/{username}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/filter/{character}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/page/{offset}/{pageSize}").authenticated()
                        .requestMatchers(HttpMethod.GET,"/api/v1/user/getallusers").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/*").permitAll()
                        .anyRequest().permitAll()

                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails userDetails = User.withUsername("ram")
//                .password(passwordEncoder().encode("ram123"))
//
//                .roles("USER", "ADMIN")
//                .build();
//        System.out.println("password" + userDetails.getPassword());
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(userDetails);
//
//        return manager;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
