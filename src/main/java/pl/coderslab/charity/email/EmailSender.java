package pl.coderslab.charity.email;

public interface EmailSender {
    void send(String to, String subject, String text);
}
