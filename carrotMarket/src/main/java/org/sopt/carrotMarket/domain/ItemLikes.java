package org.sopt.carrotMarket.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ItemLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Item item;



    public ItemLikes(Member member, Item item) {
        this.member = member;
        this.item = item;
    }

    public static ItemLikes create(Member member, Item item) {
        return new ItemLikes(member, item);
    }
}
