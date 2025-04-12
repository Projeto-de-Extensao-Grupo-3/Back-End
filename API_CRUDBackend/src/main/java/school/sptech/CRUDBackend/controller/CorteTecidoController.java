package school.sptech.CRUDBackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.corteTecido.CorteTecidoMapper;
import school.sptech.CRUDBackend.dto.corteTecido.CorteTecidoRequestDto;
import school.sptech.CRUDBackend.dto.corteTecido.CorteTecidoResponseDto;
import school.sptech.CRUDBackend.entity.CorteTecido;
import school.sptech.CRUDBackend.service.CorteTecidoService;

import java.util.List;

@RestController
@RequestMapping("/cortes-tecido")
@RequiredArgsConstructor
public class CorteTecidoController {
    private final CorteTecidoService corteTecidoService;

    @PostMapping
    public ResponseEntity<CorteTecidoResponseDto> cadastrar(
            @RequestBody @Valid CorteTecidoRequestDto corteTecido
    ) {
        CorteTecido corteParaCadastrar = CorteTecidoMapper.toEntity(corteTecido);
        CorteTecidoResponseDto corteTecidoCadastrado = CorteTecidoMapper.toResponseDto(
                corteTecidoService.cadastrarCorteTecido(corteParaCadastrar)
        );
        return ResponseEntity.status(201).body(corteTecidoCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<CorteTecidoResponseDto>> listarTodos() {
        List<CorteTecidoResponseDto> cortesTecido = CorteTecidoMapper.toResponseDtos(
                corteTecidoService.verificarTodosCortesTecido()
        );
        if (cortesTecido.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(cortesTecido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorteTecidoResponseDto> buscarPorId(@PathVariable Integer id) {
        CorteTecidoResponseDto corteTecido = CorteTecidoMapper.toResponseDto(
                corteTecidoService.buscarCorteTecidoPorId(id)
        );
        return ResponseEntity.status(200).body(corteTecido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CorteTecidoResponseDto> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid CorteTecidoRequestDto corteTecido
    ) {
        CorteTecido corteParaAtualizar = CorteTecidoMapper.toEntity(corteTecido);
        CorteTecidoResponseDto corteAtualizado = CorteTecidoMapper.toResponseDto(
                corteTecidoService.atualizarCorteTecido(id, corteParaAtualizar)
        );
        return ResponseEntity.status(200).body(corteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        corteTecidoService.deletarCorteTecido(id);
        return ResponseEntity.status(204).build();
    }
}
