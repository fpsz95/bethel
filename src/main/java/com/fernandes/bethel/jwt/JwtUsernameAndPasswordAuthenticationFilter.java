package com.fernandes.bethel.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernandes.bethel.user.UserDetailsImpl;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final JwtToken jwtToken;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager,
                                                      JwtConfig jwtConfig,
                                                      SecretKey secretKey,
                                                      JwtToken jwtToken) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.jwtToken = jwtToken;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            //authenticateRequest - Authenticate to see whether the username and password are correct
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

            //Authenticatio is an interface that has implementation UsernamePasswordAuthenticationToken
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), //Principal
                    authenticationRequest.getPassword() // Credentials
            );
            System.out.println("***********Authentication attemptAuthentication****************");
            Authentication authenticate = authenticationManager.authenticate(authentication); //authenticationManager will make sure that the username exists, if exists then its checks whether the password is correct or not
            //SecurityContextHolder.getContext().setAuthentication(authenticate); //My code from bezkoder
            return authenticate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //generating a token
    //executed when attempt authentication is done executing
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        System.out.println("***********INSIDE SUCCESSFUL AUTHENTICATION()****************");
        String token = getToken(authResult);
        //jwtToken.setToken(token);
        //String jsonTokenResponse = new Gson().toJson(jwtToken);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        //out.print(jsonTokenResponse);
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toSet());
        String jsonResponse = new Gson().toJson(new JwtResponse(token, userDetails.getId(), userDetails.getUsername(),roles,userDetails.getUserProfileImageLink(), userDetails.getSocietyId()));
        out.print(jsonResponse);
        out.flush();
        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
    }

    private String getToken(Authentication authResult) {
        return Jwts.builder() // From import io.jsonwebtoken.Jwts;
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities()) //Payload - Data
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();
    }
}
