package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dto.request.CustomerRegisterRequest;
import africa.semicolon.lumExpress.data.dto.request.LoginRequest;
import africa.semicolon.lumExpress.data.dto.response.CustomerRegisterResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    private CustomersService customersService;
    private CustomerRegisterRequest request;
    @BeforeEach
    void setUp() {
        request = CustomerRegisterRequest
                .builder()
                .email("test@gmail.com")
                .password("testPword")
                .country("Nigeria")
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registerCustomerTest() {
        CustomerRegisterResponse customerRegisterResponse = customersService.register(request);
        assertThat(customerRegisterResponse).isNotNull();
        assertThat(customerRegisterResponse.getMessage()).isNotNull();
        assertThat(customerRegisterResponse.getUserId()).isGreaterThan(0);
        assertThat(customerRegisterResponse.getCode()).isEqualTo(201);
    }


    @Test
    void completeProfile() {
    }
}