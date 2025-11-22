package com.evoluirmais.evoluirmaisapi.repository;

import com.evoluirmais.evoluirmaisapi.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByTituloContainingIgnoreCase(String titulo);

    List<Curso> findByIsTecnicoTrue();
}