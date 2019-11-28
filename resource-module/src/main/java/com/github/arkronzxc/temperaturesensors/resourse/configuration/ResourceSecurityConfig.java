package com.github.arkronzxc.temperaturesensors.resourse.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.xml.bind.annotation.XmlType;

public class ResourceSecurityConfig extends ResourceServerConfigurerAdapter {
    @Value("${auth.config.key.public}")
    private String publicKey;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(tokenServices());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user")
                    .permitAll()
                .antMatchers("/user/**", "/city")
                    .authenticated()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.NEVER);
    }

    @Bean
    public JwtAccessTokenConverter tokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(publicKey);
        return  converter;
    }

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(tokenConverter());
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }
}
