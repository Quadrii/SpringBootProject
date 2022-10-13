//package africa.semicolon.lumExpress.controller;
//
//import africa.semicolon.lumExpress.data.dto.request.CustomerRegisterRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class CustomerControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    private CustomerRegisterRequest request;
//    @BeforeEach
//    void setUp() {
//        request = CustomerRegisterRequest.builder()
//                .email("john@gmail.com")
//                .password("johnny400")
//                .country("Naija")
//                .build();
//    }
//
//    @Test
//    void registerControllerTest() throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/api/v1/customer")
//                        .contentType("application/json")
//                        .content(mapper.writeValueAsString(request)))
//                .andExpect(MockMvcResultMatchers.status().is(201))
//                .andDo(MockMvcResultHandlers.print());
//    }
//}