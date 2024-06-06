package com.ocenanovivo.oceanovivo;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table(name = "Especie")
public class EspecieDTO {
    private Long idEspecie;
    
    @NotBlank(message = "Nome comum é obrigatório")
    @Size(max = 255, message = "Nome comum deve ter no máximo 255 caracteres")
    private String nomeComum;
    
    @NotBlank(message = "Nome científico é obrigatório")
    @Size(max = 255, message = "Nome científico deve ter no máximo 255 caracteres")
    private String nomeCientifico;
    
    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    private String descricao;
    private Long idCategoria;
    private Long idSituacao;
}

