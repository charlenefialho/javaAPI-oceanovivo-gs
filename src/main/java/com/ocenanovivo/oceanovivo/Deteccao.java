package com.ocenanovivo.oceanovivo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Deteccao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeteccao;

    private String urlImagem;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataDeteccao;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_localizacao")
    private Localizacao localizacao;

    @ManyToOne
    @JoinColumn(name = "id_especie")
    private Especie especie;

	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

	public void setOngs(Set<Ong> ongs) {
		// TODO Auto-generated method stub
		
	}
}

