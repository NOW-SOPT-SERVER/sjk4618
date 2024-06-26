package org.sopt.carrotMarket.service;

import lombok.RequiredArgsConstructor;
import org.sopt.carrotMarket.common.dto.ErrorMessage;
import org.sopt.carrotMarket.common.utils.DateFormatUtil;
import org.sopt.carrotMarket.constant.Location;
import org.sopt.carrotMarket.domain.Item;
import org.sopt.carrotMarket.domain.Member;
import org.sopt.carrotMarket.exception.NotFoundException;
import org.sopt.carrotMarket.repository.ItemLikesRepository;
import org.sopt.carrotMarket.repository.ItemRepository;
import org.sopt.carrotMarket.repository.MemberRepository;
import org.sopt.carrotMarket.service.dto.GetAllItemsInfoResponseDTO;
import org.sopt.carrotMarket.service.dto.RegisterItemDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final ItemLikesRepository itemLikesRepository;
    private final ItemLikesService itemLikesService;

    @Transactional
    public void registerItem(final Long memberId, final RegisterItemDTO registerItemDTO) {

        Member member = findMemberById(memberId);

        Location.checkIsLocationEnumHasString((registerItemDTO.hopeTradeSpot()));

        Item item = Item.register(
                member,
                registerItemDTO.title(),
                registerItemDTO.price(),
                registerItemDTO.isReceived(),
                registerItemDTO.detailInfo(),
                Location.valueOf(registerItemDTO.hopeTradeSpot()));

        itemRepository.save(item);
    }

    public List<GetAllItemsInfoResponseDTO> getAllItemsByMemberId(final Long memberId) {

        Member member = findMemberById(memberId);
        return itemRepository.findProductsBymemberId(memberId)
                .stream()
                .map((Item item) -> {
                    String createdAt = DateFormatUtil.format(item.getCreatedAt());
                    boolean isLiked = itemLikesRepository.existsBymemberIdAndItemId(memberId, item.getId());
                    return GetAllItemsInfoResponseDTO.of(item, isLiked, createdAt);
                })
                .toList();
    }

    public List<GetAllItemsInfoResponseDTO> getAllItemsByLocation(final String location) {

        Location.checkIsLocationEnumHasString((location));
        return itemRepository.findByHopeTradeSpot(Location.valueOf(location))
                .stream()
                .map((Item item) -> {
                    String createdAt = DateFormatUtil.format(item.getCreatedAt());
                    return GetAllItemsInfoResponseDTO.builder()
                            .itemId(item.getId())
                            .title(item.getTitle())
                            .likesCount(item.getLikesCount())
                            .price(item.getPrice())
                            .detailInfo(item.getDetailInfo())
                            .hopeTradeSpot(String.valueOf(item.getHopeTradeSpot()))
                            .isReceived(item.isReceived())
                            .createdTime(createdAt)
                            .build();
                })
                .toList();
    }

    public Member findMemberById(final Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
    }
}
