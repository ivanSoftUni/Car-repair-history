package softuni.carrepairhistory.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import softuni.carrepairhistory.models.enums.UserRoleEnum;
import softuni.carrepairhistory.repositories.UserRepository;
import softuni.carrepairhistory.services.ApplicationUserDetailsService;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests().
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                requestMatchers("/", "/users/login", "/users/register").permitAll().
                requestMatchers("/admin/users").hasRole(UserRoleEnum.ADMIN.name()).
                anyRequest().
                authenticated().
                and().

                formLogin().
                loginPage("/users/login").
                usernameParameter("username").
                passwordParameter("password").
                defaultSuccessUrl("/", true).
                failureForwardUrl("/users/login-error").
                and().
                logout().
                logoutUrl("/users/logout").
                logoutSuccessUrl("/").
                deleteCookies("JSESSIONID").
                invalidateHttpSession(true).
                clearAuthentication(true);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new ApplicationUserDetailsService(userRepository);
    }

}
