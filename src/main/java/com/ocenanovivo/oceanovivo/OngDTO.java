package com.ocenanovivo.oceanovivo;

import java.util.List;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table(name = "Ong")
public class OngDTO {
    private Long idOng;
    
    @NotBlank(message = "CNPJ é obrigatório")
    @Size(max = 14, message = "CNPJ deve ter no máximo 14 caracteres")
    private String cnpj;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    private String nome;
    
    @NotBlank(message = "Email é obrigatório")
    @Size(max = 255, message = "Email deve ter no máximo 255 caracteres")
    private String email;
    
    @NotBlank(message = "Telefone é obrigatório")
    @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres")
    private String telefone;
    
    @NotBlank(message = "categoria animal é obrigatório")
    private String categoriaAnimal;
    
    private List<Long> idDeteccoes;
}

