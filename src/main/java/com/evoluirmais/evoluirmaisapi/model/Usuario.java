package com.evoluirmais.evoluirmaisapi.model;

import com.evoluirmais.evoluirmaisapi.model.enums.Habilidade;
import com.evoluirmais.evoluirmaisapi.model.enums.ObjetivoPessoal;
import com.evoluirmais.evoluirmaisapi.model.enums.Profissao;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuarios")
//@Data
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- CAMPOS DE CADASTRO E LOGIN ---
    private String nomeCompleto;
    private String cpf;
    private LocalDate dataNascimento;
    private String cep;

    @Column(unique = true, nullable = false)
    private String email;

    // gera um hash longo
    @Column(nullable = false, length = 100)
    private String senhaCriptografada;

    // CAMPOS DE PERFIL (Enums)
    @Enumerated(EnumType.STRING)
    private Profissao profissao;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_habilidades", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "nome_habilidade")
    @Enumerated(EnumType.STRING)
    private Set<Habilidade> habilidades;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_objetivos", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "nome_objetivo")
    @Enumerated(EnumType.STRING)
    private Set<ObjetivoPessoal> objetivos;

    // RELACIONAMENTO COM PROGRESSO
    // Mapeado pelo campo 'usuario' na entidade ProgressoUsuario
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn // Indica que o Progresso usa o mesmo ID do Usuario
    private ProgressoUsuario progresso;

    // IMPLEMENTAÇÃO OBRIGATÓRIA DE SPRING SECURITY
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Papel fixo, assumindo que todos os usuários são básicos
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senhaCriptografada;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    // Métodos de status da conta (true por padrão para as contas ativas)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }

    public void setSenhaCriptografada(String senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public Set<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Set<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public Set<ObjetivoPessoal> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(Set<ObjetivoPessoal> objetivos) {
        this.objetivos = objetivos;
    }

    public ProgressoUsuario getProgresso() {
        return progresso;
    }

    public void setProgresso(ProgressoUsuario progresso) {
        this.progresso = progresso;
    }
}