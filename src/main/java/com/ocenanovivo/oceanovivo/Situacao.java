package com.ocenanovivo.oceanovivo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Situacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSituacao;

    private boolean riscoExtincao;
    private boolean invasora;

    @OneToMany(mappedBy = "situacao")
    private List<Especie> especies;
}

