package org.sopt.carrotMarket.service;

import lombok.RequiredArgsConstructor;
import org.sopt.carrotMarket.common.dto.ErrorMessage;
import org.sopt.carrotMarket.domain.Item;
import org.sopt.carrotMarket.domain.Member;
import org.sopt.carrotMarket.exception.NotFoundException;
import org.sopt.carrotMarket.repository.ItemRepository;
import org.sopt.carrotMarket.repository.MemberRepository;
import org.sopt.carrotMarket.service.dto.RegisterItemDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void registerItem(final Long memberId, final RegisterItemDTO registerItemDTO) {

        Member member = findMemberById(memberId);
        Item item = Item.register(
                member,
                registerItemDTO.title(),
                registerItemDTO.price(),
                registerItemDTO.isReceived(),
                registerItemDTO.detailInfo(),
                registerItemDTO.hopeTradeSpot());

        itemRepository.save(item);
    }

    public Member findMemberById(final Long memberId) {
        return memberRepository.findById(memberId).orElseThrow( () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
    }
}
