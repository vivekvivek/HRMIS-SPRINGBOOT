package com.javarnd.hrmis.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter implements EnvironmentAware {

	//static final String CLIENT_ID = "hrmis-client";
	//static final String CLIENT_SECRET = "hrmis-secret";
	static final String GRANT_TYPE = "password";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 120;//Access token is only valid for 2 minutes.//1*60*60;
    static final int REFRESH_TOKEN_VALIDITY_SECONDS = 600;//Refreesh token is only valid for 10 minutes.//6*60*60;
    
    private static final String ENV_OAUTH = "authentication.oauth.";
    private static final String PROP_CLIENTID = "clientid";
    private static final String PROP_SECRET = "secret";
    //private static final String PROP_TOKEN_VALIDITY_SECONDS = "tokenValidityInSeconds";
    
    private RelaxedPropertyResolver propertyResolver;
    
	/*@Autowired
	private TokenStore tokenStore;*/
    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

	@Autowired
	private UserApprovalHandler userApprovalHandler;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

		configurer
				.inMemory()
				//.withClient(CLIENT_ID)
				.withClient(propertyResolver.getProperty(PROP_CLIENTID))
				//.secret(CLIENT_SECRET)
				.secret(propertyResolver.getProperty(PROP_SECRET))
				//.authorizedGrantTypes(GRANT_TYPE)
				.authorizedGrantTypes(GRANT_TYPE, "refresh_token")
				.scopes(SCOPE_READ, SCOPE_WRITE)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
				//.accessTokenValiditySeconds(propertyResolver.getProperty(PROP_TOKEN_VALIDITY_SECONDS, Integer.class, 1800));
				.refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
		//.tokenStore(tokenStore)
		.tokenStore(tokenStore())
		.userApprovalHandler(userApprovalHandler)
				.authenticationManager(authenticationManager);
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment, ENV_OAUTH);
	}
}