package school.sptech.CRUDBackend.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.Prateleira.PrateleiraMapper;
import school.sptech.CRUDBackend.dto.Prateleira.PrateleiraRequestDto;
import school.sptech.CRUDBackend.dto.Prateleira.PrateleiraResponseDto;
import school.sptech.CRUDBackend.entity.Prateleira;
import school.sptech.CRUDBackend.service.PrateleiraService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prateleiras")
public class PrateleiraController {

    private final PrateleiraService service;

    public PrateleiraController(PrateleiraService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PrateleiraResponseDto> cadastrar(@RequestBody PrateleiraRequestDto prateleiraParaCadastrar) {
        Prateleira prateleira = PrateleiraMapper.toEntity(prateleiraParaCadastrar);
        PrateleiraResponseDto prateleiraCadastrada = PrateleiraMapper.toResponseDto(service.cadastrarPrateleira(prateleira));
        return ResponseEntity.status(201).body(prateleiraCadastrada);
    }

    @GetMapping
    public ResponseEntity<List<PrateleiraResponseDto>> listarTodas() {
        List<PrateleiraResponseDto> todasPrateleiras = service.listarTodos()
                .stream()
                .map(PrateleiraMapper::toResponseDto)
                .collect(Collectors.toList());

        if (todasPrateleiras.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(todasPrateleiras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrateleiraResponseDto> buscarPorId(@PathVariable Integer id) {
        PrateleiraResponseDto prateleiraEncontrada = PrateleiraMapper.toResponseDto(service.buscarPorId(id));
        return ResponseEntity.status(200).body(prateleiraEncontrada);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<PrateleiraResponseDto> buscarPorCodigo(@PathVariable String codigo) {
        PrateleiraResponseDto prateleiraEncontrada = PrateleiraMapper.toResponseDto(service.buscarPorCodigo(codigo));
        return ResponseEntity.status(200).body(prateleiraEncontrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrateleiraResponseDto> atualizarPorId(
            @PathVariable Integer id,
            @RequestBody @Valid PrateleiraRequestDto prateleiraAtualizar){
        Prateleira prateleiraParaAtualizar = PrateleiraMapper.toEntity(prateleiraAtualizar);
        PrateleiraResponseDto prateleiraAtualizada = PrateleiraMapper.toResponseDto(service.atualizarPorId(id, prateleiraParaAtualizar));
        return ResponseEntity.status(200).body(prateleiraAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id) {
        service.removerPorId(id);
        return ResponseEntity.status(200).build();
    }

}
