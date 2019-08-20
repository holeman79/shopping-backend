package com.holeman79.config.security;

import com.holeman79.config.security.oauth2.CustomOAuth2Provider;
import com.holeman79.config.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.holeman79.config.security.oauth2.OAuth2AuthenticationSuccessHandler;
import com.holeman79.shoppingbackend.user.CustomOAuth2UserService;
import com.holeman79.shoppingbackend.user.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }


    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            logger.error("Responding with unauthorized error. Message - {}", authException.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            logger.error("Responding with fobidden error. Message - {}", accessDeniedException.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
        };
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint())
                    .accessDeniedHandler(accessDeniedHandler())
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .antMatchers("/", "/login/**", "/oauth2/**", "/images/**", "/favicon.ico",
                        "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.ttf",
                        "/swagger*/**", "/**/api-docs", "/**/ui", "/configuration/**",
                        "/**/*.html*", "/**/*.css", "/**/*.js")
                        .permitAll()
                    .antMatchers("/api/user/**")
                        .permitAll()
                    .antMatchers(HttpMethod.GET,"/api/product/option")
                        .authenticated()
                    .antMatchers(HttpMethod.GET,"/api/product/**")
                        .permitAll()
                    .antMatchers(HttpMethod.GET,"/api/file/**")
                        .permitAll()
                    .antMatchers("/api/common/**")
                        .permitAll()
                    .antMatchers("/h2-console/**")
                        .permitAll()
                    .anyRequest()
                        .authenticated()
                    .and()
                .oauth2Login()
                    .authorizationEndpoint()
                        .baseUri("/oauth2/authorization")
                        .authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository)
                        .and()
                    .redirectionEndpoint()
                        .baseUri("/login/oauth2/code/*")
                        .and()
                    .userInfoEndpoint()
                        .userService(customOAuth2UserService)
                        .and()
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    .and()
                .headers()
                    .frameOptions().disable()
                    .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(
            OAuth2ClientProperties oAuth2ClientProperties, @Value("${custom.oauth2.kakao.client-id}") String kakaoClientId)
    {
        List<ClientRegistration> registrations = oAuth2ClientProperties.getRegistration().keySet().stream()
                .map(client -> getRegistration(oAuth2ClientProperties, client))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
                .clientId(kakaoClientId)
                .clientSecret("test")
                .jwkSetUri("test")
                .build());

        return new InMemoryClientRegistrationRepository(registrations);

    }

    private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String client){
        if("google".equals(client)){
            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("google");
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(registration.getClientId())
                    .clientSecret(registration.getClientSecret())
                    .scope("email", "profile")
                    .build();
        }
        if ("facebook".equals(client)) {
            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("facebook");
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId(registration.getClientId())
                    .clientSecret(registration.getClientSecret())
                    .userInfoUri("https://graph.facebook.com/me?fields=id,name,email,link")
                    .scope("email")
                    .build();
        }
        return null;
    }
}
