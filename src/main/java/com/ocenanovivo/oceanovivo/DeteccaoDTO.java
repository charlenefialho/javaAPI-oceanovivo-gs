package com.ocenanovivo.oceanovivo;

import java.util.Date;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Table(name = "Deteccao")
public class DeteccaoDTO {
    private Long idDeteccao;
    
    @NotBlank(message = "URL da imagem é obrigatória")
    private String urlImagem;
    
    @NotNull(message = "Data da detecção é obrigatória")
    private Date dataDeteccao;
    private Long idUsuario;
    private Long idLocalizacao;
    private Long idEspecie;
}

