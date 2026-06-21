package web.software.imoclick.apirest.DTOs.imovel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de resposta do Imóvel.")
public class ImovelResponseDTO {
    @Schema(description = "Identificador único", type = "String", example = "6529e3a7a5845c008f66bb17")
    private String id;
    @Schema(description = "Título do imóvel", type = "String", example = "Apartamento")
    private String titulo;
    @Schema(description = "Descrição do imóvel", type = "String", example = "2 quartos, 1 banheiro, 1 cozinha americana")
    private String descricao;
    @Schema(description = "Endereço do imóvel", type = "String", example = "Rua 01, qd02 lt03 - St. Centro")
    private String endereco;
    @Schema(description = "Tipo de negociação do imóvel", type = "String", example = "Aluguel")
    private String tipo;
    @Schema(description = "Valor do imóvel", type = "BigDecimal", example = "1000.00")
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
