package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
