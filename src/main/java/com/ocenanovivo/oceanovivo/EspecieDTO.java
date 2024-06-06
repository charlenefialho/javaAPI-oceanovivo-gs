package com.ocenanovivo.oceanovivo;

import lombok.Data;

@Data
public class EspecieDTO {
    private Long idEspecie;
    private String nomeComum;
    private String nomeCientifico;
    private String descricao;
    private Long idCategoria;
    private Long idSituacao;
}

