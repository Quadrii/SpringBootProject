package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.VerificationToken;
import africa.semicolon.lumExpress.exception.VerificationTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class VerificationTokenRepositoryTest {
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    private VerificationToken verificationToken;
    @BeforeEach
    void setUp(){
        verificationToken = new VerificationToken();
        verificationToken.setToken("12345");
        verificationToken.setUserEmail("test@gmail.com");
        verificationTokenRepository.deleteAll();
    }
    @Test
    void findByUserEmail(){

    }
    @Test
    void findByToken(){
        verificationTokenRepository.save(verificationToken);
       VerificationToken checkToken =  verificationTokenRepository.findByUserEmail("test@gmail.com").orElseThrow(()-> new VerificationTokenException(
               "token not found"
       ));
        assertThat(checkToken).isNotNull();
        assertThat(checkToken.getToken())
                .isEqualTo(verificationToken.getToken());
    }
}