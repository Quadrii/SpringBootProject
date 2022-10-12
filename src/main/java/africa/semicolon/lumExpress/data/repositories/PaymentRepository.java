package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
