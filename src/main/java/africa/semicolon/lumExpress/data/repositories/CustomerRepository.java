package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.Admin;
import africa.semicolon.lumExpress.data.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

}
