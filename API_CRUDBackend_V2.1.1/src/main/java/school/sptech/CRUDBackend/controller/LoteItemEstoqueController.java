package school.sptech.CRUDBackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.Lote.LoteMapper;
import school.sptech.CRUDBackend.dto.LoteItemEstoque.LoteItemEstoqueMapper;
import school.sptech.CRUDBackend.dto.LoteItemEstoque.LoteItemEstoqueRequestDto;
import school.sptech.CRUDBackend.dto.LoteItemEstoque.LoteItemEstoqueResponseDto;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;
import school.sptech.CRUDBackend.service.LoteItemEstoqueService;

import java.util.List;

@RestController
@RequestMapping("/lotesItemEstoques")
public class LoteItemEstoqueController {

    private LoteItemEstoqueService service;

    @PostMapping
    public ResponseEntity<LoteItemEstoqueResponseDto> cadastrar(@RequestBody LoteItemEstoqueRequestDto loteItemEstoqueCadastrar){

      LoteItemEstoque loteItemEstoqueParaCadastrar = LoteItemEstoqueMapper.toEntity(loteItemEstoqueCadastrar);
      LoteItemEstoqueResponseDto loteItemEstoqueCadastrado = LoteItemEstoqueMapper.toResponseDto(service.cadastrarLoteItemEstoque(loteItemEstoqueParaCadastrar));

      return ResponseEntity.status(201).body(loteItemEstoqueCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<LoteItemEstoqueResponseDto>> listarTodos(){

        List<LoteItemEstoqueResponseDto> todosOsLoteItemEstoque = service
                .listarTodos()
                .stream()
                .map(LoteItemEstoqueMapper::toResponseDto)
                .toList();
        if (todosOsLoteItemEstoque.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(todosOsLoteItemEstoque);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteItemEstoqueResponseDto> buscarPorId(@PathVariable Integer id){
        LoteItemEstoqueResponseDto loteItemEstoqueEcontrado = LoteItemEstoqueMapper.toResponseDto(service.buscarLoteItemEstoquePorId(id));

        return ResponseEntity.status(200).body(loteItemEstoqueEcontrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoteItemEstoqueResponseDto> atualizarPorId(@PathVariable Integer id,
                                                                     @RequestBody LoteItemEstoqueRequestDto loteItemEstoqueCadastrar)
    {
        LoteItemEstoque loteItemEstoqueParaAtualizar = LoteItemEstoqueMapper.toEntity(loteItemEstoqueCadastrar);
        LoteItemEstoqueResponseDto loteItemEstoqueAtualizado = LoteItemEstoqueMapper.toResponseDto(
                service.atualizarLoteItemEstoquePorId(id, loteItemEstoqueParaAtualizar));

        return ResponseEntity.status(200).body(loteItemEstoqueAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(
            @PathVariable Integer id)
    {
       service.removerPorId(id);
       return ResponseEntity.status(200).build();
    }
}
