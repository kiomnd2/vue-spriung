package com.kiomnd2.vuespring.listmockTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiomnd2.vuespring.dto.JSONResult;
import com.kiomnd2.vuespring.dto.ListDto;
import com.kiomnd2.vuespring.dto.MemberDto;
import com.kiomnd2.vuespring.entity.MemberEntity;
import com.kiomnd2.vuespring.service.ListService;
import com.kiomnd2.vuespring.service.MemberService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ListTest {


    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MemberService memberService;


    @Autowired
    ObjectMapper mapper;

    MemberDto memberDto;

    @Before
    public void init() {

    }


    @Test
    @WithAnonymousUser
    public void 권한없이_리스트호출_테스트() throws Exception{
        ListDto listdto = ListDto.builder()
                .id(any())
                .contents(any())
                .memberDto(memberDto)
                .build();

//        list.registerItem(listdto);

        String listAsString = mockMvc.perform(post("/api/list/getAll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(mapper.writeValueAsString(listdto))))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse()
                .getContentAsString();

    }


/*
    @Test
    @WithMockUser(roles = "USER", username = "user")
    public void 권한주고_리스트호출_테스트() throws Exception{
        memberDto = MemberDto.builder()
                .userId("test")
                .userNm("test")
                .password("test")
                .email("test").build();

        MemberEntity memberEntity = memberService.registerUser(memberDto);



        ListDto listdto = ListDto.builder()
                .memberDto(memberEntity.toDto())
                .contents(any())
                .build();

        System.out.println(listdto);
//        list.registerItem(listdto);

        String listAsString = mockMvc.perform(post("/api/list/getAll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(mapper.writeValueAsString(listdto))))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }*/

}
