package org.sopt.carrotMarket.service;

import lombok.RequiredArgsConstructor;
import org.sopt.carrotMarket.common.dto.ErrorMessage;
import org.sopt.carrotMarket.domain.Item;
import org.sopt.carrotMarket.domain.ItemLikes;
import org.sopt.carrotMarket.domain.Member;
import org.sopt.carrotMarket.exception.NotFoundException;
import org.sopt.carrotMarket.repository.ItemLikesRepository;
import org.sopt.carrotMarket.repository.ItemRepository;
import org.sopt.carrotMarket.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemLikesService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final ItemLikesRepository itemLikesRepository;

    @Transactional
    public void addLikesByMemberId(final Long memberId, final Long itemId) {
        Member member = checkMember(memberId);
        Item item = checkItem(itemId);

        if (itemLikesRepository.existsBymemberIdAndItemId(memberId, itemId)) {
            throw new NotFoundException(ErrorMessage.ALREADY_ITEM_LIKED);
        }

        ItemLikes newItemLikes = ItemLikes.create(member, item);
        itemLikesRepository.save(newItemLikes);

        item.setLikesCount(item.getLikesCount() + 1);
        itemRepository.save(item);

    }

    //멤버 검증 메서드
    private Member checkMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND)
        );
    }

    //아이템 검증 메서드
    private Item checkItem(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.INVALID_INPUT)
        );
    }

}
