package org.sopt.springFirstSeminar.settings;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

//PORT를 지정해주는 클래스
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach //모든 테스트를 하기 전에 이걸 하겠다 하는 어노테이션
    void setUp() {
        RestAssured.port = port;
    }
}
