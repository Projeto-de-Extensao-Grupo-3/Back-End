package school.sptech.CRUDBackend.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.Categoria.CategoriaMapper;
import school.sptech.CRUDBackend.dto.Categoria.CategoriaRequestDto;
import school.sptech.CRUDBackend.dto.Categoria.CategoriaResponseDto;
import school.sptech.CRUDBackend.entity.Categoria;
import school.sptech.CRUDBackend.service.CategoriaService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> cadastrar(@RequestBody CategoriaRequestDto categoriaParaCadastrar) {
        Categoria categoria = CategoriaMapper.toEntity(categoriaParaCadastrar);
        CategoriaResponseDto categoriaCadastrada = CategoriaMapper.toResponseDto(service.cadastrarCategoria(categoria));
        return ResponseEntity.status(201).body(categoriaCadastrada);
    }

   @GetMapping
   public ResponseEntity<List<CategoriaResponseDto>> listarTodas() {
       List<CategoriaResponseDto> todasCategorias = service.listarTodos()
               .stream()
               .map(CategoriaMapper::toResponseDto)
               .collect(Collectors.toList());

       if (todasCategorias.isEmpty()) {
           return ResponseEntity.status(204).build();
       }

       return ResponseEntity.status(200).body(todasCategorias);
   }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> buscarPorId(@PathVariable Integer id) {
        CategoriaResponseDto categoriaEncontrada = CategoriaMapper.toResponseDto(service.buscarPorId(id));
        return ResponseEntity.status(200).body(categoriaEncontrada);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<CategoriaResponseDto> buscarPorId(@PathVariable String nome) {
        CategoriaResponseDto categoriaEncontrada = CategoriaMapper.toResponseDto(service.buscarPorNome(nome));
        return ResponseEntity.status(200).body(categoriaEncontrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> atualizarPorId(
            @PathVariable Integer id,
            @RequestBody @Valid CategoriaRequestDto categoriaAtualizar){
        Categoria categoriaParaAtualizar = CategoriaMapper.toEntity(categoriaAtualizar);
        CategoriaResponseDto categoriaAtualizada = CategoriaMapper.toResponseDto(service.atualizarPorId(id, categoriaParaAtualizar));
        return ResponseEntity.status(200).body(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id) {
        service.removerPorId(id);
        return ResponseEntity.status(200).build();
    }
}
