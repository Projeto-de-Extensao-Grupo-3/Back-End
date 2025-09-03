package school.sptech.CleanArchitecture.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CleanArchitecture.core.application.usecase.imagem.ImagemAtualizarUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.imagem.ImagemCadastrarUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.imagem.ImagemDeletarPorIdUseCase;
import school.sptech.CleanArchitecture.infrastructure.web.dto.imagem.ImagemRequestDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.imagem.ImagemResponseDto;

@Tag(name = "Imagem Controller",  description = "Operações CRUD relacionadas a entidade Imagem")
@RestController
@RequestMapping("/imagens")
@RequiredArgsConstructor
public class ImagemController {
    private final ImagemCadastrarUseCase imagemCadastrarUseCase;

    private final ImagemAtualizarUseCase imagemAtualizarUseCase;

    private final ImagemDeletarPorIdUseCase imagemDeletarPorIdUseCase;

    @Operation(
            summary = "Cadastro de uma nova imagem",
            description = "Cria e retorna um objeto do tipo ImagemResponseDto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Imagem cadastrada com sucesso."),
            @ApiResponse(responseCode = "409", description = "Imagem já foi cadastrada previamente.")
    })
    @SecurityRequirement(name = "Bearer")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo ImagemaRequestDto.",
            required = true
    )
    @PostMapping
    public ResponseEntity<ImagemResponseDto> cadastrarImagem(@RequestBody @Valid ImagemRequestDto imagemDto) {
        Imagem imagem = ImagemMapper.toEntity(imagemDto);
        ImagemResponseDto imagemCadastrada = ImagemMapper.toResponseDto(service.cadastrarImagem(imagem));
        return ResponseEntity.status(201).body(imagemCadastrada);
    }

    @Operation(
            summary = "Atualização de uma imagem",
            description = "Atualiza a imagem e retorna um objeto do tipo ImagemResponseDto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagem atualizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Imagem para atualizar não encontrada.")
    })
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public ResponseEntity<ImagemResponseDto> atualizarImagem(
            @PathVariable Integer id,
            @RequestBody @Valid ImagemRequestDto imagemDto
    ) {
        Imagem imagem = ImagemMapper.toEntity(imagemDto);
        ImagemResponseDto imagemAtualizada = ImagemMapper.toResponseDto(service.atualizarImagem(id, imagem));
        return ResponseEntity.status(200).body(imagemAtualizada);
    }

    @Operation(
            summary = "Deletar uma imagem cadastrada",
            description = "Deleta uma imagem a partir do ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Imagem deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Imagem para deletar não encontrada.")
    })
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarImagem(@PathVariable Integer id) {
        service.deletarImagem(id);
        return ResponseEntity.status(204).build();
    }
}
