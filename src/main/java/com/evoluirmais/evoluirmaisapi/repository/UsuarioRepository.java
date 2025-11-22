package com.evoluirmais.evoluirmaisapi.repository;

import com.evoluirmais.evoluirmaisapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Metodo customizado p/ buscar um usuário pelo email
    Optional<Usuario> findByEmail(String email);

}