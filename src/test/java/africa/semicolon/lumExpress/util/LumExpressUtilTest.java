package africa.semicolon.lumExpress.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LumExpressUtilTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void generateTokenTest() {
        String token=LumExpressUtil.generateToken();
        assertThat(token).isNotNull();
        assertThat(token.length()).isEqualTo(5);
    }
}