package com.ocenanovivo.oceanovivo;

import lombok.Data;
import java.util.List;

@Data
public class OngDTO {
    private Long idOng;
    private String cnpj;
    private String nome;
    private String email;
    private String telefone;
    private List<Long> idDeteccoes;
}

