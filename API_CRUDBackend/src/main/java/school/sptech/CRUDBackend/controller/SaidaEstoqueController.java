package school.sptech.CRUDBackend.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.SaidaEstoque.SaidaEstoqueMapper;
import school.sptech.CRUDBackend.dto.SaidaEstoque.SaidaEstoqueRequestDto;
import school.sptech.CRUDBackend.dto.SaidaEstoque.SaidaEstoqueResponseDto;
import school.sptech.CRUDBackend.entity.SaidaEstoque;
import school.sptech.CRUDBackend.service.SaidaEstoqueService;

import java.util.List;

@RestController
@RequestMapping("/saidas-estoque")
public class SaidaEstoqueController {

    private final SaidaEstoqueService service;

    public SaidaEstoqueController(SaidaEstoqueService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SaidaEstoqueResponseDto> cadastrarSaida(@RequestBody @Valid SaidaEstoqueRequestDto saidaCadastro){
        SaidaEstoque saidaParaCadastrar = SaidaEstoqueMapper.toEntity(saidaCadastro);
        SaidaEstoqueResponseDto saidaCadastrada = SaidaEstoqueMapper
                .toResponseDto(service
                .cadastrar(saidaParaCadastrar));
        return ResponseEntity.status(201).body(saidaCadastrada);
    }

    @GetMapping
    public ResponseEntity<List<SaidaEstoqueResponseDto>> listarSaidas(){
        List<SaidaEstoqueResponseDto> todasAsSaidas = SaidaEstoqueMapper.toResponseDtos(service.listar());

        return todasAsSaidas.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(todasAsSaidas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaidaEstoqueResponseDto> buscarPorId(@PathVariable Integer id){
        SaidaEstoqueResponseDto saidaEncontrada = SaidaEstoqueMapper.toResponseDto(service.buscarPorId(id));

        return ResponseEntity.status(200).body(saidaEncontrada);
    }

    @GetMapping("/{motivo}")
    public ResponseEntity<List<SaidaEstoqueResponseDto>> buscarPorMotivo(@PathVariable String motivo){
        List<SaidaEstoqueResponseDto> saidasEncontradas = SaidaEstoqueMapper.toResponseDtos(service.buscarPorMotivo(motivo));

        return ResponseEntity.status(200).body(saidasEncontradas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaidaEstoqueResponseDto> atualizarSaida(
            @PathVariable Integer id,
            @RequestBody SaidaEstoqueRequestDto saidaParaAtualizar
    ) {
        SaidaEstoque saida = SaidaEstoqueMapper.toEntity(saidaParaAtualizar);
        SaidaEstoqueResponseDto saidaAtualizada = SaidaEstoqueMapper.toResponseDto(
                service.atualizarPorId(id, saida)
        );
        return ResponseEntity.status(200).body(saidaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSaida(@PathVariable Integer id) {
        service.removerPorId(id);
        return ResponseEntity.status(204).build();
    }
}
