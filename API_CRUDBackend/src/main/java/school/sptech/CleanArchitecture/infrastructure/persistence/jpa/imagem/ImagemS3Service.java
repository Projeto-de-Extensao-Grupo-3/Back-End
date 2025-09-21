package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class ImagemS3Service {
    private final S3Client s3Client;

    public ImagemS3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Value("${aws.s3.nome-bucket}")
    private String bucketName;

    public String uploadFile(String nomeArquivo, byte[] conteudoArquivo) {
        if (conteudoArquivo == null || conteudoArquivo.length == 0) {
            throw new ResponseStatusException(400,
                    "O arquivo não pode ser vazio", null);
        }
        String s3key = nomeArquivo;

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3key)
                    .build();
            s3Client.putObject(putObjectRequest,
                    RequestBody.fromBytes(conteudoArquivo));
            String fileUrl = s3Client.utilities().getUrl(
                    builder -> builder.bucket(bucketName).key(s3key)).toString();

            return fileUrl;
        } catch (S3Exception exception) {
            throw new ResponseStatusException(500,
                    "Erro de envio para o S3: "
                            + exception.getMessage(), exception);
        }
    }
}
