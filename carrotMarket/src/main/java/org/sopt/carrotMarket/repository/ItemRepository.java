package org.sopt.carrotMarket.repository;

import org.sopt.carrotMarket.constant.Location;
import org.sopt.carrotMarket.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findProductsBymemberId(Long memberId);
    List<Item> findByHopeTradeSpot(Location hopeTradeSpot);
}
