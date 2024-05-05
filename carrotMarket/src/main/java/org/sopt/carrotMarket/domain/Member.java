package org.sopt.carrotMarket.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    @NotBlank
    private String name;

    public static Member register(String name) {
        return new Member(name);
    }

    private Member(String name) {
        this.name = name;
    }


}
