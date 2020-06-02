package com.kiomnd2.vuespring.config;

import com.kiomnd2.vuespring.dto.JSONResult;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        String errMessage = exception.getMessage();

        JSONResult result = JSONResult.fail(errMessage);

        MediaType mediaType = MediaType.APPLICATION_JSON;
        if ( converter.canWrite(result.getClass(), mediaType)) {
            converter.write(result, mediaType, new ServletServerHttpResponse(response));
        }

    }
}
