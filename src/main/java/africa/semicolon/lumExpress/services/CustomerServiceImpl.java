package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dto.request.CustomerRegisterRequest;
import africa.semicolon.lumExpress.data.dto.request.LoginRequest;
import africa.semicolon.lumExpress.data.dto.request.UpdateCustomerProfile;
import africa.semicolon.lumExpress.data.dto.response.CustomerRegisterResponse;
import africa.semicolon.lumExpress.data.dto.response.LoginResponse;
import africa.semicolon.lumExpress.data.models.Address;
import africa.semicolon.lumExpress.data.models.Cart;
import africa.semicolon.lumExpress.data.models.Customer;
import africa.semicolon.lumExpress.data.models.VerificationToken;
import africa.semicolon.lumExpress.data.repositories.CartRepository;
import africa.semicolon.lumExpress.data.repositories.CustomerRepository;
import africa.semicolon.lumExpress.exception.UserNotFoundException;
import africa.semicolon.lumExpress.services.notification.EmailDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomersService{
    private final CustomerRepository customerRepository;
    private final EmailSender emailSender;
    private final ModelMapper mapper;
    private VerificationTokenService verificationTokenService;
    private final ModelMapper model = new ModelMapper();
    private final CartService cartService;
    @Override
    public CustomerRegisterResponse register(CustomerRegisterRequest customerRegisterRequest) {
        Customer customer = model.map(customerRegisterRequest, Customer.class);
       customer.setCart(new Cart());
       var customerAddress = new Address();
       customerAddress.setCountry(customerRegisterRequest.getCountry());
       customer.getAddresses().add(customerAddress);
      Customer savedCustomer = customerRepository.save(customer);
      log.info("customer to be saved in db::{}", savedCustomer);
      var token = verificationTokenService.generateVerificationToken(savedCustomer.getEmail());
      emailSender.sendHtmlEmail(buildEmailNotificationRequest(token));
        return customerRegisterResponseBuilder(savedCustomer);
    }
    private EmailDetails buildEmailNotificationRequest(VerificationToken verificationToken){
        var email = getEmailTemplate();
        String mail = null;
        if (email != null) {
            mail = String.format(email, verificationToken.getUserEmail(), "http://localhost:8080/" + verificationToken.getToken());
        }
           return EmailDetails.builder()
                    .userEmail(verificationToken.getUserEmail())
                    .mailContent(mail)
                    .build();
        }

    private String getEmailTemplate(){
        try(
                BufferedReader bufferedReader = new BufferedReader(new FileReader("/var/www/html/lumExpress/src/main/resources/welcome.txt"))
                ){
            return bufferedReader.lines().collect(Collectors.joining());
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return null;
    }
    private CustomerRegisterResponse customerRegisterResponseBuilder(Customer customer){
        return CustomerRegisterResponse.builder()
                .message("success")
                .userId(customer.getId())
                .code(201)
                .build();
    }

//    @Override
//    public LoginResponse login(LoginRequest loginRequest) {
//        return null;
//    }

    @Override
    public String completeProfile(UpdateCustomerProfile updateCustomerProfile) {
      Customer customerToUpdate =  customerRepository.findById(updateCustomerProfile.getCustomerId())
                .orElseThrow(()->new UserNotFoundException(String.format("customer with id %d not found", updateCustomerProfile.getCustomerId())));
        mapper.map(updateCustomerProfile, customerToUpdate);
        Set<Address> customerAddressList = customerToUpdate.getAddresses();
        Optional<Address> foundAddress = customerAddressList.stream().findFirst();
        foundAddress.ifPresent(address -> mapper.map(updateCustomerProfile, address));
        customerToUpdate.getAddresses().add(foundAddress.get());
        Customer updatedCustomer = customerRepository.save(customerToUpdate);
        return String.format("%s details updated successfully", updatedCustomer.getFirstName());
    }

    private void applyAddressUpdate(Address address, UpdateCustomerProfile updateCustomerProfile){
        address.setBuildingNumber(updateCustomerProfile.getBuildingNumber());
        address.setState(updateCustomerProfile.getState());
        address.setStreet(updateCustomerProfile.getStreet());
        address.setCity(updateCustomerProfile.getCity());
    }

}
