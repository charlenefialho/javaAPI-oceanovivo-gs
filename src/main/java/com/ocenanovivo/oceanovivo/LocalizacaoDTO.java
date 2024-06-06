package com.ocenanovivo.oceanovivo;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table(name = "Localizacao")
public class LocalizacaoDTO {
    private Long idLocalizacao;
    
    @NotNull(message = "Latitude é obrigatória")
    private String latitude;
    
    @NotNull(message = "Longitude é obrigatória")
    private String longitude;
    
    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 255, message = "Cidade deve ter no máximo 255 caracteres")
    private String cidade;
    
    @NotBlank(message = "Estado é obrigatório")
    @Size(max = 255, message = "Estado deve ter no máximo 255 caracteres")
    private String estado;
    
    @NotBlank(message = "País é obrigatório")
    @Size(max = 255, message = "País deve ter no máximo 255 caracteres")
    private String pais;
}

