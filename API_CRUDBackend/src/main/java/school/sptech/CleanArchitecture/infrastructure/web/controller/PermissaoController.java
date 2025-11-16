package school.sptech.CleanArchitecture.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.CleanArchitecture.core.application.usecase.permissao.ListarTodasPermissoesUseCase;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.permissao.PermissaoEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.permissao.PermissaoEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.permissao.PermissaoDTOMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.permissao.PermissaoListDTO;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Prateleria Controller", description = "Operação de listar todas Permissões")
@RestController
@RequestMapping("/permissoes")
@RequiredArgsConstructor
public class PermissaoController {

    private final ListarTodasPermissoesUseCase listarTodasPermissoesUseCase;

    @GetMapping()
    public ResponseEntity<List<PermissaoListDTO>> listarTodas() {
        List<PermissaoEntity> todasPermissoes = listarTodasPermissoesUseCase.execute()
                .stream()
                .map(PermissaoEntityMapper::ofDomain)
                .collect(Collectors.toList());

        List<PermissaoListDTO> response = todasPermissoes
                .stream()
                .map(PermissaoDTOMapper::toResponseDto)
                .collect(Collectors.toList());

        return response.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(response);
    }

}
