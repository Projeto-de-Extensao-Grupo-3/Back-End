package school.sptech.CRUDBackend.Model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.entity.ItemEstoque;

@RestController
@RequestMapping("/estoques")
public class ObserverController {

    private final PublicadoraService publicadoraService;

    public ObserverController(PublicadoraService publicadoraService) {
        this.publicadoraService = publicadoraService;
    }

    @GetMapping()
    public ResponseEntity<String> testarNotificacao(){
        ItemEstoque item = new ItemEstoque();
        item.setDescricao("Oversize");
        item.setQtdArmazenado(5.0);
        item.setQtdMinima(10.0);

        publicadoraService.verificarEstoque(item);

        return ResponseEntity.status(200).body("A notificação foi enviada");
    }
}
