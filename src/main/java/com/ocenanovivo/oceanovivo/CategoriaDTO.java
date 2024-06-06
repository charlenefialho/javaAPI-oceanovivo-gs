package com.ocenanovivo.oceanovivo;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table(name = "Categoria")
public class CategoriaDTO {
    private Long idCategoria;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    private String nome;
    
    @NotBlank(message = "Habitat é obrigatório")
    @Size(max = 255, message = "Habitat deve ter no máximo 255 caracteres")
    private String habitat;
    
    @NotBlank(message = "Reino é obrigatório")
    @Size(max = 255, message = "Reino deve ter no máximo 255 caracteres")
    private String reino;
    
    @NotBlank(message = "Família é obrigatória")
    @Size(max = 255, message = "Família deve ter no máximo 255 caracteres")
    private String familia;
}

