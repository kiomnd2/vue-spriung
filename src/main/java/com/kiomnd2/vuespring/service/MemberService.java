package com.kiomnd2.vuespring.service;

import com.kiomnd2.vuespring.dto.MemberDto;
import com.kiomnd2.vuespring.entity.MemberEntity;
import com.kiomnd2.vuespring.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

    final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<MemberEntity> member = memberRepository.findByUserId(id);

        MemberEntity userEntity = member.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(userEntity.getUserId(), userEntity.getPassword(), authorities);
    }



    public MemberEntity registerUser(MemberDto member) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        MemberEntity entity = MemberEntity.builder()
                .userId(member.getUserId())
                .userNm(member.getUserNm())
                .email(member.getEmail())
                .password(member.getPassword())
                .build();
        return memberRepository.save(entity);
    }

}
