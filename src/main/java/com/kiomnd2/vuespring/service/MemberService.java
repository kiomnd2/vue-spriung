package com.kiomnd2.vuespring.service;

import com.kiomnd2.vuespring.dto.MemberDto;
import com.kiomnd2.vuespring.entity.MemberEntity;
import com.kiomnd2.vuespring.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

    final MemberRepository memberRepository;

    final PasswordEncoder encoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder encoder) {
        this.memberRepository = memberRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        MemberEntity member = memberRepository.findByUserId(id);

        if(member == null ){
            throw new UsernameNotFoundException(id);
        }

        // memberDto

        return MemberDto.builder().auth("ROLE_USER")
                .password(member.getPassword())
                .userNm(member.getUserNm())
                .userId(member.getUserId())
                .email(member.getEmail())
                .build();
    }

    public MemberEntity registerUser(MemberDto member)
    {
        MemberDto.builder().password(encoder.encode(member.getPassword()));

        return memberRepository.save(member.toEntity());
    }

}
