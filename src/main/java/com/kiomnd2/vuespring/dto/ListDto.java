package com.kiomnd2.vuespring.dto;

import com.kiomnd2.vuespring.entity.ListEntity;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
@Getter
@NoArgsConstructor
public class ListDto {

    private Long id;
    private String contents;
    private boolean isComplete;
    private MemberDto memberDto;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    @Builder
    public ListDto(Long id, String contents, boolean isComplete, MemberDto memberDto, LocalDateTime regDate, LocalDateTime updateDate) {
        this.id = id;
        this.contents = contents;
        this.isComplete = isComplete;
        this.memberDto = memberDto;
        this.regDate = regDate;
        this.updateDate = updateDate;
    }

    public ListEntity toEntity() {
        return ListEntity
                .builder()
                .contents(contents)
                .member(memberDto.toEntity())
                .isComplete(isComplete)
                .build();
    }
}
