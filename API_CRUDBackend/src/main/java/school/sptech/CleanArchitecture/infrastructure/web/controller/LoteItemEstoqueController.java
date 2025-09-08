package school.sptech.CleanArchitecture.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.CleanArchitecture.core.application.command.loteItemEstoque.CriarLoteItemEstoqueCommand;
import school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque.*;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.LoteItemEstoqueItemResponseDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.LoteItemEstoqueMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.LoteItemEstoqueRequestDto;

@Tag(name = "* Entrada de Item Estoque Controller", description = "Operações CRUD relacionadas aos itens que chegaram em um lote.")
@RestController
@RequestMapping("/lotes-item-estoque")
@RequiredArgsConstructor
public class LoteItemEstoqueController {

    private final AtualizarPorIdloteItemEstoqueUseCase atualizarPorIdloteItemEstoqueUseCase;
    private final BuscarPorIdLoteItemEstoqueUseCase buscarPorIdLoteItemEstoqueUseCase;
    private final BuscarTodosLoteItemEstoqueUseCase buscarTodosLoteItemEstoqueUseCase;
    private final CadastrarLoteItemEstoqueUseCase cadastrarLoteItemEstoqueUseCase;
    private final RemoverLoteItemEstoqueUseCase removerLoteItemEstoqueUseCase;

    @Operation(
            summary = "* Cadastro de um novo Lote de Item.",
            description = "Retorna um objeto do tipo LoteItemEstoqueResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lote de Item cadastrado com sucesso.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo LoteItemEstoqueRequestDto para cadastro.",
            required = true
    )
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<LoteItemEstoqueItemResponseDto> cadastrar(
            @RequestBody @Valid LoteItemEstoqueRequestDto loteItemEstoqueCadastrar
    ) {
        CriarLoteItemEstoqueCommand command = LoteItemEstoqueMapper.to
    }
}
