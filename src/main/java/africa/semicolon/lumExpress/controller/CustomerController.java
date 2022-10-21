package africa.semicolon.lumExpress.controller;

import africa.semicolon.lumExpress.data.dto.request.CustomerRegisterRequest;
import africa.semicolon.lumExpress.data.models.Customer;
import africa.semicolon.lumExpress.services.CustomersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomersService customersService;
    @PostMapping()
    public ResponseEntity<?> register(@Valid @RequestBody CustomerRegisterRequest registerRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customersService.register(registerRequest));
    }
}
