package com.devdream.blackjackaccountservice.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        {
            final Map<String, Object> mapBodyException = new HashMap<>() ;

            mapBodyException.put("error"      , "Error from AuthenticationCredentials") ;
            mapBodyException.put("message"    , "Bad credentials, please check password and email") ;
            mapBodyException.put("reason exception", authException.getMessage()) ;
            mapBodyException.put("path"       , request.getServletPath()) ;
            mapBodyException.put("timestamp"  , (new Date()).getTime()) ;

            response.setContentType("application/json") ;
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED) ;

            final ObjectMapper mapper = new ObjectMapper() ;
            mapper.writeValue(response.getOutputStream(), mapBodyException) ;
        }
    }
}