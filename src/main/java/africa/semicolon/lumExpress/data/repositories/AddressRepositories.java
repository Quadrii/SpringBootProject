package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepositories extends JpaRepository<Address, Long> {

}
