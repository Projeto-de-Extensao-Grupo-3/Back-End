package school.sptech.CRUDBackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.imagem.ImagemMapper;
import school.sptech.CRUDBackend.dto.imagem.ImagemRequestDto;
import school.sptech.CRUDBackend.dto.imagem.ImagemResponseDto;
import school.sptech.CRUDBackend.entity.Imagem;
import school.sptech.CRUDBackend.service.ImagemService;

@RestController
@RequestMapping("/imagens")
@RequiredArgsConstructor
public class ImagemController {
    private final ImagemService service;

    @PostMapping
    public ResponseEntity<ImagemResponseDto> cadastrarImagem(@RequestBody @Valid ImagemRequestDto imagemDto) {
        Imagem imagem = ImagemMapper.toEntity(imagemDto);
        ImagemResponseDto imagemCadastrada = ImagemMapper.toResponseDto(service.cadastrarImagem(imagem));
        return ResponseEntity.status(201).body(imagemCadastrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagemResponseDto> atualizarImagem(
            @PathVariable Integer id,
            @RequestBody @Valid ImagemRequestDto imagemDto
    ) {
        Imagem imagem = ImagemMapper.toEntity(imagemDto);
        ImagemResponseDto imagemAtualizada = ImagemMapper.toResponseDto(service.atualizarImagem(id, imagem));
        return ResponseEntity.status(200).body(imagemAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarImagem(@PathVariable Integer id) {
        service.deletarImagem(id);
        return ResponseEntity.status(204).build();
    }
}
