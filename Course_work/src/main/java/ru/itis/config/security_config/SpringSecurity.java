package ru.itis.config.security_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    @Qualifier(value = "jwtAuthenticationFilter")
    private GenericFilterBean jwtAuthenticationFilter;

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

        //TODO: Перенести в другой проект REST, либо продолжить страдать и подключать его тут
//        http.antMatcher("/rest/**").sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.antMatcher("/rest/**").addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/rest/signIn");
    }
}
