package web.software.imoclick.apirest.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.software.imoclick.apirest.DTOs.imovel.ImovelCreateDTO;
import web.software.imoclick.apirest.DTOs.imovel.ImovelResponseDTO;
import web.software.imoclick.apirest.DTOs.imovel.ImovelUpdateDTO;
import web.software.imoclick.apirest.services.ImovelService;

@RestController
@RequestMapping("/imovel")
public class ImovelController {

    private final ImovelService service;
    public ImovelController(ImovelService service){
        this.service = service;
    }

    @GetMapping("/get")
    public ResponseEntity<List<ImovelResponseDTO>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    } 

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<ImovelResponseDTO>> buscarPorTitulo(@PathVariable String titulo){
        List<ImovelResponseDTO> imoveis = service.buscarPorTitulo(titulo);
        if(imoveis.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imoveis);
        //@PathVariable extrai valores diretamentes da URL da requisição
        //essencial para identificar recursos específicos na requisição.
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ImovelResponseDTO>> buscarPorTipo(@PathVariable String tipo){
        List<ImovelResponseDTO> imoveis = service.buscaPorTipo(tipo);
        if(imoveis.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imoveis);
    }

    @GetMapping("/endereco/{endereco}")
    public ResponseEntity<List<ImovelResponseDTO>> buscarPorEndereco(@PathVariable String endereco){
        List<ImovelResponseDTO> imoveis = service.buscarPorEndereco(endereco);
        if(imoveis.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imoveis);
    }

    @GetMapping("/valor")
    public ResponseEntity<List<ImovelResponseDTO>> buscarPorValor(@RequestParam BigDecimal min, @RequestParam BigDecimal max){
        List<ImovelResponseDTO> imoveis = service.buscarPorValor(min, max);
        if(imoveis.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imoveis);
    }

    @PostMapping("/post")
    public ResponseEntity<ImovelResponseDTO> salvar(@RequestBody ImovelCreateDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ImovelResponseDTO> atualizar(@PathVariable String id, @RequestBody ImovelUpdateDTO dto){
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
