package school.sptech.CRUDBackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.Lote.LoteMapper;
import school.sptech.CRUDBackend.dto.Lote.LoteRequestDto;
import school.sptech.CRUDBackend.dto.Lote.LoteResponseDto;
import school.sptech.CRUDBackend.entity.Lote;
import school.sptech.CRUDBackend.service.LoteService;

import java.util.List;

@RestController
@RequestMapping("/lotes")
public class LoteController {

    private LoteService service;

    @PostMapping
    public ResponseEntity<LoteResponseDto> cadastrar(
            @RequestBody LoteRequestDto loteParaCadastrar
    ){
        Lote lote = LoteMapper.toEntity(loteParaCadastrar);
        LoteResponseDto loteCadastrado = LoteMapper.toResponseDto(service.cadastrarLote(lote));

        return ResponseEntity.status(201).body(loteCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<LoteResponseDto>> listarTodos(){
        List<LoteResponseDto> todosOsLotes = service
                .listarTodosOsLotes()
                .stream().map(LoteMapper::toResponseDto)
                .toList();
        if (todosOsLotes.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteResponseDto> buscarPorId(@PathVariable Integer id){
        LoteResponseDto loteEncontrado = LoteMapper.toResponseDto(
                service.buscarLotePorId(id));
        return ResponseEntity.status(200).body(loteEncontrado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id){

        service.buscarLotePorId(id);
        return ResponseEntity.status(200).build();
    }
}
