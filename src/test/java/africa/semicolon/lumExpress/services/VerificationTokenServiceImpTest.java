package africa.semicolon.lumExpress.services;
import africa.semicolon.lumExpress.data.models.VerificationToken;
import africa.semicolon.lumExpress.data.repositories.VerificationTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VerificationTokenServiceImpTest {
    @Autowired
    private  VerificationTokenService verificationTokenService;
    VerificationToken verificationToken = verificationTokenService
            .generateVerificationToken("test@mail.com");
    @BeforeEach
    void setUp() {

    }

    @Test
    void generateVerificationTokenTest() {
        assertThat(verificationToken).isNotNull();
        assertThat(verificationToken.getUserEmail()).isEqualTo("test@mail.com");
    }

    @Test
    void tokenIsValidTest(){
        assertThat(verificationToken).isNotNull();
        var response = verificationTokenService.isValidToken(verificationToken.getToken());
        assertThat(response).isTrue();
    }
}