package com.denisov.anything.authservice.user;

import com.denisov.anything.security.securityconfig.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.yaml.snakeyaml.Yaml;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    JWTService jwtService = new JWTService();

    @Value("${auth.endpoints.protected}")
    private String[] enabled;

    //TODO: add deactivation
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain filterChain) throws ServletException, IOException {
        String r = req.getRequestURI();
        for(String str: enabled){
            System.out.println("Restricted: " + str);
        }

        for(String str : enabled){
            if(str.compareTo(req.getRequestURI())==0){
                String authHeader = req.getHeader(HttpHeaders.AUTHORIZATION);
                System.out.println(authHeader);


                if(authHeader == null || authHeader == null){
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                } else if(!checkAuthorization(authHeader)){
                    res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                } else{
                    filterChain.doFilter(req, res);
                }
                return;
            }
        }

        filterChain.doFilter(req, res);
    }

    private boolean checkAuthorization(String auth){

        if(!auth.startsWith("Bearer ")) {
            System.out.println("b case");
            return false;
        }

        String token = auth.substring(7);
        return jwtService.checkToken(token);
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return false;
    }
}
