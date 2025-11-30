package school.sptech.CleanArchitecture.core.adapters;

public interface EmailService {
    void send(String to, String subject, String body);
}
