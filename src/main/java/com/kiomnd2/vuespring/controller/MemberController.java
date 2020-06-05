package com.kiomnd2.vuespring.controller;

import com.kiomnd2.vuespring.dto.MemberDto;
import com.kiomnd2.vuespring.entity.MemberEntity;
import com.kiomnd2.vuespring.service.MemberService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/user")
@RestController
public class MemberController
{

    final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String login(@RequestBody MemberDto memberDto) {
        memberService.loadUserByUsername(memberDto.getUserId());

        return memberDto.getUserId();
    }
    /**
     * 회원 가입을 수행한다
     * @param member
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value ="/register")
    public MemberDto register(@RequestBody MemberDto member)
    {
        memberService.registerUser(member);
        return member;
    }

}

