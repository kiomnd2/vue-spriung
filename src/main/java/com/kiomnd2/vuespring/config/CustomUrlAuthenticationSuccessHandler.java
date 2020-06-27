package com.kiomnd2.vuespring.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiomnd2.vuespring.dto.JSONResult;
import com.kiomnd2.vuespring.dto.MemberDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());
    private RequestCache requestCache = new HttpSessionRequestCache();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 언젠간 쓸데가 있겟지
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            requestCache.removeRequest(request, response);
            clearAuthenticationAttributes(request);
        };


        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        MediaType mediaType = MediaType.APPLICATION_JSON;


        // response 에서 DTO를 리턴해 줘야 한다.



        JSONResult jsonResult = JSONResult.success("롣그인 성공");
        if( converter.canWrite(jsonResult.getClass(), mediaType)) {
            converter.write(jsonResult, mediaType, new ServletServerHttpResponse(response));
        }


    }
}
