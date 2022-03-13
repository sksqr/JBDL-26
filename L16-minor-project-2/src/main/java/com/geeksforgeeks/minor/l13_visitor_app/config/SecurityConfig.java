package com.geeksforgeeks.minor.l13_visitor_app.config;

import com.geeksforgeeks.minor.l13_visitor_app.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
//        auth.inMemoryAuthentication()
//                .withUser("shashi").password("$2a$10$Q/y0u6yk.UoeHbJCz1McS.xf7M7zXMRhNBKwDelHYX4Y3muS3KxJ6").authorities("user").
//                and().withUser("ravi").password("$2a$10$rFhjUQwSZRClNIvEbPCPvOxkOLplQfBD7vVNkBc/s97KAsgsDkDVy").authorities("admin");

    }



//    public static void main(String[] args) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(encoder.encode("123"));
//    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
        //return  NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//
        http.csrf().disable()
                .httpBasic().and()
                .authorizeRequests().antMatchers("/api/user-panel/**").hasAnyAuthority("RESIDENT","ADMIN")
                .antMatchers("/api/admin-panel/**").hasAuthority("ADMIN")
                .antMatchers("/api/gatekeeper-panel/**").hasAuthority("GATEKEEPER")
                        .antMatchers("/api/addresss/**").hasAnyAuthority("ADMIN","GATEKEEPER");
        http.formLogin();
    }

}
