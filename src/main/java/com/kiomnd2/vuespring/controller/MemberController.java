package com.kiomnd2.vuespring.controller;

import com.kiomnd2.vuespring.entity.Member;
import com.kiomnd2.vuespring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController
{
    MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    @RequestMapping(method = RequestMethod.POST, value = "/api/login")
    public String hello() {
        System.out.println("hello");
        return "hello";
    }


    @RequestMapping(method = RequestMethod.POST, value ="/api/register")
    public boolean register(Member member)
    {
        System.out.println(member.toString());
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        memberRepository.save(member);
        return true;
    }

}
