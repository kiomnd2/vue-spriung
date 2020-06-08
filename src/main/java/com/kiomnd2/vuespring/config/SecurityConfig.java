package com.kiomnd2.vuespring.config;



import com.kiomnd2.vuespring.security.filter.CustomUsernamePasswordAuthenticationFilter;
import com.kiomnd2.vuespring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    final
    MemberService memberService;

    public SecurityConfig(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/fonts/**","/js/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.memberService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf()
                .disable() // 다시한번 세팅
                .authorizeRequests()
                    .antMatchers("/","/api/user/*").permitAll()
                    .anyRequest().hasRole("USER")
                    .and()
                .formLogin().disable();

        http.addFilterAt(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    } // json 으로 응답 주게끔 설정 찾아보기

    protected CustomUsernamePasswordAuthenticationFilter getAuthenticationFilter() {
        CustomUsernamePasswordAuthenticationFilter authFilter = new CustomUsernamePasswordAuthenticationFilter();
        try{
            authFilter.setFilterProcessesUrl("/api/user/login");
            authFilter.setAuthenticationManager(this.authenticationManagerBean());
            authFilter.setUsernameParameter("userId");
            authFilter.setPasswordParameter("password");
            authFilter.setAuthenticationSuccessHandler(new CustomUrlAuthenticationSuccessHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authFilter;

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



}

