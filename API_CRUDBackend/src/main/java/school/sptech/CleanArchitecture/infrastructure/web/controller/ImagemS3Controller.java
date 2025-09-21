package school.sptech.CleanArchitecture.infrastructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem.ImagemS3Service;

@Controller
@RequestMapping("/s3")
public class ImagemS3Controller {
    private final ImagemS3Service imagemS3Service;

    public ImagemS3Controller(ImagemS3Service imagemS3Service) {
        this.imagemS3Service = imagemS3Service;
    }

    @PostMapping("/upload/{nomeArquivo}")
    public ResponseEntity<String> uploadFile(@PathVariable String nomeArquivo, @RequestBody byte[] conteudoArquivo) {
        String fileUrl = imagemS3Service.uploadFile(nomeArquivo, conteudoArquivo);
        return ResponseEntity.status(201).body(fileUrl);
    }
}
