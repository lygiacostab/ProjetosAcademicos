package web.software.imoclick.apirest.DTOs.imovel;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public abstract class ImovelBaseDTO {
    @NotBlank(message="O título é obrigatório.")
    private String titulo;
    @NotBlank(message="A descrição é obrigatória.")
    private String descricao;
    @NotBlank(message="O endereço é obrigatório.")
    private String endereco;
    @NotBlank(message="O tipo é obrigatório.")
    private String tipo;
    @NotNull(message="O valor é obrigatório.")
    private BigDecimal valor;
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
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
}
