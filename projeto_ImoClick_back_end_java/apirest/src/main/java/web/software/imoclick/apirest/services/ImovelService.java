package web.software.imoclick.apirest.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import web.software.imoclick.apirest.DTOs.imovel.ImovelCreateDTO;
import web.software.imoclick.apirest.DTOs.imovel.ImovelResponseDTO;
import web.software.imoclick.apirest.DTOs.imovel.ImovelUpdateDTO;
import web.software.imoclick.apirest.exceptions.BusinessException;
import web.software.imoclick.apirest.exceptions.ImovelNotFoundException;
import web.software.imoclick.apirest.models.Imovel;
import web.software.imoclick.apirest.repositories.ImovelRepository;

@Service
public class ImovelService {

    private final ImovelRepository repository;
    //injeção de dependência
    public ImovelService(ImovelRepository repository){
        this.repository = repository;
    }

    public List<ImovelResponseDTO> listarTodos(){
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public List<ImovelResponseDTO> buscarPorTitulo(String termo){
        return repository.findByTituloContainingIgnoreCase(termo).stream().map(this::toResponse).toList();
    }

    public List<ImovelResponseDTO> buscaPorTipo(String tipo){
        return repository.findByTipo(tipo).stream().map(this::toResponse).toList();
    }

    public List<ImovelResponseDTO> buscarPorEndereco(String termo){
        return repository.findByEnderecoContainingIgnoreCase(termo).stream().map(this::toResponse).toList();
    }

    public List<ImovelResponseDTO> buscarPorValor(BigDecimal min, BigDecimal max){
        if(min == null || max == null){
            throw new IllegalArgumentException("Os valores mínimo e máximo são obrigatórios!");
        }
        if(min.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("O valor mínimo não pode ser menor que Zero!");
        }
        if(min.compareTo(max) > 0){
            throw new IllegalArgumentException("O valor mínimo não pode ser maior que o valor máximo!");
        }
        return repository.findByValorBetween(min, max).stream().map(this::toResponse).toList();
    }

    //POST /imovel -> valida regras e salva no MongoDB
    public ImovelResponseDTO cadastrar (ImovelCreateDTO dto){
        if(dto.getValor().compareTo(BigDecimal.ZERO) <= 0 || dto.getValor().compareTo(BigDecimal.ZERO) >=100)
            throw new BusinessException("Valor inválido!");
        if(dto.getTitulo() == null || dto.getTipo().isBlank())
            throw new BusinessException("O título é obrigatório!");
        if(dto.getDescricao() == null || dto.getDescricao().isBlank())
            throw new BusinessException("A descrição é obrigatória!");
        if(dto.getEndereco() == null || dto.getEndereco().isBlank())
            throw new BusinessException("O endereço é obrigatório!");
        if(dto.getTipo() == null || dto.getTipo().isBlank())
            throw new BusinessException("O tipo é obrigatório!");
        
        return toResponse(repository.save(toEntity(dto)));
    }

    //PUT /imovel/{id}
    public ImovelResponseDTO atualizar(String id, ImovelUpdateDTO dto){
        Imovel entity = repository.findById(id).orElseThrow(() -> new ImovelNotFoundException("Imovel não encontrado" +id));
        entity.setTitulo(dto.getTitulo());
        entity.setDescricao(dto.getDescricao());
        entity.setEndereco(dto.getEndereco());
        entity.setTipo(dto.getTipo());
        entity.setValor(dto.getValor());
        return toResponse(repository.save(entity));
    }

    //DELETE /people/{id}
    public void deletar(String id){
        if(!repository.existsById(id))
            throw new ImovelNotFoundException("Imovel não encontrado: "+ id);
        repository.deleteById(id);
    }



    private ImovelResponseDTO toResponse(Imovel i){
        return new ImovelResponseDTO(
            i.getId(), i.getTitulo(), i.getDescricao(), i.getEndereco(),i.getTipo(), i.getValor(),
            i.getCreatedAt(), i.getUpdatedAt()
        );
    };

    private Imovel toEntity(ImovelCreateDTO dto){
        return new Imovel(dto.getTitulo(), dto.getDescricao(),
            dto.getEndereco(), dto.getTipo(), dto.getValor()
        );
    };

}
