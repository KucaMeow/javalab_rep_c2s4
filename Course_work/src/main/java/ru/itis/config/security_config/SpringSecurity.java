package ru.itis.config.security_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()

                .antMatchers("/").permitAll()
                .antMatchers("/verify").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/login").not().authenticated()
                .antMatchers("/register").not().authenticated()
                .antMatchers("/sandbox").authenticated()
                .antMatchers("/courses").authenticated()
                .antMatchers("editor").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                //Тут продолжать разрешения, если надо

                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureForwardUrl("/login?error=true")

                .and()
                .rememberMe().key("Don'tShowItToAnyone")
                .rememberMeParameter("remember-me");
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
