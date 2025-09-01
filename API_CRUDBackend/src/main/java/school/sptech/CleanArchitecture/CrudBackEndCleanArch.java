package school.sptech.CleanArchitecture;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Estoque de Roupas e Tecidos",
                version = "1.0",
                description = "Rastreabilidade interna de tecidos e roupas."
        )
)
@SpringBootApplication
public class CrudBackEndCleanArch {
    public static void main(String[] args) {
        SpringApplication.run(CrudBackEndCleanArch.class, args);
    }

}
