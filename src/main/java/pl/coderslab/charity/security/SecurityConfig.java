package pl.coderslab.charity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.charity.role.RoleType;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public CustomUserDetailsService customUserDetailsService(){return new CustomUserDetailsService();}

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/form/**").authenticated()
                .antMatchers("/admin/**").hasAuthority(RoleType.ROLE_ADMIN.toString())
                .antMatchers("/profile/{userId}/**").access("hasRole('ADMIN') or @userSecurity.isCurrentUser(authentication, #userId)")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/donation")
                .and()
                .logout().logoutSuccessUrl("/login").permitAll();
    }
}
