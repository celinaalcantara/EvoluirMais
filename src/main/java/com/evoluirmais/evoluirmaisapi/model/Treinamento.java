package com.evoluirmais.evoluirmaisapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "treinamentos")
public class Treinamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String focoPratico;
    private Integer duracaoMinutos;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFocoPratico() {
        return focoPratico;
    }

    public void setFocoPratico(String focoPratico) {
        this.focoPratico = focoPratico;
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(Integer duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }
}