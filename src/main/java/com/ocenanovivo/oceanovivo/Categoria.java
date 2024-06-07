package com.ocenanovivo.oceanovivo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    private String nome;
    private String habitat;
    private String reino;
    private String familia;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Especie> especies;
}
