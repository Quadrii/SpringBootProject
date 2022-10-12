package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dto.request.CustomerRegisterRequest;
import africa.semicolon.lumExpress.data.dto.request.LoginRequest;
import africa.semicolon.lumExpress.data.dto.request.UpdateCustomerProfile;
import africa.semicolon.lumExpress.data.dto.response.CustomerRegisterResponse;
import africa.semicolon.lumExpress.data.dto.response.LoginResponse;

public interface CustomersService {
    CustomerRegisterResponse register(CustomerRegisterRequest customerRegisterRequest);
//    LoginResponse login(LoginRequest loginRequest);
    String completeProfile(UpdateCustomerProfile updateCustomerProfile);
}
