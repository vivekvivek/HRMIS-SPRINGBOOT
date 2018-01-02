package com.javarnd.hrmis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.javarnd.hrmis.security.CustomAuthenticationEntryPoint;
import com.javarnd.hrmis.security.CustomLogoutSuccessHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "resource_id";
	
	@Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
        /*http.
                anonymous().disable()
                .authorizeRequests()
                //.antMatchers("/users/**").access("hasRole('ADMIN')")
                .antMatchers("/secure/**").authenticated()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());*/
		http.exceptionHandling()
        .authenticationEntryPoint(customAuthenticationEntryPoint)
        .and()
        .logout()
        .logoutUrl("/oauth/logout")
        .logoutSuccessHandler(customLogoutSuccessHandler)
        .and()
        .csrf()
        .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
        .disable()
        .headers()
        .frameOptions().disable()
        //.sessionManagement()
        .and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/hello/").permitAll()
        .antMatchers("/secure/**").authenticated();
	}

}