package org.sopt.carrotMarket.service;

import lombok.RequiredArgsConstructor;
import org.sopt.carrotMarket.common.ApiResponseUtil;
import org.sopt.carrotMarket.common.dto.ErrorMessage;
import org.sopt.carrotMarket.common.dto.SuccessMessage;
import org.sopt.carrotMarket.constant.Location;
import org.sopt.carrotMarket.domain.Item;
import org.sopt.carrotMarket.domain.Member;
import org.sopt.carrotMarket.exception.NotFoundException;
import org.sopt.carrotMarket.repository.ItemRepository;
import org.sopt.carrotMarket.repository.MemberRepository;
import org.sopt.carrotMarket.service.dto.GetAllItemsByMemberIdResponseDTO;
import org.sopt.carrotMarket.service.dto.RegisterItemDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void registerItem(final Long memberId, final RegisterItemDTO registerItemDTO) {

        Member member = findMemberById(memberId);

        checkLocation(registerItemDTO.hopeTradeSpot());

        Item item = Item.register(
                member,
                registerItemDTO.title(),
                registerItemDTO.price(),
                registerItemDTO.isReceived(),
                registerItemDTO.detailInfo(),
                registerItemDTO.hopeTradeSpot());

        itemRepository.save(item);
    }

    public List<GetAllItemsByMemberIdResponseDTO> getAllItemsByMemberId(Long memberId) {

        Member member = findMemberById(memberId);

        return itemRepository.findProductsBymemberId(memberId)
                .stream()
                .map(GetAllItemsByMemberIdResponseDTO::of)
                .toList();
    }

    public Member findMemberById(final Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
    }

    //지역 이름이 Location enum에 있는지 확인하는 메서드
    public void checkLocation(Location location) {

        List<Location> validLocations = Arrays.asList(Location.values());

        if (!validLocations.contains(location)) {
            throw new NotFoundException(ErrorMessage.LOCATION_NOT_FOUND);
        } else {
            return;
        }
    }
}
