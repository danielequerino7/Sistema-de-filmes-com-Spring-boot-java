package br.movie.sistemafilme.repository;

import br.movie.sistemafilme.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    // Você pode adicionar consultas personalizadas aqui se necessário
}
