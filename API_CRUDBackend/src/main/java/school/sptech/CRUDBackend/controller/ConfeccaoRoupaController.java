package school.sptech.CRUDBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.confeccaoRoupa.ConfeccaoRoupaMapper;
import school.sptech.CRUDBackend.dto.confeccaoRoupa.ConfeccaoRoupaRequestDto;
import school.sptech.CRUDBackend.dto.confeccaoRoupa.ConfeccaoRoupaResponseDto;
import school.sptech.CRUDBackend.entity.ConfeccaoRoupa;
import school.sptech.CRUDBackend.service.ConfeccaoRoupaService;

@RestController
@RequestMapping("/confeccao-roupas")
@RequiredArgsConstructor
public class ConfeccaoRoupaController {
    private final ConfeccaoRoupaService service;

    @PostMapping
    public ResponseEntity<ConfeccaoRoupaResponseDto> cadastrar(
            @RequestBody ConfeccaoRoupaRequestDto confeccaoRoupaCadastro
    ) {
        ConfeccaoRoupa confeccaoRoupa = ConfeccaoRoupaMapper.toEntity(confeccaoRoupaCadastro);
        ConfeccaoRoupaResponseDto confeccaoRoupaCadastrada = ConfeccaoRoupaMapper.toResponseDto(
                service.cadastrarconfeccaoRoupa(confeccaoRoupa)
        );
        return ResponseEntity.status(201).body(confeccaoRoupaCadastrada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletarConfeccaoRoupa(id);
        return ResponseEntity.status(204).build();
    }
}
