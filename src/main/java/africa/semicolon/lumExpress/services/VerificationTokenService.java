package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.models.VerificationToken;

public interface VerificationTokenService {
    VerificationToken generateVerificationToken(String userEmail);
    boolean isValidToken(String token);
}
