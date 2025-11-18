package school.sptech.CleanArchitecture.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import school.sptech.CleanArchitecture.core.application.exceptions.Lote.LoteConflitoException;
import school.sptech.CleanArchitecture.core.application.exceptions.Lote.LoteNaoEncontradoException;
import school.sptech.CleanArchitecture.core.application.exceptions.LoteItemEstoque.LoteItemEstoqueConflitoException;
import school.sptech.CleanArchitecture.core.application.exceptions.LoteItemEstoque.LoteItemEstoqueNaoEncontradoException;
import school.sptech.CleanArchitecture.core.application.exceptions.Prateleira.PrateleiraConflitoException;
import school.sptech.CleanArchitecture.core.application.exceptions.Prateleira.PrateleiraNaoEncontradaException;
import school.sptech.CleanArchitecture.core.application.exceptions.SaidaEstoque.SaidaEstoqueConflitoException;
import school.sptech.CleanArchitecture.core.application.exceptions.SaidaEstoque.SaidaEstoqueNaoEncontradoException;
import school.sptech.CleanArchitecture.core.application.exceptions.categoria.*;
import school.sptech.CleanArchitecture.core.application.exceptions.confeccaoRoupa.ConfeccaoRoupaConflitoException;
import school.sptech.CleanArchitecture.core.application.exceptions.confeccaoRoupa.ConfeccaoRoupaNaoEncontradoException;
import school.sptech.CleanArchitecture.core.application.exceptions.corteTecido.CorteTecidoConflitoException;
import school.sptech.CleanArchitecture.core.application.exceptions.corteTecido.CorteTecidoNaoEncontradoException;
import school.sptech.CleanArchitecture.core.application.exceptions.funcionario.FuncionarioConflitoException;
import school.sptech.CleanArchitecture.core.application.exceptions.funcionario.FuncionarioNaoEncontradoException;
import school.sptech.CleanArchitecture.core.application.exceptions.funcionario.SenhaException;
import school.sptech.CleanArchitecture.core.application.exceptions.imagem.ImagemConflitoException;
import school.sptech.CleanArchitecture.core.application.exceptions.imagem.ImagemNaoEncontradaexception;
import school.sptech.CleanArchitecture.core.application.exceptions.itensEstoque.ItemEstoqueConflitoException;
import school.sptech.CleanArchitecture.core.application.exceptions.itensEstoque.ItemEstoqueNaoEncontradoException;
import school.sptech.CleanArchitecture.core.application.exceptions.parceiro.ParceiroConflitoException;
import school.sptech.CleanArchitecture.core.application.exceptions.parceiro.ParceiroNaoEncontradoException;

@RestControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(FuncionarioConflitoException.class)
    public ResponseEntity<ErrorDto> handleFuncionarioConflito(FuncionarioConflitoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(409).body(error);
    }
    @ExceptionHandler(FuncionarioNaoEncontradoException.class)
    public ResponseEntity<ErrorDto> handleFuncionarioNaoEncontrado(FuncionarioNaoEncontradoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(404).body(error);
    }


    @ExceptionHandler(CategoriaConflitoException.class)
    public ResponseEntity<ErrorDto> handleCategoriaConflito(CategoriaConflitoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(409).body(error);
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<ErrorDto> handleCategoriaNaoEncontrada(CategoriaNaoEncontradaException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(ConfeccaoRoupaConflitoException.class)
    public ResponseEntity<ErrorDto> handleConfeccaoRoupaConflito(ConfeccaoRoupaConflitoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(409).body(error);
    }

    @ExceptionHandler(ConfeccaoRoupaNaoEncontradoException.class)
    public ResponseEntity<ErrorDto> handleCConfeccaoRoupaNaoEncontrado(ConfeccaoRoupaNaoEncontradoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(CorteTecidoConflitoException.class)
    public ResponseEntity<ErrorDto> handleCorteTecidoConflito(CorteTecidoConflitoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(409).body(error);
    }

    @ExceptionHandler(CorteTecidoNaoEncontradoException.class)
    public ResponseEntity<ErrorDto> handleCorteTecidoNaoEncontrado(CorteTecidoNaoEncontradoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(ImagemConflitoException.class)
    public ResponseEntity<ErrorDto> handleImagemConflito(ImagemConflitoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(409).body(error);
    }
    @ExceptionHandler(ImagemNaoEncontradaexception.class)
    public ResponseEntity<ErrorDto> handleImagemNaoEncontrada(ImagemNaoEncontradaexception ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(ItemEstoqueConflitoException.class)
    public ResponseEntity<ErrorDto> handleItemEstoqueConflito(ItemEstoqueConflitoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(409).body(error);
    }
    @ExceptionHandler(ItemEstoqueNaoEncontradoException.class)
    public ResponseEntity<ErrorDto> handleItemEstoqueNaoEncontrado(ItemEstoqueNaoEncontradoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(LoteConflitoException.class)
    public ResponseEntity<ErrorDto> handleLoteConflito(LoteConflitoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(409).body(error);
    }
    @ExceptionHandler(LoteNaoEncontradoException.class)
    public ResponseEntity<ErrorDto> handleLoteNaoEncontrado(LoteNaoEncontradoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(LoteItemEstoqueConflitoException.class)
    public ResponseEntity<ErrorDto> handleLoteItemEstoqueConflito(LoteItemEstoqueConflitoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(409).body(error);
    }

    @ExceptionHandler(LoteItemEstoqueNaoEncontradoException.class)
    public ResponseEntity<ErrorDto> handleLoteItemEstoqueNaoEncontrado(LoteItemEstoqueNaoEncontradoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(ParceiroConflitoException.class)
    public ResponseEntity<ErrorDto> handleParceiroConflito(ParceiroConflitoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(409).body(error);
    }
    @ExceptionHandler(ParceiroNaoEncontradoException.class)
    public ResponseEntity<ErrorDto> handleParceiroNaoEncontrado(ParceiroNaoEncontradoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(PrateleiraConflitoException.class)
    public ResponseEntity<ErrorDto> handlePrateleiraConflito(PrateleiraConflitoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(409).body(error);
    }
    @ExceptionHandler(PrateleiraNaoEncontradaException.class)
    public ResponseEntity<ErrorDto> handlePrateleiraNaoEncontrada(PrateleiraNaoEncontradaException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(SaidaEstoqueConflitoException.class)
    public ResponseEntity<ErrorDto> handleSaidaEstoqueConflito(SaidaEstoqueConflitoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(409).body(error);
    }
    @ExceptionHandler(SaidaEstoqueNaoEncontradoException.class)
    public ResponseEntity<ErrorDto> handleSaidaEstoqueNaoEncontrado(SaidaEstoqueNaoEncontradoException ex,  WebRequest request) {
        ErrorDto error = new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(CategoriaPaiException.class)
    public ResponseEntity<ErrorDto> handleCategoriaPai(CategoriaPaiException ex, WebRequest request){
        ErrorDto error = new ErrorDto(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "UNPROCESSABLE ENTITY",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(422).body(error);
    }

    @ExceptionHandler(CategoriaEmItemException.class)
    public ResponseEntity<ErrorDto> handleCategoriaEmItem(CategoriaEmItemException ex, WebRequest request){
        ErrorDto error = new ErrorDto(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "UNPROCESSABLE ENTITY",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(422).body(error);
    }

    @ExceptionHandler(CategoriaBadRequestException.class)
    public ResponseEntity<ErrorDto> handleCategoriaBadRequest(CategoriaBadRequestException ex, WebRequest request){
        ErrorDto error = new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                "BAD REQUEST",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(SenhaException.class)
    public ResponseEntity<ErrorDto> handleSenhaRegraDeNegocioException(SenhaException ex, WebRequest request){
        ErrorDto error = new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                "BAD REQUEST",
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(400).body(error);
    }

}
