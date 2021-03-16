package pl.coderslab.charity.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepository tokenRepository;

    @Override
    public void delete(ConfirmationToken token) {
        tokenRepository.delete(token);
    }

    @Override
    public void save(ConfirmationToken token) {
        tokenRepository.save(token);
    }

    @Override
    public ConfirmationToken findByToken(String token) {
        return tokenRepository.findByToken(token).orElseThrow(() -> new IllegalStateException("Token not found"));
    }
}
