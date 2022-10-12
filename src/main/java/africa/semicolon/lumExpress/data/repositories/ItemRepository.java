package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
