package school.sptech.CleanArchitecture.infrastructure.web.dto.alerta;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "DTO para criação de um novo Alerta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertaCriacaoDto {
    @Schema(description = "Descrição do alerta do ItemEstoque", example = "Item atingiu quantidade mínima em estoque!")
    @NotBlank
    private String descricao;
    @Schema(description = "Data/hora em que o alerta foi gerado", example = "2025-04-20T11:36:00")
    @PastOrPresent
    private LocalDateTime dataHora;
    @NotNull
    private AlertaItemEstoqueDto itemEstoque;
}
