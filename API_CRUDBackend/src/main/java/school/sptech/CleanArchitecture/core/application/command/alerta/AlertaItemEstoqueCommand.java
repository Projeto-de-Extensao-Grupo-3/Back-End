package school.sptech.CleanArchitecture.core.application.command.alerta;

public record AlertaItemEstoqueCommand (

         Integer idItemEstoque,
         String descricao,
         String complemento,
         Double qtdMinimo,
         Double qtdArmazenado

){
}
