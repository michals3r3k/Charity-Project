package pl.coderslab.charity.security.email;

public interface EmailSender {
    void send(String to, String subject, String text);
}
