package org.sopt.springFirstSeminar.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Part part;

    private int age;

    //빌더패턴
    @Builder
    private Member(String name, Part part, int age) {
        this.name = name;
        this.part = part;
        this.age = age;
    }

    //정적패토리메서드(빌더패턴을 이용한)
    public static Member create(String name, Part part, int age) {
        return Member.builder()
                .name(name)
                .age(age)
                .part(part)
                .build();
    }
}
