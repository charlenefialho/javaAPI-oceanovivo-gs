package com.ocenanovivo.oceanovivo;

import lombok.Data;

@Data
public class LocalizacaoDTO {
    private Long idLocalizacao;
    private String latitude;
    private String longitude;
    private String cidade;
    private String estado;
    private String pais;
}

