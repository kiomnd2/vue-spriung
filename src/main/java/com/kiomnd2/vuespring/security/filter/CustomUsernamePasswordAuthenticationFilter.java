package com.kiomnd2.vuespring.security.filter;

import antlr.StringUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.util.stream.Collectors;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    HashMap<String, String> jsonRequest;
    boolean postOnly = true;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        if (request.getHeader("Content-Type").split(";")[0].equals(MediaType.APPLICATION_JSON.toString())) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.jsonRequest = mapper.readValue(request.getReader().lines().collect(Collectors.joining()), new TypeReference<HashMap<String, String>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
                throw new AuthenticationServiceException("Request Content-Type(application/json) parsing error");
            }
        }

            String username = this.obtainUsername(request);
            String password = this.obtainPassword(request);
            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }

            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);

    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String passwordParameter = super.getPasswordParameter();

        if(request.getHeader("Content-Type").split(";")[0].equals(MediaType.APPLICATION_JSON.toString())) {
            return jsonRequest.get(passwordParameter);
        }
        return request.getParameter(passwordParameter);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String usernameParameter = super.getUsernameParameter();

        if(request.getHeader("Content-Type").split(";")[0].equals(MediaType.APPLICATION_JSON.toString())) {
            return jsonRequest.get(usernameParameter);
        }
        return request.getParameter(usernameParameter);
    }

    @Override
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
