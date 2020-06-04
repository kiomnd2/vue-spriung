package com.kiomnd2.vuespring.dto;

import com.kiomnd2.vuespring.entity.MemberEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


@Getter
@Data
@NoArgsConstructor
public class MemberDto{

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
