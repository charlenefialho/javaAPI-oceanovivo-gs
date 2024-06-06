package com.ocenanovivo.oceanovivo;

import lombok.Data;
import java.util.Date;

@Data
public class DeteccaoDTO {
    private Long idDeteccao;
    private String urlImagem;
    private Date dataDeteccao;
    private Long idUsuario;
    private Long idLocalizacao;
    private Long idEspecie;
}

