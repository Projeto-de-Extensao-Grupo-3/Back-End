package school.sptech.CRUDBackendV1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackendV1.entity.ServicoTerceiro;
import school.sptech.CRUDBackendV1.service.ServicoTerceiroService;

import java.util.List;

@RestController
@RequestMapping("/servico-terceiros")
public class ServicoTerceiroController {

    private ServicoTerceiroService servicoTerceiroService;

    @PostMapping
    public ResponseEntity<ServicoTerceiro> cadastrar(
            @RequestBody ServicoTerceiro servicoTerceiroCad
    ) {
        ServicoTerceiro novoServicoTerceiro = servicoTerceiroService.cadastrarServicoTerceiro(servicoTerceiroCad);
        return ResponseEntity.status(201).body(novoServicoTerceiro);
    }

    @GetMapping
    public ResponseEntity<List<ServicoTerceiro>> todosServicoTerceiroes () {
        List<ServicoTerceiro> buscarServicoTerceiroes = servicoTerceiroService.todosServicoTerceiroes();

        if (buscarServicoTerceiroes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(buscarServicoTerceiroes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoTerceiro> servicoTerceiroPorId (@PathVariable Integer id) {
        ServicoTerceiro servicoTerceiroPorId = servicoTerceiroService.servicoTerceiroPorId(id);
        return ResponseEntity.status(200).body(servicoTerceiroPorId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPorId (
            @PathVariable Integer id,
            @RequestBody ServicoTerceiro servicoTerceiroAtualizar
    ) {
        ServicoTerceiro atualizarServicoTerceiro = servicoTerceiroService.atualizarServicoTerceiroPorId(id, servicoTerceiroAtualizar);
        return ResponseEntity.status(200).body(atualizarServicoTerceiro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(
            @PathVariable Integer id
    ) {
        servicoTerceiroService.removerServicoTerceiroPorId(id);
        return ResponseEntity.status(200).build();
    }
}
