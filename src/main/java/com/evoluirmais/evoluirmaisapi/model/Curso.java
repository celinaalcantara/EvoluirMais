package com.evoluirmais.evoluirmaisapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cursos")
//@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private Integer duracaoHoras;
    private String instrutor;
    private Boolean isTecnico;

    public Boolean getTecnico() {
        return isTecnico;
    }

    public void setTecnico(Boolean tecnico) {
        isTecnico = tecnico;
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(Integer duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }

    public Boolean getIsTecnico() {
        return isTecnico;
    }

    public void setIsTecnico(Boolean isTecnico) {
        this.isTecnico = isTecnico;
    }
}