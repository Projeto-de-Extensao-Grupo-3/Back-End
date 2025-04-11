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
                corteTecidoService.cadastrar(corteParaCadastrar)
        );
        return ResponseEntity.status(201).body(corteTecidoCadastrado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorteTecidoResponseDto> buscarPorId(@PathVariable Integer id) {
        CorteTecidoResponseDto corteTecido = CorteTecidoMapper.toResponseDto(
                corteTecidoService.buscarPorId(id)
        );
        return ResponseEntity.status(200).body(corteTecido);
    }

    @GetMapping
    public ResponseEntity<List<CorteTecidoResponseDto>> listarTodos() {
        List<CorteTecido> cortesCadastrados = corteTecidoService.listar();
        List<CorteTecidoResponseDto> cortesTecido = CorteTecidoMapper.toResponseDtos(cortesCadastrados);
        return cortesTecido.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(cortesTecido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CorteTecidoResponseDto> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid CorteTecidoRequestDto corteTecido
    ) {
        CorteTecido corteParaAtualizar = CorteTecidoMapper.toEntity(corteTecido);
        CorteTecidoResponseDto corteAtualizado = CorteTecidoMapper.toResponseDto(
                corteTecidoService.atualizar(id, corteParaAtualizar)
        );
        return ResponseEntity.status(200).body(corteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        corteTecidoService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
