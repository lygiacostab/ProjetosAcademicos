package web.software.imoclick.apirest.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import web.software.imoclick.apirest.ApirestApplication;
import web.software.imoclick.apirest.controllers.ImovelController;

/*
classe responsável por centralizar o tratamento das exceções da aplicação.

ao utilizar @RestControllerAdvice, o Sprint intercepta automaticamente qualquer exceção
lançada pelos Controllers e Services, direcionando-a para o método correspondente anotado com @ExceptionHandler
Dessa forma, evitamos repetir blocos try/catch em vários Controllers e garantimos um padrão único de respostas
de erro para toda a API.
*/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ImovelController imovelController;
    private final ApirestApplication apirestApplication;
    GlobalExceptionHandler(ApirestApplication apirestApplication, ImovelController imovelController) {
        this.apirestApplication = apirestApplication;
        this.imovelController = imovelController;
    }

    @ExceptionHandler(ImovelNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ImovelNotFoundException e){
        return buildErrorResponse(
            HttpStatus.NOT_FOUND,"NOT_FOUND", e.getMessage());    
    /*Trata situações em que um imóvel não foi encontrado, ex:
    - buscar um imóvel por ID inexistente.
    - atualizar um imóvel que não existe.
    - excluir um imóvel que não existe.
    * Retorna HTTP 404 (NOT FOUND).
    */
        }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusiness(BusinessException e){
        return buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, "BUSINESS_ERROR", e.getMessage());
    /*
    Trata exceções relacionadas a regra de negócio da aplicação
    * retorna HTTP 422 (UNPROCESSABLE ENTITY), indicando que a requisição
    foi compreendida, porém não atende às regras de negócio.
    */
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException e){

        String message = e.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(f -> f.getField() + ": " + f.getDefaultMessage())
        .collect(Collectors.joining(", "));

        return buildErrorResponse(
            HttpStatus.BAD_REQUEST,
            "VALIDATION_ERROR",
            message);
            /*
            Trata erros de validação automática do Spring.

            Normalmente utilizado quando os DTOs possuem anotações como:
            @NotBlank
            @NotNull
            @Size
            @Email

            o spring reúne todos os erros encontrados a este método e monta uma única mensagem 
            contendo os campos válidos.apirestApplication

            *Retorna HTTP 400 (BAD REQUEST).
            */
    }
    @ExceptionHandler(Exception.class)
        public ResponseEntity<Map<String, Object>> handleGeneric(Exception e){
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR", e.getMessage());

        /*
        Tratamento genérico para qualquer exceção não prevista.
        Caso uma exceção seja lançada e não exista método específico, ela chegará aqui. 

        *Retorna HTTP 500 (Internal Server Error).
        */
    }
    private ResponseEntity<Map<String, Object>> buildErrorResponse(
        HttpStatus status,
        String error,
        String message){

            Map<String, Object> body = new LinkedHashMap<>();

            body.put("status", status.value());
            body.put("error", error);
            body.put("message", message);
            body.put("timestamp", LocalDateTime.now());

            return ResponseEntity.status(status).body(body);

            /*Método auxiliar responsável por montar o corpo padrão das respsotas de erro da API
            Todas as exceções tratadas nesta classe utilizam este método para garantir que o formato da resposta seja sempre o mesmo.
            */
        }

}
