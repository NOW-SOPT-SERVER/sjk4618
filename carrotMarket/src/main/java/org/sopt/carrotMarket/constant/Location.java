package org.sopt.carrotMarket.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.carrotMarket.common.dto.ErrorMessage;
import org.sopt.carrotMarket.exception.InvalidValueException;

import java.util.Arrays;

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

    Location(String location) {
        this.location = location;
    }

    //스트링값이 Location enum에 있는지 체크
    public static void checkIsLocationEnumHasString(String location) {
        Arrays.stream(values())
                .filter(result -> result.location.equals(location))
                .findFirst()
                .orElseThrow(() -> new InvalidValueException(ErrorMessage.LOCATION_NOT_FOUND));

    }
}
