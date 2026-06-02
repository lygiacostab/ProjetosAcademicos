package web.software.imoclick.apirest.DTOs.imovel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ImovelResponseDTO {
    private String id;
    private String titulo;
    private String descricao;
    private String endereco;
    private String tipo;
    private BigDecimal valor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public ImovelResponseDTO(
        String id, String titulo, String descricao, String endereco, String tipo, BigDecimal valor,
        LocalDateTime createdAt, LocalDateTime updatedAt
    ){
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.endereco = endereco;
        this.tipo = tipo;
        this.valor = valor;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
}
