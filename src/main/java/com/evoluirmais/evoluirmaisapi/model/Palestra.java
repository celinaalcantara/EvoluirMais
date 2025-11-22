package com.evoluirmais.evoluirmaisapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "palestras")
public class Palestra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String palestrante;
    private String temaPrincipal;

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

    public String getPalestrante() {
        return palestrante;
    }

    public void setPalestrante(String palestrante) {
        this.palestrante = palestrante;
    }

    public String getTemaPrincipal() {
        return temaPrincipal;
    }

    public void setTemaPrincipal(String temaPrincipal) {
        this.temaPrincipal = temaPrincipal;
    }
}