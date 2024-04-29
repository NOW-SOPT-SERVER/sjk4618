package org.sopt.carrotMarket.service;

import lombok.RequiredArgsConstructor;
import org.sopt.carrotMarket.domain.Item;
import org.sopt.carrotMarket.repository.ItemRepository;
import org.sopt.carrotMarket.service.dto.RegisterItemDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public String registerItem(final RegisterItemDTO registerItemDTO) {
        Item item = Item.register(
                registerItemDTO.title(),
                registerItemDTO.price(),
                registerItemDTO.isReceived(),
                registerItemDTO.detailInfo(),
                registerItemDTO.hopeTradeSpot());

        itemRepository.save(item);
        return item.getId().toString(); //왜 이거를 리턴하는지?
    }

}
