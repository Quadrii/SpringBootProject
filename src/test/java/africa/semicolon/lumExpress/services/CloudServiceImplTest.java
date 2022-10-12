package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.services.cloud.CloudService;
import com.cloudinary.utils.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CloudServiceImplTest {
    private MultipartFile file;
    @Autowired
    private CloudService cloudService;
    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths.get("/var/www/html/lumExpress/src/main/resources/static/testProduct.jpg");

        file = new MockMultipartFile("peak", Files.readAllBytes(path));
    }

    @Test
    @DisplayName("cloudinary upload test")
    void uploadTest() {
        try {
            String response = cloudService.upload(file.getBytes(), null);
            assertThat(response).isNotNull();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}