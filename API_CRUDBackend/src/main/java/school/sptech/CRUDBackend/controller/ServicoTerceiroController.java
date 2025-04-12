package school.sptech.CRUDBackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.servicoTerceiro.ServicoTerceiroMapper;
import school.sptech.CRUDBackend.dto.servicoTerceiro.ServicoTerceiroRequestDto;
import school.sptech.CRUDBackend.dto.servicoTerceiro.ServicoTerceiroResponseDto;
import school.sptech.CRUDBackend.entity.ServicoTerceiro;
import school.sptech.CRUDBackend.service.ServicoTerceiroService;

import java.util.List;

@RestController
@RequestMapping("/servico-terceiros")
@RequiredArgsConstructor
public class ServicoTerceiroController {
    private final ServicoTerceiroService servicoTerceiroService;

    @PostMapping
    public ResponseEntity<ServicoTerceiroResponseDto> cadastrar(
            @RequestBody @Valid ServicoTerceiroRequestDto servicoTerceiroCad
    ) {
        ServicoTerceiro servicoParaCadastrar = ServicoTerceiroMapper.toEntity(servicoTerceiroCad);
        ServicoTerceiroResponseDto novoServicoTerceiro = ServicoTerceiroMapper.toResponseDto(
                servicoTerceiroService.cadastrarServicoTerceiro(servicoParaCadastrar));
        return ResponseEntity.status(201).body(novoServicoTerceiro);
    }

    @GetMapping
    public ResponseEntity<List<ServicoTerceiroResponseDto>> verificarTodos() {
        List<ServicoTerceiroResponseDto> servicosTerceiro = ServicoTerceiroMapper.toResponseDtos(
                servicoTerceiroService.verificarTodosServicosTerceiro()
        );
        if (servicosTerceiro.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(servicosTerceiro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoTerceiroResponseDto> servicoTerceiroPorId (@PathVariable Integer id) {
        ServicoTerceiroResponseDto servicoTerceiroPorId = ServicoTerceiroMapper.toResponseDto(
                servicoTerceiroService.buscarServicoTerceiroPorId(id)
        );
        return ResponseEntity.status(200).body(servicoTerceiroPorId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoTerceiroResponseDto> atualizarPorId (
            @PathVariable Integer id,
            @RequestBody @Valid ServicoTerceiroRequestDto servicoTerceiroAtualizar
    ) {
        ServicoTerceiro servicoParaAtualizar = ServicoTerceiroMapper.toEntity(servicoTerceiroAtualizar);
        ServicoTerceiroResponseDto servicoAtualizado = ServicoTerceiroMapper.toResponseDto(
                servicoTerceiroService.atualizarServicoTerceiroPorId(id, servicoParaAtualizar)
        );
        return ResponseEntity.status(200).body(servicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id) {
        servicoTerceiroService.removerServicoTerceiroPorId(id);
        return ResponseEntity.status(204).build();
    }
}
