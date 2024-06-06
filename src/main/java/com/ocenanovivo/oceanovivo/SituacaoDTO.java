package com.ocenanovivo.oceanovivo;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Table(name = "Situacao")
public class SituacaoDTO {
    private Long idSituacao;
    
    @NotNull(message = "O campo 'risco de extinção' é obrigatório")
    private boolean riscoExtincao;
    
    @NotNull(message = "O campo 'invasora' é obrigatório")
    private boolean invasora;
}

