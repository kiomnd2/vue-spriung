package com.kiomnd2.vuespring.entity;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@EqualsAndHashCode
@ToString
@Getter
@NoArgsConstructor
@Entity
@Table(name = "list")
public class ListEntity extends TimeEntity{

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Column(length = 500)
    private String contents;

    @Column
    private boolean isComplete;


    @Builder
    public ListEntity(long id, String contents, boolean isComplete) {
        this.id = id;
        this.contents = contents;
        this.isComplete = isComplete;
    }

}
