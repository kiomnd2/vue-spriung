package com.kiomnd2.vuespring.dto;

import com.kiomnd2.vuespring.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


@Getter
@NoArgsConstructor
public class MemberDto implements UserDetails{

    private String userId;
    private String userNm;
    private String email;
    private String password;
    private String auth;

    // 뭔가 변경 필요한 속성에 대해 메서드를 생성해 줘야함

    @Builder
    public MemberDto(final String userId, final String userNm, final String email, final String password, final String auth)
    {
        this.userId = userId;
        this.userNm = userNm;
        this.email = email;
        this.password = password;
        this.auth = auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(auth));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .userId(userId)
                .userNm(userNm)
                .email(email)
                .password(password)
                .auth(auth)
                .build();
    }
}
