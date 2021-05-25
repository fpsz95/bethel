package com.fernandes.bethel.filter;//package com.fernandes.bethel.filter;
//
//import org.springframework.stereotype.Component;
//
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleCorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        System.out.println("Inside Simple cors filter - - - - - - - ");
//        response.setHeader("Access-Control-Expose-Headers","Access-Control-Allow-Origin");
//        response.setStatus(200);
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods", "GET,POST, OPTIONS, UPDATE, HEAD,PUT");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Content-Length, Accept, Accept-Encoding, Access-Control-Allow-Origin, Content-Type, Access-Control-Allow-Headers, Origin,Accept, Authorization, X-Requested-With, Access-Control-Request-Method, Access-Control-Request-Headers"); //works
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Content-Length, Accept, Accept-Encoding, Access-Control-Allow-Origin, Access-Control-Allow-Headers, Origin,Accept, Authorization, X-Requested-With, Access-Control-Request-Method, Access-Control-Request-Headers");
        filterChain.doFilter(request,response);

    }
}
