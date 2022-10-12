package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.models.VerificationToken;
import africa.semicolon.lumExpress.data.repositories.VerificationTokenRepository;
import africa.semicolon.lumExpress.exception.VerificationTokenException;
import africa.semicolon.lumExpress.util.LumExpressUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VerificationTokenServiceImp implements VerificationTokenService{
    private final VerificationTokenRepository verificationTokenRepository;
    @Override
    public VerificationToken generateVerificationToken(String userEmail) {
        String tokenString = LumExpressUtil.generateToken();
        var verificationToken = VerificationToken.builder()
                .token(tokenString)
                .userEmail(userEmail)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(5))
                .build();
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public boolean isValidToken(String token) {
        VerificationToken foundVerification = verificationTokenRepository.findByToken(token)
        .orElseThrow(()->new VerificationTokenException("Token not found"));
        if(isTokenNotExpired(foundVerification))return true;
        throw new VerificationTokenException("Token had expired");
    }
    private boolean isTokenNotExpired(VerificationToken verificationToken){
        return LocalDateTime.now().isBefore(verificationToken.getExpiresAt());
    }
}
