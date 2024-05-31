package org.sopt.carrotMarket.service;

import lombok.RequiredArgsConstructor;
import org.sopt.carrotMarket.common.dto.ErrorMessage;
import org.sopt.carrotMarket.common.utils.DateFormatUtil;
import org.sopt.carrotMarket.constant.Location;
import org.sopt.carrotMarket.domain.Item;
import org.sopt.carrotMarket.domain.Member;
import org.sopt.carrotMarket.exception.NotFoundException;
import org.sopt.carrotMarket.external.S3Service;
import org.sopt.carrotMarket.repository.ItemLikesRepository;
import org.sopt.carrotMarket.repository.ItemRepository;
import org.sopt.carrotMarket.repository.MemberRepository;
import org.sopt.carrotMarket.service.dto.GetAllItemsInfoResponseDTO;
import org.sopt.carrotMarket.service.dto.RegisterItemDTO;
import org.sopt.carrotMarket.service.dto.RegisterItemResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final ItemLikesRepository itemLikesRepository;
    private final ItemLikesService itemLikesService;

    private final S3Service s3Service;
    private static final String BLOG_S3_UPLOAD_FOLER = "blog/";

    @Transactional
    public RegisterItemResponseDTO registerItem(final Long memberId, final RegisterItemDTO registerItemDTO) {

        Member member = findMemberById(memberId);

        Location.checkIsLocationEnumHasString((registerItemDTO.hopeTradeSpot()));

        try {
        Item item = Item.register(
                member,
                s3Service.uploadImage(BLOG_S3_UPLOAD_FOLER, registerItemDTO.image()),
                registerItemDTO.title(),
                registerItemDTO.price(),
                registerItemDTO.isReceived(),
                registerItemDTO.detailInfo(),
                Location.valueOf(registerItemDTO.hopeTradeSpot()));
        itemRepository.save(item);
        return RegisterItemResponseDTO.of(item.getId());

        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
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

    @Transactional
    public void deleteItem(final Long itemId) {

        Item item = findItemById(itemId);
        try {
            s3Service.deleteImage(item.getImageUrl());
        } catch (IOException e) {
            throw new RuntimeException("이미지 삭제 오류 발생", e);
        }
        itemRepository.deleteById(itemId);
    }

    public Item findItemById(final Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.ITEM_NOT_FOUND)
        );
    }
}
