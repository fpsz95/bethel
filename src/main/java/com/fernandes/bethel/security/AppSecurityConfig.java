package com.fernandes.bethel.security;

//import com.fernandes.bethel.filter.SimpleCorsFilter;
import com.fernandes.bethel.jwt.JwtConfig;
import com.fernandes.bethel.jwt.JwtTokenVerifier;
import com.fernandes.bethel.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.fernandes.bethel.payments.paytm.SimpleCorsFilter;
import com.fernandes.bethel.user.UserDetailsServiceImpl;
import com.fernandes.bethel.jwt.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.List;

@Configuration//Properties("application.jwt")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final JwtToken jwtToken;

    @Autowired //This is from OUR PasswordConfig class
    public AppSecurityConfig(PasswordEncoder passwordEncoder,
                             UserDetailsServiceImpl userDetailsServiceImpl,
                             JwtConfig jwtConfig,
                             SecretKey secretKey, JwtToken jwtToken) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.jwtToken = jwtToken;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(request -> {
                    //var cors = new CorsConfiguration();
                    CorsConfiguration cors = new CorsConfiguration();
                    //cors.expsetExposedHeaders(List.of("Access-Control-Allow-Origin"));
                    cors.addExposedHeader("Access-Control-Allow-Origin");
                    //cors.setAllowedOrigins(List.of("*"));
                    cors.setAllowedOrigins(Arrays.asList("*"));
                    cors.setAllowCredentials(true);
                    //cors.setAllowedMethods(List.of("*"));
                    cors.setAllowedMethods(Arrays.asList("*"));
                    //cors.setAllowedHeaders(List.of("*"));
                    cors.setAllowedHeaders(Arrays.asList("*"));
                    //cor
////            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////            source.registerCorsConfiguration("/**", cors);
////            return source;
                       return cors;
                })
                .and()
                .csrf().disable()
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //.and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new SimpleCorsFilter(), JwtUsernameAndPasswordAuthenticationFilter.class)
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey, jwtToken))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig, userDetailsServiceImpl), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                //.antMatchers("/favicon.ico/**").anonymous()
                .antMatchers("/", "index", "/css/*", "/js/*", "/ico/*","/png/*","/registration/**","/payment/**","/static/**","/profile-picture/**"
                        //,"/registration/**", "/json/*"
                        //,"/{userProfileId}/image/upload")
                )
                .permitAll()
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN", "MEMBER") //it was all as role
                .antMatchers("/member/**").hasAuthority("MEMBER")
                .anyRequest()
                .authenticated();
                /*.and()
                .oauth2Login()
                    .loginPage("/login")
                    .clientRegistrationRepository(clientRegistrationRepository())
                    .userInfoEndpoint().userService(oAuth2UserService)*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder); //Allows passwords to be decoded
        provider.setUserDetailsService(userDetailsServiceImpl);
        return provider;
    }

   /* @Autowired
    private CustomOAuth2UserService oAuth2UserService;*/

}
