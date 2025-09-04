package school.sptech.CleanArchitecture.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaAtualizarCommand;
import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaCadastrarCommand;
import school.sptech.CleanArchitecture.core.application.usecase.confeccaoRoupa.ConfeccaoRoupaAtualizarUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.confeccaoRoupa.ConfeccaoRoupaCadastrarUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.confeccaoRoupa.ConfeccaoRoupaDeletarUseCase;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa.ConfeccaoRoupaEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.confeccaoRoupa.ConfeccaoRoupaRequestDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.confeccaoRoupa.ConfeccaoRoupaResponseDto;


@Tag(name = "Confecção de Roupa Controller", description = "Cadastro e deleção do registro de confecção de uma roupa por outros tecidos.")
@RestController
@RequestMapping("/confeccao-roupas")
@RequiredArgsConstructor
public class ConfeccaoRoupaController {
   private final ConfeccaoRoupaCadastrarUseCase confeccaoRoupaCadastrarUseCase;
   private final ConfeccaoRoupaDeletarUseCase confeccaoRoupaDeletarUseCase;
   private final ConfeccaoRoupaAtualizarUseCase confeccaoRoupaAtualizarUseCase;

    @Operation(
            summary = "Cadastro de uma nova Confecção de Roupa por Tecido.",
            description = "Retorna um objeto do tipo ConfeccaoRoupaResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Confecção cadastrada com sucesso."),
            @ApiResponse(responseCode = "409", description = "Roupa informada já foi confeccionada por Tecido informado.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo ConfeccaoRoupaRequestDto para cadastro.",
            required = true
    )
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<ConfeccaoRoupaResponseDto> cadastrar(
            @RequestBody ConfeccaoRoupaRequestDto confeccaoRoupaCadastro
    ) {
        ConfeccaoRoupaCadastrarCommand command = ConfeccaoRoupaEntityMapper.toCadastrarCommand(confeccaoRoupaCadastro);
        ConfeccaoRoupaResponseDto confeccaoRoupaCadastrada = ConfeccaoRoupaEntityMapper.toResponseDto(
                confeccaoRoupaCadastrarUseCase.execute(command)
        );
        return ResponseEntity.status(201).body(confeccaoRoupaCadastrada);
    }

    @Operation(summary = "Deleção de um registro de Confecção.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        confeccaoRoupaDeletarUseCase.execute(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfeccaoRoupaResponseDto> atualizar(
            @PathVariable Integer id,
            @RequestBody ConfeccaoRoupaRequestDto confeccaoRoupaDto
    ) {
        ConfeccaoRoupaAtualizarCommand command = ConfeccaoRoupaEntityMapper.toAtualizarCommand(id, confeccaoRoupaDto);
        ConfeccaoRoupaResponseDto confeccaoRoupaAtualizada = ConfeccaoRoupaEntityMapper.toResponseDto(
                confeccaoRoupaAtualizarUseCase.execute(command)
        );
        return ResponseEntity.status(200).body(confeccaoRoupaAtualizada);
    }
}
