package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.Cart;
import africa.semicolon.lumExpress.data.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {
private CustomerRepository customerRepository;
    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    void findByEmailTest(){
        var customer = Customer.builder().cart(new Cart()).build();
        customer.setEmail("test@gmail.com");
        customer.setFirstName("tester");
        customer.setLastName("testerBuga");
        customer.setPassword("testerBuga");
        var savedCustomer = customerRepository.save(customer);
        assertThat(customerRepository.findByEmail(savedCustomer.getEmail())).isNotNull();
    }
}