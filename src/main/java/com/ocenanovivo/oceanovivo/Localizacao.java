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
public class Localizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocalizacao;

    private String latitude;
    private String longitude;
    private String cidade;
    private String estado;
    private String pais;

    @OneToMany(mappedBy = "localizacao")
    private List<Deteccao> deteccoes;
}
