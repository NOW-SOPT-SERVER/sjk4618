package org.sopt.carrotMarket.repository;

import org.apache.catalina.User;
import org.sopt.carrotMarket.domain.Item;
import org.sopt.carrotMarket.domain.ItemLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemLikesRepository extends JpaRepository<ItemLikes, Long> {
    boolean existsBymemberIdAndItemId(Long memberId, Long itemId);
}
