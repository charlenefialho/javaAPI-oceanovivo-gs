package com.ocenanovivo.oceanovivo;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    
    @ManyToMany
    @JoinTable(
        name = "Ong_Deteccao",
        joinColumns = @JoinColumn(name = "id_deteccao"),
        inverseJoinColumns = @JoinColumn(name = "id_ong")
    )
    
    private Set<Ong> ongs;
    

}

