package school.sptech.CRUDBackend.controller;

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

@RestController
@RequestMapping("/alertas")
@RequiredArgsConstructor
public class AlertaController {
    private final AlertaService service;

    @PostMapping
    public ResponseEntity<AlertaResponseDto> criarAlerta(@RequestBody AlertaCriacaoDto alertaDto) {
        Alerta alerta = AlertaMapper.toEntity(alertaDto);
        AlertaResponseDto alertaCadastrado = AlertaMapper.toResponseDto(service.criarAlerta(alerta));
        return ResponseEntity.status(201).body(alertaCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<AlertaResponseDto>> listarAlertasDoItem(
            @PathVariable AlertaItemEstoqueDto itemEstoqueDto
    ) {
        ItemEstoque itemEstoque = new ItemEstoque();
        itemEstoque.setIdItemEstoque(itemEstoqueDto.getIdItemEstoque());
        List<AlertaResponseDto> alertas = AlertaMapper.toResponseDtos(
                service.listarAlertasDoItem(itemEstoque)
        );
        return ResponseEntity.status(200).body(alertas);
    }
}
