package api.parkingcontrol.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz.anyRequest().authenticated())
            .csrf((csrf) -> csrf.disable())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        //Não é recomendado para uma aplicação real, mas para demostração/treinamento como nesse caso não há problema.
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("010203")
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
    
}
