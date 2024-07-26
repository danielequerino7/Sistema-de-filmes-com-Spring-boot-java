package br.movie.sistemafilme.service;

import br.movie.sistemafilme.model.Filme;
import br.movie.sistemafilme.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public List<Filme> listarFilmes() {
        return filmeRepository.findAll();
    }

    public Filme salvarFilme(Filme filme) {
        return filmeRepository.save(filme);
    }

    // Outros métodos de serviço
}
