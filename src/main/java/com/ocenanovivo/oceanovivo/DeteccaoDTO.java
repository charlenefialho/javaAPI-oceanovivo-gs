package com.ocenanovivo.oceanovivo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class DeteccaoDTO {
    @NotNull(message = "A URL da imagem é obrigatória.")
    @Size(max = 255, message = "A URL da imagem deve ter no máximo 255 caracteres.")
    private String urlImagem;

    @NotNull(message = "A data da detecção é obrigatória.")
    private LocalDateTime dataDeteccao;

    @NotNull(message = "O ID do usuário é obrigatório.")
    private Long usuarioId;

    @NotNull(message = "O ID da localização é obrigatório.")
    private Long localizacaoId;

    @NotNull(message = "O ID da espécie é obrigatório.")
    private Long especieId;

    @NotNull(message = "Os IDs das ONGs são obrigatórios.")
    @Size(min = 1, message = "Pelo menos uma ONG deve ser informada.")
    private Set<Long> ongIds;
}

