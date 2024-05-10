package org.sopt.carrotMarket.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sopt.carrotMarket.constant.Location;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String title;

    private int price;

    private boolean isReceived;

    private String detailInfo;

    @Enumerated(EnumType.STRING)
    private Location hopeTradeSpot;

    @Setter
    private int likesCount = 0;

    @Setter
    private int textsCount = 0;



    @Builder //빌더패턴
    private Item(Member member, String title, int price, boolean isReceived, String detailInfo, Location hopeTradeSpot) {
        this.member = member;
        this.title = title;
        this.price = price;
        this.isReceived = isReceived;
        this.detailInfo = detailInfo;
        this.hopeTradeSpot = hopeTradeSpot;
    }

    //정적팩토리메서드(빌더패턴이용)
    public static Item register(Member member, String title, int price, boolean isReceived, String detailInfo, Location hopeTradeSpot) {
        return Item.builder()
                .member(member)
                .title(title)
                .price(price)
                .isReceived(isReceived)
                .detailInfo(detailInfo)
                .hopeTradeSpot(hopeTradeSpot)
                .build();
    }

}
