package web.software.imoclick.apirest.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document  (collection = "imovel")
public class Imovel {
 @Id
    private String id;
    private String titulo;
    private String descricao;
    private String endereco;
    private String tipo;
    private BigDecimal valor;
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Imovel(){
        /*PS: Este construtor não recebe nenhum parâmetro mas é obrigatório por conta dos 
        frameworks e bibliotecas utilizados. Motivo: quando o spring vai buscar um objeto no
        BD ou recebe um JSON do front-end, ele primeiro cria um objeto em branco usando esse
        construtor vazio. */
    }

    public Imovel(String titulo, String descricao, String endereco, String tipo, BigDecimal valor){
        this.titulo = titulo;
        this.descricao = descricao;
        this.endereco = endereco;
        this.tipo = tipo;
        this.valor = valor;


        /*PS: esse construtor sem o ID é necessário porque quando criamos um novo registro no banco
        o campo @Id é gerado automaticamente pelo Mongo na hora de salvar. */
    }

    public Imovel(String id, String titulo, String descricao, String endereco, String tipo, BigDecimal valor){
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.endereco = endereco;
        this.tipo = tipo;
        this.valor = valor;

        /*PS: esse construtor é útil para cenários de atualizações (updates) ou quando precisamos
        recriar um objeto que já existe no banco.  */
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
}
