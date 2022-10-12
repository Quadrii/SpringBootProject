package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dto.request.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
@Autowired
private UserService userService;
private LoginRequest loginRequest;
    @BeforeEach
    void setUp() {
        loginRequest = LoginRequest.builder()
                .email("test@gmail.com")
                .password("testpassword")
                .build();
    }

    @Test
    void loginTest() {
        var response = userService.login(loginRequest);
        assertThat(response).isNotNull();
        assertThat(response.getCode()).isEqualTo(200);
    }
}