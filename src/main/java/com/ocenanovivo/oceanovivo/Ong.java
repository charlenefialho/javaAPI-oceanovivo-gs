package com.ocenanovivo.oceanovivo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Ong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOng;

    private String cnpj;
    private String nome;
    private String email;
    private String telefone;
    
    private String categoriaAnimal;

    @ManyToMany
    @JoinTable(
        name = "Ong_Deteccao",
        joinColumns = @JoinColumn(name = "id_ong"),
        inverseJoinColumns = @JoinColumn(name = "id_deteccao")
    )
    @JsonIgnore
    private List<Deteccao> deteccoes;
}

