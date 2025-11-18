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
import school.sptech.CleanArchitecture.core.application.command.imagem.CriarImagemCommand;
import school.sptech.CleanArchitecture.core.application.command.imagem.ImagemAtualizarCommand;
import school.sptech.CleanArchitecture.core.application.usecase.imagem.ImagemAtualizarUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.imagem.ImagemCadastrarUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.imagem.ImagemDeletarPorIdUseCase;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem.ImagemEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem.ImagemEntityMapper;
import school.sptech.CleanArchitecture.core.application.mapper.ImagemMapper;
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
        CriarImagemCommand command = ImagemEntityMapper.toCriarCommand(imagemDto);
        ImagemEntity imagemCriada = ImagemEntityMapper.ofDomain(imagemCadastrarUseCase.execute(command));
        ImagemResponseDto response = ImagemEntityMapper.toResponseDto(imagemCriada);
        return ResponseEntity.status(201).body(response);
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
        ImagemAtualizarCommand command = ImagemEntityMapper.toAtualizarCommand(id, imagemDto);
        ImagemEntity imagemCriada = ImagemEntityMapper.ofDomain(imagemAtualizarUseCase.execute(command));
        ImagemResponseDto response = ImagemEntityMapper.toResponseDto(imagemCriada);
        return ResponseEntity.status(200).body(response);
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
        imagemDeletarPorIdUseCase.execute(id);
        return ResponseEntity.status(204).build();
    }
}
