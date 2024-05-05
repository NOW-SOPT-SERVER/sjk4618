package org.sopt.carrotMarket.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.carrotMarket.common.dto.ErrorMessage;
import org.sopt.carrotMarket.exception.InvalidValueException;

import java.util.Arrays;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Location {
    MOKDONG("MOKDONG"),
    BONGCHUN("BONGCHUN"),
    GANGNAM("GANGNAM"),
    SINCHON("SINCHON"),
    HONGDAE("HONGDAE"),
    SANGSU("SANGSU"),
    JAMSIL("JAMSIL");

    private final String location;

    public static void getEnumResultFromStringResult(String location) {
        Arrays.stream(values())
                .filter(result -> result.location.equals(location))
                .findFirst()
                .orElseThrow(() -> new InvalidValueException(ErrorMessage.INVALID_INPUT));
    }
}
