package org.sopt.springFirstSeminar.controller;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sopt.springFirstSeminar.domain.Part;
import org.sopt.springFirstSeminar.repository.MemberRepository;
import org.sopt.springFirstSeminar.service.MemberService;
import org.sopt.springFirstSeminar.service.dto.MemberCreateDTO;
import org.sopt.springFirstSeminar.settings.ApiTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.Nested;
import org.springframework.http.MediaType;

public class MemberControllerTest extends ApiTest {

    @Autowired //빈 등록
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Nested
    @DisplayName("멤버 생성 테스트")
    public class CreateMember {

        @Test
        @DisplayName("요청 성공 테스트")
        public  void createMemberSuccess() throws Exception {

            //given
            final var request = new MemberCreateDTO(
                    "곽성준",
                    Part.SERVER,
                    25
            );

            //when
            final var response = RestAssured
                    .given()
                    .log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(request)
                    .when()
                    .post("/api/v1/member")
                    .then().log().all().extract();

            //then
            Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        }
    }
}
