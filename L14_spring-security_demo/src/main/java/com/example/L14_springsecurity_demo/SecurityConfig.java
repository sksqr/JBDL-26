package com.example.L14_springsecurity_demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("shashi").password("$2a$10$RRQxTVGGtL8ah0IxG.X.t.832RJS5ehZ8RzgDT0UelIpV8mwJykk2").authorities("user");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
        //return  NoOpPasswordEncoder.getInstance();
    }

//    public static void main(String[] args) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        System.out.println(bCryptPasswordEncoder.encode("1234"));
//        //$2a$10$XCIBQDXdumRTkrVtJp9bKeVdCc3Jsn07G6LdpUtDr8bJsRs9HNEN6
//        //$2a$10$xWTQHObZyNMh66Z3OkWZpu1jZumhlKQi.N4IUoZ4bEXZ7XREvakRW
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                        .antMatchers("/api/admin-panel/**").hasAuthority("admin")
//                        .antMatchers("/api/user-panel/**").hasAuthority("user")
//        .antMatchers("/api/gatekeeper-panel/**").hasAuthority("gatekeeper")
//                .antMatchers("/api/visitor/**").hasAnyRole("admin", "gatekeeper");
        http.authorizeRequests().antMatchers("/api/hello").hasAuthority("user");
        http.formLogin();
    }


}
