package com.evoluirmais.evoluirmaisapi.model;

import jakarta.persistence.*;
import java.util.Set;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "progresso_usuario")
public class ProgressoUsuario {

    @Id
    @Column(name = "usuario_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // CAMPOS DE PORCENTAGEM (TELA DE PROGRESSO)
    private Double percentualGeralConclusao = 0.0;

    // Porcentagens solicitadas:
    private Double percentualCursosConcluidos = 0.0;
    private Double percentualPalestrasConcluidas = 0.0;
    private Double percentualTreinamentosConcluidos = 0.0;

    // RASTREAMENTO DE ITENS CONCLUÍDOS

    // Cursos concluídos pelo usuário
    @ManyToMany
    @JoinTable(
            name = "progresso_cursos_concluidos",
            joinColumns = @JoinColumn(name = "progresso_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private Set<Curso> cursosConcluidos = new HashSet<>();

    // Palestras concluídas pelo usuário
    @ManyToMany
    @JoinTable(
            name = "progresso_palestras_concluidas",
            joinColumns = @JoinColumn(name = "progresso_id"),
            inverseJoinColumns = @JoinColumn(name = "palestra_id")
    )
    private Set<Palestra> palestrasConcluidas = new HashSet<>();

    // Treinamentos concluídos pelo usuário
    @ManyToMany
    @JoinTable(
            name = "progresso_treinamentos_concluidos",
            joinColumns = @JoinColumn(name = "progresso_id"),
            inverseJoinColumns = @JoinColumn(name = "treinamento_id")
    )
    private Set<Treinamento> treinamentosConcluidos = new HashSet<>();

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPercentualGeralConclusao() {
        return percentualGeralConclusao;
    }

    public void setPercentualGeralConclusao(Double percentualGeralConclusao) {
        this.percentualGeralConclusao = percentualGeralConclusao;
    }

    public Double getPercentualCursosConcluidos() {
        return percentualCursosConcluidos;
    }

    public void setPercentualCursosConcluidos(Double percentualCursosConcluidos) {
        this.percentualCursosConcluidos = percentualCursosConcluidos;
    }

    public Double getPercentualPalestrasConcluidas() {
        return percentualPalestrasConcluidas;
    }

    public void setPercentualPalestrasConcluidas(Double percentualPalestrasConcluidas) {
        this.percentualPalestrasConcluidas = percentualPalestrasConcluidas;
    }

    public Double getPercentualTreinamentosConcluidos() {
        return percentualTreinamentosConcluidos;
    }

    public void setPercentualTreinamentosConcluidos(Double percentualTreinamentosConcluidos) {
        this.percentualTreinamentosConcluidos = percentualTreinamentosConcluidos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Curso> getCursosConcluidos() {
        return cursosConcluidos;
    }

    public void setCursosConcluidos(Set<Curso> cursosConcluidos) {
        this.cursosConcluidos = cursosConcluidos;
    }

    public Set<Palestra> getPalestrasConcluidas() {
        return palestrasConcluidas;
    }

    public void setPalestrasConcluidas(Set<Palestra> palestrasConcluidas) {
        this.palestrasConcluidas = palestrasConcluidas;
    }

    public Set<Treinamento> getTreinamentosConcluidos() {
        return treinamentosConcluidos;
    }

    public void setTreinamentosConcluidos(Set<Treinamento> treinamentosConcluidos) {
        this.treinamentosConcluidos = treinamentosConcluidos;
    }

    // Metodo auxiliar p/calculo de porcentagem
    // --> calculo final é feito na camada service
    public void calcularEAtualizarProgresso(int totalCursos, int totalPalestras, int totalTreinamentos) {
        if (totalCursos > 0) {
            this.percentualCursosConcluidos = (double) cursosConcluidos.size() / totalCursos * 100;
        }

    }
}
