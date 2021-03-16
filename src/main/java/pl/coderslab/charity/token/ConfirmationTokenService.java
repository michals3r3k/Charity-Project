package pl.coderslab.charity.token;

public interface ConfirmationTokenService {
    ConfirmationToken findByToken(String token);
    void save(ConfirmationToken token);
    void delete(ConfirmationToken token);
}
