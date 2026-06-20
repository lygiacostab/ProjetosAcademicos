package web.software.imoclick.apirest.exceptions;

//exceção específica para imóvel não encontrado
public class ImovelNotFoundException extends RuntimeException{
    public ImovelNotFoundException(String id){
        super("Imóvel não encontrado com o id: "+id);
    }
}
