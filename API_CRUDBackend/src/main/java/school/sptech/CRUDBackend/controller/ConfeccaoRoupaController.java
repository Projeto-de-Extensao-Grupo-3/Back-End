package school.sptech.CRUDBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.confeccaoRoupa.ConfeccaoRoupaMapper;
import school.sptech.CRUDBackend.dto.confeccaoRoupa.ConfeccaoRoupaRequestDto;
import school.sptech.CRUDBackend.dto.confeccaoRoupa.ConfeccaoRoupaResponseDto;
import school.sptech.CRUDBackend.entity.ConfeccaoRoupa;
import school.sptech.CRUDBackend.service.ConfeccaoRoupaService;

@Tag(name = "Confecção de Roupa Controller", description = "Cadastro e deleção do registro de confecção de uma roupa por outros tecidos.")
@RestController
@RequestMapping("/confeccao-roupas")
@RequiredArgsConstructor
public class ConfeccaoRoupaController {
    private final ConfeccaoRoupaService service;

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
    @PostMapping
    public ResponseEntity<ConfeccaoRoupaResponseDto> cadastrar(
            @RequestBody ConfeccaoRoupaRequestDto confeccaoRoupaCadastro
    ) {
        ConfeccaoRoupa confeccaoRoupa = ConfeccaoRoupaMapper.toEntity(confeccaoRoupaCadastro);
        ConfeccaoRoupaResponseDto confeccaoRoupaCadastrada = ConfeccaoRoupaMapper.toResponseDto(
                service.cadastrarconfeccaoRoupa(confeccaoRoupa)
        );
        return ResponseEntity.status(201).body(confeccaoRoupaCadastrada);
    }

    @Operation(summary = "Deleção de um registro de Confecção.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletarConfeccaoRoupa(id);
        return ResponseEntity.status(204).build();
    }
}
