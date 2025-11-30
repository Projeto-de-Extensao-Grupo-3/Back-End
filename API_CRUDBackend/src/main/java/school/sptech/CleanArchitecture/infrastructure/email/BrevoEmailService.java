package school.sptech.CleanArchitecture.infrastructure.email;

import school.sptech.CleanArchitecture.core.adapters.EmailService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BrevoEmailService implements EmailService {

    @Value("${brevo.api.key}")
    private String apiKey;

    private final OkHttpClient client = new OkHttpClient();

    @Override
    public void send(String to, String subject, String body) {
        String json = """
                {
                    "sender": { "name": "SeuSistema", "email": "fernandoalmeida.mda@gmail.com" },
                    "to": [{ "email": "%s" }],
                    "subject": "%s",
                    "textContent": "%s"
                }
                """.formatted(to, subject, body);

        Request request = new Request.Builder()
                .url("https://api.brevo.com/v3/smtp/email")
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .addHeader("accept", "application/json")
                .addHeader("api-key", apiKey)
                .build();

        try {
            client.newCall(request).execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar email via Brevo", e);
        }
    }
}
