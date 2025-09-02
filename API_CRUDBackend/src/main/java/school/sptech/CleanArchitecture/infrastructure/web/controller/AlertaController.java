package school.sptech.CleanArchitecture.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CleanArchitecture.core.application.command.alerta.CriarAlertaCommand;
import school.sptech.CleanArchitecture.core.application.usecase.alerta.CriarAlertaUseCase;
import school.sptech.CleanArchitecture.core.domain.entity.Alerta;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.alerta.AlertaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.alerta.AlertaEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntity;
import school.sptech.CleanArchitecture.infrastructure.web.dto.alerta.AlertaCriacaoDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.alerta.AlertaItemEstoqueDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.alerta.AlertaMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.alerta.AlertaResponseDto;

import java.util.List;

@Tag(name = "Alerta Controller",  description = "Operações CRUD relacionadas aos alertas gerados")
@RestController
@RequestMapping("/alertas")
@RequiredArgsConstructor
public class AlertaController {
    private final CriarAlertaUseCase criarAlertaUseCase;

    @Operation(
            summary = "Geração de um novo alerta",
            description = "Cria e retorna um objeto do tipo AlertaResponseDto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alerta criado com sucesso.")
    })
    @SecurityRequirement(name = "Bearer")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo AlertaaRequestDto.",
            required = true
    )
    @PostMapping
    public ResponseEntity<AlertaResponseDto> criarAlerta(@RequestBody AlertaCriacaoDto dto) {
        CriarAlertaCommand command = AlertaMapper.toCommand(dto);
        Alerta domain = criarAlertaUseCase.executar(command);
        AlertaEntity entity = AlertaEntityMapper.ofDomain(domain);
        AlertaResponseDto alertaCadastrado = AlertaMapper.toResponseDto(entity);
        return ResponseEntity.status(201).body(alertaCadastrado);
    }

    @Operation(
            summary = "Listagem dos alertas relativos a um ItemEstoque",
            description = "Cria e retorna um objeto do tipo AlertaResponseDto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alertas listados com sucesso."),
            @ApiResponse(responseCode = "204", description = "Sem alertas para o ItemEstoque.")
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<AlertaResponseDto>> listarAlertasDoItem(
            @PathVariable AlertaItemEstoqueDto itemEstoqueDto
    ) {
        ItemEstoqueEntity itemEstoque = new ItemEstoqueEntity();
        itemEstoque.setIdItemEstoque(itemEstoqueDto.getIdItemEstoque());


        List<AlertaResponseDto> alertas = AlertaMapper.toResponseDtos(
                service.listarAlertasDoItem(itemEstoque)
        );
        if (alertas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(alertas);
    }
}
