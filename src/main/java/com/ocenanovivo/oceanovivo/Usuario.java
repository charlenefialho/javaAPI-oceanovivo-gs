package com.ocenanovivo.oceanovivo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nome;
 
    private String email;

    @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
    private String senha;

    private String genero;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Deteccao> deteccoes;
}

