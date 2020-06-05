package com.kiomnd2.vuespring.securityTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiomnd2.vuespring.controller.MemberController;
import com.kiomnd2.vuespring.dto.JSONResult;
import com.kiomnd2.vuespring.dto.MemberDto;
import com.kiomnd2.vuespring.entity.MemberEntity;
import com.kiomnd2.vuespring.service.MemberService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.engine.Filter;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MemberService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void 회원가입_테스트() throws Exception {

        MemberDto dto = MemberDto.builder()
                .email("email@naver.com")
                .userId("password")
                .userNm("name")
                .password("password")
                .auth("ROLE_USER")
                .build();

        given(service.registerUser(any())).willReturn(new MemberEntity());

        String memberAsString = mockMvc.perform(post("/api/user/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(String.valueOf(mapper.writeValueAsString(dto))))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        MemberDto member = mapper.readValue(memberAsString, MemberDto.class);

        Assert.assertThat(dto.getUserId(), is(member.getUserId()));
    }


    @Test
    public void 로그인_테스트() throws Exception {

        MemberDto dto = MemberDto.builder()
                .email("email@naver.com")
                .userId("password")
                .userNm("name")
                .password("password")
                .build();

        String memberAsString = mockMvc.perform(post("/api/user/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(String.valueOf(mapper.writeValueAsString(dto))))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONResult result = mapper.readValue(memberAsString, JSONResult.class);

        Assert.assertThat("fail", is(result.getResult()));

    }



}
