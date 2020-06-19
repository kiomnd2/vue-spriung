package com.kiomnd2.vuespring.dto;

import com.kiomnd2.vuespring.entity.MemberEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Data
@NoArgsConstructor
public class MemberDto{

    private Long id;
    private String userId;
    private String userNm;
    private String email;
    private String password;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;


    // 뭔가 변경 필요한 속성에 대해 메서드를 생성해 줘야함

    @Builder
    public MemberDto(final long id, final String userId, final String userNm, final String email, final String password, final LocalDateTime regDate, final LocalDateTime updateDate )
    {
        this.userId = userId;
        this.userNm = userNm;
        this.email = email;
        this.password = password;
        this.regDate = regDate;
        this.updateDate = updateDate;
    }


    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .userId(userId)
                .userNm(userNm)
                .email(email)
                .password(password)
                .build();
    }
}
