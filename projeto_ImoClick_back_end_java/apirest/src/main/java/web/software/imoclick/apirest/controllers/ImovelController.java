package web.software.imoclick.apirest.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import web.software.imoclick.apirest.DTOs.imovel.ImovelCreateDTO;
import web.software.imoclick.apirest.DTOs.imovel.ImovelResponseDTO;
import web.software.imoclick.apirest.DTOs.imovel.ImovelUpdateDTO;
import web.software.imoclick.apirest.services.ImovelService;

@Tag(name = "Imovel", description = "Gerenciamento de Imóveis.")
@RestController
@RequestMapping("/imovel")
@CrossOrigin(origins = "*")
public class ImovelController {

    private final ImovelService service;
    public ImovelController(ImovelService service){
        this.service = service;
    }

    @GetMapping("/get")
    @Operation(summary = "Listar imóveis", description = "Retorna todos os imóveis cadastrados")
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    public ResponseEntity<List<ImovelResponseDTO>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    } 

    @Operation(summary = "Busca imóvel por título", description = "Localiza o imóvel pelo título passado por parâmetro")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Imóvel encontrado"),
        @ApiResponse(responseCode = "404", description = "Imóvel não encontrado")
    })
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<ImovelResponseDTO>> buscarPorTitulo(
        @Parameter(description = "Título do imóvel", example = "Apartamento") @PathVariable String titulo){
        List<ImovelResponseDTO> imoveis = service.buscarPorTitulo(titulo);

        return ResponseEntity.ok(imoveis);
        //@PathVariable extrai valores diretamentes da URL da requisição
        //essencial para identificar recursos específicos na requisição.
    }

    @Operation(summary = "Busca imóvel por tipo", description = "Localiza o imóvel pelo tipo passado por parâmetro")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Imóvel encontrado"),
        @ApiResponse(responseCode = "404", description = "Imóvel não encontrado")
    })
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ImovelResponseDTO>> buscarPorTipo(
        @Parameter(description = "Tipo de negociação do imóvel", example = "Aluguel") @PathVariable String tipo){
        List<ImovelResponseDTO> imoveis = service.buscaPorTipo(tipo);
       
        return ResponseEntity.ok(imoveis);
    }

    @Operation(summary = "Busca imóvel pelo endereço", description = "Localiza o(s) imóvel(is) por endereço passado por parâmetro")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Imóvel encontrado"),
        @ApiResponse(responseCode = "404", description = "Imóvel não encontrado")
    })
    @GetMapping("/endereco/{endereco}")
    public ResponseEntity<List<ImovelResponseDTO>> buscarPorEndereco(
        @Parameter(description = "Endereço de localização do imóvel", example = "Centro") @PathVariable String endereco){
        List<ImovelResponseDTO> imoveis = service.buscarPorEndereco(endereco);
    
        return ResponseEntity.ok(imoveis);
    }

    @Operation(summary = "Busca imóvel pelo valor", description = "Localiza o(s) imóvel(is) dentro de uma faixa de valores passados por parâmetro")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Imóvel encontrado"),
        @ApiResponse(responseCode = "404", description = "Imóvel não encontrado")
    })
    @GetMapping("/valor")
    public ResponseEntity<List<ImovelResponseDTO>> buscarPorValor(
        @Parameter(description = "Valor minímo e máximo do imóvel", example = "1000.00 | 9000.00") @RequestParam BigDecimal min, @RequestParam BigDecimal max){
        List<ImovelResponseDTO> imoveis = service.buscarPorValor(min, max);
        
        return ResponseEntity.ok(imoveis);
    }

    @Operation(summary = "Cadastra um novo imóvel", description = "Cria um novo registro de imóvel")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Imóvel cadastrados"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/post")
    public ResponseEntity<ImovelResponseDTO> salvar(@Valid @RequestBody ImovelCreateDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @Operation(summary = "Atualiza um imóvel cadastrado", description = "Atualiza os dados de um imóvel do sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Imóvel atualizado"),
        @ApiResponse(responseCode = "404", description = "Imóvel não encontrado")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<ImovelResponseDTO> atualizar(
        @Parameter(description = "ID do Imóvel", example = "6828f7a7a8123c001f11aa11")
        @PathVariable String id, 
        @Valid @RequestBody ImovelUpdateDTO dto){
       
            return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Exclui imóvel", description = "Remove um imóvel já cadastrado")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Imóvel removido"),
        @ApiResponse(responseCode = "404", description = "Imóvel não encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletar(
        @Parameter(description = "ID do imóvel", example = "6828f7a7a8123c001f11aa11") @PathVariable String id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
