package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dto.request.LoginRequest;
import africa.semicolon.lumExpress.data.dto.response.LoginResponse;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
}
