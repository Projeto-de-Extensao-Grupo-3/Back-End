package school.sptech.CRUDBackend.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.entity.ItemEstoque;

@RestController
@RequestMapping("/estoques")
public class ObserverController {

    private final PublicadoraService publicadoraService;
    @Autowired
    private NotificadorFuncionario notificador;

    public ObserverController(PublicadoraService publicadoraService) {
        this.publicadoraService = publicadoraService;
    }

    @GetMapping()
    public ResponseEntity<String> testarVerificacao(){
        ItemEstoque item = new ItemEstoque();
        item.setDescricao("Oversize");
        item.setQtdArmazenado(5.0);
        item.setQtdMinima(10.0);

        publicadoraService.verificarEstoque(item);

        return ResponseEntity.status(200).body("Verificação ok");
    }

    @GetMapping("/teste")
    public ResponseEntity<String> testarNotificacao(){
        ItemEstoque item = new ItemEstoque();
        item.setDescricao("Polo");
        notificador.atualizar(item);
        return ResponseEntity.ok("Notificação ok");
    }
}
