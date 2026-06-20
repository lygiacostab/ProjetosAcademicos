package web.software.imoclick.apirest.exceptions;

//exceção genérica oara regras de negócio.
public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super("Erro: "+message);
    }

}
