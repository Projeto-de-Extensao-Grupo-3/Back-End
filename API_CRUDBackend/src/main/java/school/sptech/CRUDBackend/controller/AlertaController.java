package school.sptech.CRUDBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.alerta.AlertaCriacaoDto;
import school.sptech.CRUDBackend.dto.alerta.AlertaItemEstoqueDto;
import school.sptech.CRUDBackend.dto.alerta.AlertaMapper;
import school.sptech.CRUDBackend.dto.alerta.AlertaResponseDto;
import school.sptech.CRUDBackend.entity.Alerta;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.service.AlertaService;

import java.util.List;

@Tag(name = "Alerta Controller",  description = "Operações CRUD relacionadas aos alertas gerados")
@RestController
@RequestMapping("/alertas")
@RequiredArgsConstructor
public class AlertaController {
    private final AlertaService service;

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
    public ResponseEntity<AlertaResponseDto> criarAlerta(@RequestBody AlertaCriacaoDto alertaDto) {
        Alerta alerta = AlertaMapper.toEntity(alertaDto);
        AlertaResponseDto alertaCadastrado = AlertaMapper.toResponseDto(service.criarAlerta(alerta));
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
        ItemEstoque itemEstoque = new ItemEstoque();
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
