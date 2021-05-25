package com.fernandes.bethel.jwt;

import com.fernandes.bethel.user.UserDetailsServiceImpl;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenVerifier extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(JwtTokenVerifier.class);

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public JwtTokenVerifier(SecretKey secretKey,
                            JwtConfig jwtConfig,
                            UserDetailsServiceImpl userDetailsServiceImpl) {

        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
        System.out.println(token);
        try {
            System.out.println("inside JwtTokenVerifier Try");
            System.out.println(secretKey);
            System.out.println("the above is the secretKey");
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            System.out.println(claimsJws);
            System.out.println("the above is claimsJws");
            Claims body = claimsJws.getBody();
            System.out.println(body);
            System.out.println("the above is body variable");
            String username = body.getSubject();
            System.out.println(username);
            System.out.println(SecurityContextHolder.getContext().getAuthentication() + "This should be null");
            //If username is not equal to null then give this username to UserDetails object and get the UserDetails object
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                System.out.println("****userDetailsServiceImpl.loadUserByUsername(username)****");
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
//                var authorities = (List<Map<String, String>>) body.get("authorities");
//
//                Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
//                        .map(m -> new SimpleGrantedAuthority(m.get("authority")))
//                        .collect(Collectors.toSet());

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());username,null,simpleGrantedAuthorities
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication); //Setting the Authentication to be True
            }
            System.out.println("Outside if*******************************************************");
        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }
System.out.println("*******INSIDE JWT_TOKEN_VERIFIER*****TOKEN VERIFIED****");
        filterChain.doFilter(request, response);
    }
}
