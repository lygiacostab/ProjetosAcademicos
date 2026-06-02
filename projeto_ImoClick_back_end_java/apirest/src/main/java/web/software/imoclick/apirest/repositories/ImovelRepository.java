package web.software.imoclick.apirest.repositories;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import web.software.imoclick.apirest.models.Imovel;

@Repository
public interface ImovelRepository extends MongoRepository<Imovel, String> {
    //os métodos CRUD são automaticamente criados.


    Optional<Imovel> findByTitulo(String titulo); //Optional -> evita que o sistema estoure um erro 'NullPointerException' caso não encontre nada
    // findByTitulo(String titulo) -> faz uma busca exata pelo título do imóvel
    List<Imovel> findByTituloContainingIgnoreCase(String termo); //este é o famoso método para barra de pesquisa do site -> Containing: "que contém". Ignorecase: ignora maiúsculas/minúsculas
    List<Imovel> findByTipo(String tipo); //busca por tipo -> apartamento, casa, kitnet...
    List<Imovel> findByEnderecoContainingIgnoreCase(String termo);

    @Query("{'valor': {$gte: ?0, $lte: ?1}}")
    List<Imovel> findByValorBetween(BigDecimal min, BigDecimal max);



}
