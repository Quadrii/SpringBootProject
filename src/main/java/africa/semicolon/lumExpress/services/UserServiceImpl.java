package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dto.request.LoginRequest;
import africa.semicolon.lumExpress.data.dto.response.LoginResponse;
import africa.semicolon.lumExpress.data.models.Admin;
import africa.semicolon.lumExpress.data.models.Customer;
import africa.semicolon.lumExpress.data.models.LumExpressUser;
import africa.semicolon.lumExpress.data.models.Seller;
import africa.semicolon.lumExpress.data.repositories.AdminRepository;
import africa.semicolon.lumExpress.data.repositories.CustomerRepository;
import africa.semicolon.lumExpress.data.repositories.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
       Optional<Customer> customer = customerRepository.findByEmail(loginRequest.getEmail());
       if (customer.isPresent()&&customer.get().getPassword() .equals(loginRequest.getPassword())) return buildLoginResponse(customer.get());
       Optional<Admin> admin= adminRepository.findByEmail(loginRequest.getEmail());
       if (admin.isPresent()&&admin.get().getPassword().equals(loginRequest.getPassword()))
           return buildLoginResponse(admin.get());
       Optional<Seller>seller=sellerRepository.findByEmail(loginRequest.getEmail());
       if (seller.isPresent()&&seller.get().getPassword().equals(loginRequest.getPassword()))
           return buildLoginResponse(seller.get());
       return LoginResponse.builder()
               .code(400)
               .message("login failed")
               .build();
    }
    private LoginResponse buildLoginResponse(LumExpressUser customer){
        return LoginResponse.builder()
                .message("user logged in successfully")
                .code(200)
                .build();
    }
}
