package org.sopt.carrotMarket.repository;

import org.sopt.carrotMarket.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
